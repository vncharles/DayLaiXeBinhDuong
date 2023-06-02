package com.charles.website.services;

import com.charles.website.entity.Contact;
import org.springframework.stereotype.Service;

@Service
public interface ContactService {
    void createContact(Contact contact);
}
