package com.charles.website.services.impl;

import com.charles.website.entity.Contact;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.model.request.ContactRequest;
import com.charles.website.repository.ContactRepository;
import com.charles.website.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void createContact(ContactRequest contact) {
        if(contact.getFullName()==null || contact.getPhoneNumber()==null) {
            throw new BadRequestException(400, "Please input full name and phone number");
        }

        if(!contact.getPhoneNumber().matches("^(09|03|07|08|05)\\d{8}$"))
            throw new BadRequestException(400, "Please input phone number format exactly Vietnam");

        Contact contactNew = new Contact();
        contactNew.setFullName(contact.getFullName());
        contactNew.setPhoneNumber(contact.getPhoneNumber());
        contactNew.setNote(contact.getNote());
        contactNew.setStatus(false);

        contactRepository.save(contactNew);
    }

    @Override
    public Page<Contact> getAll(Pageable pageable, Boolean status) {
        Page<Contact> result = null;
        if(status!=null) {
            result = contactRepository.findAllByStatusQuery(status, pageable);
        } else result = contactRepository.findAllQuery(pageable);

        return result;
    }

    @Override
    public Contact getDetail(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> {
           throw new NotFoundException(404, "Contact is not found!");
        });

        return contact;
    }

    @Override
    public void updateContact(Long id, ContactRequest request) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Contact is not found!");
        });

        if(request.getFullName()!=null) contact.setFullName(request.getFullName());
        if(request.getPhoneNumber()!=null) {
            if(!request.getPhoneNumber().matches("^(09|03|07|08|05)\\d{8}$"))
                throw new BadRequestException(400, "Please input phone number format exactly Vietnam");

            contact.setPhoneNumber(request.getPhoneNumber());
        }
        if(request.getNote()!=null) contact.setNote(request.getNote());
        if(request.getStatus()!=null) contact.setStatus(request.getStatus());

        contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Contact is not found!");
        });

        contactRepository.delete(contact);
    }
}
