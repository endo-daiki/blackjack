Create database blackjack;

CREATE TABLE user(
id INT,
	name VARCHAR(20) not null,
	nickname VARCHAR(100) not null,
    	password varchar(100) not null,
    	playing integer not null,
    	win integer not null,
   	lose integer not null,
    	draw integer not null,
	created_at DATETIME,
	updated_at DATETIME,
	primary key(id)
);
