package org.sid.bank.kata;

import org.sid.bank.kata.entities.AccountOperation;
import org.sid.bank.kata.entities.BankAccount;
import org.sid.bank.kata.enums.OperationType;
import org.sid.bank.kata.repositories.AccountOperationRepository;
import org.sid.bank.kata.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class BankKataApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankKataApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){
        return args -> {

                BankAccount account1=new BankAccount();
                account1.setId("123456");
                account1.setBalance(Math.random()*90000);
                account1.setCreatedAt(new Date());
                System.out.println("account1.getId(): "+account1.getId());
                bankAccountRepository.save(account1);

               bankAccountRepository.findAll().forEach(acc->{
                for (int i = 0; i <10 ; i++) {
                    AccountOperation accountOperation=new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*12000);
                    accountOperation.setType(Math.random()>0.5 ? OperationType.DEBIT: OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }
            });
        };

    }

}
