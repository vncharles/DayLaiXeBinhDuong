package com.charles.website.services;

import com.charles.website.entity.Student;
import com.charles.website.model.request.StudentRequest;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    Student seeInfoPerson();

    void updateInfoPerson(StudentRequest req);

    void updatePasswordPerson(String password);
}
