package org.sid.bank.kata.repositories;

import org.sid.bank.kata.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
