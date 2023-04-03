create database enjoytrip;
use enjoytrip;

create table hotplace(
	article_no int not null primary key auto_increment,
    longitude varchar(100) not null,
    latitude varchar(100) not null,
	image varchar(200),
    place_name varchar(100) not null,
    visite_date date not null,
    place_type int not null,
    place_describe varchar(2000) not null
);

create table user(
	user_id bigint auto_increment primary key ,
    name varchar(20) not null,
	nickname varchar(20) not null,
    password varchar(20) not null,
    email varchar(100) not null
);