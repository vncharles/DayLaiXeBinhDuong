package com.charles.website.repository;

import com.charles.website.entity.Follow;
import com.charles.website.entity.StudentDegreeID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, StudentDegreeID> {
    @Query(nativeQuery = true, value = "select * from follow where student_id=?1")
    Page<Follow> findAllByStudent(Long studentId, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from follow where student_id=?1")
    Page<Follow> findAllByDegree(Long degreeId, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT f.* FROM follow f " +
            "join student s on f.student_id = s.id " +
            "join degree d on f.degree_id = d.id " +
            "where s.address like '%?1%' or s.full_name like '%?1%' or s.phone_number like '%?1%' " +
            "or d.title like '%?1%'")
    Page<Follow> findAllByFilter(String filter, Pageable pageable);

    boolean existsById(StudentDegreeID id);
}
