drop database medical_booking;
create database medical_booking;
use medical_booking;


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

CREATE TABLE client (
    id int auto_increment,
    username_account varchar(50) NOT NULL,
    picture varchar(150) NULL,
    name varchar(150) NOT NULL,
    gender varchar(10) NOT NULL,
	dob date NOT NULL,
    rank_id int NULL,
    PRIMARY KEY (id)
);
 CREATE TABLE doctor (
    id int auto_increment,
    username_account varchar(50) NOT NULL,
    picture varchar(150) NULL,
    name varchar(150) NOT NULL,
    gender varchar(10) NOT NULL,
    dob date NOT NULL,
    specialty varchar(50) NOT NULL,
    rank_id int NULL,
    PRIMARY KEY (id)
);

 CREATE TABLE booking (
    id int auto_increment,
    doctor_id int,
    client_id int,
    id_schedule int NOT NULL,
    booking_reason varchar(255) NOT NULL,
    status varchar(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_booking_client FOREIGN KEY (client_id) REFERENCES client (id),
    CONSTRAINT fk_booking_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id)
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

CREATE TABLE bill(
	id int auto_increment,
	booking_id int NOT NULL,
	payment_status varchar(20) NOT NULL,
	PRIMARY KEY (id)
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


use medical_booking;
INSERT INTO account (username, password, phone_number, email, isAdmin) VALUES ('admin', '123456', '1234567890', 'thanh17042001@gmail.com', 0);
INSERT INTO account (username, password, phone_number, email, isAdmin) VALUES ('doctor', '123456', '1234567890', 'thanh17042001@gmail.com', 1);
INSERT INTO account (username, password, phone_number, email, isAdmin) VALUES ('client', '123456', '1234567890', 'thanh17042001@gmail.com', 2);
INSERT INTO client (username_account, picture, name, gender, dob, rank_id) VALUES ('client', null, 'Thành', 'Male', '2001-04-17', 1);
INSERT INTO doctor (username_account, picture, name, gender, dob, specialty, rank_id) VALUES ('doctor', null, 'Thành', 'Male', '2001-04-17', 'Chuyên Khoa Tai', 1);
INSERT INTO role (id, name) VALUES (0, 'Admin'),(1, 'Doctor'),(2, 'Client');
INSERT INTO rank_client (id, name) VALUES (1, 'Đồng'),(2, 'Bạc'),(3, 'Vàng'),(4, 'Kim Cương');
INSERT INTO slot (id, name) VALUES (1, '9:00 - 9:30'),(2, '10:00 - 10:30'),(3, '11:00 - 11:30'),(4, '14:00 - 14:30'),(5, '15:00 - 15:30'),(6, '16:00 - 16:30');
INSERT INTO rank_doctor (id, name) VALUES (1, 'Bác Sĩ Hạng 1'),(2, 'Bác Sĩ Hạng 2'),(3, 'Bác Sĩ Hạng 3'),(4, 'Bác Sĩ Hạng 4');
INSERT INTO schedule (date) VALUES ('2023-06-22');
INSERT INTO booking (doctor_id, client_id, id_schedule, booking_reason, status) VALUES (1, 1, 1, 'Ù Tai', 'Done');
INSERT INTO slot_booking (schedule_id, slot_id) VALUES (1, 2);
select * from rank_client;
select * from account;
select * from client;
select * from doctor;
select * from rank_client;
select * from rank_doctor;
select * from schedule;
select * from booking;

SELECT b.id, b.doctor_id, b.client_id, b.booking_reason, s.date, sl.name AS slot, b.status
FROM booking b
LEFT JOIN schedule s ON b.id_schedule = s.id
LEFT JOIN slot_booking sb ON s.id = sb.schedule_id
LEFT JOIN slot sl ON sb.slot_id = sl.id
WHERE doctor_id = 3;

SELECT * FROM doctor WHERE username_account = 'doctor';

ALTER TABLE account  ADD  CONSTRAINT FOREIGN KEY(isAdmin)
REFERENCES role (id);

ALTER TABLE bill  ADD  CONSTRAINT FOREIGN KEY(booking_id)
REFERENCES booking (id);

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






