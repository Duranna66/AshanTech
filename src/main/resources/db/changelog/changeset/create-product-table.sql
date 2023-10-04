create if not exists table products (
    id serial primary key,
    name varchar(100) not null,
    price double precision not null,
    manufacturer varchar(100) not null,
    description text,
    check (price > 0),
    unique (name)
);