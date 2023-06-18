package com.charles.website.services.impl;

import com.charles.website.entity.*;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.ForbiddenException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.model.request.FollowRequest;
import com.charles.website.model.request.IncreaseHoursRequest;
import com.charles.website.repository.AccountRepository;
import com.charles.website.repository.DegreeRepository;
import com.charles.website.repository.FollowRepository;
import com.charles.website.repository.StudentRepository;
import com.charles.website.services.FollowService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DegreeRepository degreeRepository;

    @Override
    public Page<Follow> listFollowPerson(Pageable pageable) {
        Account account = accountRepository.findByUsername(Authen.username());

        return followRepository.findAllByStudent(account.getStudent().getId(), pageable);
    }

    @Override
    public Follow getFollowDetailPerson(Long studentId, Long degreeId) {
        Account account = accountRepository.findByUsername(Authen.username());
        if(account.getStudent().getId()!=studentId) throw new ForbiddenException(403, "You don't have permission to see other people's followers");

        StudentDegreeID studentDegreeID = new StudentDegreeID(new Student(studentId), new Degree(degreeId));

        Follow follow = null;
        try {
            follow = followRepository.findById(studentDegreeID).get();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(400, "ID follow is not found!");
        }

        return follow;
    }

    @Override
    public Page<Follow> getAll(String filter, Pageable pageable) {
        return followRepository.findAllFilter(filter, pageable);
    }

    @Override
    public Page<Follow> getAllByDegree(Long degreeId, String filter, Pageable pageable) {
        return followRepository.findAllByDegreeFilter(degreeId, filter, pageable);
    }

    @Override
    public Follow getDetail(Long studentId, Long degreeId) {
        if(studentId==null || degreeId==null) throw new BadRequestException(400, "Please input ID student and degree");
        StudentDegreeID studentDegreeID = new StudentDegreeID(new Student(studentId), new Degree(degreeId));
        Follow follow = followRepository.findById(studentDegreeID).orElseThrow(() -> {
            throw new NotFoundException(404, "Follow is not found!");
        });

        return follow;
    }

    @Override
    public void create(FollowRequest request) {
        if(request.getDegreeId()==null) throw new BadRequestException(400, "Degree ID is required!");
        if(request.getStudentId()==null) throw new BadRequestException(400, "Student ID is required!");
        if(request.getCourse()==null) throw new BadRequestException(400, "Course is required!");
        if(request.getTeacher()==null) throw new BadRequestException(400, "Teacher is required!");

        Student student = studentRepository.findById(request.getStudentId()).orElseThrow(() -> {
            throw new NotFoundException(404, "Student is not found!");
        });

        Degree degree = degreeRepository.findById(request.getDegreeId()).orElseThrow(() -> {
            throw new NotFoundException(404, "Degree is not found!");
        });

        StudentDegreeID id = new StudentDegreeID(student, degree);
        if(followRepository.existsById(id)) throw new BadRequestException(400, "Follow is exist!!");

        Follow follow = new Follow(id, request);

        followRepository.save(follow);
    }

    @Override
    public void update(Long studentId, Long degreeId, FollowRequest request) {
        if(studentId==null || degreeId==null) throw new BadRequestException(400, "Please input ID student and degree");
        StudentDegreeID studentDegreeID = new StudentDegreeID(new Student(studentId), new Degree(degreeId));
        Follow follow = followRepository.findById(studentDegreeID).orElseThrow(() -> {
            throw new NotFoundException(404, "Follow is not found!");
        });

        if(request.getCourse()!=null) follow.setCourse(request.getCourse());
        if(request.getHoursRunningDAT()!=null) follow.setHoursRunningDAT(request.getHoursRunningDAT());
        if(request.getNightRunningHours()!=null) follow.setNightRunningHours(request.getNightRunningHours());
        if(request.getAutomaticRunningHours()!=null) follow.setAutomaticRunningHours(request.getAutomaticRunningHours());
        if(request.getKmDAT()!=null) follow.setKmDAT(request.getKmDAT());
        if(request.getTheotyTestScore()!=null) follow.setTheotyTestScore(request.getTheotyTestScore());
        if(request.getSimulatedTestScore()!=null) follow.setSimulatedTestScore(request.getSimulatedTestScore());
        if(request.getTeacher()!=null) follow.setTeacher(request.getTeacher());
        if(request.getNote()!=null) follow.setNote(request.getNote());

        followRepository.save(follow);
    }

    @Override
    public void delete(Long studentId, Long degreeId) {
        if(studentId==null || degreeId==null) throw new BadRequestException(400, "Please input ID student and degree");
        StudentDegreeID studentDegreeID = new StudentDegreeID(new Student(studentId), new Degree(degreeId));
        Follow follow = followRepository.findById(studentDegreeID).orElseThrow(() -> {
            throw new NotFoundException(404, "Follow is not found!");
        });

        followRepository.delete(follow);
    }

    @Override
    public void increaseHours(Long studentId, Long degreeId, IncreaseHoursRequest request) {
        if(studentId==null || degreeId==null) throw new BadRequestException(400, "Please input ID student and degree");
        StudentDegreeID studentDegreeID = new StudentDegreeID(new Student(studentId), new Degree(degreeId));
        Follow follow = followRepository.findById(studentDegreeID).orElseThrow(() -> {
            throw new NotFoundException(404, "Follow is not found!");
        });

        if(request.getCategory()==null) throw new BadRequestException(400, "Category hours increase is required!");
        if(request.getValue()==null) throw new BadRequestException(400, "Value category is required!");

        if(request.getCategory().equalsIgnoreCase("dat")) follow.increaseHoursRunningDAT(request.getValue());
        if(request.getCategory().equalsIgnoreCase("night")) follow.increaseNightRunningHours(request.getValue());
        if(request.getCategory().equalsIgnoreCase("automatic")) follow.increaseAutomaticRunningHours(request.getValue());

        followRepository.save(follow);
    }
}
