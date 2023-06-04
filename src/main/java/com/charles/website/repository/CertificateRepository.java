package com.charles.website.repository;

import com.charles.website.entity.Certificate;
import com.charles.website.entity.StudentDegreeID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, StudentDegreeID> {
    @Query(nativeQuery = true, value = "select * from certificate where student_id=?1")
    List<Certificate> findAllByStudent(Long studentId);
}
