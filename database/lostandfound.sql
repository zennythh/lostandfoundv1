-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2025 at 02:30 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lostandfound`
--

-- --------------------------------------------------------

--
-- Table structure for table `conversation`
--

CREATE TABLE `conversation` (
  `id` bigint(20) NOT NULL,
  `user1_id` bigint(20) NOT NULL,
  `user2_id` bigint(20) NOT NULL,
  `user_low_id` bigint(20) NOT NULL,
  `user_high_id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `conversation`
--

INSERT INTO `conversation` (`id`, `user1_id`, `user2_id`, `user_low_id`, `user_high_id`, `created_at`) VALUES
(1, 2, 1, 1, 2, '2025-04-16 21:15:09'),
(2, 2, 3, 2, 3, '2025-04-16 21:25:58');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `item_id` bigint(20) NOT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `campus` enum('Bagong_Silang','Camarin','Congress','South') NOT NULL,
  `category` enum('Clothes_and_Accessories','Documents','Electronics','Others','Personal_Items') NOT NULL,
  `deleted` bit(1) NOT NULL,
  `description` varchar(255) NOT NULL,
  `found_on` datetime(6) NOT NULL,
  `location` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `reported_on` datetime(6) NOT NULL,
  `status` enum('Claimed','Found','Lost','Returned') NOT NULL,
  `image_path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `author_id`, `campus`, `category`, `deleted`, `description`, `found_on`, `location`, `name`, `reported_on`, `status`, `image_path`) VALUES
(1, 1, 'Congress', 'Personal_Items', b'0', 'mahaba', '2020-01-18 12:00:00.000000', 'congress highway', 'panyo', '2020-01-18 00:00:00.000000', 'Lost', NULL),
(2, 1, 'Bagong_Silang', 'Electronics', b'0', 'itim', '2020-01-17 12:00:00.000000', 'silang room 101', 'tulfone', '2020-01-17 00:00:00.000000', 'Found', NULL),
(3, 1, 'Camarin', 'Personal_Items', b'0', 'blue tas walang laman', '2020-01-16 12:00:00.000000', 'jnv camarin', 'wallet', '2020-01-16 00:00:00.000000', 'Lost', NULL),
(4, 1, 'South', 'Personal_Items', b'0', 'red', '2020-01-15 12:00:00.000000', 'ucc south', 'aquaflask', '2020-01-15 00:00:00.000000', 'Found', NULL),
(5, 1, 'Congress', 'Personal_Items', b'0', 'maikli', '2025-03-28 19:56:07.000000', 'congress rm 101', 'panyo', '2025-03-28 19:56:07.000000', 'Lost', NULL),
(6, 1, 'Bagong_Silang', 'Electronics', b'0', 'puti', '2020-01-17 12:00:00.000000', 'silang room 101', 'cherrylobat', '2020-01-17 00:00:00.000000', 'Found', NULL),
(7, 1, 'Bagong_Silang', 'Electronics', b'0', 'puti', '2020-01-17 12:00:00.000000', 'silang room 101', 'cherrylobat', '2020-01-17 00:00:00.000000', 'Found', NULL),
(8, 1, 'Bagong_Silang', 'Electronics', b'0', 'puti', '2020-01-17 12:00:00.000000', 'silang room 101', 'cherrylobat', '2020-01-17 00:00:00.000000', 'Found', NULL),
(9, 1, 'Bagong_Silang', 'Electronics', b'0', 'puti', '2020-01-17 12:00:00.000000', 'silang room 101', 'cherrylobat', '2020-01-17 00:00:00.000000', 'Found', NULL),
(10, 1, 'Congress', 'Personal_Items', b'0', 'mahaba', '2020-01-18 12:00:00.000000', 'congress highway', 'panyo', '2020-01-18 00:00:00.000000', 'Lost', NULL),
(11, 1, 'South', 'Personal_Items', b'0', 'red', '2020-01-15 12:00:00.000000', 'ucc south', 'aquaflask', '2020-01-15 00:00:00.000000', 'Found', NULL),
(12, 1, 'Camarin', 'Documents', b'0', 'a4', '2025-04-12 15:31:21.000000', 'chs gate', 'wallet ', '2025-04-12 15:31:21.000000', 'Lost', NULL),
(13, 2, 'Camarin', 'Electronics', b'0', 'iPhone 16 Pro Max', '2025-04-12 09:13:24.000000', 'sa may JNV', 'Cellphone', '2025-04-12 09:13:24.000000', 'Lost', 'uploads/f858f77a-6b0e-44d9-b40f-8f13c294fb9c_download.jpg'),
(14, 2, 'Camarin', 'Electronics', b'0', 'iPhone 14 Pro ', '2025-04-12 09:13:24.000000', 'sa may JNV', 'Cellphone', '2025-04-12 09:13:24.000000', 'Lost', NULL),
(15, 2, 'Congress', 'Documents', b'0', 'papel', '2025-04-19 19:24:00.000000', 'mis', 'form 137', '2025-04-19 19:24:54.000000', 'Lost', NULL),
(16, 2, 'Congress', 'Documents', b'0', 'papel', '2025-04-19 19:26:00.000000', 'mis', 'form 137', '2025-04-19 19:26:19.000000', 'Lost', NULL),
(17, 2, 'Congress', 'Documents', b'0', 'papel', '2025-04-19 19:28:00.000000', 'mis', 'form 137', '2025-04-19 19:28:19.000000', 'Lost', NULL),
(18, 2, 'Congress', 'Documents', b'0', 'dasdasdsadsa', '2025-04-19 19:32:00.000000', 'mis', 'form 137', '2025-04-19 19:32:48.000000', 'Lost', NULL),
(19, 2, 'Congress', 'Documents', b'0', '12321', '2025-04-19 19:33:00.000000', 'mis', 'form 137', '2025-04-19 19:34:05.000000', 'Lost', NULL),
(20, 2, 'Congress', 'Documents', b'0', '12321', '2025-04-19 19:33:00.000000', 'mis', 'form 137', '2025-04-19 19:37:23.000000', 'Lost', NULL),
(21, 2, 'Camarin', 'Personal_Items', b'0', 'aus aus....', '2023-01-27 17:00:00.000000', 'sa kanto', 'my will to live </3', '2025-04-19 19:58:37.000000', 'Claimed', 'uploads/76bb6cb9-b023-4379-8594-0443f970d742_cb3e014d6122af3b43933bb571859ae7.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `id` bigint(20) NOT NULL,
  `sender_id` bigint(20) NOT NULL,
  `recipient_id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT current_timestamp(),
  `seen` tinyint(1) DEFAULT 0,
  `conversation_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`id`, `sender_id`, `recipient_id`, `content`, `timestamp`, `seen`, `conversation_id`) VALUES
(1, 2, 1, 'Hello from Postman!', '2025-04-16 21:21:50', 0, 1),
(2, 2, 3, 'namo carlo', '2025-04-16 21:25:58', 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Admin','User') NOT NULL,
  `username` varchar(255) NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `contact_num` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `middle_name`, `password`, `role`, `username`, `deleted`, `email`, `contact_num`) VALUES
(1, 'Guest', 'Guest', NULL, 'guest', 'User', 'guest', 0, 'guestuser@gmail.com', '09232233215'),
(2, 'Tester', 'Tester', NULL, 'mypassword', 'User', 'testuser', 0, 'testuser@gmail.com', '0929329445566'),
(3, 'Gian', 'Dela Torre', 'B.', 'sikuya', 'Admin', 'pinoybigbrother', 0, 'bigbrother@gmail.com', '09232662744'),
(4, 'Gian Carlo', 'Dela Torre', 'Bengosta', 'zenny', 'User', 'velliv', 0, 'shr0udd.02@gmail.com', '09685433022');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `conversation`
--
ALTER TABLE `conversation`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_pair` (`user_low_id`,`user_high_id`),
  ADD KEY `user1_id` (`user1_id`),
  ADD KEY `user2_id` (`user2_id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `recipient_id` (`recipient_id`),
  ADD KEY `fk_conversation_id` (`conversation_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uni` (`contact_num`,`email`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `conversation`
--
ALTER TABLE `conversation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `item_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `conversation`
--
ALTER TABLE `conversation`
  ADD CONSTRAINT `conversation_ibfk_1` FOREIGN KEY (`user1_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `conversation_ibfk_2` FOREIGN KEY (`user2_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `fk_conversation_id` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`),
  ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `message_ibfk_2` FOREIGN KEY (`recipient_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
