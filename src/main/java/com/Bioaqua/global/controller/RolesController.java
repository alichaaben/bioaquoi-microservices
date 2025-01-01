package com.Bioaqua.global.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Bioaqua.global.Exceptions.ResourceNotFoundException;
import com.Bioaqua.global.dto.RolesDto;
import com.Bioaqua.global.entity.Roles;
import com.Bioaqua.global.mapper.RolesMapper;
import com.Bioaqua.global.service.RolesService;

@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
public class RolesController {

    @Autowired
    private RolesService rolesService;
    @Autowired
    private RolesMapper rolesMapper;

    @GetMapping("/{id}")
    public ResponseEntity<RolesDto> findById(@PathVariable Long id) {
        Roles entity = rolesService.findById(id);
        RolesDto roleDto = rolesMapper.map(entity);
        return ResponseEntity.ok(roleDto);
    }

    @GetMapping()
    public ResponseEntity<List<RolesDto>> findAll() {
        List<Roles> entities = rolesService.findAll();
        List<RolesDto> roleDto = rolesMapper.map(entities);
        return ResponseEntity.ok(roleDto);
    }

    @PostMapping
public ResponseEntity<RolesDto> insert(@RequestBody RolesDto dto) {

    if (dto.getRoleName() == null || dto.getRoleName().isBlank()) {
        throw new IllegalArgumentException("Role name cannot be null or empty.");
    }

    String roleName = "ROLE_" + dto.getRoleName().trim();
    Roles role = rolesMapper.unMap(dto);
    role.setRoleName(roleName);
    Roles entity = rolesService.insert(role);
    RolesDto responseDto = rolesMapper.map(entity);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
}

    

    @PutMapping()
    public ResponseEntity<RolesDto> update(@RequestBody RolesDto dto) {

        Roles currentRoles = rolesService.findById(dto.getRoleId());
        rolesMapper.updateEntityFromDto(currentRoles, dto);
        Roles updatedRoles = rolesService.update(currentRoles);
        RolesDto responseDto = rolesMapper.map(updatedRoles);

        return ResponseEntity.ok(responseDto);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        Roles roles = rolesService.findById(id);
        if (roles == null) {
            throw new ResourceNotFoundException("Roles not found with ID: " + id);
        }
        rolesService.deleteById(id);
    }


}
