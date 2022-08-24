# Spring Cloud Function Application

This is used to investigate deploying Spring Cloud Function application to Azure Spring Apps

# Run this application

## Run this application in localhost

This is a Spring boot application. You can run it locally. Then access endpoints by these commands:

1. Supplier
```shell
curl -s http://localhost:8080/supplier
```

2. Function
```shell
curl -s -d 'testValue' -H "Content-Type: text/plain" -X POST http://localhost:8080/uppercase 
```

3. Consumer
```shell
curl -s -d 'testValue' -H "Content-Type: text/plain" -X POST http://localhost:8080/consumer
```

4. Read blob
```shell
curl -s -H "Content-Type: text/plain" http://localhost:8080/readBlob
```

5. Write blob
```shell
timestamp=$(date +%T)
echo "timestamp=$timestamp"
curl -s -d "timestamp=$timestamp" -H "Content-Type: text/plain" http://localhost:8080/writeBlob
```


## Run this application in Azure Spring Apps

Refer to [this quickstart](https://docs.microsoft.com/azure/spring-apps/quickstart?tabs=Azure-CLI&pivots=programming-language-java) to run this application in Azure Spring Apps.
