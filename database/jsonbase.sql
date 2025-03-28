-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 27, 2025 at 09:26 AM
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
  `name` varchar(250) NOT NULL,
  `description` varchar(250) NOT NULL,
  `location` varchar(250) NOT NULL,
  `reported_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `found_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` varchar(15) NOT NULL,
  `campus` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `name`, `description`, `location`, `reported_on`, `found_on`, `status`, `campus`) VALUES
(1, 'panyo', 'maikli', 'congress highway', '2020-01-17 16:00:00', '2020-01-18 04:00:00', 'Lost', 'Congress'),
(2, 'tulfone', 'itim', 'silang room 101', '2020-01-16 16:00:00', '2020-01-17 04:00:00', 'Found', 'Bagong_Silang'),
(3, 'wallet', 'blue tas walang laman', 'jnv camarin', '2020-01-15 16:00:00', '2020-01-16 04:00:00', 'Lost', 'Camarin'),
(4, 'aquaflash', 'red', 'ucc south', '2020-01-14 16:00:00', '2020-01-15 04:00:00', 'Found', 'South');

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
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
