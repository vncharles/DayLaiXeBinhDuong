package com.charles.website.services;

import com.charles.website.entity.SocialNetwork;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface SocialNetworkService {
    List<SocialNetwork> getListSocialNetwork();

    SocialNetwork getDetailSocialNetwork(Long id);
}
