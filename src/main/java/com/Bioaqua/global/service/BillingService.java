package com.Bioaqua.global.service;

import java.util.List;

import com.Bioaqua.global.entity.Billing;

public interface BillingService {
    Billing findById(Long id);

    List<Billing> findAll();

    Billing insert(Billing Entity);

    Billing update(Billing Entity);

    void deleteById(Long id);
}
