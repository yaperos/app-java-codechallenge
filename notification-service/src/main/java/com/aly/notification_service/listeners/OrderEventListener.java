package com.aly.notification_service.listeners;

import com.aly.notification_service.events.OrderEvent;
import com.aly.notification_service.util.JsonTransferUtil;
import com.aly.notification_service.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventListener {

    @KafkaListener(topics = "orders-topic")
    public void handleOrdersNotifications(String message) {
        var orderEvent = JsonUtils.fromJson(message, OrderEvent.class);
        log.info("orderEvent {} : " ,  JsonTransferUtil.objectToJson(orderEvent));
    }
}
