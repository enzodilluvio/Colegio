drop database if exists colegio;
create database colegio;
use colegio;
create table cursos(   
    id int auto_increment primary key,
titulo varchar(20) not null,
profesor varchar(20),
dia varchar(20),
turno varchar(20)
);

create table alumnos(
    id int auto_increment primary key,
    nombre varchar(20) not null,
    apellido varchar(20) not null,
    edad int,
    curso int
);