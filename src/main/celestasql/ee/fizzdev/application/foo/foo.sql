create schema foo version '1.0';

create sequence names_sequence;

create table names (
    id int not null default nextval(names_sequence),
    name varchar(30),
    constraint pk_names primary key  (id)
);