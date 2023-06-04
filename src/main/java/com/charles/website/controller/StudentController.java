package com.charles.website.controller;

import com.charles.website.entity.Student;
import com.charles.website.model.MessageResponse;
import com.charles.website.services.StudentService;
import com.charles.website.utils.Authen;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @GetMapping("/info-person")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getInfoPerson() {
        Authen.check();

        return ResponseEntity.ok(studentService.seeInfoPerson());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PutMapping("/update-person")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updateInfoPerson(@RequestBody Student request) {
        Authen.check();

        studentService.updateInfoPerson(request);

        return ResponseEntity.ok(new MessageResponse("Update info person is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PutMapping("/reset-person-password")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> resetPersonPassword(@RequestParam("password") String password) {
        Authen.check();

        studentService.updatePasswordPerson(password);

        return ResponseEntity.ok(new MessageResponse("Update password person is success"));
    }
}
