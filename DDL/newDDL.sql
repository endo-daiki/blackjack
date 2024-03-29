Create database blackjack DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `blackjack`.`newUser`(
	id varchar(100) not null,
	name varchar(100) not null,
	password varchar(100) not null,
	playing integer not null default 0,
	-- win integer not null default 0,
   	-- lose integer not null default 0,
	-- draw integer not null default 0,
	-- rate double not null default 0,
    tip integer not null default 100,
	primary key(id)
) DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_bin;

CREATE TABLE `blackjack`.`newPlayLog`(
	id integer not null auto_increment,
	user_id varchar(100) not null,
	log integer not null,
	created_at datetime not null default current_timestamp,
	primary key(id)
) DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_bin;