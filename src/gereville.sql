-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 19, 2017 at 09:42 AM
-- Server version: 5.7.19-0ubuntu0.17.04.1
-- PHP Version: 7.0.22-0ubuntu0.17.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gereville`
--

-- --------------------------------------------------------

--
-- Table structure for table `capitale`
--

CREATE TABLE `capitale` (
  `ville_id` int(11) NOT NULL,
  `president` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- --------------------------------------------------------

--
-- Table structure for table `pays`
--

CREATE TABLE `pays` (
  `id` int(11) NOT NULL,
  `nom` varchar(64) NOT NULL,
  `capitale_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- --------------------------------------------------------

--
-- Table structure for table `ville`
--

CREATE TABLE `ville` (
  `id` int(11) NOT NULL,
  `nom` varchar(256) NOT NULL,
  `nbhabitants` int(11) NOT NULL,
  `pays_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- Indexes for dumped tables
--

--
-- Indexes for table `capitale`
--
ALTER TABLE `capitale`
  ADD PRIMARY KEY (`ville_id`);

--
-- Indexes for table `pays`
--
ALTER TABLE `pays`
  ADD PRIMARY KEY (`id`),
  ADD KEY `capitale_id` (`capitale_id`);

--
-- Indexes for table `ville`
--
ALTER TABLE `ville`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pays_id` (`pays_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pays`
--
ALTER TABLE `pays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `ville`
--
ALTER TABLE `ville`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `capitale`
--
ALTER TABLE `capitale`
  ADD CONSTRAINT `capitale_ibfk_1` FOREIGN KEY (`ville_id`) REFERENCES `ville` (`id`);

--
-- Constraints for table `pays`
--
ALTER TABLE `pays`
  ADD CONSTRAINT `pays_ibfk_1` FOREIGN KEY (`capitale_id`) REFERENCES `ville` (`id`);

--
-- Constraints for table `ville`
--
ALTER TABLE `ville`
  ADD CONSTRAINT `ville_ibfk_1` FOREIGN KEY (`pays_id`) REFERENCES `pays` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
