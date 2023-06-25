drop database medical_booking;
create database medical_booking;
use medical_booking;


CREATE TABLE account(
	username varchar(50) NOT NULL,
	password varchar(50) NOT NULL,
	phone varchar(20) NOT NULL,
	email varchar(100) NOT NULL,
    isAdmin int NOT NULL,
	PRIMARY KEY (username)
);
CREATE TABLE role(
	id int NOT NULL,
	name varchar(100) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE patient (
    id int auto_increment,
    username varchar(50) NOT NULL,
    picture varchar(150) NULL,
    name varchar(150) NOT NULL,
    gender varchar(10) NOT NULL,
	dob date NOT NULL,
    rank_id int NULL,
    PRIMARY KEY (id)
);
 CREATE TABLE doctor (
    id int auto_increment,
    username varchar(50) NOT NULL,
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
    slot_id int NOT NULL,
    booking_reason varchar(255) NOT NULL,
    date date NOT NULL,
    status varchar(20) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE medical_record(
	id int auto_increment,
	booking_id int NOT NULL,
	diagnosis varchar(100) NOT NULL,
	medication varchar(100) NULL,
	PRIMARY KEY  (id)
);

CREATE TABLE bill(
	id int auto_increment,
	medical_record_id int NOT NULL,
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

CREATE TABLE slot(
	id int NOT NULL,
	name varchar(150) NOT NULL,
	PRIMARY KEY  (id)
);

ALTER TABLE account ADD CONSTRAINT fk_account_role FOREIGN KEY (isAdmin) REFERENCES role (id);
ALTER TABLE bill ADD CONSTRAINT fk_bill_medical_record FOREIGN KEY (medical_record_id) REFERENCES medical_record (id);
ALTER TABLE patient ADD CONSTRAINT fk_client_rank FOREIGN KEY (rank_id) REFERENCES rank_client (id);
ALTER TABLE patient ADD CONSTRAINT fk_client_account FOREIGN KEY (username) REFERENCES account (username);
ALTER TABLE doctor ADD CONSTRAINT fk_doctor_rank FOREIGN KEY (rank_id) REFERENCES rank_doctor (id);
ALTER TABLE doctor ADD CONSTRAINT fk_doctor_account FOREIGN KEY (username) REFERENCES account (username);
ALTER TABLE medical_record ADD CONSTRAINT fk_medical_record_booking FOREIGN KEY (booking_id) REFERENCES booking (id);
ALTER TABLE booking ADD CONSTRAINT fk_booking_slot FOREIGN KEY (slot_id) REFERENCES slot (id);
ALTER TABLE booking ADD CONSTRAINT fk_booking_client FOREIGN KEY (client_id) REFERENCES patient (id);
ALTER TABLE booking ADD CONSTRAINT fk_booking_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id);



INSERT INTO role (id, name) VALUES (0, 'Admin'),(1, 'Doctor'),(2, 'Patient');
INSERT INTO rank_client (id, name) VALUES (1, 'Đồng'),(2, 'Bạc'),(3, 'Vàng'),(4, 'Kim Cương');
INSERT INTO rank_doctor (id, name) VALUES (1, 'Bác Sĩ Hạng 1'),(2, 'Bác Sĩ Hạng 2'),(3, 'Bác Sĩ Hạng 3'),(4, 'Bác Sĩ Hạng 4');
INSERT INTO slot (id, name) VALUES (1, '9:00 - 9:30'),(2, '10:00 - 10:30'),(3, '11:00 - 11:30'),(4, '14:00 - 14:30'),(5, '15:00 - 15:30'),(6, '16:00 - 16:30');


-- Account table INSERT statements
INSERT INTO account (username, password, phone, email, isAdmin) VALUES ('admin', '123', '1234567890', 'thanh17042001@gmail.com', 0);
INSERT INTO account (username, password, phone, email, isAdmin) VALUES ('doctor', '123', '0123548293', 'jaod01@gmail.com', 1);
INSERT INTO account (username, password, phone, email, isAdmin) VALUES ('doctor1', '123', '0982783817', 'olika@gmail.com', 1);
INSERT INTO account (username, password, phone, email, isAdmin) VALUES ('doctor2', '123', '0887462736', 'akasi@gmail.com', 1);
INSERT INTO account (username, password, phone, email, isAdmin) VALUES ('doctor3', '123', '0826136264', 'naruto@gmail.com', 1);
INSERT INTO account (username, password, phone, email, isAdmin) VALUES ('patient', '123', '0916728374', 'takassi@gmail.com', 2);
INSERT INTO account (username, password, phone, email, isAdmin) VALUES ('client1', '123', '0915568374', 'anokae@gmail.com', 2);
INSERT INTO account (username, password, phone, email, isAdmin) VALUES ('client2', '123', '0913826413', 'aizawwa@gmail.com', 2);
INSERT INTO account (username, password, phone, email, isAdmin) VALUES ('client3', '123', '0316281736', 'akdiwww@gmail.com', 2);
 -- Patient table INSERT statements
INSERT INTO patient (username, picture, name, gender, dob, rank_id) VALUES ('patient', NULL, 'John Doe', 'Male', '1990-01-01', 1);
INSERT INTO patient (username, picture, name, gender, dob, rank_id) VALUES ('client1', NULL, 'Sarah Lee', 'Female', '1985-05-05', 2);
INSERT INTO patient (username, picture, name, gender, dob, rank_id) VALUES ('client2', NULL, 'Peter Kim', 'Male', '1978-06-06', 3);
INSERT INTO patient (username, picture, name, gender, dob, rank_id) VALUES ('client3', NULL, 'Ngân', 'Female', '1992-02-02', 2);
-- Add more clients as needed
 -- Doctor table INSERT statements
INSERT INTO doctor (username, picture, name, gender, dob, specialty, rank_id) VALUES ('doctor', NULL, 'Dr. Smith', 'Male', '1980-03-03', 'Bác Sĩ Chuyên Khoa Mũi Họng', 1);
INSERT INTO doctor (username, picture, name, gender, dob, specialty, rank_id) VALUES ('doctor1', NULL, 'Dr. Park', 'Female', '1975-07-07', 'Bác Sĩ Chuyên Khoa Mũi', 2);
INSERT INTO doctor (username, picture, name, gender, dob, specialty, rank_id) VALUES ('doctor2', NULL, 'Dr. Lee', 'Male', '1969-08-08', 'Bác Sĩ Chuyên Khoa Tai', 3);
INSERT INTO doctor (username, picture, name, gender, dob, specialty, rank_id) VALUES ('doctor3', NULL, 'Thành', 'Female', '1982-04-04', 'Bác Sĩ Chuyên Khoa Tai Họng', 2);
-- Add more doctors as needed
 -- Booking table INSERT statements
INSERT INTO booking (doctor_id, client_id, slot_id, booking_reason, date, status) VALUES (1, 1, 1, 'Viêm tai giữa', '2023-06-22', 'Confirmed');
INSERT INTO booking (doctor_id, client_id, slot_id, booking_reason, date, status) VALUES (2, 2, 2, 'Viêm họng', '2023-06-23', 'Confirmed');
INSERT INTO booking (doctor_id, client_id, slot_id, booking_reason, date, status) VALUES (3, 3, 3, 'Vaccination', '2023-06-22', 'Confirmed');
INSERT INTO booking (doctor_id, client_id, slot_id, booking_reason, date, status) VALUES (4, 4, 4, 'Sổ mũi hắt hơi', '2023-06-23', 'Confirmed');
INSERT INTO booking (doctor_id, client_id, slot_id, booking_reason, date, status) VALUES (1, 2, 3, 'Annual check-up', '2023-06-23', 'Pending');
-- Add more bookings as needed
 -- Medical_record table INSERT statements
INSERT INTO medical_record (booking_id, diagnosis, medication) VALUES (1, 'Healthy', NULL);
INSERT INTO medical_record (booking_id, diagnosis, medication) VALUES (2, 'Allergic reaction', 'Antihistamine');
INSERT INTO medical_record (booking_id, diagnosis, medication) VALUES (3, 'Healthy', NULL);
INSERT INTO medical_record (booking_id, diagnosis, medication) VALUES (4, 'Cancer', 'Chemotherapy');
-- Add more medical records as needed
 -- Bill table INSERT statements
INSERT INTO bill (medical_record_id, payment_status) VALUES (1, 'Paid');
INSERT INTO bill (medical_record_id, payment_status) VALUES (2, 'Unpaid');
INSERT INTO bill (medical_record_id, payment_status) VALUES (3, 'Unpaid');
INSERT INTO bill (medical_record_id, payment_status) VALUES (4, 'Paid');
-- Add more bills as needed




