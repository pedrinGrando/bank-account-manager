package com.pedro.accountsservice.resource;

import com.pedro.accountsservice.dto.AccountDTO;
import com.pedro.accountsservice.dto.DepositInputRequest;
import com.pedro.accountsservice.dto.WithdrawInputRequest;
import com.pedro.accountsservice.dto.TransferInputRequest;
import com.pedro.accountsservice.model.Account;
import com.pedro.accountsservice.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @param accDto the account to be created, passed in the request body.
     * @return a ResponseEntity indicating that the account was created with HTTP status 201 (Created).
     * @throws InterruptedException if the account creation process is interrupted.
     */
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO accDto) throws InterruptedException {
        accountsService.createAccount(accDto);
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
     * Request for deposit into any account.
     * @param request with request body.
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


    /**
     * Request for withdraw from any account.
     * @param request with request body.
     * @return a ResponseEntity containing request response.
     */
    @PutMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawInputRequest request) {

        if (accountsService.withdraw(request)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Request for transfer from any account to anyone.
     * @param request with request body.
     * @return a ResponseEntity containing request response.
     */
    @PutMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferInputRequest request){
        if (accountsService.transfer(request)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}





