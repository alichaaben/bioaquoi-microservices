package com.Bioaqua.global.dto;

import lombok.Data;

@Data
public class BillingDto {

    private Long billingId;
    private double totalAmount;
    private String projectName;
    private String userName;

}
