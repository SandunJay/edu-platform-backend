-- Create the database if it not exists
CREATE DATABASE IF NOT EXISTS `jwt_security`;

-- Switch to the created database
USE `jwt_security`;

-- Create user table
CREATE TABLE `_user` (
  `id` int NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('USER','ADMIN','INSTRUCTOR') DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Create token table
CREATE TABLE `token` (
  `expired` bit(1) NOT NULL,
  `id` int NOT NULL,
  `revoked` bit(1) NOT NULL,
  `user_id` int DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_type` enum('BEARER') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pddrhgwxnms2aceeku9s2ewy5` (`token`),
  KEY `FKiblu4cjwvyntq3ugo31klp1c6` (`user_id`),
  CONSTRAINT `FKiblu4cjwvyntq3ugo31klp1c6` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`)
);

-- Insert data to _user table
INSERT INTO `jwt_security`.`_user`
(`id`, `email`, `firstname`, `lastname`, `password`, `role`)
VALUES
(1, "hansani@gmail.com", "Hansani", "Neththasinghe", "1234", "ADMIN"),
(2, "kasuni@gmail.com", "Kasuni", "Perera", "1234", "USER"),
(3, "malshan@gmail.com", "Malshan", "Rathnayake", "1234", "INSTRUCTOR");
