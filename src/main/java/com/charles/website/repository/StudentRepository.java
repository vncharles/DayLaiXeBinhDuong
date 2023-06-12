package com.charles.website.repository;

import com.charles.website.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(nativeQuery = true, value = "select * from student where full_name like '%?1%' or phone_number like '%?1%'")
    Page<Student> findAllByFilter(String filter, Pageable pageable);

    boolean existsByPhoneNumber(String phone);
}
