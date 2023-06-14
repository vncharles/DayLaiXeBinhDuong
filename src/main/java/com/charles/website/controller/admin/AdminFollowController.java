package com.charles.website.controller.admin;

import com.charles.website.entity.Contact;
import com.charles.website.entity.Follow;
import com.charles.website.model.MessageResponse;
import com.charles.website.model.request.FollowRequest;
import com.charles.website.model.request.IncreaseHoursRequest;
import com.charles.website.services.FollowService;
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
@RequestMapping("/api/admin/follow")
public class AdminFollowController {
    @Autowired
    private FollowService followService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(value = "filter", required = false)String filter,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Authen.check();

        Pageable pageable = PageRequest.of(page, size);
        Page<Follow> result = followService.getAll(filter, pageable);

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
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Authen.check();

        Pageable pageable = PageRequest.of(page, size);
        Page<Follow> result = followService.getAllByDegree(degreeId, pageable);

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

        Follow follow = followService.getDetail(studentId, degreeId);

        return ResponseEntity.ok(follow);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody FollowRequest request) {
        Authen.check();

        followService.create(request);

        return ResponseEntity.ok(new MessageResponse("Create follow is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("student-id")Long studentId,
                                    @RequestParam("degree-id")Long degreeId,
                                    @RequestBody FollowRequest request) {
        Authen.check();

        followService.update(studentId, degreeId, request);

        return ResponseEntity.ok(new MessageResponse("Update follow is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("student-id")Long studentId,
                                    @RequestParam("degree-id")Long degreeId) {
        Authen.check();

        followService.delete(studentId, degreeId);

        return ResponseEntity.ok(new MessageResponse("Delete follow is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PostMapping("/increase-hours")
    public ResponseEntity<?> increaseHours(@RequestParam("student-id")Long studentId,
                                           @RequestParam("degree-id")Long degreeId,
                                           @RequestBody IncreaseHoursRequest request) {
        Authen.check();

        followService.increaseHours(studentId, degreeId, request);

        return ResponseEntity.ok(new MessageResponse("Increase hours on follow is success"));
    }
}
