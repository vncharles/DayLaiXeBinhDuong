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
}
