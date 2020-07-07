insert into products (name, scale, description, inStock, inOrder, price, productlineId) VALUES
                    ('nametest','scaletest','descriptiontest',10,10,10.10,(select id from productlines where productlines.name ='nametest'));
insert into products (name, scale, description, inStock, inOrder, price, productlineId) VALUES
('nametest2','scaletest2','descriptiontest2',20,20,20.20,(select id from productlines where productlines.name ='nametest2'));