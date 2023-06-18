package com.charles.website.services.impl;

import com.charles.website.entity.*;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.ForbiddenException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.repository.*;
import com.charles.website.services.CertificateService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DegreeRepository degreeRepository;

    @Autowired
    private FollowRepository followRepository;

    @Override
    public Page<Certificate> listCertificatePerson(Pageable pageable) {
        Account account = accountRepository.findByUsername(Authen.username());

        return certificateRepository.findAllByStudent(account.getStudent().getId(), pageable);
    }

    @Override
    public Certificate getCertificateDetailPerson(Long studentId, Long degreeId) {
        Account account = accountRepository.findByUsername(Authen.username());
        if(account.getStudent().getId()!=studentId) throw new ForbiddenException(403, "You don't have permission to see other people's certificates");

        StudentDegreeID studentDegreeID = new StudentDegreeID(new Student(studentId), new Degree(degreeId));

        Certificate certificate = null;
        try {
            certificate = certificateRepository.findById(studentDegreeID).get();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(400, "ID certificate is not found!");
        }

        return certificate;
    }

    @Override
    public Page<Certificate> getAll(String filter, Pageable pageable) {
        return certificateRepository.findAllFilter(filter, pageable);
    }

    @Override
    public Page<Certificate> getAllByDegree(Long degreeId, String filter, Pageable pageable) {
        return certificateRepository.findAllByDegreeFilter(degreeId, filter, pageable);
    }

    @Override
    public Certificate getDetail(Long studentId, Long degreeId) {
        StudentDegreeID id = new StudentDegreeID(new Student(studentId), new Degree(degreeId));
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Certificate is not found!");
        });

        return certificate;
    }

    @Override
    public void create(Long studentId, Long degreeId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> {
            throw new NotFoundException(404, "Student is not found!");
        });
        Degree degree = degreeRepository.findById(degreeId).orElseThrow(() -> {
            throw new NotFoundException(404, "Degree is not found!");
        });

        StudentDegreeID id = new StudentDegreeID(student, degree);
        Follow follow = followRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Follow is not found!");
        });

        if(certificateRepository.existsById(id))
            throw new BadRequestException(400, "Certificate is exist!");

        Certificate certificate = new Certificate(id);
        certificate.setFinishDay(LocalDate.now());
        certificate.setKmDAT(follow.getKmDAT());
        certificate.setTheotyTestScore(follow.getTheotyTestScore());
        certificate.setSimulatedTestScore(follow.getSimulatedTestScore());
        certificate.setStatus(false);
        double totalHoursRunning = follow.getHoursRunningDAT() + follow.getNightRunningHours() + follow.getAutomaticRunningHours();
        certificate.setTotalHoursRunning(totalHoursRunning);

        certificateRepository.save(certificate);
    }

    @Override
    public void delete(Long studentId, Long degreeId) {
        StudentDegreeID id = new StudentDegreeID(new Student(studentId), new Degree(degreeId));
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Certificate is not found!");
        });

        certificateRepository.delete(certificate);
    }

    @Override
    public void destatus(Long studentId, Long degreeId) {
        StudentDegreeID id = new StudentDegreeID(new Student(studentId), new Degree(degreeId));
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Certificate is not found!");
        });

        certificate.setStatus(!certificate.isStatus());
        certificateRepository.save(certificate);
    }
}
