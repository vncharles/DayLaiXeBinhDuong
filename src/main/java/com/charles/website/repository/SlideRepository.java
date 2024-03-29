package com.charles.website.repository;

import com.charles.website.entity.Slide;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {
}
