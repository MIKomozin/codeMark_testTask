--users
insert into users (login, name, password) values ('Max', 'Максим', '1bs');
insert into users (login, name, password) values ('Vova', 'Владимир', '2sbs');
insert into users (login, name, password) values ('Evgen', 'Евгений', '12sbb3');
insert into users (login, name, password) values ('Hikolya', 'Николай', 'gsgs');
insert into users (login, name, password) values ('An', 'Анна', '1sbsb');

--roles
insert into roles (name) values ('Аналитик');
insert into roles (name) values ('Программист');
insert into roles (name) values ('Оператор');
insert into roles (name) values ('Технический директор');
insert into roles (name) values ('Бухгалтер');
insert into roles (name) values ('Начальник разработки');
insert into roles (name) values ('Главный экономист');

--user2role
insert into user2role (user_login, role_id) values ('Max', 1);
insert into user2role (user_login, role_id) values ('Max', 2);
insert into user2role (user_login, role_id) values ('Vova', 1);
insert into user2role (user_login, role_id) values ('Vova', 2);
insert into user2role (user_login, role_id) values ('Vova', 3);
insert into user2role (user_login, role_id) values ('Evgen', 4);
insert into user2role (user_login, role_id) values ('Evgen', 6);
insert into user2role (user_login, role_id) values ('Hikolya', 2);
insert into user2role (user_login, role_id) values ('Hikolya', 3);
insert into user2role (user_login, role_id) values ('An', 5);
insert into user2role (user_login, role_id) values ('An', 7);
