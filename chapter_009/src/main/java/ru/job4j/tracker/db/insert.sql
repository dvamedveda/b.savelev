-- Заполнение таблицы с заявками для Tracker SQL
insert into items (name, description, created)
values ('Реализовать панель для навигации по трекеру', 'Описание первой заявки', current_timestamp),
       ('Исправить ошибку с перемещением по дереву каталогов в левой части окна', 'Описание второй заявки',
        current_timestamp),
       ('Нужно завести темную тему в трекере', 'Описание третьей заявки', current_timestamp),
       ('Собрать отзывы и предложения по трекеру, расставить соответствующие задачи', 'Описание четвертой заявки',
        current_timestamp),
       ('Добавить побольше тестовых заданий', 'Описание пятой заявки', current_timestamp),
       ('Тестовая задача для проверки отображения задач в дереве каталогов в левой части окна',
        'Описание шестой заявки', current_timestamp),
       ('Переделать авторизацию с логинов на почты', 'Описание седьмой заявки', current_timestamp);