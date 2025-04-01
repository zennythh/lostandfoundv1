-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 01, 2025 at 07:21 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

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
  `item_id` int(11) NOT NULL,
  `campus` enum('Bagong_Silang','Camarin','Congress','South') NOT NULL,
  `description` varchar(255) NOT NULL,
  `found_on` datetime(6) NOT NULL,
  `location` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `reported_on` datetime(6) NOT NULL,
  `status` enum('Claimed','Found','Lost','Returned') NOT NULL,
  `deleted` tinyint(1) DEFAULT 0,
  `category` enum('Electronics','Clothes_and_Accessories','Documents','Personal_Items','Others') NOT NULL,
  `author_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `campus`, `description`, `found_on`, `location`, `name`, `reported_on`, `status`, `deleted`, `category`, `author_id`) VALUES
(1, 'Congress', 'mahaba', '2020-01-18 12:00:00.000000', 'congress highway', 'panyo', '2020-01-18 00:00:00.000000', 'Lost', 0, 'Personal_Items', 1),
(2, 'Bagong_Silang', 'itim', '2020-01-17 12:00:00.000000', 'silang room 101', 'tulfone', '2020-01-17 00:00:00.000000', 'Found', 0, 'Electronics', 1),
(3, 'Camarin', 'blue tas walang laman', '2020-01-16 12:00:00.000000', 'jnv camarin', 'wallet', '2020-01-16 00:00:00.000000', 'Lost', 0, 'Personal_Items', 1),
(4, 'South', 'red', '2020-01-15 12:00:00.000000', 'ucc south', 'aquaflask', '2020-01-15 00:00:00.000000', 'Found', 0, 'Personal_Items', 1),
(5, 'Congress', 'maikli', '2025-03-28 19:56:07.000000', 'congress rm 101', 'panyo', '2025-03-28 19:56:07.000000', 'Lost', 0, 'Personal_Items', 1),
(6, 'Bagong_Silang', 'puti', '2020-01-17 12:00:00.000000', 'silang room 101', 'cherrylobat', '2020-01-17 00:00:00.000000', 'Found', 0, 'Electronics', 1),
(7, 'Bagong_Silang', 'puti', '2020-01-17 12:00:00.000000', 'silang room 101', 'cherrylobat', '2020-01-17 00:00:00.000000', 'Found', 0, 'Electronics', 1),
(8, 'Bagong_Silang', 'puti', '2020-01-17 12:00:00.000000', 'silang room 101', 'cherrylobat', '2020-01-17 00:00:00.000000', 'Found', 0, 'Electronics', 1),
(9, 'Bagong_Silang', 'puti', '2020-01-17 12:00:00.000000', 'silang room 101', 'cherrylobat', '2020-01-17 00:00:00.000000', 'Found', 0, 'Electronics', 1),
(10, 'Congress', 'mahaba', '2020-01-18 12:00:00.000000', 'congress highway', 'panyo', '2020-01-18 00:00:00.000000', 'Lost', 0, 'Personal_Items', 1),
(11, 'Congress', 'mahaba', '2020-01-18 12:00:00.000000', 'congress highway', 'panyo', '2020-01-18 00:00:00.000000', 'Lost', 0, 'Personal_Items', 1),
(12, 'Congress', 'mahaba', '2020-01-18 12:00:00.000000', 'congress highway', 'panyo', '2020-01-18 00:00:00.000000', 'Lost', 0, 'Personal_Items', 1),
(13, 'Congress', 'mahaba', '2020-01-18 12:00:00.000000', 'congress highway', 'panyo', '2020-01-18 00:00:00.000000', 'Lost', 0, 'Personal_Items', 1),
(14, 'South', 'A brown leather wallet with ID and credit cards.', '2025-04-01 09:00:00.000000', 'Park', 'Lost Wallet', '2025-04-01 10:00:00.000000', 'Lost', 0, 'Personal_Items', 1);

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
  `username` varchar(255) NOT NULL,
  `role` enum('Admin','User') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `middle_name`, `password`, `username`, `role`) VALUES
(1, 'guest', 'guest', 'guest', 'guest', 'guest', 'Admin'),
(2, 'Gian', 'Dela Torre', 'Bengosta', 'sikuya', 'pinoybigbrother', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `FOREIGN` (`author_id`) USING BTREE;

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `FK_author_id` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
