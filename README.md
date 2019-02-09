# Omnicuris_Ecommerce
 ---backend application for ecommerce ----
 ## Functionalities
 ### Admin:
 * Add item 
 * Remove an item from inventory
 * Update item (like: sell_price, purchase_price,item quantity,description)
 * View all items
 
 ### User:
 * Add item/items of respective quantity to Cart
 * Purchase items present in Cart
 * View all items
 
  ### Order:
 * total qty of items ordered 
 * total amount
 * When Order Status is Ordered Tansaction Id generated
 * When Order Status is Ordered respective Item quantity ordered subtracted for that Item 
 
 
 ### Validation:
 * User cannot buy Items having qty. 0 or lesser
 * Admin cannot add item having selling price lesser than purchase price
 * Valid email and contact number
 
 
 ## Tools and Technologies:

    * Technology : Java, Spring Boot, Hibernate, JPA, Maven.
    * Application Servicer: Apache Tomcat Server
    * Database : Mysql Database.
    
 Database designed in way that by orderId Admin can see the quantity of each item ordered to which customer and address,
 can see the number of orders placed by Customer, number of items of particular type placed by Customer.     

 
 
      
 
