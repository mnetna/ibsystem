package com.slee.web.common.controller;

import com.slee.web.jpa.entity.auth.RefreshToken;
import com.slee.web.jpa.repository.auth.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final RefreshTokenRepository refreshTokenRepository;
    private final EntityManager entityManager;

    @Transactional
    @GetMapping("/insert/{key}")
    public void insert(@PathVariable("key") String key) {
//        RefreshToken refreshToken = refreshTokenRepository.findById("TEST").get();
//        refreshToken.setValue("12345");

        // insert
        RefreshToken insertRefreshToken = new RefreshToken();
        insertRefreshToken.setKey(key);
        insertRefreshToken.setValue("55555");

//        refreshTokenRepository.save(insertRefreshToken);

        entityManager.persist(insertRefreshToken);
    }

    @Transactional
    @GetMapping("/update/{key}")
    public void update(@PathVariable("key") String key) {
        RefreshToken refreshToken = refreshTokenRepository.findById(key).get();
        refreshToken.setValue("12345");

        // update
//        RefreshToken updateRefreshToken = new RefreshToken();
//        updateRefreshToken.setKey(key);
//        updateRefreshToken.setValue("12345");

//        refreshTokenRepository.save(insertRefreshToken);

        entityManager.merge(refreshToken);
    }
}
