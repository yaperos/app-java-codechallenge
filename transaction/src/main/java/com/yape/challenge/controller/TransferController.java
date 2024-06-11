package com.yape.challenge.controller;

import com.yape.challenge.model.dtos.TransferDto;
import com.yape.challenge.model.dtos.TransferRptDto;
import com.yape.challenge.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    TransferService transferService;

    @GetMapping()
    public List<TransferRptDto> getTransfers() {

        return transferService.getTransfers();

    }

    @PostMapping
    public TransferDto saveTransfers(@RequestBody TransferDto transfer) {

        return transferService.saveTransfer(transfer);

    }
}
