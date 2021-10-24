create table user_role
(
    id int auto_increment
        primary key,
    role_value varchar(255) not null ,
    user_id int , foreign key (user_id) references user(id)

);