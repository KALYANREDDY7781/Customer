create table cards(
	id INT auto_increment primary key,
    customer_id int not null,
    card_number varchar(16) NOT NULL UNIQUE,
    card_type varchar(20) NOT NULL,
    expiry_date date not null,
    status varchar(10) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp ON UPDATE current_timestamp
);