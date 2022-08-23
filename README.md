# Spring Cloud Function Application

This is used to investigate deploying Spring Cloud Function application to Azure Spring Apps

# Run this application

This is a Spring boot application. You can run it locally. Then access endpoints by these commands:

1. Function
```shell
curl -s -d test -X POST http://localhost:8080/uppercase 
```

2. Consumer
```shell
curl -s -d test -X POST http://localhost:8080/consumer
```

3. Supplier
```shell
curl -s http://localhost:8080/supplier
```
