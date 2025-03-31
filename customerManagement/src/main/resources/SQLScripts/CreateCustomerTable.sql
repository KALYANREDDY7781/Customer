create table customer(
	id INT auto_increment PRIMARY key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(50) unique not null,
    phone varchar(50) unique not null,
    address TEXT,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp ON UPDATE current_timestamp
);