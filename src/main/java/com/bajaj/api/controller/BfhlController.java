package com.bajaj.api.controller;

import com.bajaj.api.dto.BfhlRequest;
import com.bajaj.api.dto.BfhlResponse;
import com.bajaj.api.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponse> processData(@RequestBody(required = false) BfhlRequest request) {
        try {
            BfhlResponse response = bfhlService.processData(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            BfhlResponse errorResponse = new BfhlResponse();
            errorResponse.setIsSuccess(false);
            return ResponseEntity.ok(errorResponse);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<java.util.Map<String, String>> healthCheck() {
        return ResponseEntity.ok(java.util.Map.of("status", "UP"));
    }
}
