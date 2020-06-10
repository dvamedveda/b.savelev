-- Запрос, создающий таблицу для компаний
CREATE TABLE company
(
    id   integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

-- Запрос, создающий таблицу для сотрудников
CREATE TABLE person
(
    id         integer NOT NULL,
    name       character varying,
    company_id integer references company (id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

-- Запрос, заполняющий таблицу для компаний
insert
into company(id, name)
values (1, 'company1'),
       (2, 'company2'),
       (3, 'company3'),
       (4, 'company4'),
       (5, 'company5'),
       (6, 'company6'),
       (7, 'company7'),
       (8, 'company8'),
       (9, 'company9'),
       (10, 'company10');

-- Запрос, заполняющий таблицу для сотрудников
insert
into person(id, name, company_id)
values (1, 'person1_1', 1),
       (2, 'person1_2', 1),
       (3, 'person2_1', 2),
       (4, 'person2_2', 2),
       (5, 'person3_1', 3),
       (6, 'person3_2', 3),
       (7, 'person4_1', 4),
       (8, 'person4_2', 4),
       (9, 'person5_1', 5),
       (10, 'person5_2', 5),
       (11, 'person6_1', 6),
       (12, 'person6_2', 6),
       (13, 'person7_1', 7),
       (14, 'person7_2', 7),
       (15, 'person8_1', 8),
       (16, 'person8_2', 8),
       (17, 'person9_1', 9),
       (18, 'person9_2', 9),
       (19, 'person10_1', 10),
       (20, 'person10_2', 10),
       (21, 'person10_3', 10),
       (22, 'person10_4', 10);

-- Запрос, возвращающий в одном запросе:
-- 1. имена всех сотрудников, которые НЕ в компании с id=5
-- 2. содержит имена компаний для всех сотрудников
select p.id, p.name, c.id, c.name
from person p
         join company c on p.company_id = c.id
where c.id != 5;

-- Запрос, который выбирает имя компании с наибольшим количеством сотрудников,
-- а также число сотрудников в этой компании.
select c.name, count(*) as count
from person p
         join company c on p.company_id = c.id
group by c.name
order by count desc
limit 1;