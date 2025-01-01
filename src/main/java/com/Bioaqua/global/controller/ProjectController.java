package com.Bioaqua.global.controller;

import com.Bioaqua.global.Exceptions.ResourceNotFoundException;
import com.Bioaqua.global.dto.ProjectDto;
import com.Bioaqua.global.entity.Project;
import com.Bioaqua.global.entity.Users;
import com.Bioaqua.global.mapper.ProjectMapper;
import com.Bioaqua.global.repository.UsersRepo;
import com.Bioaqua.global.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final UsersRepo usersRepo;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<Project> projects = projectService.findAll();
        List<ProjectDto> projectDtos = projectMapper.map(projects);
        return ResponseEntity.ok(projectDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        Project project = projectService.findById(id);
        ProjectDto projectDto = projectMapper.map(project);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping()
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        Users user = usersRepo.findByUserName(projectDto.getUserName());
        if (user == null) {
            throw new ResourceNotFoundException("User not found with username: " + projectDto.getUserName());
        }
        Project project = projectMapper.unMap(projectDto);
        project.setUser(user);

        Project savedProject = projectService.insert(project);
        ProjectDto savedProjectDto = projectMapper.map(savedProject);
        return ResponseEntity.ok(savedProjectDto);
    }


    @PutMapping()
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto) {
        Project existingProject = projectService.findById(projectDto.getProjectId());
        projectMapper.updateEntityFromDto(existingProject, projectDto);
        Project updatedProject = projectService.update(existingProject);
        ProjectDto updatedProjectDto = projectMapper.map(updatedProject);
        return ResponseEntity.ok(updatedProjectDto);
    }


    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        Project project = projectService.findById(id);
        if (project == null) {
            throw new ResourceNotFoundException("project not found with ID: " + id);
        }
        projectService.deleteById(id);
    }
}