package com.yape.transaction.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;

@Getter
@Setter
@Builder
public class ValueName {
    private  String name;
}
