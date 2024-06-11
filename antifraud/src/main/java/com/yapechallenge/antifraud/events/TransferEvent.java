package com.yapechallenge.antifraud.events;

import com.yapechallenge.antifraud.model.enums.TransferStatus;

public record TransferEvent(Long transferId, double amount, TransferStatus transferStatus) {}