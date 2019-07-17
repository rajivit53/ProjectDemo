DROP DATABASE  IF EXISTS `guest_directory`;

CREATE DATABASE  IF NOT EXISTS `guest_directory`;
USE `guest_directory`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `users` 
VALUES 
('rajiv','{noop}test123',1),
('ansh','{noop}test123',1),
('ayush','{noop}test123',1);



--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('rajiv','ROLE_USER'),
('ansh','ROLE_ADMIN'),
('ayush','ROLE_NA');



DROP TABLE IF EXISTS `entry`;
CREATE TABLE `entry` (
  `id` int(10)  NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NULL,
  `txt_entry` varchar(100)  NULL,
  `img_entry` longblob  NULL,
  `img_type` varchar(10)  NULL,
  `last_update` DateTime  NULL,
  `is_approved` tinyint(1) NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



