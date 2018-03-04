-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 03, 2018 at 04:53 PM
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
  `MEAL_TYPE` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `ZONE_SYMB` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `MEAL_TTME` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `SHIFT` decimal(2,0) NOT NULL,
  `CATER_DATE` datetime(3) NOT NULL,
  `MEAL` tinyint(4) NOT NULL,
  `CATERED` tinyint(4) NOT NULL,
  `CATER_TIME` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `STATUS` tinyint(4) NOT NULL,
  `WS_ID` decimal(3,0) NOT NULL,
  `USER_ID` decimal(3,0) NOT NULL,
  `OPEN_DATE` datetime(3) DEFAULT NULL,
  `MODI_DATE` datetime(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `DEPTLIST`
--

CREATE TABLE `DEPTLIST` (
  `DEPT_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `DEPT_NAME` varchar(120) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `STAFF_CODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `TYPE` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `GRP_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `NODE_ID` char(3) COLLATE utf8_unicode_ci NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `TITLE` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `ADDRESS` varchar(120) CHARACTER SET utf8 NOT NULL,
  `ADDRESS2` varchar(120) CHARACTER SET utf8 NOT NULL,
  `COUNTRY` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `PLC_ID` char(9) COLLATE utf8_unicode_ci NOT NULL,
  `PHONE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `FAX` varchar(24) COLLATE utf8_unicode_ci NOT NULL,
  `MOBI` varchar(24) COLLATE utf8_unicode_ci NOT NULL,
  `MOBI2` varchar(24) COLLATE utf8_unicode_ci NOT NULL,
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SKYPE` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `FACEBOOK` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `BANK_ACC` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ACC_HOLDER` varchar(120) CHARACTER SET utf8 NOT NULL,
  `BANK` varchar(120) CHARACTER SET utf8 NOT NULL,
  `BANK_ADDR` varchar(120) CHARACTER SET utf8 NOT NULL,
  `DEPT_ID` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `STK_ID` char(12) COLLATE utf8_unicode_ci NOT NULL,
  `DEP_CODE` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `PERSON_ID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ISS_DATE` datetime(3) DEFAULT NULL,
  `SEX` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `BIRTHDAY` datetime(3) DEFAULT NULL,
  `USER_ID` decimal(3,0) NOT NULL,
  `CARD_ID` char(12) COLLATE utf8_unicode_ci NOT NULL,
  `ZONE_SYMB` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `MEAL_TYPE` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `CONTR_NUM` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `CONTR_DT` datetime(3) DEFAULT NULL,
  `REG_NUM` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `CONTR_BR` varchar(120) CHARACTER SET utf8 NOT NULL,
  `DUE_DATE` datetime(3) DEFAULT NULL,
  `REMARK` varchar(120) CHARACTER SET utf8 NOT NULL,
  `OPEN_DATE` datetime(3) DEFAULT NULL,
  `MODI_DATE` datetime(3) DEFAULT NULL,
  `LAST_DATE` datetime(3) DEFAULT NULL,
  `STATUS` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
-- Table structure for table `USERS`
--

CREATE TABLE `USERS` (
  `ID` int(11) NOT NULL,
  `NODE_ID` char(3) COLLATE utf8_unicode_ci NOT NULL,
  `TYPE` int(11) NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `FULLNAME` varchar(60) CHARACTER SET utf8 NOT NULL,
  `PASSWORD` decimal(18,0) NOT NULL,
  `PASSCODE` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `STAFF_ID` char(12) COLLATE utf8_unicode_ci NOT NULL,
  `LAST_DATE` datetime(3) DEFAULT NULL,
  `RGT_LEVEL` int(11) NOT NULL,
  `GROUPS` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `S_EQUAL` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `R_MENUS` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `CONFIRMS` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `STATIONS` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `TRANS` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `REPORTS` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `M_DEPTS` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `M_FNS` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `SMTPHOST` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SMTPPORT` decimal(3,0) NOT NULL,
  `SMTPSSL` decimal(3,0) NOT NULL,
  `SMTPEMAIL` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SMTPUSER` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SMTPPW` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `LOGIN` tinyint(4) NOT NULL,
  `STATUS` tinyint(4) NOT NULL,
  `CHK` tinyint(4) NOT NULL,
  `LANG_TYPE` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `STOCK` varchar(128) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
-- Indexes for table `USERS`
--
ALTER TABLE `USERS`
  ADD PRIMARY KEY (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
