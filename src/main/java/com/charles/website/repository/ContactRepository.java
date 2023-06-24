package com.charles.website.repository;

import com.charles.website.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query(nativeQuery = true, value = "select * from contact where status=?2 and (full_name LIKE %?1% OR note like %?1% OR phone_number LIKE %?1%) order by created_date DESC",
            countQuery = "select COUNT(*) from contact where status=?2 and (full_name LIKE %?1% OR note like %?1% OR phone_number LIKE %?1%)")
    Page<Contact> findAllByStatusQuery(String filter, boolean status, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM contact WHERE full_name LIKE %?1% OR note like %?1% OR phone_number LIKE %?1% order by created_date DESC",
            countQuery = "SELECT COUNT(*)  FROM contact WHERE full_name LIKE %?1% OR note like %?1% OR phone_number LIKE %?1%")
    Page<Contact> findAllQuery(String filter, Pageable pageable);
}
