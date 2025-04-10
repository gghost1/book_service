create table if not exists users(
    id uuid primary key,
    name varchar(255) not null unique,
    password varchar(255) not null
);