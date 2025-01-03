package com.Bioaqua.global.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bioaqua.global.entity.Project;
import com.Bioaqua.global.repository.ProjectRepo;
import com.Bioaqua.global.service.ProjectService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepo projectRepo;

   @Override
    public Project findById(Long id) {
        return projectRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + id));
    }

    @Override
    public List<Project> findAll() {
        return projectRepo.findAll();
    }
    
    @Override
    public Project findByProjectName(String ProjectName) {
        return projectRepo.findByProjectName(ProjectName);
    }

    @Override
    public Project insert(Project entity) {
        // if (entity.getUserName() == null || entity.getUserName().isEmpty()) {
        //     throw new InvalidEntityException("Username cannot be empty.");
        // }
        return projectRepo.save(entity);
    }

    @Override
    public Project update(Project Entity) {
        Project currentProject = projectRepo.findById(Entity.getProjectId())
        .orElseThrow(() -> new IllegalArgumentException("Billing not found"));

        currentProject.setProjectName(Entity.getProjectName());
        currentProject.setStatus(Entity.getStatus());
        
        return projectRepo.save(currentProject);
    }

    @Override
    public void deleteById(Long id) {
        projectRepo.deleteById(id);
    }


}