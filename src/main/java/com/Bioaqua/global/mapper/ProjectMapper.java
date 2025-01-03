package com.Bioaqua.global.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.Bioaqua.global.dto.ProjectDto;
import com.Bioaqua.global.entity.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(source = "projectName", target = "projectName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "user.userName", target = "userName")
    ProjectDto map(Project entity);

    List<ProjectDto> map(List<Project> entities);

    @Mapping(source = "projectName", target = "projectName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "userName", target = "user.userName")
    @Mapping(target = "components", ignore = true)
    @Mapping(target = "billings", ignore = true)
    Project unMap(ProjectDto dto);

    @Mapping(source = "projectName", target = "projectName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "userName", target = "user.userName")
    @Mapping(target = "components", ignore = true)
    @Mapping(target = "billings", ignore = true) 
    void updateEntityFromDto(@MappingTarget Project entity, ProjectDto dto);

}