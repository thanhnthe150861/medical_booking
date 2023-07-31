drop database medical_booking;
create database medical_booking;
use medical_booking;
ALTER DATABASE medical_booking CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

	CREATE TABLE account(
		username varchar(50) NOT NULL,
		password varchar(50) NOT NULL,
		phone varchar(20),
		email varchar(100) NOT NULL,
		isAdmin int NOT NULL,
		status bit NOT NULL,
		PRIMARY KEY (username)
	);
    
	CREATE TABLE role(
		id int NOT NULL,
		name varchar(100) ,
		PRIMARY KEY (id)
	);

	CREATE TABLE patient (
		id int auto_increment,
		username varchar(50) ,
        url varchar(500),
		name varchar(150) ,
		gender varchar(10),
		dob date,
        dayoff_id int,
		rank_id int NULL,
		PRIMARY KEY (id)
	);
	 CREATE TABLE doctor (
		id int auto_increment,
		username varchar(50) ,
        url varchar(500),
		name varchar(150) ,
		gender varchar(10),
		dob date,
		specialty int,
		rank_id int NULL,
		PRIMARY KEY (id)
	);
    
CREATE TABLE ngaynghi (
		id int auto_increment,
        doctor_id int,
		date date,
        slot_id int,
        status bit,
		PRIMARY KEY (id)
	);

    CREATE TABLE specialty (
		id int auto_increment,
		name varchar(50),
		PRIMARY KEY (id)
	);
    
	CREATE TABLE staff (
		id int auto_increment,
		username varchar(50) ,
        url varchar(500),
		name varchar(150) ,
		gender varchar(10),
		dob date,
		PRIMARY KEY (id)
	);
	 CREATE TABLE booking (
		id int auto_increment,
		doctor_id int,
		patient_id int,
		slot_id int,
        specialty_id int,
		booking_reason varchar(500),
		date date,
		status varchar(20),
        reason varchar(500),
		PRIMARY KEY (id)
	);

	CREATE TABLE medical_record(
		id int auto_increment,
		booking_id int ,
		diagnosis varchar(100) ,
		prescription varchar(100) NULL,
        url varchar(500),
		PRIMARY KEY  (id)
	);

	CREATE TABLE bill(
		id int auto_increment,
		medical_record_id int,
        priceMedical float,
        pricePrescription float,
		totalPrice float,
		payment_status varchar(20),
		PRIMARY KEY (id)
	);

	CREATE TABLE rank_patient(
		id int NOT NULL,
		name varchar(150) ,
		PRIMARY KEY  (id)
	);

	CREATE TABLE rank_doctor(
		id int NOT NULL,
		name varchar(150) ,
		PRIMARY KEY  (id)
	);

	CREATE TABLE slot(
		id int NOT NULL,
		name varchar(150) ,
		PRIMARY KEY  (id)
	);


	ALTER TABLE account ADD CONSTRAINT fk_account_role FOREIGN KEY (isAdmin) REFERENCES role (id);
	ALTER TABLE bill ADD CONSTRAINT fk_bill_medical_record FOREIGN KEY (medical_record_id) REFERENCES medical_record (id);
	ALTER TABLE patient ADD CONSTRAINT fk_patient_rank FOREIGN KEY (rank_id) REFERENCES rank_patient (id);
	ALTER TABLE patient ADD CONSTRAINT fk_patient_account FOREIGN KEY (username) REFERENCES account (username);
	ALTER TABLE doctor ADD CONSTRAINT fk_doctor_rank FOREIGN KEY (rank_id) REFERENCES rank_doctor (id);
	ALTER TABLE doctor ADD CONSTRAINT fk_doctor_account FOREIGN KEY (username) REFERENCES account (username);
	ALTER TABLE staff ADD CONSTRAINT fk_staff_account FOREIGN KEY (username) REFERENCES account (username);
	ALTER TABLE medical_record ADD CONSTRAINT fk_medical_record_booking FOREIGN KEY (booking_id) REFERENCES booking (id);
	ALTER TABLE booking ADD CONSTRAINT fk_booking_slot FOREIGN KEY (slot_id) REFERENCES slot (id);
	ALTER TABLE booking ADD CONSTRAINT fk_booking_patient FOREIGN KEY (patient_id) REFERENCES patient (id);
	ALTER TABLE booking ADD CONSTRAINT fk_booking_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id);
	ALTER TABLE booking ADD CONSTRAINT fk_booking_specialty FOREIGN KEY (specialty_id) REFERENCES specialty (id);
    ALTER TABLE doctor ADD CONSTRAINT fk_specialty_doctor FOREIGN KEY (specialty) REFERENCES specialty (id);
    ALTER TABLE ngaynghi ADD CONSTRAINT fk_ngaynghi_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id);
    ALTER TABLE ngaynghi ADD CONSTRAINT fk_ngaynghi_slot FOREIGN KEY (slot_id) REFERENCES slot (id);



