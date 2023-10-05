create table if not exists likes(
    id integer primary key generated always as IDENTITY,
    id_product int REFERENCES product(id)
)
