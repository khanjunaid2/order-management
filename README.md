#Order Management RESTful API with Spring Boot, MySQL, Spring Data JPA and Hibernate

**API endpoints**:

+ GET all orders 
  http://localhost:8080/orders
  
+ GET all orders with pagination and sorted by the given attribute value
  http://localhost:8080/orders/pagination?pageNumber=0&pageSize=5&sortBy=createdDate
  
+ GET  order by Id
  http://localhost:8080/orders/{id}
  
+ GET all orders which are placed in the given time interval.
  http://localhost:8080/orders/interval/{startTime}/{endTime}
  
+ GET top 10 orders with highest total billing amount In a given Zip location
  http://localhost:8080/orders/search?zip=60016
  
+ POST create new order
  http://localhost:8080/orders/create
  example payload:
  {
    "creationDate": "2021-06-20T23:53:38.2682",
    "modificationDate": "2021-06-20T23:53:38.2682",
    "customerId": "customer1",
    "total": 280.50,
    "subtotal": 250.00,
    "tax":2.50,
    "status": "PENDING",
    "items": [{"itemName": "item1", 
               "itemQuantity":4,
               "itemUnitPrice": 20.40},
               {
               "itemName": "item2", 
               "itemQuantity":2,
               "itemUnitPrice": 10.40
               }

    ],
    "payments": [{"confirmationNumber":"PAID1234",
                  "paymentType":"CREDIT",
                  "amount":280.50,
                  "billing": {
                      "addressLine1": "river st",
                      "addressLine2": "desplaines",
                      "city": "chicago",
                      "state": "illinios",
                      "zip": "60016"
                  }
    }], 
    "shipping": {
                   "shipmentType": "PICKUP",
                   "addressLine1": "river st",
                   "addressLine2": "desplaines",
                   "city": "chicago",
                   "state": "illinios",
                   "zip": "60016"
    }
}

+ PUT update existing order
http://localhost:8080/orders/update

+ POST cancel order
http://localhost:8080/orders/cancel/{id}