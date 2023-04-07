package com.berat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/authservice")
    public ResponseEntity<String> authServiceFallback(){
        return ResponseEntity.ok("Auth service is currently unavailable");
    }
    @GetMapping("/accountservice")
    public ResponseEntity<String> accountServiceFallback(){
        return ResponseEntity.ok("Account service is currently unavailable");
    }
    @GetMapping("/cardservice")
    public ResponseEntity<String> cardServiceFallback(){
        return ResponseEntity.ok("Card service is currently unavailable");
    }
    @GetMapping("/customerservice")
    public ResponseEntity<String> customerServiceFallback(){
        return ResponseEntity.ok("Customer service is currently unavailable");
    }
    @GetMapping("/transactionservice")
    public ResponseEntity<String> transactionServiceFallback(){
        return ResponseEntity.ok("Transaction service is currently unavailable");
    }
}
