CREATE TABLE `contents` (
  `id` VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `courseId` VARCHAR(255),
  `videoUrl` VARCHAR(255) DEFAULT NULL,
  `pdfUrl` VARCHAR(255) DEFAULT NULL,
  `status` BOOLEAN DEFAULT FALSE,
  `createdDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastUpdatedDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);
