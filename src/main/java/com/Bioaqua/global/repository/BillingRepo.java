package com.Bioaqua.global.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Bioaqua.global.entity.Billing;

@Repository
public interface BillingRepo extends JpaRepository<Billing,Long> {

}
