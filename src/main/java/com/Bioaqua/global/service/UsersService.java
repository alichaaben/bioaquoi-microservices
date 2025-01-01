package com.Bioaqua.global.service;

import java.util.List;

import com.Bioaqua.global.entity.Users;

public interface UsersService {
    Users findById(Long id);

    List<Users> findAll();

    Users findByUserName(String userName);

    Users insert(Users Entity);

    Users update(Users Entity);

    void deleteById(Long id);

}
