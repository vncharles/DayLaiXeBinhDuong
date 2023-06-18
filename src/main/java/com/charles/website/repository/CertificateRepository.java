package com.charles.website.repository;

import com.charles.website.entity.Certificate;
import com.charles.website.entity.Degree;
import com.charles.website.entity.Follow;
import com.charles.website.entity.StudentDegreeID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, StudentDegreeID> {
    boolean existsById(StudentDegreeID id);

    @Query(nativeQuery = true,
            value = "select * from certificate where student_id=?1",
            countQuery = "select COUNT(*) from certificate where student_id=?1")
    Page<Certificate> findAllByStudent(Long studentId, Pageable pageable);

    @Query(nativeQuery = true, value = "select c.* from certificate c " +
            "join student s on c.student_id = s.id " +
            "join degree d on c.degree_id = d.id " +
            "where s.address like %?1% or s.full_name like %?1% or s.phone_number like %?1% or d.title like %?1%",
            countQuery = "select COUNT(*) from certificate c " +
                    "join student s on c.student_id = s.id " +
                    "join degree d on c.degree_id = d.id " +
                    "where s.address like %?1% or s.full_name like %?1% or s.phone_number like %?1% or d.title like %?1%")
    Page<Certificate> findAllFilter(String filter, Pageable pageable);

    @Query(nativeQuery = true, value = "select c.* from certificate c " +
            "join student s on c.student_id = s.id " +
            "join degree d on c.degree_id = d.id " +
            "where c.degree_id=?1 and (s.address like %?2% or s.full_name like %?2% or s.phone_number like %?2% or d.title like %?2%)",
            countQuery = "select COUNT(*) from certificate c " +
                    "join student s on c.student_id = s.id " +
                    "join degree d on c.degree_id = d.id " +
                    "where c.degree_id=?1 and (s.address like %?2% or s.full_name like %?2% or s.phone_number like %?2% or d.title like %?2%)")
    Page<Certificate> findAllByDegreeFilter(Long degreeId, String filter, Pageable pageable);
}
