package com.pedro.accountsservice.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/healthcheck")
public class Healthcheck {

    @GetMapping
    public ResponseEntity<Boolean> healthcheck() {
        return ResponseEntity.ok().body(true);
    }
}
