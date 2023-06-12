package com.charles.website.controller.admin;

import com.charles.website.entity.Contact;
import com.charles.website.entity.Student;
import com.charles.website.model.MessageResponse;
import com.charles.website.model.request.StudentRequest;
import com.charles.website.services.StudentService;
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
@RequestMapping("/api/admin/student")
public class AdminStudentController {
    @Autowired
    private StudentService studentService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(value = "filter", required = false)String filter) {
        Authen.check();

        Pageable pageable = PageRequest.of(page, size);
        Page<Student> result = studentService.getAll(pageable, filter);

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
    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable("id")Long id) {
        Authen.check();

        return ResponseEntity.ok(studentService.getDetail(id));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentRequest request) {
        Authen.check();

        studentService.create(request);

        return ResponseEntity.ok(new MessageResponse("Create Student is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id,
                                    @RequestBody StudentRequest request) {
        Authen.check();

        studentService.update(id, request);

        return ResponseEntity.ok(new MessageResponse("Update Student is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("id")Long id) {
        Authen.check();

        studentService.resetPassword(id);

        return ResponseEntity.ok(new MessageResponse("Update password Student is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        Authen.check();

        studentService.delete(id);

        return ResponseEntity.ok(new MessageResponse("Delete student is success"));
    }
}
