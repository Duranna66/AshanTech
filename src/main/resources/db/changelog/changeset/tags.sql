create table Tags(
    id integer primary key generated always as IDENTITY,
    id_product int REFERENCES products(id)
)