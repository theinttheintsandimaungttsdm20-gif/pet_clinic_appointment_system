SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS appointment_service;
DROP TABLE IF EXISTS pet_drug_join;
DROP TABLE IF EXISTS vocher;
DROP TABLE IF EXISTS pet_drug;
DROP TABLE IF EXISTS appointment;
DROP TABLE IF EXISTS schedule_service;
DROP TABLE IF EXISTS doctor_schedule;
DROP TABLE IF EXISTS doctor_service;
DROP TABLE IF EXISTS doctor;
DROP TABLE IF EXISTS drug;
DROP TABLE IF EXISTS drug_type;
DROP TABLE IF EXISTS pet;
DROP TABLE IF EXISTS owner;
DROP TABLE IF EXISTS payment_type;
DROP TABLE IF EXISTS pet_login;
DROP TABLE IF EXISTS rank;
DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS service_type;
DROP TABLE IF EXISTS species;




/* Create Tables */

CREATE TABLE appointment
(
	appointment_id int NOT NULL AUTO_INCREMENT,
	appointment_date date NOT NULL,
	appointment_time time,
	appointment_info varchar(200) NOT NULL,
	owner_id int NOT NULL,
	schedule_id int NOT NULL,
	doctor_id int NOT NULL,
	pet_id int NOT NULL,
	PRIMARY KEY (appointment_id)
);


CREATE TABLE appointment_service
(
	service_id int NOT NULL,
	appointment_id int NOT NULL
);


CREATE TABLE doctor
(
	doctor_id int NOT NULL AUTO_INCREMENT,
	doctor_name varchar(25) NOT NULL,
	doctor_gmail varchar(50) NOT NULL,
	doctor_address varchar(100) NOT NULL,
	doctor_about varchar(200) NOT NULL,
	doctor_gender enum('M','F') NOT NULL,
	doctor_phone varchar(20) NOT NULL,
	rank_id int NOT NULL,
	PRIMARY KEY (doctor_id)
);


CREATE TABLE doctor_schedule
(
	schedule_id int NOT NULL AUTO_INCREMENT,
	day_name enum('MON','TUE','WED','THU','FRI','SAT','SUN'),
	start_time time,
	end_time time,
	doctor_id int NOT NULL,
	PRIMARY KEY (schedule_id)
);


CREATE TABLE doctor_service
(
	service_type_id int NOT NULL,
	doctor_id int NOT NULL
);


CREATE TABLE drug
(
	drug_id int NOT NULL AUTO_INCREMENT,
	drug_name varchar(50) NOT NULL,
	drug_desc varchar(200) NOT NULL,
	drug_duration_type enum('none','day','week','month','year') NOT NULL,
	drug_duration_no int NOT NULL,
	drug_type_id int NOT NULL,
	PRIMARY KEY (drug_id)
);


CREATE TABLE drug_type
(
	drug_type_id int NOT NULL AUTO_INCREMENT,
	drug_type_name varchar(50) NOT NULL,
	drug_type_desc varchar(200) NOT NULL,
	PRIMARY KEY (drug_type_id)
);


CREATE TABLE owner
(
	owner_id int NOT NULL AUTO_INCREMENT,
	owner_name varchar(20) NOT NULL,
	owner_ph varchar(20) NOT NULL,
	owner_email varchar(50) NOT NULL,
	owner_add varchar(100) NOT NULL,
	owner_gender enum('M','F') NOT NULL,
	owner_address varchar(200) NOT NULL,
	PRIMARY KEY (owner_id)
);


CREATE TABLE payment_type
(
	payment_type_id int NOT NULL AUTO_INCREMENT,
	payment_type_name varchar(40) NOT NULL,
	PRIMARY KEY (payment_type_id)
);


CREATE TABLE pet
(
	pet_id int NOT NULL AUTO_INCREMENT,
	pet_name varchar(30),
	pet_sex enum('F','M'),
	pet_birth date,
	pet_death date,
	species_id int NOT NULL,
	owner_id int NOT NULL,
	PRIMARY KEY (pet_id)
);


CREATE TABLE pet_drug
(
	pet_drug_id int NOT NULL AUTO_INCREMENT,
	drug_date date,
	drug_next_date date,
	drug_desc varchar(50),
	pet_id int NOT NULL,
	doctor_id int NOT NULL,
	appointment_id int NOT NULL,
	PRIMARY KEY (pet_drug_id)
);


