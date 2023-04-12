package org.sid.bank.kata.services;

import org.sid.bank.kata.dtos.AccountHistoryDTO;
import org.sid.bank.kata.dtos.AccountOperationDTO;
import org.sid.bank.kata.dtos.BankAccountDTO;
import org.sid.bank.kata.exceptions.BalanceNotSufficientException;
import org.sid.bank.kata.exceptions.BankAccountNotFoundException;

import java.util.List;
public interface BankAccountService {
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    BankAccountDTO debit(String accountId, double amount) throws BankAccountNotFoundException;
    BankAccountDTO credit(String accountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;
    List<BankAccountDTO> bankAccountList();
    List<AccountOperationDTO> accountHistory(String accountId);
    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
