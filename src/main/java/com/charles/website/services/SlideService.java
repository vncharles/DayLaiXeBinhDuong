package com.charles.website.services;

import com.charles.website.entity.Slide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface SlideService {
    List<Slide> getListSlide();

    Slide getDetailSlide(Long id);

    byte[] getSlideImage(String imageName) throws IOException;
}
