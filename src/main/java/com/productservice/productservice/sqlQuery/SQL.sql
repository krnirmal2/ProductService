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