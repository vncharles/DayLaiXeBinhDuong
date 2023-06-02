package com.charles.website.controller;

import com.charles.website.services.IntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/intro")
public class IntroController {
    @Autowired
    private IntroService introService;

    @GetMapping("")
    public ResponseEntity<?> getAllIntro() {

        return ResponseEntity.ok(introService.getListIntro());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailIntro(@PathVariable("id")Long id) {

        return ResponseEntity.ok(introService.getDetailIntro(id));

    }
}
