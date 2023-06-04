package com.charles.website.services.impl;

import com.charles.website.entity.Account;
import com.charles.website.entity.Student;
import com.charles.website.repository.AccountRepository;
import com.charles.website.repository.StudentRepository;
import com.charles.website.services.StudentService;
import com.charles.website.utils.Authen;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Student seeInfoPerson() {
        return accountRepository.findByUsername(Authen.username()).getStudent();
    }

    @Override
    public void updateInfoPerson(Student req) {
        Student student = accountRepository.findByUsername(Authen.username()).getStudent();

        if(req.getFullName()!=null) student.setFullName(req.getFullName());
        if(req.getPhoneNumber()!=null) student.setPhoneNumber(req.getPhoneNumber());
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
}
