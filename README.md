# civalue-assignment

## api-service (Personalized data API Service)

api-service is a dockerized service which contains internal and external interfaces.

To trigger the receiving of shoppers’ personalized information and product metadata from data team service, two task scheduler have been implemented.
The reason the scheduler implementation is to replicate the functionality of ciValue data team internal service

To expose the shoppers’ personalized information to the external service, two endpoints have been created.

http://localhost:8090/api/v1/external/shopper?productId=MB-2093193398&category=Sports&brand=Ballshop&limit=20

http://localhost:8090/api/v1/external/products?shopperId=S-1000&limit=10


## data-team-service (ciValue Data team internal Service replication)

This is a replication of ciValue Data team (Apache Spark) service. 
It exposes 2 API's to receive shopper shelf and product metadata details.

http://localhost:8081/api/v1/shoppers
http://localhost:8081/api/v1/products