-- 1. Есть таблица встреч(id, name), есть таблица юзеров(id, name).
-- Нужно доработать модель базы данных, так чтобы пользователи могли учавствовать во встречах.
-- У каждого участника встречи может быть разный статус участия - например подтвердил участие, отклонил.
-- 2. Нужно написать запрос, который получит список всех заяков и количество подтвердивших участников.
-- 3. Нужно получить все совещания, где не было ни одной заявки на посещения

-- drop table visits;
-- drop table meetings;
-- drop table users;

create table meetings (
    id serial primary key,
    name character varying(20)
);

create table users (
    id serial primary key,
    name character varying(20)
);

insert into meetings (name)
values ('party'),
       ('retro'),
       ('investors'),
       ('review'),
       ('elections'),
       ('lunch'),
       ('playtest'),
       ('coffeebreak'),
       ('conference'),
       ('meeting');

insert into users (name)
values ('user1'), ('user2'), ('user3'), ('user4'), ('user5'), ('user6'),
       ('user7'), ('user8'), ('user9'), ('user10'), ('user11'), ('user12'),
       ('user13'), ('user14'), ('user15'), ('user16'), ('user17'), ('user18'),
       ('user19'), ('user20'), ('user21'), ('user22'), ('user23'), ('user24'),
       ('user25'), ('user26'), ('user27'), ('user28'), ('user29'), ('user30'),
       ('user31'), ('user32'), ('user33'), ('user34'), ('user35'), ('user36');

-- решение
-- создаем соединительную таблицу
create table visits (
      id serial primary key,
      user_id integer references users(id),
      meeting_id integer references meetings(id),
      status character varying(20)
);

-- заполняем ее
insert into visits (user_id, meeting_id, status)
values (1, 1, 'requested'), (2, 1, 'confirmed'), (3, 1, 'rejected'),
       (4, 1, 'requested'), (5, 1, 'confirmed'), (6, 1, 'rejected'),
       (7, 1, 'requested'), (8, 1, 'confirmed'), (9, 1, 'rejected'),
       (10, 1, 'requested'), (11, 1, 'confirmed'), (12, 1, 'rejected'),
       (13, 3, 'requested'), (14, 3, 'confirmed'), (15, 3, 'rejected'),
       (16, 3, 'requested'), (17, 3, 'confirmed'), (18, 3, 'rejected'),
       (19, 4, 'requested'), (20, 4, 'confirmed'), (21, 4, 'rejected'),
       (22, 4, 'requested'), (23, 4, 'confirmed'), (24, 4, 'rejected'),
       (25, 5, 'requested'), (26, 6, 'confirmed'), (27, 7, 'rejected'),
       (28, 7, 'requested'), (29, 8, 'confirmed'), (30, 9, 'rejected');

-- Выбрать встречи и количество подтвержденных заявок на них из всех заявок.
with confirms as (select m.id, count(*)
                  from visits v
                           join meetings m on m.id = v.meeting_id
                  where v.status = 'confirmed'
                  group by m.id)
select m.name as meeting, c.count as confirmed
from meetings m
        left join confirms c on m.id = c.id;

-- Все совещания, где не было ни одной заявки на посещения.
select
    m.id, m.name, count(v.*)
from meetings m
         left join visits v on m.id = v.meeting_id
where v.id is null
group by m.id, m.name
