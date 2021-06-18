# Order Management

## Implemented Order Management System backend API  using Spring Boot
--Added two new features--
- Create new user
- Add item to the stock

---
## POST REQUEST

navigate to http://localhost:8080/api/orders/placeorder to create a new order

navigate to http://localhost:8080/api/orders/additem to create a new item in the stock

navigate to http://localhost:8080/api/orders/registeruser to create a new user 

---
## GET REQUESTS

navigate to http://localhost:8080/api/orders to view all the orders

navigate to http://localhost:8080/api/orders/zipcode/{zipcode} to get the top 10 highest amount orders in that area

navigate to http://localhost:8080/api/orders/interval?startTime={timeStamp}&endTime={timeStamp} to get orders in that interval

---

## PUT REQUESTS

navigate to http://localhost:8080/api/orders/updateorder/{id} to change the status of the order to delivered

navigate to http://localhost:8080/api/orders/cancel/{id} to cancel the order

---

## NOTE
 Refer the enums package to know the Shipping Method, Payment Mode, and Order Status values
