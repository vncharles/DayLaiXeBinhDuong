package com.charles.website.repository;

import com.charles.website.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query(nativeQuery = true, value = "select * from contact where status=?1 order by created_date DESC",
            countQuery = "select COUNT(*) from contact where status=?1")
    Page<Contact> findAllByStatusQuery(boolean status, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from contact order by created_date DESC",
            countQuery = "select COUNT(*) from contact")
    Page<Contact> findAllQuery(Pageable pageable);
}
