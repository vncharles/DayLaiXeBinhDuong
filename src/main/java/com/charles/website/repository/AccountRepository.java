package com.charles.website.repository;

import com.charles.website.entity.Account;
import com.charles.website.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);

    Account findByStudent(Student student);

    Boolean existsByUsername(String username);

    Page<Account> findAllByUsernameIsContaining(String filter, Pageable pageable);
}

