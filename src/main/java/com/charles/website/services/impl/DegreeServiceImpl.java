package com.charles.website.services.impl;

import com.charles.website.entity.Degree;
import com.charles.website.exception.BadRequestException;
import com.charles.website.repository.DegreeRepository;
import com.charles.website.services.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DegreeServiceImpl implements DegreeService {
    @Autowired
    private DegreeRepository degreeRepository;

    @Override
    public Page<Degree> getListDegree(Pageable pageable) {
        return degreeRepository.findAll(pageable);
    }

    @Override
    public Degree getDetailDegree(Long id) {
        Degree degree = null;
        try {
            degree = degreeRepository.findById(id).get();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(400, "Degree is not found!");
        }

        return degree;
    }
}
