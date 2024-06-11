package com.yape.challenge.events;

import com.yape.challenge.model.enums.TransferStatus;

public record TransferEvent(Long transferId, double amount, TransferStatus transferStatus) {}