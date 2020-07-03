package com.eren.consumer.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.core.AbstractMessagingTemplate;
import org.springframework.stereotype.Service;

import com.eren.consumer.model.OrderRequest;
import com.eren.consumer.service.ConsumerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

	private final AbstractMessagingTemplate<String> messagingTemplate;

	@Value("${spring.cloud.stream.bindings.orderChannel.destination}")
	private String orderQueueName;

	@Value("${spring.cloud.stream.bindings.orderChannel.group}")
	private String orderQueueGroupName;

	@Override
	public void processPurchase(OrderRequest request) {
		try {
			Thread.sleep(1000);
			System.out.println("Order " + request.getId() + " proccessed");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String processDLQ() {
		Message<?> msg = receiveDLQ();
		int msgCount = 0;
		while (msg != null) {
			msgCount++;
			messagingTemplate.send(orderQueueName + "." + orderQueueGroupName, msg);
			msg = receiveDLQ();
		}
		return msgCount + " orders resent.";
	}

	private Message<?> receiveDLQ() {
		return messagingTemplate.receive(orderQueueName + "." + orderQueueGroupName + ".dlq");
	}

}
