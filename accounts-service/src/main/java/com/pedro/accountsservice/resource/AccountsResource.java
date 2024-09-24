package com.pedro.accountsservice.resource;

import com.pedro.accountsservice.model.Account;
import com.pedro.accountsservice.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts/transactions")
public class AccountsResource {

    /**
     * Endpoint for create an account
     */

    private final AccountsService accountsService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        //return accountsService.createAccount(account);
        return null;
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        return null;
    }
}
