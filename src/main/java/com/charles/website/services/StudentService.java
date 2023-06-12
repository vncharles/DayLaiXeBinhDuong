package com.charles.website.services;

import com.charles.website.entity.Student;
import com.charles.website.model.request.StudentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    Student seeInfoPerson();

    void updateInfoPerson(StudentRequest req);

    void updatePasswordPerson(String password);

    Page<Student> getAll(Pageable pageable, String filter);

    Student getDetail(Long id);

    void create(StudentRequest request);

    void update(Long id, StudentRequest request);

    void resetPassword(Long id);

    void delete(Long id);
}
