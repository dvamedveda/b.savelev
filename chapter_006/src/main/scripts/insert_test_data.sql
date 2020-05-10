INSERT INTO user_role (name) VALUES ('Tester'), ('Developer'), ('Manager');

INSERT INTO issue_state (state) VALUES ('New'), ('Assigned'), ('Resolved'), ('Verified');

INSERT INTO issue_type (type) VALUES ('Bug'), ('Task'), ('Research');

INSERT INTO rights (name, allowed) VALUES
('resolve', true),
('assign', true),
('verify', true),
('resolve', false),
('assign', false),
('verify', false);

INSERT INTO member (login, password, role) VALUES
('savelev', '123', 2),
('arsentev', '321', 3),
('ivanov', 'qwerty', 1);

INSERT INTO role_rights (role_id, right_id) VALUES
(2, 6),
(3, 4),
(3, 5);

INSERT INTO issues (summary, description, author, state, type) VALUES
('Не открывается главная панель приложения',
 'При клике на кнопку открытия главной панели, главная панель не открывается', 1, 1, 1),
('Не удается загрузить xml-файл в систему',
 'Файлы невозможно загрузить, потому что при загрузке вываливается непонятная ошибка.', 2, 2, 1),
('Надо починить систему отчетов до конца июня',
 'Отчеты нужны в двух видах - xml и html. Отчеты должны включать данные за последний год,' ||
 'размер отчетов не должен превышать 1 мегабайт.',
 3, 1, 3);

INSERT INTO issue_comments (comment, author, time, issue_id) VALUES
('Мне кажется, это было сломано при выполнении задачи №51', 2,
 current_timestamp, 1),
('Нет, дефект присутствовал в системе изначально', 1,
 current_timestamp, 1),
('Невозможно работать по другим задачам, пока не загружу файл', 3,
 current_timestamp, 2),
('Это очень важная для заказчика возможность, задачи по ней идут первым приоритетом', 2,
 '2001-09-28 00:00:00', 3);

INSERT INTO issue_attachments (issue_id, attachment_data) VALUES
(1, decode('Это содержимое тестового файла номер один для первой задачи', 'escape')),
(1, decode('Это содержимое тестового файла номер два для первой задачи', 'escape')),
(2, decode('Это содержимое тестового файла для второй задачи', 'escape')),
(3, decode('Это содержимое тестового файла для третьей задачи', 'escape'));