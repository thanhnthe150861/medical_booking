drop database medical_booking;
create database medical_booking;
use medical_booking;
ALTER DATABASE medical_booking CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

	CREATE TABLE account(
		username varchar(50) NOT NULL,
		password varchar(50) NOT NULL,
		phone varchar(20) NOT NULL,
		email varchar(100) NOT NULL,
		isAdmin int NOT NULL,
		status bit NOT NULL,
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
        url varchar(500),
		name varchar(150) NOT NULL,
		gender varchar(10),
		dob date,
		rank_id int NULL,
		PRIMARY KEY (id)
	);
	 CREATE TABLE doctor (
		id int auto_increment,
		username varchar(50) NOT NULL,
        url varchar(500),
		name varchar(150) NOT NULL,
		gender varchar(10),
		dob date,
		specialty varchar(50),
		rank_id int NULL,
		PRIMARY KEY (id)
	);
	CREATE TABLE staff (
		id int auto_increment,
		username varchar(50) NOT NULL,
        url varchar(500),
		name varchar(150) NOT NULL,
		gender varchar(10),
		dob date,
		PRIMARY KEY (id)
	);
	 CREATE TABLE booking (
		id int auto_increment,
		doctor_id int,
		patient_id int,
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
	ALTER TABLE patient ADD CONSTRAINT fk_patient_rank FOREIGN KEY (rank_id) REFERENCES rank_patient (id);
	ALTER TABLE patient ADD CONSTRAINT fk_patient_account FOREIGN KEY (username) REFERENCES account (username);
	ALTER TABLE doctor ADD CONSTRAINT fk_doctor_rank FOREIGN KEY (rank_id) REFERENCES rank_doctor (id);
	ALTER TABLE doctor ADD CONSTRAINT fk_doctor_account FOREIGN KEY (username) REFERENCES account (username);
	ALTER TABLE staff ADD CONSTRAINT fk_staff_account FOREIGN KEY (username) REFERENCES account (username);
	ALTER TABLE medical_record ADD CONSTRAINT fk_medical_record_booking FOREIGN KEY (booking_id) REFERENCES booking (id);
	ALTER TABLE booking ADD CONSTRAINT fk_booking_slot FOREIGN KEY (slot_id) REFERENCES slot (id);
	ALTER TABLE booking ADD CONSTRAINT fk_booking_patient FOREIGN KEY (patient_id) REFERENCES patient (id);
	ALTER TABLE booking ADD CONSTRAINT fk_booking_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id);



INSERT INTO role (id, name) VALUES (0, 'Admin'),(1, 'Doctor'),(2, 'Patient'), (3, 'Staff');
INSERT INTO rank_patient (id, name) VALUES (1, 'Đồng'),(2, 'Bạc'),(3, 'Vàng'),(4, 'Kim Cương');
INSERT INTO rank_doctor (id, name) VALUES (1, 'Bác Sĩ Hạng 1'),(2, 'Bác Sĩ Hạng 2'),(3, 'Bác Sĩ Hạng 3'),(4, 'Bác Sĩ Hạng 4');
INSERT INTO slot (id, name) VALUES (1, '9:00 - 9:30'),(2, '10:00 - 10:30'),(3, '11:00 - 11:30'),(4, '14:00 - 14:30'),(5, '15:00 - 15:30'),(6, '16:00 - 16:30');



INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('admin', '123', '1234567890', 'thanh17042001@gmail.com', 0, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('doctor', '123', '0123548293', 'jaod01@gmail.com', 1, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('doctor1', '123', '0982783817', 'olika@gmail.com', 1, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('doctor2', '123', '0887462736', 'akasi@gmail.com', 1, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('doctor3', '123', '0826136264', 'naruto@gmail.com', 1, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('patient', '123', '0916728374', 'takassi@gmail.com', 2, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('patient1', '123', '0915568374', 'anokae@gmail.com', 2, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('patient2', '123', '0913826413', 'aizawwa@gmail.com', 2, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('patient3', '123', '0316281736', 'akdiwww@gmail.com', 2, 1);
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('staff1', '123', '1234567890', 'user1@example.com', 3, 1); 
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('staff2', '123', '0987654321', 'user2@example.com', 3, 1); 
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('staff3', '123', '9876543210', 'user3@example.com', 3, 1); 
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('staff4', '123', '0123456789', 'user4@example.com', 3, 1); 
INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES ('staff5', '123', '5432109876', 'user5@example.com', 3, 1); 
INSERT INTO account (username, password, phone, email, isAdmin, status) 
VALUES 
    ('doctor_user1', 'doctor_pass1', '0987654321', 'doctor1@example.com', 1, 1),
    ('doctor_user2', 'doctor_pass2', '0111222333', 'doctor2@example.com', 1, 1),
    ('doctor_user3', 'doctor_pass3', '0222222220', 'doctor3@example.com', 1, 1),
    ('doctor_user4', 'doctor_pass4', '0222222222', 'doctor4@example.com', 1, 1),
    ('doctor_user5', 'doctor_pass5', '0333333333', 'doctor5@example.com', 1, 1),
	('doctor_user6', 'doctor_pass6', '0987654322', 'doctor6@example.com', 1, 1),
    ('doctor_user7', 'doctor_pass7', '0111222334', 'doctor7@example.com', 1, 1),
    ('doctor_user8', 'doctor_pass8', '0222222224', 'doctor8@example.com', 1, 1),
    ('doctor_user9', 'doctor_pass9', '0222222225', 'doctor9@example.com', 1, 1),
    ('doctor_user10', 'doctor_pass10', '0333333334', 'doctor10@example.com', 1, 1),
    ('patient_user1', 'patient_pass1', '0555444666', 'patient1@example.com', 2, 1),
    ('patient_user2', 'patient_pass2', '0777888999', 'patient2@example.com', 2, 1),
	('patient_user3', 'patient_pass3', '0333333330', 'patient3@example.com', 2, 1),
    ('patient_user4', 'patient_pass4', '0444444444', 'patient4@example.com', 2, 1),
    ('patient_user5', 'patient_pass5', '0555555555', 'patient5@example.com', 2, 1),
    ('patient_user6', 'patient_pass6', '0555444667', 'patient6@example.com', 2, 1),
    ('patient_user7', 'patient_pass7', '0777888990', 'patient7@example.com', 2, 1),
	('patient_user8', 'patient_pass8', '0333333331', 'patient8@example.com', 2, 1),
    ('patient_user9', 'patient_pass9', '0444444445', 'patient9@example.com', 2, 1),
    ('patient_user10', 'patient_pass10', '0555555556', 'patient10@example.com', 2, 1),
    ('staff_user1', 'staff_pass1', '0222333444', 'staff1@example.com', 3, 1),
    ('staff_user2', 'staff_pass2', '0999888777', 'staff2@example.com', 3, 1),
    ('staff_user3', 'staff_pass3', '0444444440', 'staff3@example.com', 3, 1),
    ('staff_user4', 'staff_pass4', '0666666666', 'staff4@example.com', 3, 1),
    ('staff_user5', 'staff_pass5', '0777777777', 'staff5@example.com', 3, 1),
    ('staff_user6', 'staff_pass6', '0222333445', 'staff6@example.com', 3, 1),
    ('staff_user7', 'staff_pass7', '0999888778', 'staff7@example.com', 3, 1),
    ('staff_user8', 'staff_pass8', '0444444441', 'staff8@example.com', 3, 1),
    ('staff_user9', 'staff_pass9', '0666666667', 'staff9@example.com', 3, 1),
    ('staff_user10', 'staff_pass10', '0777777778', 'staff10@example.com', 3, 1);



INSERT INTO patient (username, url, name, gender, dob, rank_id)
VALUES 
    ('patient_user1', 'url_patient1', 'Nguyễn Văn K', 'Nam', '1998-07-12', 2),
    ('patient_user2', 'url_patient2', 'Trần Thị L', 'Nữ', '2002-02-25', 1),
    ('patient_user3', 'url_patient3', 'Lê Văn M', 'Nam', '1995-11-08', 3),
    ('patient_user4', 'url_patient4', 'Phạm Thị N', 'Nữ', '1993-06-30', 4),
    ('patient_user5', 'url_patient5', 'Hoàng Văn P', 'Nam', '2000-09-18', 2),
    ('patient_user6', 'url_patient6', 'Nguyễn Văn F', 'Nam', '1994-04-01', 1),
    ('patient_user7', 'url_patient7', 'Trần Thị G', 'Nữ', '1999-10-12', 3),
    ('patient_user8', 'url_patient8', 'Lê Văn H', 'Nam', '1992-03-22', 2),
    ('patient_user9', 'url_patient9', 'Phạm Thị I', 'Nữ', '1989-08-06', 4),
    ('patient_user10', 'url_patient10', 'Hoàng Văn J', 'Nam', '1997-12-15', 3);



INSERT INTO doctor (username, url, name, gender, dob, specialty, rank_id)
VALUES 
    ('doctor_user1', 'url_doctor1', 'Nguyễn Văn X', 'Nam', '1975-03-05', 'Bác sĩ chuyên khoa Tai Mũi Họng', 3),
    ('doctor_user2', 'url_doctor2', 'Trần Thị Y', 'Nữ', '1981-06-20', 'Bác sĩ chuyên khoa Tai Mũi Họng', 2),
    ('doctor_user3', 'url_doctor3', 'Lê Văn Z', 'Nam', '1979-09-15', 'Bác sĩ chuyên khoa Tai Mũi Họng', 1),
    ('doctor_user4', 'url_doctor4', 'Phạm Thị T', 'Nữ', '1984-11-25', 'Bác sĩ chuyên khoa Tai Mũi Họng', 4),
    ('doctor_user5', 'url_doctor5', 'Hoàng Văn S', 'Nam', '1987-04-10', 'Bác sĩ chuyên khoa Tai Mũi Họng', 2),
    ('doctor_user6', 'url_doctor6', 'Nguyễn Văn K', 'Nam', '1976-05-18', 'Bác sĩ chuyên khoa Tai Mũi Họng', 2),
    ('doctor_user7', 'url_doctor7', 'Trần Thị L', 'Nữ', '1980-11-30', 'Bác sĩ chuyên khoa Tai Mũi Họng', 4),
    ('doctor_user8', 'url_doctor8', 'Lê Văn M', 'Nam', '1983-02-14', 'Bác sĩ chuyên khoa Tai Mũi Họng', 1),
    ('doctor_user9', 'url_doctor9', 'Phạm Thị N', 'Nữ', '1978-07-25', 'Bác sĩ chuyên khoa Tai Mũi Họng', 3),
    ('doctor_user10', 'url_doctor10', 'Hoàng Văn P', 'Nam', '1985-09-08', 'Bác sĩ chuyên khoa Tai Mũi Họng', 4);


INSERT INTO staff (username, url, name, gender, dob) VALUES ('staff1', 'www.example.com/staff1', 'John Doe', 'Male', '1990-01-01'); 
INSERT INTO staff (username, url, name, gender, dob) VALUES ('staff2', 'www.example.com/staff2', 'Jane Smith', 'Female', '1985-05-10'); 
INSERT INTO staff (username, url, name, gender, dob) VALUES ('staff3', 'www.example.com/staff3', 'Michael Johnson', 'Male', '1982-11-15'); 
INSERT INTO staff (username, url, name, gender, dob) VALUES ('staff4', 'www.example.com/staff4', 'Emily Davis', 'Female', '1993-07-20'); 
INSERT INTO staff (username, url, name, gender, dob) VALUES ('staff5', 'www.example.com/staff5', 'Robert Wilson', 'Male', '1978-03-25');
INSERT INTO staff (username, url, name, gender, dob)
VALUES 
    ('staff_user1', 'url_staff1', 'Nguyễn Văn A', 'Nam', '1980-01-10'),
    ('staff_user2', 'url_staff2', 'Trần Thị B', 'Nữ', '1985-05-15'),
    ('staff_user3', 'url_staff3', 'Lê Văn C', 'Nam', '1990-08-20'),
    ('staff_user4', 'url_staff4', 'Phạm Thị D', 'Nữ', '1982-12-25'),
    ('staff_user5', 'url_staff5', 'Hoàng Văn E', 'Nam', '1988-09-30'),
    ('staff_user6', 'url_staff6', 'Nguyễn Văn Q', 'Nam', '1986-04-09'),
    ('staff_user7', 'url_staff7', 'Trần Thị R', 'Nữ', '1991-10-20'),
    ('staff_user8', 'url_staff8', 'Lê Văn S', 'Nam', '1984-03-05'),
    ('staff_user9', 'url_staff9', 'Phạm Thị T', 'Nữ', '1987-08-16'),
    ('staff_user10', 'url_staff10', 'Hoàng Văn U', 'Nam', '1993-12-27');

INSERT INTO booking (doctor_id, patient_id, slot_id, booking_reason, date, status) VALUES (1, 1, 1, 'Viêm tai giữa', '2023-06-22', 'Completed');
INSERT INTO booking (doctor_id, patient_id, slot_id, booking_reason, date, status)
VALUES 
    (2, 5, 1, 'Viêm họng', '2023-07-01', 'Completed'),
    (3, 2, 5, 'Dị ứng mũi', '2023-07-02', 'Completed'),
    (1, 4, 6, 'Viêm xoang', '2023-07-05', 'Completed'),
    (4, 5, 1, 'Viêm amidan', '2023-07-06', 'Completed'),
    (2, 2, 1, 'Viêm amidan', '2023-07-07', 'Completed'),
    (4, 1, 6, 'Viêm tai giữa', '2023-07-11', 'Completed'),
    (3, 5, 5, 'Viêm xoang', '2023-07-12', 'Completed'),
    (1, 3, 3, 'Viêm họng', '2023-07-13', 'Completed'),
    (5, 3, 2, 'Viêm họng', '2023-07-14', 'Completed'),
    (4, 1, 5, 'Viêm tai giữa', '2023-07-14', 'Completed'),
    (1, 5, 2, 'Viêm xoang', '2023-07-15', 'Completed'),
    (3, 4, 1, 'Dị ứng mũi', '2023-07-20', 'Completed'),
    (2, 4, 4, 'Dị ứng mũi', '2023-07-09', 'Completed'),
    (4, 1, 6, 'Viêm tai giữa', '2023-07-11', 'Confirmed'),
	(7, 4, 6, 'Viêm tai giữa', '2023-07-12', 'Completed'),
    (5, 4, 2, 'Dị ứng mũi', '2023-09-03', 'Confirmed'),
    (3, 5, 3, 'Viêm xoang', '2023-09-04', 'Confirmed'),
    (2, 1, 4, 'Viêm tai giữa', '2023-09-05', 'Confirmed'),
    (4, 3, 5, 'Viêm amidan', '2023-09-06', 'Confirmed'),
    (1, 2, 6, 'Viêm họng', '2023-09-07', 'Confirmed'),
    (5, 4, 1, 'Dị ứng mũi', '2023-09-08', 'Confirmed'),
    (2, 3, 2, 'Viêm xoang', '2023-09-09', 'Confirmed'),
    (4, 5, 3, 'Viêm tai giữa', '2023-09-10', 'Confirmed'),
    (5, 1, 3, 'Viêm amidan', '2023-08-16', 'Cancelled'),
    (5, 1, 5, 'Viêm tai giữa', '2023-08-12', 'Cancelled'),
    (3, 3, 2, 'Viêm họng', '2023-07-26', 'Cancelled'),
    (1, 1, 1, 'Viêm amidan', '2023-07-22', 'Confirmed'),
    (2, 2, 3, 'Dị ứng mũi', '2023-07-24', 'Pending'),
    (1, 2, 5, 'Viêm xoang', '2023-07-25', 'Confirmed'),
    (4, 4, 4, 'Viêm tai giữa', '2023-07-27', 'Pending'),
    (2, 1, 6, 'Viêm amidan', '2023-07-28', 'Confirmed'),
    (5, 3, 1, 'Dị ứng mũi', '2023-07-29', 'Confirmed'),
    (1, 5, 3, 'Viêm họng', '2023-07-30', 'Pending'),
    (3, 2, 4, 'Viêm xoang', '2023-08-01', 'Confirmed'),
    (4, 1, 5, 'Viêm tai giữa', '2023-08-02', 'Pending'),
    (2, 4, 2, 'Viêm họng', '2023-08-03', 'Pending'),
    (3, 5, 3, 'Dị ứng mũi', '2023-08-04', 'Confirmed'),
    (1, 3, 4, 'Viêm xoang', '2023-08-05', 'Pending'),
    (5, 2, 5, 'Viêm amidan', '2023-08-06', 'Confirmed'),
    (4, 1, 6, 'Viêm tai giữa', '2023-08-07', 'Pending'),
    (2, 5, 1, 'Viêm họng', '2023-08-08', 'Confirmed'),
    (3, 2, 2, 'Viêm xoang', '2023-08-09', 'Confirmed'),
    (1, 4, 3, 'Dị ứng mũi', '2023-08-10', 'Pending'),
    (4, 3, 4, 'Viêm amidan', '2023-08-11', 'Confirmed'),
    (5, 1, 5, 'Viêm tai giữa', '2023-08-12', 'Cancelled'),
    (2, 3, 6, 'Viêm họng', '2023-08-13', 'Confirmed'),
    (3, 4, 1, 'Dị ứng mũi', '2023-08-14', 'Pending'),
    (1, 5, 2, 'Viêm xoang', '2023-08-15', 'Confirmed'),
    (5, 1, 3, 'Viêm amidan', '2023-08-16', 'Cancelled'),
    (4, 2, 4, 'Viêm tai giữa', '2023-08-17', 'Confirmed'),
    (2, 4, 5, 'Viêm họng', '2023-08-18', 'Confirmed'),
    (3, 5, 6, 'Dị ứng mũi', '2023-08-19', 'Confirmed'),
    (1, 3, 1, 'Viêm xoang', '2023-08-20', 'Confirmed'),
    (5, 2, 2, 'Viêm amidan', '2023-08-21', 'Confirmed'),
    (4, 1, 3, 'Viêm tai giữa', '2023-08-22', 'Confirmed');
INSERT INTO booking (doctor_id, patient_id, slot_id, booking_reason, date, status) VALUES (2, 2, 2, 'Viêm họng', '2023-06-23', 'Confirmed');
INSERT INTO booking (doctor_id, patient_id, slot_id, booking_reason, date, status) VALUES (3, 3, 3, 'Vaccination', '2023-06-22', 'Confirmed');
INSERT INTO booking (doctor_id, patient_id, slot_id, booking_reason, date, status) VALUES (4, 4, 4, 'Sổ mũi hắt hơi', '2023-06-23', 'Confirmed');
INSERT INTO booking (doctor_id, patient_id, slot_id, booking_reason, date, status) VALUES (1, 2, 3, 'Annual check-up', '2023-06-23', 'Pending');


 -- patient table INSERT statements
INSERT INTO patient (username, url, name, gender, dob, rank_id) VALUES ('patient', NULL, 'John Doe', 'Male', '1990-01-01', 1);
INSERT INTO patient (username, url, name, gender, dob, rank_id) VALUES ('patient1', NULL, 'Sarah Lee', 'Female', '1985-05-05', 2);
INSERT INTO patient (username, url, name, gender, dob, rank_id) VALUES ('patient2', NULL, 'Peter Kim', 'Male', '1978-06-06', 3);
INSERT INTO patient (username, url, name, gender, dob, rank_id) VALUES ('patient3', NULL, 'Ngân', 'Female', '1992-02-02', 2);
-- Add more patients as needed
 -- Doctor table INSERT statements
INSERT INTO doctor (username, url, name, gender, dob, specialty, rank_id) VALUES ('doctor', NULL, 'Dr. Smith', 'Male', '1980-03-03', 'Bác Sĩ Chuyên Khoa Mũi Họng', 1);
INSERT INTO doctor (username, url, name, gender, dob, specialty, rank_id) VALUES ('doctor1', NULL, 'Dr. Park', 'Female', '1975-07-07', 'Bác Sĩ Chuyên Khoa Mũi', 2);
INSERT INTO doctor (username, url, name, gender, dob, specialty, rank_id) VALUES ('doctor2', NULL, 'Dr. Lee', 'Male', '1969-08-08', 'Bác Sĩ Chuyên Khoa Tai', 3);
INSERT INTO doctor (username, url, name, gender, dob, specialty, rank_id) VALUES ('doctor3', NULL, 'Thành', 'Female', '1982-04-04', 'Bác Sĩ Chuyên Khoa Tai Họng', 2);
-- Add more doctors as needed
-- Add more bookings as needed
 -- Medical_record table INSERT statements
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (1, 'Khỏe mạnh', NULL, NULL);
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (2, 'Phản ứng dị ứng', NULL, 'Thuốc chống dị ứng');
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (3, 'Khỏe mạnh', NULL, NULL);
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (4, 'Ung thư', NULL, 'Hóa trị');
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (5, 'Viêm Họng', NULL, 'Thuốc Ho');
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (6, 'Sốt cao', NULL, 'Paracetamol');
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (7, 'Béo phì', NULL, 'Kiêng ăn đường và tập luyện');
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (8, 'Đau lưng', 'http://example.com/scan001', 'Thuốc giảm đau');
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (9, 'Đau dạ dày', 'http://example.com/endoscopy004', 'Thuốc kháng acid');
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (10, 'Trầm cảm', NULL, 'Liều dùng thuốc theo hướng dẫn bác sĩ');
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (11, 'Tiểu đường', NULL, 'Insulin');
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (12, 'Loét dạ dày', 'http://example.com/gastroscopy005', 'Thuốc chống loét');
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (13, 'Viêm xoang', NULL, 'Kháng sinh');
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (14, 'Đau đầu', NULL, 'Paracetamol');
INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (15, 'Covid-19', 'http://example.com/covid_test_result007', 'Ivermectin');

-- Add more medical records as needed
 -- Bill table INSERT statements
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) VALUES (1, 'Paid', NULL, 120.0, 220.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) VALUES (2, 'Unpaid', 120.0, 120.0, 240.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) VALUES (3, 'Unpaid', NULL, 110.0, 110.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) VALUES (4, 'Paid', 100.0, 120.0, 220.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) 
VALUES (5, 'Paid', 100.0, 120.0, 220.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) 
VALUES (6, 'Unpaid', 120.0, 120.0, 240.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) 
VALUES (7, 'Unpaid', 150.0, 0.0, 150.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) 
VALUES (8, 'Paid', 100.0, 120.0, 220.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) 
VALUES (9, 'Unpaid', 90.0, 100.0, 190.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) 
VALUES (10, 'Paid', 80.0, 150.0, 230.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) 
VALUES (11, 'Unpaid', 110.0, 100.0, 210.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) 
VALUES (12, 'Paid', 90.0, 80.0, 170.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) 
VALUES (13, 'Unpaid', 120.0, 150.0, 270.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) 
VALUES (14, 'Paid', 130.0, 90.0, 220.0);
INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) 
VALUES (15, 'Paid', 100.0, 110.0, 210.0);







