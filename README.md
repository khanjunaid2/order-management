---------- Order Management Using Spring Boot ------------

List of Rest APIs implemented in this project:

POST REQUEST
1. Create new order http://localhost:8080/api/orders

GET REQUESTS
1. Get all orders http://localhost:8080/api/orders
2. Get order by Id http://localhost:8080/api/orders/{id}
3. Get order by order created date Within Interval startTime and endTime as RequestParam http://localhost:8080/api/orders/interval
4. Get top 10 orders with highest order amount sorted by zipcode http://localhost:8080/api/orders/zipcode/{zip}

PUT REQUESTS
1. Cancel order by updating orderStatus as "CANCELLED". http://localhost:8080/api/orders/cancel/{id}
2. Update order by update OrderStatus as required (from "PLACED" to "DELIVERED"). Here OrderStatus is enum to know all order status. 
   http://localhost:8080/api/orders/update/{id}

Added Features Using Spring Data JPA
1. Pagination added to get all orders. http://localhost:8080/api/orders/?page=1&size=1
2. Implemented findByAttribute to get Order by order created date "createdAt"
