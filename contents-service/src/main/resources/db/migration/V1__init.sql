CREATE TABLE `contents` (
   `id` VARCHAR(255) NOT NULL,
   `title` VARCHAR(255) DEFAULT NULL,
   `description` VARCHAR(255) DEFAULT NULL,
   `courseId` VARCHAR(255),
   `videoUrl` VARCHAR(255) DEFAULT NULL,
   `pdfUrl` VARCHAR(255) DEFAULT NULL,
   `createdDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `lastUpdatedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
);



-- Insert data into the contents table
INSERT INTO contents (id, title, description, courseId, videoUrl, pdfUrl, createdDate, lastUpdatedDate) VALUES
    ('1', 'Introduction to Java', 'An introductory course to Java programming.', 'IT8664', 'http://example.com/video1', 'http://example.com/pdf1', '2023-01-01 10:00:00', '2023-01-01 10:00:00'),
    ('2', 'Advanced Java Concepts', 'A course on advanced Java programming topics.', 'course-301', 'http://example.com/video2', 'http://example.com/pdf2', '2023-02-01 11:00:00', '2023-02-01 11:00:00'),
    ('3', 'Spring Boot Basics', 'Learn the basics of Spring Boot framework.', 'course-401', 'http://example.com/video3', 'http://example.com/pdf3', '2023-03-01 12:00:00', '2023-03-01 12:00:00'),
    ('4', 'Microservices Architecture', 'Understanding the architecture of microservices.', 'course-101', 'http://example.com/video4', 'http://example.com/pdf4', '2023-04-01 13:00:00', '2023-04-01 13:00:00'),
    ('5', 'Database Design', 'Introduction to database design principles.', 'course-201', 'http://example.com/video5', 'http://example.com/pdf5', '2023-05-01 14:00:00', '2023-05-01 14:00:00');