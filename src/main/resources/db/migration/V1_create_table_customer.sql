create database aula_kotlin

create table customer(
  id int auto_increment PRIMARY KEY,
  name varchar(255),
  email varchar(255) not null unique
)

create table book(
  id int auto_increment PRIMARY KEY,
  name varchar(255) not null,
  price decimal(10,2) not null,
  status varchar(255) not null,
  customer_id int not null,
  FOREIGN KEY (customer_id) REFERENCES customer(id)
)