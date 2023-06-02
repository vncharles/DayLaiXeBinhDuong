package com.charles.website.services.impl;

import com.charles.website.entity.SocialNetwork;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.repository.SocialNeworkRepository;
import com.charles.website.services.SocialNetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialNetworkServiceImpl implements SocialNetworkService {
    @Autowired
    private SocialNeworkRepository socialNeworkRepository;

    @Override
    public List<SocialNetwork> getListSocialNetwork() {
        return socialNeworkRepository.findAll();
    }

    @Override
    public SocialNetwork getDetailSocialNetwork(Long id) {
        SocialNetwork socialNetwork = null;
        try {
            socialNetwork = socialNeworkRepository.findById(id).get();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(400, "Social network is not found!");
        }

        return socialNetwork;
    }
}
