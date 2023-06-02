package com.charles.website.repository;

import com.charles.website.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);

    Boolean existsByUsername(String username);

}

