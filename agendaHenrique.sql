create database agendahenrique;
-- drop database agendahenrique;
use agendahenrique;

create table tipocontato(
 id int not null auto_increment primary key,
tipo varchar(25) not null
);

create table contato(
id int not null auto_increment primary key,
nome varchar(45) not null,
dataNascimento date,
email varchar(150),
idTipoContato int not null,
FOREIGN KEY (idTipoContato )REFERENCES TipoContato(ID) on delete no action
);

create table telefone(
id int not null auto_increment primary key,
ddd char(2),
numero varchar(10) NOT NULL,
tipo varchar(50),
idContato int not null,
FOREIGN KEY (idContato) REFERENCES contato(id) on delete cascade
);