CREATE TABLE pet_drug_join
(
	drug_id int NOT NULL,
	pet_drug_id int NOT NULL
);


CREATE TABLE pet_login
(
	login_id varchar(20) NOT NULL,
	password varchar(150) NOT NULL,
	user_name varchar(40) NOT NULL,
	PRIMARY KEY (login_id)
);


CREATE TABLE rank
(
	rank_id int NOT NULL AUTO_INCREMENT,
	rank_name varchar(40) NOT NULL,
	short_term varchar(20) NOT NULL,
	PRIMARY KEY (rank_id)
);


CREATE TABLE schedule_service
(
	service_type_id int NOT NULL,
	schedule_id int NOT NULL
);


CREATE TABLE service
(
	service_id int NOT NULL AUTO_INCREMENT,
	service_name varchar(60) NOT NULL,
	service_point int NOT NULL,
	service_minute int NOT NULL,
	service_about varchar(200) NOT NULL,
	service_type_id int NOT NULL,
	PRIMARY KEY (service_id)
);


CREATE TABLE service_type
(
	service_type_id int NOT NULL AUTO_INCREMENT,
	service_type_name varchar(30) NOT NULL,
	average_time int NOT NULL,
	service_type_desc varchar(100) NOT NULL,
	PRIMARY KEY (service_type_id)
);


CREATE TABLE species
(
	species_id int NOT NULL AUTO_INCREMENT,
	species_name varchar(30),
	PRIMARY KEY (species_id)
);


CREATE TABLE vocher
(
	voucher_no int NOT NULL AUTO_INCREMENT,
	voucher_date date NOT NULL,
	total_amount int NOT NULL,
	vocher_desc varchar(200) CHARACTER SET utf8 NOT NULL,
	pet_drug_id int NOT NULL,
	payment_type_id int NOT NULL,
	PRIMARY KEY (voucher_no)
) ENGINE = InnoDB;



/* Create Foreign Keys */

ALTER TABLE appointment_service
	ADD FOREIGN KEY (appointment_id)
	REFERENCES appointment (appointment_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE pet_drug
	ADD FOREIGN KEY (appointment_id)
	REFERENCES appointment (appointment_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE appointment
	ADD FOREIGN KEY (doctor_id)
	REFERENCES doctor (doctor_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE doctor_schedule
	ADD FOREIGN KEY (doctor_id)
	REFERENCES doctor (doctor_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE doctor_service
	ADD FOREIGN KEY (doctor_id)
	REFERENCES doctor (doctor_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE pet_drug
	ADD FOREIGN KEY (doctor_id)
	REFERENCES doctor (doctor_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE appointment
	ADD FOREIGN KEY (schedule_id)
	REFERENCES doctor_schedule (schedule_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE schedule_service
	ADD FOREIGN KEY (schedule_id)
	REFERENCES doctor_schedule (schedule_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE pet_drug_join
	ADD FOREIGN KEY (drug_id)
	REFERENCES drug (drug_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE drug
	ADD FOREIGN KEY (drug_type_id)
	REFERENCES drug_type (drug_type_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE appointment
	ADD FOREIGN KEY (owner_id)
	REFERENCES owner (owner_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE pet
	ADD FOREIGN KEY (owner_id)
	REFERENCES owner (owner_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE vocher
	ADD FOREIGN KEY (payment_type_id)
	REFERENCES payment_type (payment_type_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE appointment
	ADD FOREIGN KEY (pet_id)
	REFERENCES pet (pet_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE pet_drug
	ADD FOREIGN KEY (pet_id)
	REFERENCES pet (pet_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE pet_drug_join
	ADD FOREIGN KEY (pet_drug_id)
	REFERENCES pet_drug (pet_drug_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE vocher
	ADD FOREIGN KEY (pet_drug_id)
	REFERENCES pet_drug (pet_drug_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE doctor
	ADD FOREIGN KEY (rank_id)
	REFERENCES rank (rank_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE appointment_service
	ADD FOREIGN KEY (service_id)
	REFERENCES service (service_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE doctor_service
	ADD FOREIGN KEY (service_type_id)
	REFERENCES service_type (service_type_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE schedule_service
	ADD FOREIGN KEY (service_type_id)
	REFERENCES service_type (service_type_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE service
	ADD FOREIGN KEY (service_type_id)
	REFERENCES service_type (service_type_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE pet
	ADD FOREIGN KEY (species_id)
	REFERENCES species (species_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



