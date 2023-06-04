package com.charles.website.services;

import com.charles.website.entity.Certificate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CertificateService {
    List<Certificate> listCertificatePerson();
}
