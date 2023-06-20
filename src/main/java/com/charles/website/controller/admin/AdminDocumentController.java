package com.charles.website.controller.admin;

import com.charles.website.model.MessageResponse;
import com.charles.website.model.request.DocumentRequest;
import com.charles.website.model.request.SlideRequest;
import com.charles.website.services.DocumentService;
import com.charles.website.utils.Authen;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin/document")
public class AdminDocumentController {
    @Autowired
    private DocumentService documentService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PostMapping(value = "/create", headers = { "content-type=multipart/mixed", "content-type=multipart/form-data" })
    public ResponseEntity<?> create(@RequestParam(value = "data", required = false) String data,
                                    @RequestParam("image") MultipartFile image) throws IOException {
        Authen.check();
        DocumentRequest req = null;
        if(data!=null) {
            ObjectMapper mapper = new ObjectMapper();
            req = mapper.readValue(data, DocumentRequest.class);
        } else req = new DocumentRequest();

        documentService.create(req, image);
        return ResponseEntity.ok(new MessageResponse("create document is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PostMapping(value = "/update", headers = { "content-type=multipart/mixed", "content-type=multipart/form-data" })
    public ResponseEntity<?> update(@RequestParam("id") Long id,
                                    @RequestParam(value = "data", required = false) String data,
                                    @RequestParam(value = "image", required = false) MultipartFile image ) throws IOException {
        Authen.check();
        DocumentRequest req = null;
        if(data!=null) {
            ObjectMapper mapper = new ObjectMapper();
            req = mapper.readValue(data, DocumentRequest.class);
        } else req = new DocumentRequest();
        documentService.update(id, req, image);

        return ResponseEntity.ok(new MessageResponse("Update document is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        Authen.check();

        documentService.delete(id);

        return ResponseEntity.ok(new MessageResponse("Delete document is success"));
    }
}
