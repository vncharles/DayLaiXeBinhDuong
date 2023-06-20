package com.charles.website.services;

import com.charles.website.entity.Document;
import com.charles.website.model.request.DocumentRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface DocumentService {
    List<Document> getAll();

    Document getDetail(Long id);

    void create(DocumentRequest request, MultipartFile multipartFile) throws IOException;

    void update(Long id, DocumentRequest request, MultipartFile multipartFile) throws IOException;

    void delete(Long id);

    byte[] getImage(Long id) throws IOException;
}
