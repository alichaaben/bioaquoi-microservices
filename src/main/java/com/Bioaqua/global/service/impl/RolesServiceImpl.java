package com.Bioaqua.global.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bioaqua.global.entity.Roles;
import com.Bioaqua.global.repository.RolesRepo;
import com.Bioaqua.global.service.RolesService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepo rolesRepo;

    @Override
    public Roles findById(Long id) {
        return rolesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Roles not found with ID: " + id));
    }

    @Override
    public List<Roles> findAll() {
        return rolesRepo.findAll();
    }

    @Override
    public Roles findByRoleName(String roleName) {
        return rolesRepo.findByRoleName(roleName);

    }


    @Override
    public Roles insert(Roles Entity) {
        return rolesRepo.save(Entity);
    }

    @Override
    public Roles update(Roles Entity) {
        Roles currentRole = rolesRepo.findById(Entity.getRoleId())
        .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        currentRole.setRoleName(Entity.getRoleName());
        
        return rolesRepo.save(currentRole);
    }

    @Override
    public void deleteById(Long id) {
        rolesRepo.deleteById(id);
    }

}
