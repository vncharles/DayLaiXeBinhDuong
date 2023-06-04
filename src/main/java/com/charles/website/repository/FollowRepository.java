package com.charles.website.repository;

import com.charles.website.entity.Follow;
import com.charles.website.entity.StudentDegreeID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, StudentDegreeID> {
    @Query(nativeQuery = true, value = "select * from follow where student_id=?1")
    List<Follow> findAllByStudent(Long studentId);
}
