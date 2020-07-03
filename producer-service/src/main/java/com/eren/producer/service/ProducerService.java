package com.eren.producer.service;

import com.eren.producer.model.OrderRequest;
import com.eren.producer.model.OrderResponse;

public interface ProducerService {

	OrderResponse createOrder(OrderRequest request);

}
