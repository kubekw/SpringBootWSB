create table user
(
    id int auto_increment
        primary key,
    username varchar(255) unique,
    password varchar(255) not null,
    email varchar(255) unique

);