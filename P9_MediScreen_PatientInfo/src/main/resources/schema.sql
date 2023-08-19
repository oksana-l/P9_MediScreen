CREATE TABLE IF NOT EXISTS patient
(
id INT PRIMARY KEY,
first_name VARCHAR(255),
last_name VARCHAR(255),
date_of_birth DATE,
gender VARCHAR(10),
address VARCHAR(255),
phone_number VARCHAR(20)
);
