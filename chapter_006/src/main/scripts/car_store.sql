-- Создаем базу данных для хранилища машин.
create database car_store;

-- Создаем таблицу для хранения типов кузовов.
create table body
(
    id   serial primary key,
    name character varying(20)
);

-- Создаем таблицу для хранения типов двигателей.
create table engine
(
    id   serial primary key,
    name character varying(20)
);

-- Создаем таблицу для хранения типов коробок передач.
create table transmission
(
    id   serial primary key,
    name character varying(20)
);

-- Создаем таблицу для хранения авто.
create table auto
(
    id              serial primary key,
    name            character varying(20),
    body_id         integer references body (id),
    engine_id       integer references engine (id),
    transmission_id integer references transmission (id)
);

-- Заполняем таблицу типами кузовов.
insert into body (name)
values ('sedan'),
       ('coupe'),
       ('jeep'),
       ('wagon'),
       ('hatchbak'),
       ('miniwan');

-- Заполняем таблицу типами двигателей.
insert into engine (name)
values ('v8'),
       ('v6'),
       ('v16'),
       ('v4'),
       ('opposite');

-- Заполняем таблицу типами коробок передач.
insert into transmission (name)
values ('manual'),
       ('auto'),
       ('semi-auto'),
       ('robot');

-- Заполняем таблицу авто.
insert into auto (name, body_id, engine_id, transmission_id)
values ('BMW X5', 3, 3, 2),
       ('Honda Fit', 5, 4, 3),
       ('Wolksvagen Passat', 4, 4, 1),
       ('Shevrolet Camaro', 2, 1, 2);

-- Вывести список всех машин и все привязанные к ним детали.
select a.name,
       b.name as body,
       e.name as engine,
       t.name as transmission
from auto a
         left outer join body b on a.body_id = b.id
         left outer join engine e on a.engine_id = e.id
         left outer join transmission t on a.transmission_id = t.id;

-- Вывести отдельно детали, которые не используются в машине - кузова.
select b.id,
       b.name
from auto a
         right outer join body b on b.id = a.body_id
where a.id is null

-- Вывести отдельно детали, которые не используются в машине - двигатели.
select e.id,
       e.name
from auto a
         right outer join engine e on e.id = a.engine_id
where a.id is null

-- Вывести отдельно детали, которые не используются в машине - коробки передач.
select t.id,
       t.name
from auto a
         right outer join transmission t on t.id = a.transmission_id
where a.id is null