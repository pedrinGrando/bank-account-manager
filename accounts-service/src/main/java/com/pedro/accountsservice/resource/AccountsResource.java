package com.pedro.accountsservice.resource;

import com.pedro.accountsservice.dto.DepositInputRequest;
import com.pedro.accountsservice.dto.WithdrawInputRequest;
import com.pedro.accountsservice.model.Account;
import com.pedro.accountsservice.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountsResource {

    /**
     * Service layer for account operations.
     * Responsible for the business logic related to accounts.
     */
    private final AccountsService accountsService;

    /**
     * Creates a new account.
     *
     * @param account the account to be created, passed in the request body.
     * @return a ResponseEntity indicating that the account was created with HTTP status 201 (Created).
     * @throws InterruptedException if the account creation process is interrupted.
     */
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account) throws InterruptedException {
        accountsService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Fetches all accounts.
     * @return a ResponseEntity containing a list of all accounts, with HTTP status 200 (OK).
     */
    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> activeAccounts = accountsService.findAll();
        return ResponseEntity.ok().body(activeAccounts);
    }

    /**
     * Requst for deposit into any account.
     * @return a ResponseEntity containing request response.
     */
    @PutMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositInputRequest request) {

        if (accountsService.deposit(request)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawInputRequest request) {

        if (accountsService.withdraw(request)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}





