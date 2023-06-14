package com.charles.website.controller.admin;

import com.charles.website.model.MessageResponse;
import com.charles.website.model.request.DegreeRequest;
import com.charles.website.services.DegreeService;
import com.charles.website.utils.Authen;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/degree")
public class AdminDegreeController {
    @Autowired
    private DegreeService degreeService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DegreeRequest request) {
        Authen.check();

        degreeService.create(request);

        return ResponseEntity.ok(new MessageResponse("Create degree is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id,
                                    @RequestBody DegreeRequest request) {
        Authen.check();

        degreeService.update(id, request);

        return ResponseEntity.ok(new MessageResponse("Update degree is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        Authen.check();

        degreeService.delete(id);

        return ResponseEntity.ok(new MessageResponse("Delete degree is success"));
    }
}
