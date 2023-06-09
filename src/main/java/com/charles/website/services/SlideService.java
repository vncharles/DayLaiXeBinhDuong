package com.charles.website.services;

import com.charles.website.entity.Slide;
import com.charles.website.model.request.SlideRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface SlideService {
    List<Slide> getListSlide();

    Slide getDetailSlide(Long id);

    byte[] getSlideImage(String imageName) throws IOException;

    void create(SlideRequest request, MultipartFile image) throws IOException;

    void update(Long id, SlideRequest req, MultipartFile image) throws IOException;

    void delete(Long id);
}
