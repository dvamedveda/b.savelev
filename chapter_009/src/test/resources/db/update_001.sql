create table orders (
    id serial primary key,
    name varchar(50) unique,
    description varchar(50),
    created timestamp
)