package com.Bioaqua.global.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bioaqua.global.entity.Users;

public interface UsersRepo extends JpaRepository<Users,Long>{
    Users findByUserName(String userName);
}
