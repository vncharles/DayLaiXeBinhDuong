package com.charles.website.services;

import com.charles.website.entity.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    Student seeInfoPerson();

    void updateInfoPerson(Student req);

    void updatePasswordPerson(String password);
}
