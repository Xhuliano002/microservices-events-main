package com.xhmata.user.service;

import com.xhmata.user.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findByUsername(String username);

    List<String> findUsers(List<Long> idList);

}
