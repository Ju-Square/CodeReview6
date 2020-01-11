-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 11, 2020 at 11:07 AM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cr6_julia_hinterecker_school`
--

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `classID` int(11) NOT NULL,
  `className` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`classID`, `className`) VALUES
(1, '1A'),
(2, '1B'),
(3, '2A'),
(4, '2B'),
(5, '3A'),
(6, '3B');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studentID` int(11) NOT NULL,
  `name` varchar(15) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `classID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studentID`, `name`, `surname`, `email`, `classID`) VALUES
(1, 'Harry', 'Potter', 'h.potter@gmx.at', 1),
(2, 'Ron', 'Weasly', 'r.weasly@gmx.at', 1),
(3, 'Hermine', 'Granger', 'h.granger@gmx.at', 1),
(4, 'Bruce', 'Lee', 'b.lee@gmx.at', 2),
(5, 'Monica', 'Geller', 'm.geller@gmx.at', 3),
(6, 'Rachel', 'Green', 'r.green@gmx.at', 3),
(7, 'Joey', 'Tribbiani', 'j.tribbiani@gmx.at', 4),
(8, 'Chandler', 'Bing', 'c.bing@gmx.at', 5),
(9, 'Ross', 'Geller', 'r.geller@gmx.at', 5),
(10, 'Phoebe', 'Buffay', 'p.buffay@gmx.at', 5),
(11, 'Janice', 'Litman', 'j.litman@gmx.at', 6),
(12, 'Greta', 'Thunberg', 'g.thunberg@gmx.at', 3),
(13, 'Clarice', 'Starling', 'c.starling@gmx.at', 3),
(14, 'Hannibal', 'Lecter', 'h.lecter@gmx.at', 3);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacherID` int(11) NOT NULL,
  `name` varchar(15) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacherID`, `name`, `surname`, `email`) VALUES
(1, 'Hansi', 'Hinterseer', 'h.hinterseer@gmail.com'),
(2, 'Andrea', 'Berg', 'a.berg@gmail.com'),
(3, 'Helene', 'Fischer', 'h.fischer@gmail.com'),
(4, 'Andreas', 'Gabalier', 'a.gabalier@gmail.com'),
(5, 'Andre', 'Rieu', 'a.rieu@gmail.com'),
(6, 'Florian', 'Silbereisen', 'f.silbereisen@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `teachertoclass`
--

CREATE TABLE `teachertoclass` (
  `tacherToClassID` int(11) NOT NULL,
  `teacherID` int(11) DEFAULT NULL,
  `classID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teachertoclass`
--

INSERT INTO `teachertoclass` (`tacherToClassID`, `teacherID`, `classID`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 5, 4),
(5, 1, 5),
(6, 6, 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`classID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studentID`),
  ADD KEY `classID` (`classID`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacherID`);

--
-- Indexes for table `teachertoclass`
--
ALTER TABLE `teachertoclass`
  ADD PRIMARY KEY (`tacherToClassID`),
  ADD KEY `teacherID` (`teacherID`),
  ADD KEY `classID` (`classID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `classID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `studentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `teacherID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `teachertoclass`
--
ALTER TABLE `teachertoclass`
  MODIFY `tacherToClassID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`classID`) REFERENCES `class` (`classID`);

--
-- Constraints for table `teachertoclass`
--
ALTER TABLE `teachertoclass`
  ADD CONSTRAINT `teachertoclass_ibfk_1` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`teacherID`),
  ADD CONSTRAINT `teachertoclass_ibfk_2` FOREIGN KEY (`classID`) REFERENCES `class` (`classID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
