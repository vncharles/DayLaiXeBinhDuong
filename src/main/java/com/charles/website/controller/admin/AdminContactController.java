package com.charles.website.controller.admin;

import com.charles.website.entity.Contact;
import com.charles.website.model.MessageResponse;
import com.charles.website.model.request.ContactRequest;
import com.charles.website.services.ContactService;
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
@RequestMapping("/api/admin/contact")
public class AdminContactController {
    @Autowired
    private ContactService contactService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(value = "status", required = false) Boolean status,
                                    @RequestParam(value = "filter", required = false, defaultValue = "")String filter) {
        Authen.check();

        Pageable pageable = PageRequest.of(page, size);
        Page<Contact> result = contactService.getAll(status, filter, pageable);

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

        return ResponseEntity.ok(contactService.getDetail(id));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id,
                                    @RequestBody ContactRequest request) {
        Authen.check();

        contactService.updateContact(id, request);

        return ResponseEntity.ok(new MessageResponse("Update contact is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        Authen.check();

        contactService.deleteContact(id);

        return ResponseEntity.ok(new MessageResponse("Delete contact is success"));
    }
}
