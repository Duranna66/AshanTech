create table if not exists product
(
    id           integer primary key generated always as IDENTITY,
    name         varchar(100)     not null,
    price        double precision not null,
    manufacturer varchar(100)     not null,
    description  text,
    check (price > 0)
)