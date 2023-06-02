package com.charles.website.controller;

import com.charles.website.entity.Slide;
import com.charles.website.services.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/slide")
public class SlideController {

    @Autowired
    private SlideService slideService;

    @GetMapping("")
    public ResponseEntity<?> getAllSilde() {

        return ResponseEntity.ok(slideService.getListSlide());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailSlide(@PathVariable("id")Long id) {

        return ResponseEntity.ok(slideService.getDetailSlide(id));

    }

    // controller
    @GetMapping("/image")
    public ResponseEntity<byte[]> getSlideImage(@RequestParam("image-name")String imageName) throws IOException {
        byte[] imageData = slideService.getSlideImage(imageName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

}
