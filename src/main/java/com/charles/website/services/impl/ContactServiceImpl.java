package com.charles.website.services.impl;

import com.charles.website.entity.Contact;
import com.charles.website.exception.BadRequestException;
import com.charles.website.repository.ContactRepository;
import com.charles.website.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void createContact(Contact contact) {
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
}
