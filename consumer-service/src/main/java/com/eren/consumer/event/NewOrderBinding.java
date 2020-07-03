package com.eren.consumer.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface NewOrderBinding {

	String ORDER_CHANNEL = "orderChannel";

	@Input(ORDER_CHANNEL)
	SubscribableChannel processOrder();

}
