package com.eren.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eren.consumer.service.ConsumerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ConsumerController {
	
	private final ConsumerService consumerService;
	
	@GetMapping(value = "/process-dlq")
	String processDLQ() {
		return consumerService.processDLQ();
	}
}
