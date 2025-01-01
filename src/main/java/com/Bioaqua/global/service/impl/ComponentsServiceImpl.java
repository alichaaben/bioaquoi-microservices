package com.Bioaqua.global.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bioaqua.global.entity.Components;
import com.Bioaqua.global.repository.ComponentsRepo;
import com.Bioaqua.global.service.ComponentsService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ComponentsServiceImpl implements ComponentsService {

    @Autowired
    private ComponentsRepo componentsRepo;

    @Override
    public Components findById(Long id) {
        return componentsRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Material not found with ID: " + id));
    }

    @Override
    public List<Components> findAll() {
        return componentsRepo.findAll();
    }

    @Override
    public Components insert(Components Entity) {
        return componentsRepo.save(Entity);
    }

    @Override
    public Components update(Components Entity) {
        Components currentMatiral = componentsRepo.findById(Entity.getMaterialId())
                .orElseThrow(() -> new IllegalArgumentException("Matirial not found"));

        currentMatiral.setUsageQuantity(Entity.getUsageQuantity());
        currentMatiral.setQuantityInStock(Entity.getQuantityInStock());

        return componentsRepo.save(currentMatiral);
    }

    @Override
    public void deleteById(Long id) {
        Components Material = componentsRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Matirial not found with ID: " + id));
        componentsRepo.delete(Material);
    }

}
