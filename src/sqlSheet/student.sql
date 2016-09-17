-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: uiustudentrecordsystem
-- ------------------------------------------------------
-- Server version	5.7.13-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `dbStudentSerialNo` int(11) NOT NULL AUTO_INCREMENT,
  `dbStudentFname` varchar(64) DEFAULT NULL,
  `dbStudentLname` varchar(64) DEFAULT NULL,
  `dbStudentID` varchar(64) DEFAULT NULL,
  `dbStudentPassword` varchar(64) DEFAULT 'password',
  `dbStudentDepartment` varchar(64) DEFAULT NULL,
  `dbStudentEmail` varchar(64) DEFAULT NULL,
  `dbStudentPhone` varchar(64) DEFAULT NULL,
  `dbStudentAddress` varchar(64) DEFAULT NULL,
  `dbStudentDOB` varchar(64) DEFAULT NULL,
  `dbGuardianFname` varchar(64) DEFAULT NULL,
  `dbGuardianLname` varchar(64) DEFAULT NULL,
  `dbGuardianEmail` varchar(64) DEFAULT NULL,
  `dbGuardianPhone` varchar(64) DEFAULT NULL,
  `dbGuardianPassword` varchar(64) DEFAULT 'password',
  `dbStudent1stSemGpa` decimal(10,2) NOT NULL DEFAULT '0.00',
  `dbStudent2ndSemGpa` decimal(10,2) NOT NULL DEFAULT '0.00',
  `dbStudent3rdSemGpa` decimal(10,2) NOT NULL DEFAULT '0.00',
  `dbStudent4thSemGpa` decimal(10,2) NOT NULL DEFAULT '0.00',
  `dbStudent5thSemGpa` decimal(10,2) NOT NULL DEFAULT '0.00',
  `dbStudent6thSemGpa` decimal(10,2) NOT NULL DEFAULT '0.00',
  `dbStudent7thSemGpa` decimal(10,2) NOT NULL DEFAULT '0.00',
  `dbStudent8thSemGpa` decimal(10,2) NOT NULL DEFAULT '0.00',
  `dbStudent9thSemGpa` decimal(10,2) NOT NULL DEFAULT '0.00',
  `dbStudent10thSemGpa` decimal(10,2) NOT NULL DEFAULT '0.00',
  `dbStudent11thSemGpa` decimal(10,2) NOT NULL DEFAULT '0.00',
  `dbStudent12thSemGpa` decimal(10,2) NOT NULL DEFAULT '0.00',
  `dbStudentCgpa` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`dbStudentSerialNo`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Tanvir','Rahman','011142053','1234','CSE','tanvir3080@gmail.com','01750 943080','House no 307, Dhanmondi 15','1995-02-05','Mokaddesh','Hossain','Mokaddesh.hossain@gmail.com','01748 737217','password',3.67,2.00,2.67,3.67,4.00,3.33,2.33,0.00,0.00,0.00,0.00,0.00,3.33),(2,'Mustafizur','Rahman','011142047','1234','CSE','mustafizur.rahman@gmail.com','01676 338612','House no.71, Zigatola, Dhaka','1995-06-15','Name','JaniNa','sofisF@gmail.com','01799 556644','password',2.00,4.00,3.67,4.00,3.67,2.33,3.67,0.00,0.00,0.00,0.00,0.00,3.67),(3,'Saimoon','Imran','011142153','1234','CSE','saimoon.imran@gmail.com','01818 556699','House no.336, Dhanmondi 15, Dhaka','2010-07-14','Saimoons','Father','his.father@gmail.com','01775 442255','password',2.67,3.67,1.67,4.00,3.67,2.33,0.00,0.00,0.00,0.00,0.00,0.00,3.33),(4,'Jubair','Joy','114142010','1234','BBA','J.joy@gmail.com','01687 176783','House no.30, Dhanmondi 15, Dhaka','2007-07-26','His','Father','janina@gmail.com','01745 556689','password',4.00,2.00,3.67,1.33,2.33,4.00,0.00,0.00,0.00,0.00,0.00,0.00,3.00),(5,'Chowdhury ','Sohag ','011142080','1234','EEE','c.shoag@gmail.com','01748 454525','House no.12, Dhanmondi 15, Dhaka','2016-07-06','Father','Name','sjdfsd@gmail.com','01855 223355','password',3.33,1.00,3.67,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,2.67),(6,'Wakib ','Avro','011142032','1234','CSE','wakib.avro@gmail.com','01799 666331','House no.111, Dhanmondi 15, Dhaka','2016-07-06','Father','Name','uyygbgc@gmail.com','01698 556611','password',3.33,4.00,1.33,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,3.00),(7,'Faarid ','Alin','011143087','1234','CSE','asdsad@asdfsaf.com','01897 523656','House no.87, Zigatola, Dhaka','2016-07-07','Sdssd','Asdsa','asdsad@asdfsaf.com','01742 258899','password',3.67,1.33,3.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,3.33),(8,'Shaikh ','Maruf ','011142052','1234','CSE','adsas@gjsal.com','01744 526655','House no.94, Zigatola, Dhaka','2016-07-21','Aasds','Fsdssd','adsas@gjsal.com','01425 522236','password',1.00,2.67,2.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,3.67),(9,'Tarek ','Mahmud','011142501','1234','CSE','tmahmud@gmail.com','01552 558877','House no.65, Farmghate, Dhaka','2016-07-05','Bdfgf','Ddfgdf','wefsfvfx@gmail.com','01866 222354','password',2.00,4.00,4.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,2.67),(10,'Wahid ','Raj','011152064','1234','CSE','wahidraj@gmail.com','01557 881122','House no.7/A, Farmghate, Dhaka','2016-06-29','Edfgf','Tdfgdg','sfdsdg@gmail.com','01924 223344','password',2.67,3.67,3.33,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,2.33),(11,'Mohammad ','Osman','011153033','1234','EEE','m.osman@gmail.com','01745 256353','House no.87, Motijheel, Dhaka','2016-07-09','Janina','Ami','rsdtrgf@gmail.com','01748 557788','password',2.33,4.00,1.33,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,3.00);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-24 10:14:01
