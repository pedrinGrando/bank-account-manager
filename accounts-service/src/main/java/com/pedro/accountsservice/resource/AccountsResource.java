package com.pedro.accountsservice.resource;

import com.pedro.accountsservice.dto.*;
import com.pedro.accountsservice.model.Account;
import com.pedro.accountsservice.service.AccountsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountsResource {

    private final AccountsService accountsService;

    /**
     * Creates a new account.
     *
     * @param accDto the account to be created, passed in the request body.
     * @return a ResponseEntity indicating that the account was created with HTTP status 201 (Created).
     * @throws InterruptedException if the account creation process is interrupted.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequestDTO accDto) throws InterruptedException {
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
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deposited R$ " + request.getValue()
                    + " successfully for: "
                    + request.getAccountNumber());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deposit not accepted.");
        }
    }

    @GetMapping("/findByName/{accountHolder}")
    public ResponseEntity<Account> getByAccountHolder(@PathVariable String accountHolder) {
        if (accountsService.findByName(accountHolder).isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountsService.findByName(accountHolder).get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Withdraw R$ " + request.getValue()
                    + " successfully from: "
                    + request.getAccountNumber());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Withdraw not accepted.");
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
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Transfer R$ " + request.getValue()
                    + " successfully for: "
                    + request.getAccountNumber());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transfer not accepted.");
        }
    }

    @Transactional
    @DeleteMapping("/clean-nactive-accounts")
    public ResponseEntity<?> cleanNactiveAccounts() {
        accountsService.cleanInactiveAccounts();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Inactive accounts cleaned.");
    }

    @PostMapping("/request-loan")
    public ResponseEntity<?> requestLoan(@RequestBody LoanRequestDTO loan) {
        if (accountsService.requestLoan(loan)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Loan request approved R$ " + loan.getValue()
                    + " successfully for: "
                    + loan.getAccountNumber());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Loan request not accepted.");
        }

    }

}





