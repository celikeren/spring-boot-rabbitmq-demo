package com.eren.producer.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eren.producer.model.OrderRequest;
import com.eren.producer.model.OrderResponse;
import com.eren.producer.service.ProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProducerController {

	private final ProducerService producerService;

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	OrderResponse createOrder(@RequestBody OrderRequest request) {
		return producerService.createOrder(request);
	}

}
