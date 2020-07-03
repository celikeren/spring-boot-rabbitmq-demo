package com.eren.producer.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface NewOrderBinding {

	@Output("orderChannel")
	MessageChannel newOrder();

}
