#create database lab8


create table employee(
	id bigint NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    address varchar(200) NOT NULL,
    phone varchar(15) NOT NULL,
    primary key (id)
)