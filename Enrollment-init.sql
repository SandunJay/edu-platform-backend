-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS `enrollment_service`;

-- Switch to the created database
USE `enrollment_service`;

-- Create the 'enrollments' table
CREATE TABLE IF NOT EXISTS `enrollments` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `userId` VARCHAR(255) NOT NULL,
    `enrollmentDate` DATETIME NOT NULL
    );

-- Create the 'enrollment_items' table
CREATE TABLE IF NOT EXISTS `enrollment_items` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `courseId` VARCHAR(255) NOT NULL,
    `isCompleted` BOOLEAN NOT NULL DEFAULT FALSE,
    `enrollment_id` BIGINT,
    FOREIGN KEY (`enrollment_id`) REFERENCES `enrollments`(`id`) ON DELETE CASCADE
    );

-- Create the 'progress_tracker' table
CREATE TABLE IF NOT EXISTS `progress_tracker` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `contentId` VARCHAR(255) NOT NULL,
    `addedDate` DATETIME,
    `lastUpdatedDate` DATETIME,
    `enrollment_item_id` BIGINT,
    FOREIGN KEY (`enrollment_item_id`) REFERENCES `enrollment_items`(`id`) ON DELETE CASCADE
    );

-- Insert sample data into 'enrollments'
INSERT INTO `enrollments` (`userId`, `enrollmentDate`)
VALUES
    ('user1', '2023-04-01 10:00:00'),
    ('user2', '2023-05-01 11:00:00');

-- Insert sample data into 'enrollment_items'
INSERT INTO `enrollment_items` (`courseId`, `isCompleted`, `enrollment_id`)
VALUES
    ('course1', FALSE, 1),
    ('course2', TRUE, 2);

-- Insert sample data into 'progress_tracker'
INSERT INTO `progress_tracker` (`contentId`, `addedDate`, `lastUpdatedDate`, `enrollment_item_id`)
VALUES
    ('content1', '2023-04-01 10:00:00', '2023-04-02 10:00:00', 1),
    ('content2', '2023-05-01 11:00:00', '2023-05-02 11:00:00', 2);
