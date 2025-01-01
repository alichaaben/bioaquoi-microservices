package com.Bioaqua.global.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.Bioaqua.global.dto.UsersDto;
import com.Bioaqua.global.entity.Users;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role.roleName", target = "roleName")
    UsersDto map(Users entity);

    
    List<UsersDto> map(List<Users> entities);

    
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "roleName", target = "role", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "billings", ignore = true)
    Users unMap(UsersDto dto);

    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "roleName", target = "role", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "billings", ignore = true)
    void updateEntityFromDto(@MappingTarget Users entity, UsersDto dto);

}
