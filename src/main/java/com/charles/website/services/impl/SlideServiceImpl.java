package com.charles.website.services.impl;

import com.charles.website.entity.Slide;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.model.request.SlideRequest;
import com.charles.website.repository.SlideRepository;
import com.charles.website.services.SlideService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
        return FileUtils.readFileToByteArray(new File("images/" + imageName));
    }

    @Override
    public void create(SlideRequest request, MultipartFile image) throws IOException {
        if(request.getTitle()==null || request.getDescription()==null)
            throw new BadRequestException(400, "Please input full info");
        if(image == null) throw new BadRequestException(400, "Image is required");
        Slide slide = new Slide();
        slide.setTitle(request.getTitle());
        slide.setDescription(request.getDescription());
        String imageName = saveImage(image);
        slide.setImage(imageName);

        slideRepository.save(slide);
    }

    @Override
    public void update(Long id, SlideRequest req, MultipartFile image) throws IOException {
        Slide slide = slideRepository.findById(id).orElseThrow(() -> {
           throw new NotFoundException(404, "Slide is not found!");
        });

        if(req.getTitle()!=null) slide.setTitle(req.getTitle());
        if(req.getDescription()!=null) slide.setDescription(req.getDescription());
        if(image!=null) {
            String imageUrl = saveImage(image);
            slide.setImage(imageUrl);
        }

        slideRepository.save(slide);
    }

    @Override
    public void delete(Long id) {
        Slide slide = slideRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Slide is not found!");
        });

        slideRepository.delete(slide);
    }

    private String saveImage(MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(originalFilename);
        String imageName = UUID.randomUUID().toString() + "." + extension;

        File file = new File("images/" + imageName);
        FileUtils.writeByteArrayToFile(file, image.getBytes());

        return imageName;
    }
}
