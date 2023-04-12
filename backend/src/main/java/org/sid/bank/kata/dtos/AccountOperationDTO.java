package org.sid.bank.kata.dtos;

import lombok.Data;
import org.sid.bank.kata.enums.OperationType;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}

