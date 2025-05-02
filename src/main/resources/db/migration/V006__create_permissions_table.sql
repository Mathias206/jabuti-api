CREATE TABLE `permissions`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `permission_name` VARCHAR(255) NOT NULL,
    `permission_description` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY(`id`)
);