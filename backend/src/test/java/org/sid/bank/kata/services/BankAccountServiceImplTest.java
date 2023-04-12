package org.sid.bank.kata.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.sid.bank.kata.dtos.BankAccountDTO;
import org.sid.bank.kata.entities.AccountOperation;
import org.sid.bank.kata.entities.BankAccount;
import org.sid.bank.kata.enums.OperationType;
import org.sid.bank.kata.exceptions.BalanceNotSufficientException;
import org.sid.bank.kata.exceptions.BankAccountNotFoundException;
import org.sid.bank.kata.mappers.BankAccountMapperImpl;
import org.sid.bank.kata.repositories.AccountOperationRepository;
import org.sid.bank.kata.repositories.BankAccountRepository;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class BankAccountServiceImplTest {

    @InjectMocks
    BankAccountServiceImpl bankAccountService;

    @Mock
    BankAccountRepository bankAccountRepository;

    @Mock
    AccountOperationRepository accountOperationRepository;

    private BankAccountMapperImpl bankAccountMapper = new BankAccountMapperImpl();

    @BeforeEach
    void setup() {
        bankAccountService = new BankAccountServiceImpl(bankAccountMapper,
                                                        bankAccountRepository,
                                                        accountOperationRepository);
    }

    @Test
    void getBankAccount() throws BankAccountNotFoundException {
        String accountId = "123456";
        BankAccount bankAccount = new BankAccount("123456",120,new Date(), null);
        when(bankAccountRepository.findById(accountId)).thenReturn(Optional.of(bankAccount));
        BankAccountDTO result = bankAccountService.getBankAccount(accountId);
        assertThat(bankAccount.getId()).isEqualTo(result.getId());
        assertThat(bankAccount.getBalance()).isEqualTo(result.getBalance());
        assertThat(bankAccount.getCreatedAt()).isEqualTo(result.getCreatedAt());
    }

    @Test
    void debit() throws BankAccountNotFoundException {
        String accountId = "123456";
        double amount = 10.0;
        BankAccount bankAccount = new BankAccount("123456",120,new Date(), null);

        when(bankAccountRepository.findById(anyString())).thenReturn(Optional.of(bankAccount));
        when(accountOperationRepository.save(any())).thenReturn(any());
        when(bankAccountRepository.save(bankAccount)).thenReturn(bankAccount);

        BankAccountDTO result = bankAccountService.debit(accountId, amount);
        assertThat(bankAccount.getBalance()).isEqualTo(result.getBalance());
    }

    @Test
    void credit() throws BankAccountNotFoundException, BalanceNotSufficientException {
        String accountId = "123456";
        double amount = 10.0;
        BankAccount bankAccount = new BankAccount("123456",120,new Date(), null);

        when(bankAccountRepository.findById(anyString())).thenReturn(Optional.of(bankAccount));
        when(accountOperationRepository.save(any())).thenReturn(any());
        when(bankAccountRepository.save(bankAccount)).thenReturn(bankAccount);

        BankAccountDTO result = bankAccountService.credit(accountId, amount);
        assertThat(bankAccount.getBalance()).isEqualTo(result.getBalance());
    }

    @Test
    void credit_should_throw_exception() throws BankAccountNotFoundException, BalanceNotSufficientException {
        String accountId = "123456";
        double amount = 200.0;
        BankAccount bankAccount = new BankAccount("123456",120,new Date(), null);

        when(bankAccountRepository.findById(anyString())).thenReturn(Optional.of(bankAccount));
        when(accountOperationRepository.save(any())).thenReturn(any());
        when(bankAccountRepository.save(bankAccount)).thenReturn(bankAccount);

        Assertions.assertThrows(BalanceNotSufficientException.class, () -> {
            bankAccountService.credit(accountId, amount);
        });
    }
}