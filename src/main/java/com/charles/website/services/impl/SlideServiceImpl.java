package com.charles.website.services.impl;

import com.charles.website.entity.Slide;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.repository.SlideRepository;
import com.charles.website.services.SlideService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SlideServiceImpl implements SlideService {
    @Autowired
    private SlideRepository slideRepository;

    @Override
    public List<Slide> getListSlide() {
        return slideRepository.findAll();
    }

    @Override
    public Slide getDetailSlide(Long id) {
        Slide slide = null;
        try {
            slide = slideRepository.findById(id).get();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(400, "Slide is not found!");
        }

        return slide;
    }

    @Override
    public byte[] getSlideImage(String imageName) throws IOException {
        return FileUtils.readFileToByteArray(new File("src/main/resources/images/slide/" + imageName));
    }
}
