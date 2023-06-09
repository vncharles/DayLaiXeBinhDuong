package com.charles.website.services.impl;

import com.charles.website.entity.Intro;
import com.charles.website.exception.BadRequestException;
import com.charles.website.repository.IntroRepository;
import com.charles.website.services.IntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IntroServiceImpl implements IntroService {
    @Autowired
    private IntroRepository introRepository;

    @Override
    public List<Intro> getListIntro() {
        return introRepository.findAll();
    }

    @Override
    public Intro getDetailIntro(Long id) {
        Intro intro = introRepository.findById(id).orElseThrow(() -> {
            throw new BadRequestException(400, "ID intro is not found");
        });

        return intro;
    }

    @Override
    public void add(String link) {
        if(link==null) throw new BadRequestException(400, "Link is required!");

        introRepository.save(new Intro(link));
    }

    @Override
    public void update(Long id, String link) {
        Intro intro = introRepository.findById(id).orElseThrow(() -> {
            throw new BadRequestException(400, "ID intro is not found");
        });

        if(link!=null) intro.setLink(link);
        intro.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
        introRepository.save(intro);
    }

    @Override
    public void delete(Long id) {
        Intro intro = introRepository.findById(id).orElseThrow(() -> {
            throw new BadRequestException(400, "ID intro is not found");
        });

        introRepository.delete(intro);
    }
}
