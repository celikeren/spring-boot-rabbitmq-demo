# Messaging with RabbitMQ in Spring Boot
This is a demo for messaging between two microservices with RabbitMQ in Spring Boot.

## How To Run
- First run this command to run rabbitmq locally.
```cmd
docker run -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

- Then run producer-service and consumer-service.

- producer-service runs on :8080 and consumer-service on :8081.

- You can test services via Swagger. 
  - [Producer Service Swagger](http://localhost:8080/swagger-ui.html)
  - [Consumer Service Swagger](http://localhost:8081/swagger-ui.html)

- When you send **POST** request to producer-service's **"/create"** endpoint with a request body including only an **id**  in JSON, order will be sent to queue.

  - POST
  - http://localhost:8080/create
  - {"id": "1"}

- You can view RabbitMQ interactions on [RabbitMQ's management port](http://localhost:15672). Default username and password are both **guest**.

- Some messages will give error to demonstrate dead letter queue concept.

- Queues part in RabbitMQ UI will list two queues. **order.order-group** is the main queue. If there's an error when processing message, message will be sent to **order.order-group.dlq**.

- When you send a GET request to "/process-dlq" ednpoint of consumer service, messages in dlq will be processed again.
  - GET
  - http://localhost:8081/process-dlq
  
## In Detail

- Objects are sent as String after being converted to JSON.

- Group declaration is important when you have more than one instance of consumer-service. If you don't declare a group in that case, your messages may be processed more than one time. But if you declare the group, all messages are processed once. For example if there are 5 messages in queue and 4 instances of consumer-service, 4 messages will be processed at the same time and 1 will wait until a consumer is idle.
```yml
spring.cloud.stream.bindings.orderChannel.group : order-group
```

- You can specify the number of retries when there's an error while processing before message is sent to dlq.
```yml
#spring.cloud.stream.bindings.orderChannel.consumer.max-attempts: 2
```

- 