INSERT INTO role (id, name) VALUES (0, 'Admin'),(1, 'Doctor'),(2, 'Patient'), (3, 'Staff');
INSERT INTO rank_patient (id, name) VALUES (1, 'Đồng'),(2, 'Bạc'),(3, 'Vàng'),(4, 'Kim Cương');
INSERT INTO rank_doctor (id, name) VALUES (1, 'Bác Sĩ Hạng 1'),(2, 'Bác Sĩ Hạng 2'),(3, 'Bác Sĩ Hạng 3'),(4, 'Bác Sĩ Hạng 4');
INSERT INTO slot (id, name) VALUES (1, '9:00 - 9:30'),(2, '10:00 - 10:30'),(3, '11:00 - 11:30'),(4, '14:00 - 14:30'),(5, '15:00 - 15:30'),(6, '16:00 - 16:30');
INSERT INTO specialty (id, name) VALUES (1, 'Tai'),(2, 'Mũi'),(3, 'Mắt');

select * from account;

INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('admin', '202cb962ac59075b964b07152d234b70', '1234567890', 'thanh17042001@gmail.com', 0, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('doctor', '202cb962ac59075b964b07152d234b70', '0123548293', 'jaod01@gmail.com', 1, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('doctor1', '202cb962ac59075b964b07152d234b70', '0982783817', 'olika@gmail.com', 1, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('doctor2', '202cb962ac59075b964b07152d234b70', '0887462736', 'akasi@gmail.com', 1, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('doctor3', '202cb962ac59075b964b07152d234b70', '0826136264', 'naruto@gmail.com', 1, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('doctor4', '202cb962ac59075b964b07152d234b70', '0826136264', 'naruto@gmail.com', 1, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('doctor5', '202cb962ac59075b964b07152d234b70', '0826136264', 'naruto@gmail.com', 1, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('patient', '202cb962ac59075b964b07152d234b70', '0916728374', 'takassi@gmail.com', 2, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('patient1', '202cb962ac59075b964b07152d234b70', '0915568374', 'anokae@gmail.com', 2, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('patient2', '202cb962ac59075b964b07152d234b70', '0913826413', 'aizawwa@gmail.com', 2, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('patient3', '202cb962ac59075b964b07152d234b70', '0316281736', 'akdiwww@gmail.com', 2, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('staff1', '202cb962ac59075b964b07152d234b70', '1234567890', 'user1@example.com', 3, 1); 
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('staff2', '202cb962ac59075b964b07152d234b70', '0987654321', 'user2@example.com', 3, 1); 
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('staff3', '202cb962ac59075b964b07152d234b70', '9876543210', 'user3@example.com', 3, 1); 

INSERT INTO patient (username, url, name, gender, dob, rank_id) VALUES ('patient', NULL, 'John Doe', 'Male', '1990-01-01', 1);
INSERT INTO patient (username, url, name, gender, dob, rank_id) VALUES ('patient1', NULL, 'Sarah Lee', 'Female', '1985-05-05', 2);
INSERT INTO patient (username, url, name, gender, dob, rank_id) VALUES ('patient2', NULL, 'Peter Kim', 'Male', '1978-06-06', 3);
INSERT INTO patient (username, url, name, gender, dob, rank_id) VALUES ('patient3', NULL, 'Ngân', 'Female', '1992-02-02', 2);
-- Add more patients as needed
 -- Doctor table INSERT statements
INSERT INTO doctor (username, url, name, gender, dob, specialty, rank_id) VALUES ('doctor', NULL, 'Dr Smith', 'Male', '1980-03-03', 1, 1);
INSERT INTO doctor (username, url, name, gender, dob, specialty, rank_id) VALUES ('doctor1', NULL, 'Dr Park', 'Female', '1975-07-07', 1, 2);
INSERT INTO doctor (username, url, name, gender, dob, specialty, rank_id) VALUES ('doctor2', NULL, 'Dr Lee', 'Male', '1969-08-08', 2, 3);
INSERT INTO doctor (username, url, name, gender, dob, specialty, rank_id) VALUES ('doctor3', NULL, 'Thành', 'Female', '1982-04-04', 2, 2);
INSERT INTO doctor (username, url, name, gender, dob, specialty, rank_id) VALUES ('doctor4', NULL, 'Quyết', 'Female', '1982-04-04', 3, 2);
INSERT INTO doctor (username, url, name, gender, dob, specialty, rank_id) VALUES ('doctor5', NULL, 'Tuấn', 'Female', '1982-04-04', 3, 2);

INSERT INTO staff (username, url, name, gender, dob) VALUES ('staff1', 'www.example.com/staff1', 'John Does', 'Male', '1990-01-01'); 
INSERT INTO staff (username, url, name, gender, dob) VALUES ('staff2', 'www.example.com/staff2', 'Jane Smith', 'Female', '1985-05-10'); 
INSERT INTO staff (username, url, name, gender, dob) VALUES ('staff3', 'www.example.com/staff3', 'Michael Johnson', 'Male', '1982-11-15'); 
