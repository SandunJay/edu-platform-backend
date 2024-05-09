CREATE TABLE `t_orders` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `order_number` varchar(255) DEFAULT NULL,
   `user_id` varchar(255),
   `total_price` decimal(19,2),
   `order_date` date DEFAULT NULL,
   PRIMARY KEY (`id`)
);

CREATE TABLE `order_course_ids` (
    `order_id` bigint(20) NOT NULL,
    `course_id` bigint(255),
    FOREIGN KEY (`order_id`) REFERENCES `t_orders` (`id`)
);

