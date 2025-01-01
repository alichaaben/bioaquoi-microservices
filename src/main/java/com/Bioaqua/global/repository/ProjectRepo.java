package com.Bioaqua.global.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Bioaqua.global.entity.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Long>{
    Project findByProjectName(String ProjectName);
}
