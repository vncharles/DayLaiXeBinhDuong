package com.charles.website.repository;

import com.charles.website.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, StudentDegreeID> {
    @Query(nativeQuery = true,
            value = "select * from follow where student_id=?1",
            countQuery = "select COUNT(*) from follow where student_id=?1")
    Page<Follow> findAllByStudent(Long studentId, Pageable pageable);

    List<Follow> findAllById_Student(Student student);

    List<Follow> findAllById_Degree(Degree degree);

    @Query(nativeQuery = true,
            value = "SELECT f.* from follow f " +
                    "join student s on f.student_id = s.id " +
                    "join degree d on f.degree_id = d.id " +
                    "where f.degree_id=?1 and (s.address like %?2% or s.full_name like %?2% or s.phone_number like %?2% or d.title like %?2%)",
            countQuery = "SELECT COUNT(*) from follow f " +
                    "join student s on f.student_id = s.id " +
                    "join degree d on f.degree_id = d.id " +
                    "where f.degree_id=?1 and (s.address like %?2% or s.full_name like %?2% or s.phone_number like %?2% or d.title like %?2%)")
    Page<Follow> findAllByDegreeFilter(Long degreeId, String filter, Pageable pageable);

    @Query(value = "SELECT f.* FROM follow f " +
            "JOIN student s ON f.student_id = s.id " +
            "JOIN degree d ON f.degree_id = d.id " +
            "WHERE s.address LIKE %?1% OR s.full_name LIKE %?1% OR s.phone_number LIKE %?1% OR d.title LIKE %?1%",
            countQuery = "SELECT COUNT(*) FROM follow f " +
                    "JOIN student s ON f.student_id = s.id " +
                    "JOIN degree d ON f.degree_id = d.id " +
                    "WHERE s.address LIKE %?1% OR s.full_name LIKE %?1% OR s.phone_number LIKE %?1% OR d.title LIKE %?1%",
            nativeQuery = true)
    Page<Follow> findAllFilter(String filter, Pageable pageable);
    boolean existsById(StudentDegreeID id);
}
