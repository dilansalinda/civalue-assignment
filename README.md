# civalue-assignment

## api-service (Personalized data API Service)

api-service is a dockerized service which contains internal and external interfaces.

To trigger the receiving of shoppers’ personalized information and product metadata from data team service, two task scheduler have been implemented.
The reason the scheduler implementation is to replicate the functionality of ciValue data team internal service

To expose the shoppers’ personalized information to the external service, two endpoints have been created.

> Get shopper by product (with filters)
>
>http://localhost:8090/api/v1/external/shopper?productId=MB-2093193398&category=Sports&brand=Ballshop&limit=20


> Get Products by shopper
> 
>http://localhost:8090/api/v1/external/products?shopperId=S-1000&limit=10


## data-team-service (ciValue - data team internal service replication)

This is a replication of ciValue Data team (Apache Spark) service. 
It exposes 2 endpoint to receive shopper shelf and product metadata details.

http://localhost:8081/api/v1/shoppers

http://localhost:8081/api/v1/products

# Installation

### data-team-service

Execute the docker build command inside root dir.
```
docker build -t data-team-service .
```

### api-service

Execute the docker build command inside root dir.

```
docker compose up
```
Once execute above command, connection details beans for those services will be added to the application context so that 
the services can be used without any further configuration.

When the application stops, the services will then be shut down using docker compose down.