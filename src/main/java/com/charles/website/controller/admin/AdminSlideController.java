package com.charles.website.controller.admin;

import com.charles.website.model.MessageResponse;
import com.charles.website.model.request.SlideRequest;
import com.charles.website.services.SlideService;
import com.charles.website.utils.Authen;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.Nullable;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin/slide")
public class AdminSlideController {
    @Autowired
    private SlideService slideService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PostMapping(value = "/create", headers = { "content-type=multipart/mixed", "content-type=multipart/form-data" })
    public ResponseEntity<?> createProduct(@RequestParam("data") String data,
                                           @Nullable @RequestParam("image") MultipartFile image) throws IOException {
        Authen.check();
        ObjectMapper mapper = new ObjectMapper();
        SlideRequest req = mapper.readValue(data, SlideRequest.class);

        slideService.create(req, image);
        return ResponseEntity.ok(new MessageResponse("create slide is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PostMapping(value = "/update", headers = { "content-type=multipart/mixed", "content-type=multipart/form-data" })
    public ResponseEntity<?> updateProduct(@RequestParam("id") Long id,
                                           @RequestParam(value = "data", required = false) String data,
                                           @RequestParam(value = "image", required = false) MultipartFile image ) throws IOException {
        Authen.check();
        SlideRequest req = null;
        if(data!=null) {
            ObjectMapper mapper = new ObjectMapper();
            req = mapper.readValue(data, SlideRequest.class);
        }
        slideService.update(id, req, image);
        return ResponseEntity.ok(new MessageResponse("Update slide is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        Authen.check();

        slideService.delete(id);

        return ResponseEntity.ok(new MessageResponse("Delete slide is success"));
    }
}
