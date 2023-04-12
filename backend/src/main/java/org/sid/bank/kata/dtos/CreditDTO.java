package org.sid.bank.kata.dtos;

import lombok.Data;

@Data
public class CreditDTO {
    private String accountId;
    private double amount;
}
