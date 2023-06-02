package com.charles.website.services;

import com.charles.website.entity.Degree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DegreeService {
    Page<Degree> getListDegree(Pageable pageable);

    Degree getDetailDegree(Long id);
}
