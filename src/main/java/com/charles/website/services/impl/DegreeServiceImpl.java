package com.charles.website.services.impl;

import com.charles.website.entity.Certificate;
import com.charles.website.entity.Degree;
import com.charles.website.entity.Follow;
import com.charles.website.exception.BadRequestException;
import com.charles.website.model.request.DegreeRequest;
import com.charles.website.repository.CertificateRepository;
import com.charles.website.repository.DegreeRepository;
import com.charles.website.repository.FollowRepository;
import com.charles.website.services.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DegreeServiceImpl implements DegreeService {
    @Autowired
    private DegreeRepository degreeRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Override
    public Page<Degree> getListDegree(Pageable pageable) {
        return degreeRepository.findAll(pageable);
    }

    @Override
    public Degree getDetailDegree(Long id) {
        Degree degree = degreeRepository.findById(id).orElseThrow(() -> {
            throw new BadRequestException(400, "Degree is not found!");
        });

        return degree;
    }

    @Override
    public void create(DegreeRequest request) {
        if(request.getTitle()==null) throw new BadRequestException(400, "Title is required!");
        if(request.getDescription()==null) throw new BadRequestException(400, "description is required!");
        if(request.getPrice()==null) throw new BadRequestException(400, "price is required!");
        if(request.getRating()==null) throw new BadRequestException(400, "rating is required!");
        if(request.getStudyTime()==null) throw new BadRequestException(400, "studyTime is required!");
        if(request.getDAT()==null) throw new BadRequestException(400, "DAT is required!");

        Degree newDegree = new Degree(request);
        degreeRepository.save(newDegree);
    }

    @Override
    public void update(Long id, DegreeRequest request) {
        Degree degree = degreeRepository.findById(id).orElseThrow(() -> {
            throw new BadRequestException(400, "Degree is not found!");
        });

        if(request.getTitle()!=null) degree.setTitle(request.getTitle());
        if(request.getDescription()!=null) degree.setDescription(request.getDescription());
        if(request.getPrice()!=null) degree.setPrice(request.getPrice());
        if(request.getAllowAge()!=null) degree.setAllowAge(request.getAllowAge());
        if(request.getRating()!=null) degree.setRating(request.getRating());
        if(request.getStudyTime()!=null) degree.setStudyTime(request.getStudyTime());
        if(request.getCategoryCar()!=null) degree.setCategoryCar(request.getCategoryCar());
        if(request.getDAT()!=null) degree.setDAT(request.getDAT());
        if(request.getAdvantage()!=null) degree.setAdvantage(request.getAdvantage());

        degreeRepository.save(degree);
    }

    @Override
    public void delete(Long id) {
        Degree degree = degreeRepository.findById(id).orElseThrow(() -> {
            throw new BadRequestException(400, "Degree is not found!");
        });

        List<Follow> followList = followRepository.findAllById_Degree(degree);
        followRepository.deleteAll(followList);

        List<Certificate> certificates = certificateRepository.findAllById_Degree(degree);
        certificateRepository.deleteAll(certificates);

        degreeRepository.delete(degree);
    }
}
