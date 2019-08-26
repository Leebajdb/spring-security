-- -----------------
-- Create user table
-- -----------------

CREATE TABLE `auth_user` (
 `id` BIGINT NOT NULL AUTO_INCREMENT,
 `first_name` VARCHAR(50) NOT NULL,
`last_name` VARCHAR(50),
`email` VARCHAR(100) NOT NULL,
`password` VARCHAR(255) NOT NULL,
`role` ENUM('ADMIN','USER') NOT NULL,
`is_deleted` TINYINT(2) DEFAULT 0,
`last_logged_in` DATETIME NOT NULL,
`created_on` DATETIME NOT NULL,
`updated_on` DATETIME,
`deleted_on` DATETIME,
PRIMARY KEY (`id`),
UNIQUE INDEX `email_UNIQUE` (`email` ASC),
UNIQUE INDEX `id_UNIQUE` (`id` ASC));

-- ----------------------
-- Insert into user table
-- ----------------------
INSERT INTO `auth_user` (
`id`,
`first_name`,
`last_name`,
`email`,
`password`,
`role`,
`last_logged_in`, `created_on`)
VALUES ('1',
'Leeba',
'Babu',
'admin@example.in',
'$2a$10$rwf.X6ybFQpTgOHRV.Lp5uzd9hOD8FaWDyU1lBzSad76bjaRYq53O',
'ADMIN', '2019-08-13 13:27:58', '2019-08-13 13:28:00');