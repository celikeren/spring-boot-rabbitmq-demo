package com.eren.producer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.eren.producer.model.OrderRequest;
import com.eren.producer.model.OrderResponse;
import com.eren.producer.service.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProducerServiceImpl implements ProducerService {

	@Autowired
	@Qualifier("messageChannel")
	private MessageChannel newOrder;

	@Override
	public OrderResponse createOrder(OrderRequest request) {
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Message<String> msg = MessageBuilder.withPayload(json).build();
		this.newOrder.send(msg);
		return new OrderResponse("Your order has been received");
	}

}
