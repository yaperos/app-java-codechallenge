package com.yape.antifraud.usecases.in;

import com.yape.antifraud.entities.models.Transaction;
import com.yape.antifraud.usecases.models.AntifraudValidateModel;

public interface AntifraudValidateInputBoundary {
        Transaction antifraudValidate(AntifraudValidateModel antifraudValidateModel);
}
