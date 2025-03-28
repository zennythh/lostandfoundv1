-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2025 at 01:28 PM
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
  `deleted` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `campus`, `description`, `found_on`, `location`, `name`, `reported_on`, `status`, `deleted`) VALUES
(1, 'Congress', 'mahaba', '2020-01-18 12:00:00.000000', 'congress highway', 'panyo', '2020-01-18 00:00:00.000000', 'Lost', 0),
(2, 'Bagong_Silang', 'itim', '2020-01-17 12:00:00.000000', 'silang room 101', 'tulfone', '2020-01-17 00:00:00.000000', 'Found', 0),
(3, 'Camarin', 'blue tas walang laman', '2020-01-16 12:00:00.000000', 'jnv camarin', 'wallet', '2020-01-16 00:00:00.000000', 'Lost', 0),
(4, 'South', 'red', '2020-01-15 12:00:00.000000', 'ucc south', 'aquaflash', '2020-01-15 00:00:00.000000', 'Found', 0),
(5, 'Congress', 'maikli', '2025-03-28 19:56:07.000000', 'congress rm 101', 'panyo', '2025-03-28 19:56:07.000000', 'Lost', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`item_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
