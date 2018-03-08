-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 08, 2018 at 02:26 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 5.6.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `NSRP_CATER`
--

-- --------------------------------------------------------

--
-- Table structure for table `ALLCODE`
--

CREATE TABLE `ALLCODE` (
  `IDX` decimal(3,0) NOT NULL,
  `CODE_NAME` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `TYPE_NAME` varchar(60) CHARACTER SET utf8 NOT NULL,
  `DEP_CODE` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `CODE_VAL` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `CONTENTS` varchar(120) CHARACTER SET utf8 NOT NULL,
  `MODIFY` tinyint(4) NOT NULL,
  `ISDEFAULT` tinyint(4) NOT NULL,
  `RSR_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `APPVAR`
--

CREATE TABLE `APPVAR` (
  `NAME` char(15) COLLATE utf8_unicode_ci NOT NULL,
  `TYPE` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `VALUE` varchar(120) CHARACTER SET utf8 NOT NULL,
  `DESCRIPT` varchar(120) CHARACTER SET utf8 NOT NULL,
  `INPUTMASK` char(12) COLLATE utf8_unicode_ci NOT NULL,
  `MODIFY` tinyint(4) NOT NULL,
  `INVALID` char(12) COLLATE utf8_unicode_ci NOT NULL,
  `DEP_CODE` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `RSR_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `CATERING`
--

CREATE TABLE `CATERING` (
  `STAFF_ID` char(12) COLLATE utf8_unicode_ci NOT NULL,
  `MEAL_ID` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `ZONE_SYMB` char(4) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MEAL_TTME` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `SHIFT` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CATER_DATE` date NOT NULL,
  `CATERED` bit(1) NOT NULL,
  `CATER_TIME` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `STATUS` bit(1) NOT NULL,
  `WS_ID` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `USER_ID` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OPEN_DATE` date DEFAULT NULL,
  `MODI_DATE` datetime(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `CATERING`
--

INSERT INTO `CATERING` (`STAFF_ID`, `MEAL_ID`, `ZONE_SYMB`, `MEAL_TTME`, `SHIFT`, `CATER_DATE`, `CATERED`, `CATER_TIME`, `STATUS`, `WS_ID`, `USER_ID`, `OPEN_DATE`, `MODI_DATE`) VALUES
('1000032', 'HL', '12', 'Lunch', '12', '2018-03-08', b'0', '12', b'1', '12', '12', '2018-03-08', '2018-03-08 08:01:27.000'),
('1000051', 'HL', '12', 'Lunch', '12', '2018-03-08', b'0', '12', b'1', '12', '12', '2018-03-08', '2018-03-08 08:01:27.000'),
('1000160', 'JP', '12', 'Lunch', '12', '2018-03-08', b'0', '12', b'1', '12', '12', '2018-03-08', '2018-03-08 08:01:27.000'),
('1000180', 'VN', '12', 'Lunch', '12', '2018-03-08', b'0', '12', b'1', '12', '12', '2018-03-08', '2018-03-08 08:01:27.000'),
('1000210', 'VN', '12', 'Lunch', '12', '2018-03-08', b'0', '12', b'1', '12', '12', '2018-03-08', '2018-03-08 08:01:27.000');

-- --------------------------------------------------------

--
-- Table structure for table `DEPTLIST`
--

CREATE TABLE `DEPTLIST` (
  `DEPT_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `DEPT_NAME` varchar(120) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `DEPTLIST`
--

INSERT INTO `DEPTLIST` (`DEPT_ID`, `DEPT_NAME`) VALUES
('D001', 'IT'),
('D002', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `LOCATION`
--

CREATE TABLE `LOCATION` (
  `ZONE_SYMB` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPT` varchar(120) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `MEAL`
--

CREATE TABLE `MEAL` (
  `MEAL_ID` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `MEAL_NAME` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `ORIGIN` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `MEAL`
--

INSERT INTO `MEAL` (`MEAL_ID`, `MEAL_NAME`, `ORIGIN`, `DESCRIPTION`) VALUES
('AC', 'OTHERS', '', ''),
('HL', 'HALAL', 'HALAL', ''),
('JP', 'JAPAN', 'NIHON', ''),
('VN', 'VIETNAM', 'VIETNAM', '');

-- --------------------------------------------------------

--
-- Table structure for table `MERCPROP`
--

CREATE TABLE `MERCPROP` (
  `TYPE` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `CODE` char(16) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPT` varchar(120) CHARACTER SET utf8 NOT NULL,
  `DATA_TYPE` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `DATA_LEN` decimal(2,0) NOT NULL,
  `FORMAT` char(16) COLLATE utf8_unicode_ci NOT NULL,
  `INPUTMASK` char(16) COLLATE utf8_unicode_ci NOT NULL,
  `STATUS` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `STAFF`
--

CREATE TABLE `STAFF` (
  `STAFF_ID` char(12) COLLATE utf8_unicode_ci NOT NULL,
  `STAFF_CODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `TITLE` char(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS2` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COUNTRY` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PHONE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` varchar(24) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SKYPE` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FACEBOOK` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BANK_ACC` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BANK` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DEPT_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `SEX` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `BIRTHDAY` datetime(3) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `CARD_ID` char(12) COLLATE utf8_unicode_ci NOT NULL,
  `ZONE_SYMB` char(4) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MEAL_TYPE` char(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `STAFF`
--

INSERT INTO `STAFF` (`STAFF_ID`, `STAFF_CODE`, `NAME`, `TITLE`, `ADDRESS`, `ADDRESS2`, `COUNTRY`, `PHONE`, `FAX`, `EMAIL`, `SKYPE`, `FACEBOOK`, `BANK_ACC`, `BANK`, `DEPT_ID`, `SEX`, `BIRTHDAY`, `USER_ID`, `CARD_ID`, `ZONE_SYMB`, `MEAL_TYPE`, `STATUS`) VALUES
('1000032', '1000032', 'CHU KHANH', 'Chief Executive Officer', '0', '0', 'Kuwait', '+84 (0) 902 264 988', NULL, 'turki.alajmi@nsrp.com.vn', NULL, NULL, NULL, NULL, 'F', '1', NULL, NULL, '1000032', NULL, NULL, b'1'),
('1000051', '1000051', 'Hiroshi Akiya', 'Coordinator', '0', '0', 'Japan', '+84 (0) 904 874 867', NULL, 'hiroshi.akiya@nsrp.com.vn', NULL, NULL, NULL, NULL, 'F', '1', NULL, NULL, '1000051', NULL, NULL, b'1'),
('1000160', '1000160', 'Mai Th? Xuân Liên', 'Leader', 'Hà N?i', 'Hà N?i', 'VN', '+84 (0) 966 678 686', NULL, 'lien.mtx@nsrp.com.vn', NULL, NULL, NULL, NULL, 'F', '0', NULL, NULL, '1000160', NULL, NULL, b'1'),
('1000180', '1000180', 'Nguy?n Th? Qu?nh Hoa', 'Chief Accounting Officer', 'Hà N?i', 'Hà N?i', 'VN', '+84 (0) 902 008 228', NULL, 'hoa.ntq@nsrp.com.vn', NULL, NULL, NULL, NULL, 'F', '0', NULL, NULL, '1000180', NULL, NULL, b'1'),
('1000210', '1000210', 'Hoàng Th? Y?n', 'Officer', 'Hà N?i', 'Hà N?i', 'VN', '+84 (0) 986 364 459', NULL, 'yen.ht@nsrp.com.vn', NULL, NULL, NULL, NULL, 'F', '0', NULL, NULL, '1000210', NULL, NULL, b'1');

-- --------------------------------------------------------

--
-- Table structure for table `STATIONS`
--

CREATE TABLE `STATIONS` (
  `ID` decimal(3,0) NOT NULL,
  `TYPE` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `NODE_ID` char(3) COLLATE utf8_unicode_ci NOT NULL,
  `POS_ALIAS` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `WS_ALIAS` char(30) COLLATE utf8_unicode_ci NOT NULL,
  `NAME` char(30) COLLATE utf8_unicode_ci NOT NULL,
  `FULLNAME` char(30) COLLATE utf8_unicode_ci NOT NULL,
  `TEMPDIR` char(30) COLLATE utf8_unicode_ci NOT NULL,
  `DISABLE` tinyint(4) NOT NULL,
  `BADLOGIN` decimal(3,0) NOT NULL,
  `LOGIN` tinyint(4) NOT NULL,
  `PRNPORT` decimal(2,0) NOT NULL,
  `PRNNAME` decimal(2,0) NOT NULL,
  `POSPRN` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SCNPORT` decimal(2,0) NOT NULL,
  `COMSET` char(12) COLLATE utf8_unicode_ci NOT NULL,
  `CSDPORT` decimal(2,0) NOT NULL,
  `CDRPORT` decimal(2,0) NOT NULL,
  `SCLPORT` decimal(2,0) NOT NULL,
  `PINPORT` decimal(2,0) NOT NULL,
  `DOCPRN` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `BARPRN` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `POS_ID` char(12) COLLATE utf8_unicode_ci NOT NULL,
  `USER_ID` decimal(3,0) NOT NULL,
  `MENUSTAT` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `CHK` tinyint(4) NOT NULL,
  `STATUS` tinyint(4) NOT NULL,
  `OS` int(11) NOT NULL,
  `SRN` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `SYSVAR`
--

CREATE TABLE `SYSVAR` (
  `NAME` char(15) COLLATE utf8_unicode_ci NOT NULL,
  `TYPE` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VALUE` varchar(120) CHARACTER SET utf8 NOT NULL,
  `DESCRIPT` varchar(120) CHARACTER SET utf8 DEFAULT NULL,
  `INPUTMASK` char(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODIFY` bit(1) DEFAULT NULL,
  `INVALID` char(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DEP_CODE` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RSR_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `SYSVAR`
--

INSERT INTO `SYSVAR` (`NAME`, `TYPE`, `VALUE`, `DESCRIPT`, `INPUTMASK`, `MODIFY`, `INVALID`, `DEP_CODE`, `RSR_ID`) VALUES
('m_FTPInDir', NULL, '/khanhct/', NULL, NULL, NULL, NULL, NULL, NULL),
('m_FTPPassword', NULL, 'hanoi', NULL, NULL, NULL, NULL, NULL, NULL),
('m_FTPPort', NULL, '21', NULL, NULL, NULL, NULL, NULL, NULL),
('m_FTPServer', NULL, '117.6.130.185', NULL, NULL, NULL, NULL, NULL, NULL),
('m_FTPUserName', NULL, 'daisy', NULL, NULL, NULL, NULL, NULL, NULL),
('m_TimeScheduler', NULL, '10000', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `TRANSLATION`
--

CREATE TABLE `TRANSLATION` (
  `TRANSLATION_ID` int(11) NOT NULL,
  `OBJECT_CLASS` varchar(63) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `OBJECT_PROPERTY` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `OBJECT_ID` int(11) DEFAULT NULL,
  `OBJECT_UID` char(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `CODE` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `LOCALE` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `VALUE` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `LAST_UPDATE` datetime DEFAULT NULL,
  `CREATION` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=armscii8;

-- --------------------------------------------------------

--
-- Table structure for table `USER`
--

CREATE TABLE `USER` (
  `ID` int(11) NOT NULL,
  `USERNAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `FULLNAME` varchar(60) CHARACTER SET utf8 NOT NULL,
  `PASSWORD` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `RESTORE_CODE` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STAFF_ID` char(12) COLLATE utf8_unicode_ci NOT NULL,
  `STATIONS` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TRANS` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_UPDATE` datetime DEFAULT NULL,
  `M_DEPTS` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SMTPEMAIL` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `STATUS` bit(1) NOT NULL,
  `LANG_TYPE` char(2) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `USER`
--

INSERT INTO `USER` (`ID`, `USERNAME`, `FULLNAME`, `PASSWORD`, `RESTORE_CODE`, `STAFF_ID`, `STATIONS`, `TRANS`, `LAST_UPDATE`, `M_DEPTS`, `SMTPEMAIL`, `STATUS`, `LANG_TYPE`) VALUES
(1, 'khanhct', 'Chu Trong Khanh', 'khanhct123', 'khanhct123', '1000032', '', '', '0000-00-00 00:00:00', '', 'khanct@gmail.com', b'0', '12'),
(2, 'khanhct12', 'Chu Trong KHanh', 'khanhct12', NULL, '01234567', NULL, NULL, NULL, NULL, 'khanhct@gmail.com', b'1', NULL),
(3, 'khanhct12', 'Chu Trong KHanh', 'khanhct12', NULL, '01234565', NULL, NULL, NULL, NULL, 'khanhct@gmail.com', b'1', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ALLCODE`
--
ALTER TABLE `ALLCODE`
  ADD PRIMARY KEY (`CODE_NAME`,`DEP_CODE`,`CODE_VAL`);

--
-- Indexes for table `APPVAR`
--
ALTER TABLE `APPVAR`
  ADD PRIMARY KEY (`NAME`);

--
-- Indexes for table `CATERING`
--
ALTER TABLE `CATERING`
  ADD PRIMARY KEY (`STAFF_ID`,`MEAL_TTME`,`CATER_DATE`);

--
-- Indexes for table `DEPTLIST`
--
ALTER TABLE `DEPTLIST`
  ADD PRIMARY KEY (`DEPT_ID`);

--
-- Indexes for table `MEAL`
--
ALTER TABLE `MEAL`
  ADD PRIMARY KEY (`MEAL_ID`);

--
-- Indexes for table `MERCPROP`
--
ALTER TABLE `MERCPROP`
  ADD PRIMARY KEY (`TYPE`,`CODE`);

--
-- Indexes for table `STAFF`
--
ALTER TABLE `STAFF`
  ADD PRIMARY KEY (`STAFF_ID`);

--
-- Indexes for table `STATIONS`
--
ALTER TABLE `STATIONS`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `SYSVAR`
--
ALTER TABLE `SYSVAR`
  ADD PRIMARY KEY (`NAME`);

--
-- Indexes for table `TRANSLATION`
--
ALTER TABLE `TRANSLATION`
  ADD PRIMARY KEY (`TRANSLATION_ID`);

--
-- Indexes for table `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `TRANSLATION`
--
ALTER TABLE `TRANSLATION`
  MODIFY `TRANSLATION_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `USER`
--
ALTER TABLE `USER`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
