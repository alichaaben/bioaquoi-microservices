package com.Bioaqua.global.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bioaqua.global.Exceptions.ResourceNotFoundException;

import com.Bioaqua.global.dto.UsersDto;
import com.Bioaqua.global.entity.Roles;
import com.Bioaqua.global.entity.Users;
import com.Bioaqua.global.mapper.UsersMapper;
import com.Bioaqua.global.repository.RolesRepo;
import com.Bioaqua.global.service.UsersService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private RolesRepo rolesRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> findById(@PathVariable Long id) {
        Users entity = usersService.findById(id);
        UsersDto userDto = usersMapper.map(entity);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping()
    public ResponseEntity<List<UsersDto>> findAll() {
        List<Users> entities = usersService.findAll();
        List<UsersDto> userDtos = usersMapper.map(entities);
        return ResponseEntity.ok(userDtos);
    }

    @PostMapping
    public ResponseEntity<UsersDto> createUsers(@RequestBody UsersDto usersDto) {

        Roles role = rolesRepo.findByRoleName(usersDto.getRoleName());
        if (role == null) {
            throw new ResourceNotFoundException("Role non trouvé avec le nom: " + usersDto.getRoleName());
        }


        Users users = usersMapper.unMap(usersDto);
        users.setRole(role);
        String hashedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(hashedPassword);

        
        
        Users savedUsers = usersService.insert(users);
        UsersDto savedUsersDto = usersMapper.map(savedUsers);
        return ResponseEntity.ok(savedUsersDto);
    }

    
    @PutMapping()
    public ResponseEntity<UsersDto> updateBilling(@RequestBody UsersDto usersDto) {
        Users existingUsers = usersService.findById(usersDto.getUserId());
        usersMapper.updateEntityFromDto(existingUsers, usersDto);
        Users updatedUsers = usersService.update(existingUsers);
        UsersDto updatedUsersDto = usersMapper.map(updatedUsers);
        return ResponseEntity.ok(updatedUsersDto);
    }



    @DeleteMapping("/{id}")
    public void deleteBilling(@PathVariable Long id) {
        Users user = usersService.findById(id);
        if (user == null) {
            throw new ResourceNotFoundException("user not found with ID: " + id);
        }

        usersService.deleteById(id);
    }

}
