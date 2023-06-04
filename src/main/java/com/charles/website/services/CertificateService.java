package com.charles.website.services;

import com.charles.website.entity.Certificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CertificateService {
    Page<Certificate> listCertificatePerson(Pageable pageable);
}
