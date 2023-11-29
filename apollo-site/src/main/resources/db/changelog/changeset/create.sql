create TABLE if not exists users(id int generated always as  IDENTITY
                                , isDeleted varchar(255)
                                , isPredator varchar(255)
                                , email varchar(255)
                                , name varchar(255)
                                , password varchar(255)
                                , phoneNumber integer
                                , PRIMARY KEY(id));
create TABLE if not exists role(id int generated always as  IDENTITY
                               , name varchar(255)
                               , primary key(id));
insert into role (name)
values
    ('ROLE_USER'), ('ROLE_ADMIN');
insert into users (email,isDeleted, name, password, phoneNumber)
values
    ('user@gmail.com', true, 'user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 1),
    ('admin@gmail.com', false, 'admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 2);