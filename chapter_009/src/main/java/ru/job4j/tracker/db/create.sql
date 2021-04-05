--Создание таблицы items для проекта Tracker SQL
CREATE TABLE items
(
    id          SERIAL PRIMARY KEY,
    name        text,
    description text,
    created     timestamp
);