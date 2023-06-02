package com.charles.website.controller;

import com.charles.website.entity.Degree;
import com.charles.website.services.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/degree")
public class DegreeController {
    @Autowired
    private DegreeService degreeService;

    @GetMapping("")
    public ResponseEntity<?> getAllDegree(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);

        Page<Degree> pageDegree = degreeService.getListDegree(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", pageDegree.getContent());
        response.put("currentPage", pageDegree.getNumber());
        response.put("totalItems", pageDegree.getTotalElements());
        response.put("totalPages", pageDegree.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailDegree(@PathVariable("id")Long id) {

        return ResponseEntity.ok(degreeService.getDetailDegree(id));

    }
}
