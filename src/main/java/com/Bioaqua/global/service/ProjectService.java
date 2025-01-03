package com.Bioaqua.global.service;

import java.util.List;

import com.Bioaqua.global.entity.Project;

public interface ProjectService {
    Project findById(Long id);

    List<Project> findAll();

    Project findByProjectName(String ProjectName);

    Project insert(Project Entity);

    Project update(Project Entity);

    void deleteById(Long id);
}