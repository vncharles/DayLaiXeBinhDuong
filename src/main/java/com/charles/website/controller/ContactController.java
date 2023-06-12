package com.charles.website.controller;

import com.charles.website.entity.Contact;
import com.charles.website.model.MessageResponse;
import com.charles.website.model.request.ContactRequest;
import com.charles.website.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/send-contact")
    public ResponseEntity<?> sendContact(@RequestBody ContactRequest contact) {
        contactService.createContact(contact);

        return ResponseEntity.ok(new MessageResponse("Send contact is success."));
    }
}
