drop database medical_booking;
create database medical_booking;
use medical_booking;


CREATE TABLE bill(
	id int auto_increment,
	booking_id int NOT NULL,
	payment_status varchar(20) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE booking(
	id int NOT NULL,
	doctor_id int NOT NULL,
	client_id int NOT NULL,
	id_schedule int NOT NULL,
	booking_reason varchar(255) NOT NULL,
	status varchar(20) NOT NULL,
	PRIMARY KEY  (id)
);

CREATE TABLE account(
	username varchar(50) NOT NULL,
	password varchar(50) NOT NULL,
	phone_number varchar(20) NOT NULL,
	email varchar(100) NOT NULL,
    isAdmin int NOT NULL,
	PRIMARY KEY (username)
);
CREATE TABLE role(
	id int NOT NULL,
	name varchar(100) NOT NULL,
	PRIMARY KEY (id)    
);

CREATE TABLE client(
	id int auto_increment,
	username_account varchar(50) NOT NULL,
	picture varchar(150) NULL,
	name varchar(150) NOT NULL,
	rank_id int NULL,
	PRIMARY KEY  (id)
);


CREATE TABLE doctor(
	id int auto_increment,
	username_account varchar(50) NOT NULL,
    picture varchar(150) NULL,
	name varchar(150) NOT NULL,
	specialty varchar(50) NOT NULL,
	rank_id int NULL,
	PRIMARY KEY  (id)
);


CREATE TABLE medical_record(
	id int auto_increment,
	booking_id int NOT NULL,
	name varchar(150) NOT NULL,
	dob date NOT NULL,
	gender bit NOT NULL,
	diagnosis varchar(100) NOT NULL,
	medication varchar(100) NULL,
	PRIMARY KEY  (id)
);

CREATE TABLE rank_client(
	id int NOT NULL,
	name varchar(150) NOT NULL,
	PRIMARY KEY  (id)
);

CREATE TABLE rank_doctor(
	id int NOT NULL,
	name varchar(150) NOT NULL,
    PRIMARY KEY  (id)
);

CREATE TABLE schedule(
	id int auto_increment,
	date date NULL,
	PRIMARY KEY  (id)
);

CREATE TABLE slot(
	id int NOT NULL,
	name varchar(150) NOT NULL,
	PRIMARY KEY  (id)
);

CREATE TABLE slot_booking(
	schedule_id int NOT NULL,
	slot_id int NOT NULL,
	PRIMARY KEY  (schedule_id,slot_id)
);



INSERT account (username, password, phone_number, email, isAdmin) VALUES (N'admin', N'123456', N'1234567890', N'thanh17042001@gmail.com', N'0');
INSERT account (username, password, phone_number, email, isAdmin) VALUES (N'doctor', N'123456', N'1234567890', N'thanh17042001@gmail.com', N'1');
INSERT account (username, password, phone_number, email, isAdmin) VALUES (N'client', N'123456', N'1234567890', N'thanh17042001@gmail.com', N'2');
INSERT role (id, name) VALUES (0,N'Admin'),(1,N'Doctor'),(2,N'Client');
INSERT rank_client (id, name) VALUES (1, N'Đồng'),(2, N'Bạc'),(3, N'Vàng'),(4, N'Kim Cương');
select * from rank_client;
select * from account;
select * from client;
select * from role;

ALTER TABLE account  ADD  CONSTRAINT FOREIGN KEY(isAdmin)
REFERENCES role (id);

ALTER TABLE bill  ADD  CONSTRAINT FOREIGN KEY(booking_id)
REFERENCES booking (id);

ALTER TABLE booking  ADD  CONSTRAINT FOREIGN KEY(client_id)
REFERENCES client (id);


ALTER TABLE booking  ADD  CONSTRAINT FOREIGN KEY(doctor_id)
REFERENCES doctor (id);


ALTER TABLE booking  ADD  CONSTRAINT FOREIGN KEY(id_schedule)
REFERENCES schedule (id);


ALTER TABLE client  ADD  CONSTRAINT FOREIGN KEY(rank_id)
REFERENCES rank_client (id);

--
ALTER TABLE client  ADD  CONSTRAINT FOREIGN KEY(username_account)
REFERENCES account (username);


ALTER TABLE doctor  ADD  CONSTRAINT FOREIGN KEY(rank_id)
REFERENCES rank_doctor (id);

-- 
ALTER TABLE doctor  ADD  CONSTRAINT FOREIGN KEY(username_account)
REFERENCES account (username);


ALTER TABLE medical_record  ADD  CONSTRAINT FOREIGN KEY(booking_id)
REFERENCES booking (id);


ALTER TABLE slot_booking  ADD  CONSTRAINT FOREIGN KEY(schedule_id)
REFERENCES schedule (id);


ALTER TABLE slot_booking  ADD  CONSTRAINT FOREIGN KEY(slot_id)
REFERENCES slot (id);






