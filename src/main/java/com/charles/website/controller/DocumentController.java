package com.charles.website.controller;

import com.charles.website.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(documentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable("id")Long id) {
        return ResponseEntity.ok(documentService.getDetail(id));
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getSlideImage(@PathVariable("id")Long id) throws IOException {
        byte[] imageData = documentService.getImage(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }
}
