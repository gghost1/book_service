create table if not exists books(
    id uuid primary key,
    vendor_code varchar(255) not null unique,
    title varchar(255) not null,
    year integer not null,
    brand varchar(255) not null,
    stock integer not null,
    price double precision not null
);