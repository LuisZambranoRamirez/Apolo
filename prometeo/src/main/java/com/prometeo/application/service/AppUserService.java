package com.prometeo.application.service;

import com.prometeo.application.entity.AppUser;
import com.prometeo.application.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public AppUser findByUsername(String username) {
        return appUserRepository.findById(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );
    }

    public AppUser save(AppUser user) {
        return appUserRepository.save(user);
    }

    public void delete(String username) {
        appUserRepository.deleteById(username);
    }
}
