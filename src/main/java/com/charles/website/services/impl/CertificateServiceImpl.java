package com.charles.website.services.impl;

import com.charles.website.entity.Account;
import com.charles.website.entity.Certificate;
import com.charles.website.repository.AccountRepository;
import com.charles.website.repository.CertificateRepository;
import com.charles.website.services.CertificateService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Override
    public List<Certificate> listCertificatePerson() {
        Account account = accountRepository.findByUsername(Authen.username());

        return certificateRepository.findAllByStudent(account.getStudent().getId());
    }
}
