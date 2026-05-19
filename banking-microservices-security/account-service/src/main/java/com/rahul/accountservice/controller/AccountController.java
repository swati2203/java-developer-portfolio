package com.rahul.accountservice.controller;
import com.rahul.accountservice.model.*;
import com.rahul.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
@RestController @RequestMapping("/api/accounts") @RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @PostMapping public ResponseEntity<Account> create(@RequestParam AccountType type, Principal p) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(p.getName(), type));
    }
    @GetMapping("/my") public ResponseEntity<List<Account>> myAccounts(Principal p) { return ResponseEntity.ok(accountService.getByUsername(p.getName())); }
    @GetMapping @PreAuthorize("hasRole('ADMIN')") public ResponseEntity<List<Account>> all() { return ResponseEntity.ok(accountService.getByUsername("*")); }
}
