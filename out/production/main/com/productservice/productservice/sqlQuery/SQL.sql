select c1_0.id,
       c1_0.name,
       p1_0.category_id,
       p1_0.id,
       p1_0.description,
       p1_0.image,
       p1_0.price,
       p1_0.title
from category c1_0
         left join product p1_0 on c1_0.id = p1_0.category_id
where c1_0.id = ?


#     // findall from product
select c1_0.id,
       c1_0.name,
       p1_0.category_id,
       p1_0.id,
       p1_0.description,
       p1_0.image,
       p1_0.inventory_count,
       p2_0.id,
       p2_0.currency,
       p2_0.value,
       p1_0.prices,
       p1_0.title
from category c1_0
         left join product p1_0 on c1_0.id = p1_0.category_id
         left join price p2_0 on p2_0.id = p1_0.price_id
where c1_0.id = ?
select p1_0.id,
       p1_0.category_id,
       p1_0.description,
       p1_0.image,
       p1_0.inventory_count,
       p1_0.price_id,
       p1_0.prices,
       p1_0.title
from product p1_0
select c1_0.id,
       c1_0.name,
       p1_0.category_id,
       p1_0.id,
       p1_0.description,
       p1_0.image,
       p1_0.inventory_count,
       p2_0.id,
       p2_0.currency,
       p2_0.value,
       p1_0.prices,
       p1_0.title
from category c1_0
         left join product p1_0 on c1_0.id = p1_0.category_id
         left join price p2_0 on p2_0.id = p1_0.price_id
where c1_0.id = ?
