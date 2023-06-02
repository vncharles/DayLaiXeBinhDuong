package com.charles.website.services.impl;

import com.charles.website.entity.Intro;
import com.charles.website.exception.BadRequestException;
import com.charles.website.repository.IntroRepository;
import com.charles.website.services.IntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Intro intro = introRepository.findById(id).get();
        if(intro==null) throw new BadRequestException(400, "Intro is not found");

        return intro;
    }
}
