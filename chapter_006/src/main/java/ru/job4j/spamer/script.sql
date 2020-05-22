-- Создание базы данных для спамеров.
create database spamer;

-- Переключение на базу данных для спамеров.
\c spamer;

-- Создание таблицы для хранения спамеров.
create table users
(
    id    serial primary key,
    name  varchar(100),
    email varchar(100)
);