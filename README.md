# Spring boot project to manage Customer and Address

[![Build Status](https://travis-ci.com/lmachnicvicz/customerAddress.svg?branch=develop)](https://github.com/lmachnicvicz/customerAddress)

### Instalation

Application requires Java 8 and maven.

Install the dependencies and start the application:
```sh
$ cd customerAddress
$ mvn clean install
$ cd target
$ java -jar customerAddress-0.0.1-SNAPSHOT.jar
```


### Endpoint Details:

Api documentation can be found in [Swagger](http:localhost:8080/swagger-ui.html)

|Endpoint|Verb|Url|
|--------|----|---|
|Get Customer by Id|GET|/api/v1/customer/{id}|
|Get All Customers|GET|/api/v1/customer/{id}|
|Create a Customer|POST|/api/v1/customer/|
|Update a Customer|PUT|/api/v1/customer/{id}|
|Delete a Customer|DELETE|/api/v1/customer/{id}|
|Delete an Address from Customer|DELETE|/api/v1/customer/{id}/address|
|Search all Address|GET|/api/v1/address|
|Search Customers by ZipCode|GET|/api/v1/address?zipCode=00000-000|

Example request to save a customer:

Request:
```sh
$ curl -X POST "http://localhost:8080/api/v1/customer" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"age\": 30, \"name\": \"Joao\", \"number\": 10, \"zipCode\": \"00000-000\"}"
```
Response:
```json
{
  "documentId": 1,
  "name": "Joao",
  "age": 30,
  "registrationDate": "2020-12-10T11:51:25.158+00:00",
  "lastUpdatedDate": "2020-12-10T11:51:25.158+00:00",
  "addresses": [
    {
      "zipCode": "00000-000",
      "number": 10
    }
  ]
}
```

Example to get customers by ZipCode:

Request:
```sh
$ curl -X GET "http://localhost:8080/api/v1/address?zipCode=00000-000" -H "accept: */*"
```
Response:
```json
[
  {
    "zipCode": "00000-000",
    "number": 10,
    "customers": [
      {
        "documentId": 1,
        "name": "Joao"
      }
    ]
  }
]
```