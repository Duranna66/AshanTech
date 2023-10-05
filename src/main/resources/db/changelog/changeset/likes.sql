create table if not exist Likes(
    id integer primary key generated always as IDENTITY,
    id_product int REFERENCES products(id)
)
