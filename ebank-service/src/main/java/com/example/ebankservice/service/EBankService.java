package com.example.ebankservice.service;

import com.example.ebankservice.entities.BankAccount;
import com.example.ebankservice.feign.CustomerRestClient;
import com.example.ebankservice.model.Customer;
import com.example.ebankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EBankService {
    private final BankAccountRepository accountRepository;
    private final CustomerRestClient customerRestClient;

    public EBankService(
            BankAccountRepository bankAccountRepository,
            CustomerRestClient customerRestClient) {

        this.accountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }
    public List<BankAccount> getAllBankAccounts() {
        return accountRepository.findAll();
    }

    public BankAccount getBankAccountById(String id) {
        BankAccount bankAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        bankAccount.setCustomer(
                customerRestClient.getCustomerById(bankAccount.getCustomerId()));
 return bankAccount;
    }


    public BankAccount save(BankAccount bankAccount) {
        try {
            Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
            return accountRepository.save(bankAccount);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}