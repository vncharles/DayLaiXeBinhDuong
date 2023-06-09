package com.charles.website.services;

import com.charles.website.entity.Intro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntroService {
    List<Intro> getListIntro();

    Intro getDetailIntro(Long id);

    void add(String link);

    void update(Long id, String link);

    void delete(Long id);
}
