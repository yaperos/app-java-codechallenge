package com.yape.transactional.controller;

import com.yape.transactional.requestDTO.FinancialTransactionRequestDTO;
import com.yape.transactional.facade.TransactionalFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    private TransactionalFacade transactionalFacade;

    @PostMapping(value = "/financial")
    public ResponseEntity<String> sendTransaction(@RequestBody FinancialTransactionRequestDTO request){
        return this.transactionalFacade.sendTransaction(request);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("llamada satisfactoria", HttpStatus.OK);
    }
}
