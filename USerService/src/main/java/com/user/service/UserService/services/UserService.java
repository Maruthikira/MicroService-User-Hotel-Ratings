package com.user.service.UserService.services;

import com.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUSer();
    User getUser(String userId);
    //TODO : delete and update

}
