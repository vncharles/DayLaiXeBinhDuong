package com.charles.website.controller;

import com.charles.website.entity.Degree;
import com.charles.website.services.DegreeService;
import com.charles.website.services.SocialNetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/social-network")
public class SocialNetworkController {
    @Autowired
    private SocialNetworkService socialNetworkService;

    @GetMapping("")
    public ResponseEntity<?> getAllSocialNetwork(){
        return ResponseEntity.ok(socialNetworkService.getListSocialNetwork());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailSocialNetwork(@PathVariable("id")Long id) {

        return ResponseEntity.ok(socialNetworkService.getDetailSocialNetwork(id));

    }
}
