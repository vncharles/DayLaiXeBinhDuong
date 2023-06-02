package com.charles.website.repository;

import com.charles.website.entity.SocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialNeworkRepository extends JpaRepository<SocialNetwork, Long> {
}
