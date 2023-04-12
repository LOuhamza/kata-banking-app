package org.sid.bank.kata.dtos;

import lombok.Data;

@Data
public class DebitDTO {
    private String accountId;
    private double amount;
}
