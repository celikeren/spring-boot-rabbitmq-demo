package com.eren.consumer.event;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.eren.consumer.model.OrderRequest;
import com.eren.consumer.service.ConsumerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@EnableBinding(NewOrderBinding.class)
@RequiredArgsConstructor
public class NewOrderListener {

	private final ConsumerService consumerService;

	@StreamListener(target = NewOrderBinding.ORDER_CHANNEL)
	public void processNewOrder(String msg) {

		if (Math.random() > 0.5) {
			throw new RuntimeException();
		}

		ObjectMapper mapper = new ObjectMapper();
		System.out.println("message from listener: " + msg);
		OrderRequest request = new OrderRequest();
		try {
			request = mapper.readValue(msg, OrderRequest.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		consumerService.processPurchase(request);
	}

}
