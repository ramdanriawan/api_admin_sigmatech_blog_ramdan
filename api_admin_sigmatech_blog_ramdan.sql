-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2023 at 08:28 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `api_admin_sigmatech_blog_ramdan`
--

-- --------------------------------------------------------

--
-- Table structure for table `facilities`
--

CREATE TABLE `facilities` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `dtype` varchar(30) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `facilities`
--

INSERT INTO `facilities` (`id`, `name`, `dtype`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'Kolam renang', 'Facilities', '2023-06-08 06:20:30', '2023-06-08 06:20:30', NULL),
(2, 'Ac', 'Facilities', '2023-06-08 06:20:30', '2023-06-08 06:20:30', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `order_facilities`
--

CREATE TABLE `order_facilities` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `facilities_id` int(11) NOT NULL,
  `dtype` varchar(30) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_facilities`
--

INSERT INTO `order_facilities` (`id`, `order_id`, `facilities_id`, `dtype`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 9, 1, 'orderFacilities', '2023-06-08 08:28:11', '2023-06-08 08:28:10', NULL),
(2, 9, 1, 'orderFacilities', '2023-06-08 08:28:11', '2023-06-08 08:28:10', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `order_table`
--

CREATE TABLE `order_table` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `dtype` varchar(30) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_table`
--

INSERT INTO `order_table` (`id`, `user_id`, `room_id`, `dtype`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 1, 'orderCreateDto', '2023-06-08 07:27:52', '2023-06-08 07:27:52', NULL),
(2, 1, 1, 'orderCreateDto', '2023-06-08 07:32:05', '2023-06-08 07:32:05', NULL),
(3, 1, 1, 'orderCreateDto', '2023-06-08 07:34:06', '2023-06-08 07:34:06', NULL),
(4, 1, 1, 'orderCreateDto', '2023-06-08 07:36:52', '2023-06-08 07:36:52', NULL),
(5, 1, 1, 'orderCreateDto', '2023-06-08 08:21:32', '2023-06-08 08:21:32', NULL),
(6, 1, 1, 'orderCreateDto', '2023-06-08 08:22:27', '2023-06-08 08:22:27', NULL),
(7, 1, 1, 'orderCreateDto', '2023-06-08 08:23:52', '2023-06-08 08:23:52', NULL),
(8, 1, 1, 'orderCreateDto', '2023-06-08 08:26:56', '2023-06-08 08:26:56', NULL),
(9, 1, 1, 'orderCreateDto', '2023-06-08 08:28:10', '2023-06-08 08:28:10', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `no` varchar(10) NOT NULL,
  `type` enum('Single','Twin','Deluxe','Family') NOT NULL,
  `dtype` varchar(30) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`id`, `no`, `type`, `dtype`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'room 001', 'Single', 'Room', '2023-06-08 06:26:21', '2023-06-08 06:26:21', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(13) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `level` enum('Admin','Karyawan') COLLATE utf8mb4_unicode_ci DEFAULT 'Karyawan',
  `dtype` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `phone`, `email`, `password`, `level`, `dtype`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'Admin', '082282692480', 'admin@gmail.com', '$2y$10$wJtxHl0HHlReswNIwucHKeF5ICZ0rcx1.l.QSyvURJHkZoVIM82r2', 'Admin', 'UserCreateDto', '2022-04-24 09:23:52', '2022-11-05 15:16:13', NULL),
(175, 'Ramdan Riawan', '082282692480', 'usertest1@gmail.com', '$2a$10$E3vzDdaYpSmGmLIGTpBs4O6XDNlGpNHYsznH8kIJQqZOGjfjJKOka', 'Karyawan', 'UserCreateDto', '2023-06-08 13:08:56', '2023-06-08 13:08:56', NULL),
(176, 'Ramdan Riawan', '082282692480', 'usertest1@gmail.com', '$2a$10$X0H0kATpFtVmcuiboCbRouM5awRlWfzQ8bdSWpz6IRtQWyO8iugWu', 'Karyawan', 'UserCreateDto', '2023-06-08 13:35:07', '2023-06-08 13:35:07', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `facilities`
--
ALTER TABLE `facilities`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_facilities`
--
ALTER TABLE `order_facilities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `facilities_id` (`facilities_id`);

--
-- Indexes for table `order_table`
--
ALTER TABLE `order_table`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room_id` (`room_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `nik` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `facilities`
--
ALTER TABLE `facilities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `order_facilities`
--
ALTER TABLE `order_facilities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `order_table`
--
ALTER TABLE `order_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=177;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_facilities`
--
ALTER TABLE `order_facilities`
  ADD CONSTRAINT `order_facilities_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `order_facilities_ibfk_2` FOREIGN KEY (`facilities_id`) REFERENCES `facilities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `order_table`
--
ALTER TABLE `order_table`
  ADD CONSTRAINT `order_table_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `order_table_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
