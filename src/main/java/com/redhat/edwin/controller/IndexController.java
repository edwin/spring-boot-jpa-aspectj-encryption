package com.redhat.edwin.controller;

import com.redhat.edwin.annotation.Decrypt;
import com.redhat.edwin.annotation.Encrypt;
import com.redhat.edwin.model.UserProfile;
import com.redhat.edwin.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 *     com.redhat.edwin.controller.IndexController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 04 Mei 2020 11:37
 */
@RestController
public class IndexController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping("/")
    @Decrypt(values = {"fullname", "address", "phonenumber"})
    public List<UserProfile> index() {
        return userProfileRepository.findAll();
    }

    @GetMapping("/{id}")
    @Decrypt(values = {"fullname", "address", "phonenumber"})
    public UserProfile get(@PathVariable Integer id) {
        return userProfileRepository.findById(id).orElse(new UserProfile());
    }

    @PostMapping("/save")
    @Encrypt(values = {"fullname", "address", "phonenumber"})
    public HashMap save(@RequestBody UserProfile userProfile) {
        userProfile.setCreatedDate(new Date());
        userProfileRepository.save(userProfile);

        return new HashMap() {{
            put("id", userProfile.getId());
        }};
    }
}
