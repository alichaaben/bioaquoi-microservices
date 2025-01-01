package com.Bioaqua.global.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bioaqua.global.entity.Billing;
import com.Bioaqua.global.repository.BillingRepo;
import com.Bioaqua.global.service.BillingService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BillingServiceImpl implements BillingService {
    @Autowired
    private BillingRepo billingRepo;

   @Override
    public Billing findById(Long id) {
        return billingRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Billing not found with ID: " + id));
    }

    @Override
    public List<Billing> findAll() {
        return billingRepo.findAll();
    }
    
    @Override
    public Billing insert(Billing entity) {
        // if (entity.getUserName() == null || entity.getUserName().isEmpty()) {
        //     throw new InvalidEntityException("Username cannot be empty.");
        // }
        return billingRepo.save(entity);
    }

    @Override
    public Billing update(Billing Entity) {
        Billing currentBilling = billingRepo.findById(Entity.getBillingId())
        .orElseThrow(() -> new IllegalArgumentException("Billing not found"));

        currentBilling.setTotalAmount(Entity.getTotalAmount());
        
        return billingRepo.save(currentBilling);
    }

    @Override
    public void deleteById(Long id) {
        Billing billing = billingRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Billing not found with ID: " + id));
        billingRepo.delete(billing);
    }
    


}
