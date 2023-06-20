package com.charles.website.services.impl;

import com.charles.website.entity.Document;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.model.request.DocumentRequest;
import com.charles.website.repository.DocumentRepository;
import com.charles.website.services.DocumentService;
import com.charles.website.utils.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    @Override
    public Document getDetail(Long id) {
        Document document = documentRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Document is not found!");
        });

        return document;
    }

    @Override
    public void create(DocumentRequest request, MultipartFile image) throws IOException {
        if(request.getTitle()==null || request.getDescription()==null || request.getLink()==null) {
            throw new BadRequestException(400, "Please input full info!");
        }
        if(image==null) throw new BadRequestException(400, "Image is required!");

        Document document = new Document();
        document.setTitle(request.getTitle());
        document.setDescription(request.getDescription());
        document.setLink(request.getLink());
        document.setImage(ImageService.saveImage("images/document/", image));

        documentRepository.save(document);
    }

    @Override
    public void update(Long id, DocumentRequest request, MultipartFile image) throws IOException {
        Document document = documentRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Document is not found!");
        });

        if(request.getTitle()!=null) document.setTitle(request.getTitle());
        if(request.getDescription()!=null) document.setDescription(request.getDescription());
        if(request.getLink()!=null) document.setLink(request.getLink());
        if(image!=null) {
            ImageService.FileRemover("images/document/" + document.getImage());
            document.setImage(ImageService.saveImage("images/document/", image));
        }

        documentRepository.save(document);
    }

    @Override
    public void delete(Long id) {
        Document document = documentRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Document is not found!");
        });

        ImageService.FileRemover("images/document/" + document.getImage());
        documentRepository.delete(document);
    }

    @Override
    public byte[] getImage(Long id) throws IOException {
        Document document = documentRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Document is not found!");
        });

        return ImageService.getImage("images/document/" + document.getImage());
    }
}
