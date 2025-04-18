-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2025 at 11:32 AM
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
(14, 2, 'Camarin', 'Electronics', b'0', 'iPhone 14 Pro ', '2025-04-12 09:13:24.000000', 'sa may JNV', 'Cellphone', '2025-04-12 09:13:24.000000', 'Lost', NULL);

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
(3, 'Gian', 'Dela Torre', 'B.', 'sikuya', 'Admin', 'pinoybigbrother', 0, 'bigbrother@gmail.com', '09232662744');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`item_id`);

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
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `item_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
