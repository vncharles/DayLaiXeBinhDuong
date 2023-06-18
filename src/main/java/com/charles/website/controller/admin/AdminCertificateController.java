package com.charles.website.controller.admin;

import com.charles.website.entity.Certificate;
import com.charles.website.entity.Follow;
import com.charles.website.model.MessageResponse;
import com.charles.website.services.CertificateService;
import com.charles.website.utils.Authen;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminCertificateController {
    @Autowired
    private CertificateService certificateService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(value = "filter", required = false, defaultValue = "")String filter,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Authen.check();

        Pageable pageable = PageRequest.of(page, size);
        Page<Certificate> result = certificateService.getAll(filter, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @GetMapping("/by-degree")
    public ResponseEntity<?> getAllByDegree(@RequestParam("degree-id")Long degreeId,
                                            @RequestParam(value = "filter", defaultValue = "")String filter,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Authen.check();

        Pageable pageable = PageRequest.of(page, size);
        Page<Certificate> result = certificateService.getAllByDegree(degreeId, filter, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @GetMapping("/detail")
    public ResponseEntity<?> getDetail(@RequestParam("student-id")Long studentId,
                                       @RequestParam("degree-id")Long degreeId) {
        Authen.check();

        Certificate certificate = certificateService.getDetail(studentId, degreeId);

        return ResponseEntity.ok(certificate);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam("student-id")Long studentId,
                                       @RequestParam("degree-id")Long degreeId) {
        Authen.check();

        certificateService.create(studentId, degreeId);

        return ResponseEntity.ok(new MessageResponse("Create certificate is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PutMapping("/destatus")
    public ResponseEntity<?> deStatus(@RequestParam("student-id")Long studentId,
                                    @RequestParam("degree-id")Long degreeId) {
        Authen.check();

        certificateService.destatus(studentId, degreeId);

        return ResponseEntity.ok(new MessageResponse("De status certificate is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("student-id")Long studentId,
                                    @RequestParam("degree-id")Long degreeId) {
        Authen.check();

        certificateService.delete(studentId, degreeId);

        return ResponseEntity.ok(new MessageResponse("Delete certificate is success"));
    }
}
