insert into orderdetails (orderId, productId, ordered, priceEach) VALUES ((select id from orders where orders.customerId=(select id from customers where customers.name= 'nametest')),(select id from products where products.name='nametest'),1,1.1);
insert into orderdetails (orderId, productId, ordered, priceEach) VALUES ((select id from orders where orders.customerId=(select id from customers where customers.name= 'nametest')),(select id from products where products.name='nametest2'),2,2.2)