package com.charles.website.services.impl;

import com.charles.website.entity.Account;
import com.charles.website.entity.ERole;
import com.charles.website.entity.Role;
import com.charles.website.entity.Student;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.model.request.StudentRequest;
import com.charles.website.repository.AccountRepository;
import com.charles.website.repository.RoleRepository;
import com.charles.website.repository.StudentRepository;
import com.charles.website.services.StudentService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Student seeInfoPerson() {
        return accountRepository.findByUsername(Authen.username()).getStudent();
    }

    @Override
    public void updateInfoPerson(StudentRequest req) {
        Student student = accountRepository.findByUsername(Authen.username()).getStudent();

        if(req.getFullName()!=null) student.setFullName(req.getFullName());
        if(req.getPhoneNumber()!=null) {
            if(!req.getPhoneNumber().matches("^(09|03|07|08|05)\\d{8}$"))
                throw new BadRequestException(400, "Please input phone number format exactly Vietnam");

            student.setPhoneNumber(req.getPhoneNumber());
        }
        if(req.getAddress()!=null) student.setAddress(req.getAddress());
        if(req.getBirthday()!=null) student.setBirthday(req.getBirthday());

        studentRepository.save(student);
    }

    @Override
    public void updatePasswordPerson(String password) {
        Account account = accountRepository.findByUsername(Authen.username());
        account.setPassword(encoder.encode(password));

        accountRepository.save(account);
    }

    @Override
    public Page<Student> getAll(Pageable pageable, String filter) {
        if(filter!=null) {
            return studentRepository.findAllByFilter(filter, pageable);
        }

        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getDetail(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Student is not found!");
        });

        return student;
    }

    @Override
    public void create(StudentRequest request) {
        if(request.getFullName()==null) throw new BadRequestException(400, "Full name is required");
        if(request.getPhoneNumber()==null) throw new BadRequestException(400, "Phone number is required");

        if(!request.getPhoneNumber().matches("^(09|03|07|08|05)\\d{8}$"))
            throw new BadRequestException(400, "Please input phone number format exactly Vietnam");

        if(studentRepository.existsByPhoneNumber(request.getPhoneNumber()))
            throw new BadRequestException(400, "Number phone is exist!");

        Student newStudent = new Student(request);
        Student student = studentRepository.save(newStudent);

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> {throw new NotFoundException(404, "Error: Role is not found");});

        Account newAccount = new Account();
        newAccount.setStudent(student);
        newAccount.setUsername(student.getPhoneNumber());
        newAccount.setPassword(encoder.encode(student.getPhoneNumber()));
        newAccount.setActive(true);
        newAccount.setRoles(userRole);
        accountRepository.save(newAccount);
    }

    @Override
    public void update(Long id, StudentRequest request) {
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Student is not found!");
        });

        if(request.getFullName()!=null) student.setFullName(request.getFullName());
        if(request.getAddress()!=null) student.setAddress(request.getAddress());
        if(request.getBirthday()!=null) student.setBirthday(request.getBirthday());
        if(request.getPhoneNumber()!=null) {
            if(!request.getPhoneNumber().matches("^(09|03|07|08|05)\\d{8}$"))
                throw new BadRequestException(400, "Please input phone number format exactly Vietnam");

            if(studentRepository.existsByPhoneNumber(request.getPhoneNumber()))
                throw new BadRequestException(400, "Number phone is exist!");

            student.setPhoneNumber(request.getPhoneNumber());
        }
        studentRepository.save(student);

        Account account = accountRepository.findByStudent(student);
        account.setUsername(student.getPhoneNumber());
        account.setPassword(encoder.encode(student.getPhoneNumber()));
        accountRepository.save(account);
    }

    @Override
    public void resetPassword(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Student is not found!");
        });

        Account account = accountRepository.findByStudent(student);
        account.setPassword(encoder.encode(student.getPhoneNumber()));
        accountRepository.save(account);
    }

    @Override
    public void delete(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Student is not found!");
        });

        Account account = accountRepository.findByStudent(student);
        accountRepository.delete(account);
        studentRepository.delete(student);
    }
}
