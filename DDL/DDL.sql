Create database blackjack DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `blackjack`.`user`(
	id varchar(100) not null,
	name varchar(100) not null,
	password varchar(100) not null,
	playing integer not null default 0,
	win integer not null default 0,
   	lose integer not null default 0,
	draw integer not null default 0,
	primary key(id)
) DEFAULT CHARSET=utf8mb4;

