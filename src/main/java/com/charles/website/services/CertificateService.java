package com.charles.website.services;

import com.charles.website.entity.Certificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CertificateService {
    Page<Certificate> listCertificatePerson(Pageable pageable);

    Certificate getCertificateDetailPerson(Long studentId, Long degreeId);

    Page<Certificate> getAll(String filter, Pageable pageable);

    Page<Certificate> getAllByDegree(Long degreeId, String filter, Pageable pageable);

    Certificate getDetail(Long studentId, Long degreeId);

    void create(Long studentId, Long degreeId);

    void delete(Long studentId, Long degreeId);

    void destatus(Long studentId, Long degreeId);
}
