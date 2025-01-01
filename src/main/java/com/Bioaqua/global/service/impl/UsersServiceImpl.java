package com.Bioaqua.global.service.impl;

import java.util.List;

import com.Bioaqua.global.Exceptions.InvalidEntityException;
import com.Bioaqua.global.entity.Users;
import com.Bioaqua.global.repository.UsersRepo;
import com.Bioaqua.global.service.UsersService;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService{

    private final UsersRepo usersRepo;

    @Override
    public Users findById(Long id) {
        return usersRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    @Override
    public List<Users> findAll() {
        return usersRepo.findAll();
    }
    
    @Override
    public Users findByUserName(String userName) {
        return usersRepo.findByUserName(userName);
    }

    @Override
    public Users insert(Users entity) {
        if (entity.getUserName() == null || entity.getUserName().isEmpty()) {
            throw new InvalidEntityException("Username cannot be empty.");
        }
        return usersRepo.save(entity);
    }

    @Override
    public Users update(Users Entity) {
        Users currentUser = usersRepo.findById(Entity.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

        currentUser.setUserName(Entity.getUserName());
        currentUser.setEmail(Entity.getEmail());
        currentUser.setPassword(Entity.getPassword());
        
        return usersRepo.save(currentUser);
    }

    @Override
    public void deleteById(Long id) {
        usersRepo.deleteById(id);
    }



}
