package com.Bioaqua.global.controller;

import com.Bioaqua.global.Exceptions.ResourceNotFoundException;
import com.Bioaqua.global.dto.BillingDto;
import com.Bioaqua.global.entity.Billing;
import com.Bioaqua.global.entity.Project;
import com.Bioaqua.global.entity.Users;
import com.Bioaqua.global.mapper.BillingMapper;
import com.Bioaqua.global.repository.ProjectRepo;
import com.Bioaqua.global.repository.UsersRepo;
import com.Bioaqua.global.service.BillingService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billings")
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;
    private final BillingMapper billingMapper;
    private final ProjectRepo projectRepo;
    private final UsersRepo usersRepo;

    @GetMapping
    public ResponseEntity<List<BillingDto>> getAllBillings() {
        List<Billing> billings = billingService.findAll();
        List<BillingDto> billingDtos = billingMapper.map(billings);
        return ResponseEntity.ok(billingDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillingDto> getBillingById(@PathVariable Long id) {
        Billing billing = billingService.findById(id);
        BillingDto billingDto = billingMapper.map(billing);
        return ResponseEntity.ok(billingDto);
    }

    @PostMapping
    public ResponseEntity<BillingDto> createBilling(@RequestBody BillingDto billingDto) {
        Project Project = projectRepo.findByProjectName(billingDto.getProjectName());
        if (Project == null) {
            throw new ResourceNotFoundException("Projet non trouvé avec le nom: " + billingDto.getProjectName());
        }

        Users user = usersRepo.findByUserName(billingDto.getUserName());
        if (user == null) {
            throw new ResourceNotFoundException("user non trouvé avec le nom: " + billingDto.getUserName());
        }

        Billing billing = billingMapper.unMap(billingDto);
        billing.setProject(Project);
        billing.setUser(user);

        Billing savedBilling = billingService.insert(billing);
        BillingDto savedBillingDto = billingMapper.map(savedBilling);
        return ResponseEntity.ok(savedBillingDto);
    }

    @PutMapping()
    public ResponseEntity<BillingDto> updateBilling(@RequestBody BillingDto billingDto) {
        Billing existingBilling = billingService.findById(billingDto.getBillingId());
        billingMapper.updateEntityFromDto(existingBilling, billingDto);
        Billing updatedBilling = billingService.update(existingBilling);
        BillingDto updatedBillingDto = billingMapper.map(updatedBilling);
        return ResponseEntity.ok(updatedBillingDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBilling(@PathVariable Long id) {
        Billing billing = billingService.findById(id);
        if (billing == null) {
            throw new ResourceNotFoundException("billing not found with ID: " + id);
        }

        billingService.deleteById(id);
    }
}
