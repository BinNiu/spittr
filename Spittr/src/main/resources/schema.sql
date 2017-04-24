create table Spitter (
	id identity,
	username varchar(20) unique not null,
	password varchar(20) not null,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	fullName varchar(100) not null,
	email varchar(30) not null,
	updateByEmail boolean not null
	
);

create table Spittle (
	id identity,
	message varchar(140) not null,
	created_at timestamp not null,
	latitude double,
	longitude double,
	spitter integer not null,
	foreign key (spitter) references Spitter(id)
);


insert into Spitter(id, username, password, first_name, last_name, fullName, email, updateByEmail) 
values(1, 'tom', '123', 'Tom', 'Sean', 'Tom Sean', 'tom@gmail.com', false);

insert into Spitter(id, username, password, first_name, last_name, fullName, email, updateByEmail) 
values(2, 'jerry', '456', 'Jerry', 'Lee', 'Jerry Lee', 'jerry@gmail.com', false);

insert into Spittle(id, message, created_at, spitter) 
values (1, 'test1', date'1999-01-10', 1);

insert into Spittle(id, message, created_at, spitter) 
values (2, 'test2', date'2000-02-20', 2);

insert into Spittle(id, message, created_at, spitter) 
values (3, 'test3', date'2001-03-30', 1);

insert into Spittle(id, message, created_at, spitter) 
values (4, 'test4', date'2002-04-04', 2);

