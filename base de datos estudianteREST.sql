-- borra la bd si existe
DROP DATABASE IF EXISTS crudestudiante;
-- creamos la bd
CREATE DATABASE crudestudiante;
-- activamos la bd
USE crudestudiante;

CREATE TABLE Curso(
id_curso bigint auto_increment,
nomcurs varchar(200) NOT NULL,
primary key (id_curso)
);

CREATE TABLE Estudiante(
id_estudiante bigint auto_increment,
nom varchar(150) NOT NULL,
apell varchar(200) NOT NULL,
eda int NOT NULL,
direc varchar(250) NOT NULL,
img varchar(300),
id_cur bigint not null,
primary key (id_estudiante),
foreign key (id_cur) references Curso(id_curso)
);

select * from estudiante;

insert into curso value (null,"informatica");
insert into curso value (null,"diseño");
insert into estudiante value (null,"glenn","mejia",24,"la esperanza","imagen de glenn", 1);
insert into estudiante value (null,"juan","palacios",10,"la esperanza","imagen de juan", 2);
insert into estudiante value (null,"Alison","rosales",31,"la esperanza","imagen de alison", 1)
