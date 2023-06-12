package com.charles.website.services;

import com.charles.website.entity.Contact;
import com.charles.website.model.request.ContactRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ContactService {
    void createContact(ContactRequest contact);

    Page<Contact> getAll(Pageable pageable, Boolean status);

    Contact getDetail(Long id);

    void updateContact(Long id, ContactRequest request);

    void deleteContact(Long id);
}
