package com.eren.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

import com.eren.producer.event.NewOrderBinding;

@Configuration
public class ApplicationConfig {

	@Bean
	public MessageChannel messageChannel(NewOrderBinding binding) {
		MessageChannel newOrder = binding.newOrder();
		return newOrder;
	}

}
