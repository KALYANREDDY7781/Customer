use customer_db;
create table address(
	id INT auto_increment primary key,
    customer_id INT NOT NULL,
    address_line1 varchar(50) NOT NULL,
    city varchar(25) NOT NULL,
    state varchar(25) NOT NULL,
    zip_code int not null,
    type varchar(10) not null,
    is_default boolean,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp

);