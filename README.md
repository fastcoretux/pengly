# Peng.ly
Example application which simply emulates bit.ly.

It was created just for demonstration purposes how easily you can use Spring Boot
with Spring Web MVC, Spring Data JPA, embedded HSQL database and Spring integration testing.

### Run on local
* IntelliJ IDEA: Go to `AppBoot` class _(right click on class, select Create 'AppBoot')_
* maven: mvn spring-boot:run

### Run on Docker
Prepare Docker image
```
docker build -t fascoretux/pengly .
```

Run Docker image
```
docker run -p 8080:8080 fastcoretux/pengly
```

### Run on Azure
Deploy on Azure as App Service using [azure-webapp-maven-plugin](https://github.com/microsoft/azure-maven-plugins/blob/develop/azure-webapp-maven-plugin/README.md).
```
az login
mvn azure-webapp:deploy
```

### Usage
You can create link alias by calling
```
POST http://localhost:8080/rest/link/v1
```
Request body
```
{
  "url":"https://www.google.com",
  "maxCount":3
}
```
Than alias UUID will be returned
```
ca38adff-9d70-48c1-ba5e-8b2a09a8169b
```

Alias could be obtained by
```
GET http://localhost:8080/rest/link/v1/ca38adff-9d70-48c1-ba5e-8b2a09a8169b
```

or used for redirect in web browser
```
http://localhost:8080/pgly/inf
```