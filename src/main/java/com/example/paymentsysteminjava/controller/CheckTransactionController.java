package com.example.paymentsysteminjava.controller;

import com.example.paymentsysteminjava.dto.request.agent.DefaultAgentRequest;
import com.example.paymentsysteminjava.dto.response.agent.BaseAgentResponse;
import com.example.paymentsysteminjava.service.transaction.CheckTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class CheckTransactionController {

    @Autowired
    private CheckTransactionService checkTransactionService;

    @PreAuthorize("hasRole('ROLE_AGENT')")
    @PostMapping("/check")
    public BaseAgentResponse checkTransaction(
            @RequestBody DefaultAgentRequest baseAgentRequest,
            @AuthenticationPrincipal String username
    ) {
        return checkTransactionService.check(baseAgentRequest.getDefaultAgentRequest(), username);
    }
}
