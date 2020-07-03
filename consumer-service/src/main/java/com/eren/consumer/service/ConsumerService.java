package com.eren.consumer.service;

import com.eren.consumer.model.OrderRequest;

public interface ConsumerService {

	void processPurchase(OrderRequest request);

	String processDLQ();

}
