package com.charles.website.services.impl;

import com.charles.website.entity.*;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.ForbiddenException;
import com.charles.website.repository.AccountRepository;
import com.charles.website.repository.CertificateRepository;
import com.charles.website.services.CertificateService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CertificateRepository certificateRepository;

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
}
