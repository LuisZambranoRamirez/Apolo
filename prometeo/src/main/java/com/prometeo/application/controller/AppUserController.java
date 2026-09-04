package com.prometeo.application.controller;

import com.prometeo.application.entity.AppUser;
import com.prometeo.application.service.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUser> getUsers() {
        return appUserService.findAll();
    }

    @GetMapping("/{username}")
    public AppUser getUser(
            @PathVariable String username
    ) {
        return appUserService.findByUsername(username);
    }

    @PostMapping
    public AppUser createUser(
            @RequestBody AppUser user
    ) {
        return appUserService.save(user);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(
            @PathVariable String username
    ) {
        appUserService.delete(username);
    }
}
