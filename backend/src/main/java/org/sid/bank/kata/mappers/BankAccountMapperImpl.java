package org.sid.bank.kata.mappers;

import org.sid.bank.kata.entities.BankAccount;
import org.sid.bank.kata.dtos.AccountOperationDTO;
import org.sid.bank.kata.dtos.BankAccountDTO;
import org.sid.bank.kata.entities.AccountOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class BankAccountMapperImpl {

    public BankAccountDTO fromBankAccount(BankAccount bankAccount){
        BankAccountDTO bankAccountDTO=new BankAccountDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountDTO);
        return bankAccountDTO;
    }

    public BankAccount fromBankAccountDTO(BankAccountDTO bankAccountDTO){
        BankAccount bankAccount=new BankAccount();
        BeanUtils.copyProperties(bankAccountDTO,bankAccount);
        return bankAccount;
    }


    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDTO accountOperationDTO=new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation,accountOperationDTO);
        return accountOperationDTO;
    }

}
