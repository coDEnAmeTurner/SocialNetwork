-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: socialdb
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `academic_rank`
--

DROP TABLE IF EXISTS `academic_rank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `academic_rank` (
  `id` int NOT NULL AUTO_INCREMENT,
  `academic_rank_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_rank`
--

LOCK TABLES `academic_rank` WRITE;
/*!40000 ALTER TABLE `academic_rank` DISABLE KEYS */;
INSERT INTO `academic_rank` VALUES (1,'professor'),(2,'associate professor');
/*!40000 ALTER TABLE `academic_rank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `action`
--

DROP TABLE IF EXISTS `action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `action` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action`
--

LOCK TABLES `action` WRITE;
/*!40000 ALTER TABLE `action` DISABLE KEYS */;
INSERT INTO `action` VALUES (1,'like'),(2,'haha'),(3,'love');
/*!40000 ALTER TABLE `action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `action_post`
--

DROP TABLE IF EXISTS `action_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `action_post` (
  `user_id` int NOT NULL,
  `post_id` int NOT NULL,
  `action_id` int NOT NULL,
  `counts` int DEFAULT (0),
  PRIMARY KEY (`user_id`,`post_id`),
  KEY `post_id` (`post_id`),
  KEY `action_id` (`action_id`),
  CONSTRAINT `action_post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `action_post_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE,
  CONSTRAINT `action_post_ibfk_3` FOREIGN KEY (`action_id`) REFERENCES `action` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action_post`
--

LOCK TABLES `action_post` WRITE;
/*!40000 ALTER TABLE `action_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `action_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `user_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (23);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alumnus`
--

DROP TABLE IF EXISTS `alumnus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumnus` (
  `typical_user_id` int NOT NULL,
  `student_id` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`typical_user_id`),
  CONSTRAINT `alumnus_ibfk_1` FOREIGN KEY (`typical_user_id`) REFERENCES `typical_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnus`
--

LOCK TABLES `alumnus` WRITE;
/*!40000 ALTER TABLE `alumnus` DISABLE KEYS */;
INSERT INTO `alumnus` VALUES (11,'2154050297'),(12,'2154050297'),(17,'2154050297'),(19,'2154050297'),(22,''),(43,'2151010429');
/*!40000 ALTER TABLE `alumnus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choice`
--

DROP TABLE IF EXISTS `choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `choice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `voteCount` int DEFAULT '0',
  `survey_question_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `survey_question_id` (`survey_question_id`),
  CONSTRAINT `choice_ibfk_1` FOREIGN KEY (`survey_question_id`) REFERENCES `survey_question` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choice`
--

LOCK TABLES `choice` WRITE;
/*!40000 ALTER TABLE `choice` DISABLE KEYS */;
INSERT INTO `choice` VALUES (28,' Phát triển phần mềm',0,8),(29,'Quản trị mạng',0,8),(30,'Dữ liệu và phân tích',0,8),(36,'Trí tuệ nhân tạo (AI)',0,10),(38,'Internet vạn vật (IoT)',0,10),(41,' Kỹ sư phần mềm',0,15),(42,'Chuyên gia an ninh mạng',0,15),(43,'Ý kiến khác?',0,10),(45,'Điện toán đám mây',0,15),(46,'Chỉ một số vị trí mới thuận lợi',0,16),(47,' Không, không có nhiều cơ hội thăng tiến',0,16),(48,' Có, rất thuận lợi',0,16),(49,'Khó đánh giá',0,16),(50,'Máy học ',0,10),(51,'Lập trình game',0,8);
/*!40000 ALTER TABLE `choice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `user_id` int NOT NULL,
  `post_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_comment_foreign_key` (`user_id`),
  KEY `post_foreign_key` (`post_id`),
  CONSTRAINT `post_foreign_key` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_comment_foreign_key` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (9,'Hay',17,26,'2024-06-19 13:26:34','2024-06-19 20:26:34'),(10,'Ngon',17,26,'2024-06-19 13:41:42','2024-06-19 20:41:42'),(12,'Milo > Ovaltine',17,26,'2024-06-19 13:48:00','2024-06-19 20:48:00'),(16,'T mún uống thuốc :)))))',11,26,NULL,NULL);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content_type`
--

DROP TABLE IF EXISTS `content_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `content_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content_type`
--

LOCK TABLES `content_type` WRITE;
/*!40000 ALTER TABLE `content_type` DISABLE KEYS */;
INSERT INTO `content_type` VALUES (1,'typical'),(2,'invitation'),(3,'survey');
/*!40000 ALTER TABLE `content_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `degree`
--

DROP TABLE IF EXISTS `degree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `degree` (
  `id` int NOT NULL AUTO_INCREMENT,
  `degree_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `degree`
--

LOCK TABLES `degree` WRITE;
/*!40000 ALTER TABLE `degree` DISABLE KEYS */;
INSERT INTO `degree` VALUES (1,'bachelor'),(2,'master'),(3,'doctorate');
/*!40000 ALTER TABLE `degree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitation`
--

DROP TABLE IF EXISTS `invitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invitation` (
  `post_id` int NOT NULL,
  `location` varchar(2050) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  CONSTRAINT `invitation_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitation`
--

LOCK TABLES `invitation` WRITE;
/*!40000 ALTER TABLE `invitation` DISABLE KEYS */;
INSERT INTO `invitation` VALUES (29,'364 Cộng Hòa, Tân Bình, Tp. HCM. ','2024-04-17 07:30:00');
/*!40000 ALTER TABLE `invitation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer`
--

DROP TABLE IF EXISTS `lecturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturer` (
  `typical_user_id` int NOT NULL,
  `title_id` int NOT NULL,
  `locked` tinyint(1) DEFAULT '0',
  `dispatching_admin_id` int DEFAULT NULL,
  PRIMARY KEY (`typical_user_id`),
  KEY `dispatching_admin_id` (`dispatching_admin_id`),
  KEY `title_id` (`title_id`),
  CONSTRAINT `lecturer_ibfk_1` FOREIGN KEY (`dispatching_admin_id`) REFERENCES `admin` (`user_id`),
  CONSTRAINT `lecturer_ibfk_2` FOREIGN KEY (`typical_user_id`) REFERENCES `typical_user` (`user_id`),
  CONSTRAINT `lecturer_ibfk_3` FOREIGN KEY (`title_id`) REFERENCES `title` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer`
--

LOCK TABLES `lecturer` WRITE;
/*!40000 ALTER TABLE `lecturer` DISABLE KEYS */;
INSERT INTO `lecturer` VALUES (37,2,0,23),(38,4,0,23),(40,1,0,23);
/*!40000 ALTER TABLE `lecturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `content_type_id` int NOT NULL,
  `unlocked` tinyint(1) DEFAULT '1',
  `user_id` int NOT NULL,
  `content` longtext,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `content_type_id` (`content_type_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`content_type_id`) REFERENCES `content_type` (`id`),
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (26,'Where does it come from?',1,1,11,'sectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of G','2024-06-17 08:01:20','2024-06-17 15:01:20'),(27,'Where can I get some?',1,1,11,'ave suffered alteration in some form, by injected humour, or randomised words which don\'t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn\'t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predef','2024-06-17 08:01:20','2024-06-17 15:01:20'),(29,'OFFICE TOUR - THAM QUAN CÔNG TY DXC VIETNAM',2,1,11,'Với sự tham dự của Thầy Dương Hữu Thành - Giảng viên Khoa; Anh Nguyễn Trung Hậu - Phụ trách quan hệ doanh nghiệp của Khoa và cùng các bạn sinh viên Khoa CNTT, chương trình diễn ra tốt đẹp và đã tạo điều kiện cho các bạn sinh viên có thêm cơ hội việc làm và có những trải nghiệm thú vị tại công ty. ','2024-06-17 08:01:20','2024-06-17 15:01:20'),(30,'SurveySruevy',3,1,11,'SurveySruevy','2024-06-17 08:01:20','2024-06-17 15:01:20'),(31,'I\'m new here',1,1,40,'Anyone tells me your experience so far on this platform? I\'m new here and I need some guidance for how this place functions.','2024-06-23 08:47:56','2024-06-23 15:47:56');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_image`
--

DROP TABLE IF EXISTS `post_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` longtext,
  `post_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `post_image_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_image`
--

LOCK TABLES `post_image` WRITE;
/*!40000 ALTER TABLE `post_image` DISABLE KEYS */;
INSERT INTO `post_image` VALUES (28,'https://res.cloudinary.com/dymtveeni/image/upload/v1718553166/k6587wja4bgikdfmignj.jpg',26),(29,'https://res.cloudinary.com/dymtveeni/image/upload/v1718553169/rp5jj9t0prmgxagsmazb.jpg',26),(30,'https://res.cloudinary.com/dymtveeni/image/upload/v1718553171/p9o7ywn24gf9cbb8vetb.jpg',26),(31,'https://res.cloudinary.com/dymtveeni/image/upload/v1718553173/vdpa4hrhvhp5s5r40nwi.jpg',26),(32,'https://res.cloudinary.com/dymtveeni/image/upload/v1718555549/sthwtgxsjyram7gvca9z.png',27),(33,'https://res.cloudinary.com/dymtveeni/image/upload/v1718555551/dsgqydzjci5ffcu7oxnp.jpg',27),(34,'https://res.cloudinary.com/dymtveeni/image/upload/v1718555554/r1zu9xins8wlsfdbjdgc.jpg',27),(35,'https://res.cloudinary.com/dymtveeni/image/upload/v1718555556/jglseneo1pgfhsgsrm0v.jpg',27),(39,'https://res.cloudinary.com/dymtveeni/image/upload/v1718686324/h0pm0xcfphbfqpcvzvvo.png',29),(40,'https://res.cloudinary.com/dymtveeni/image/upload/v1718686326/cvntjagyjkxqom1wfmma.jpg',29),(41,'https://res.cloudinary.com/dymtveeni/image/upload/v1718686327/vtdtxcwj7wbjbfqubiky.jpg',29),(42,'https://res.cloudinary.com/dymtveeni/image/upload/v1718729185/mpkccucgsprl0ndy1l1q.png',30),(43,'https://res.cloudinary.com/dymtveeni/image/upload/v1718729187/pmilnuo1alqwqjctkffr.jpg',30),(44,'https://res.cloudinary.com/dymtveeni/image/upload/v1718729188/ikf3bpk7pjqyvtiyuosb.webp',30),(45,'https://res.cloudinary.com/dymtveeni/image/upload/v1719132479/plaenjgscnd3piakv5xe.jpg',31),(46,'https://res.cloudinary.com/dymtveeni/image/upload/v1719132482/zfu3pgb7zrkzci9lwdt8.jpg',31),(47,'https://res.cloudinary.com/dymtveeni/image/upload/v1719132484/t3z5hwd7uet0wy9wgz8s.jpg',31),(48,'https://res.cloudinary.com/dymtveeni/image/upload/v1719132486/dlwguef2hqw0vidnkyej.jpg',31);
/*!40000 ALTER TABLE `post_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey` (
  `post_id` int NOT NULL,
  PRIMARY KEY (`post_id`),
  CONSTRAINT `survey_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey`
--

LOCK TABLES `survey` WRITE;
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
INSERT INTO `survey` VALUES (30);
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_question`
--

DROP TABLE IF EXISTS `survey_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `survey_id` int NOT NULL,
  `content` longtext,
  PRIMARY KEY (`id`),
  KEY `survey_id` (`survey_id`),
  CONSTRAINT `survey_question_ibfk_1` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`post_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_question`
--

LOCK TABLES `survey_question` WRITE;
/*!40000 ALTER TABLE `survey_question` DISABLE KEYS */;
INSERT INTO `survey_question` VALUES (8,30,'Ngành công nghệ thông tin đang có nhu cầu tuyển dụng nhiều nhất ở những lĩnh vực nào?'),(10,30,'Trong 5 năm tới, công nghệ nào được dự báo sẽ tạo ra nhiều cơ hội việc làm nhất trong ngành CNTT?'),(15,30,'Các công ty trong ngành CNTT thường ưu tiên tuyển dụng ứng viên có kinh nghiệm ở vị trí nào?'),(16,30,'Theo bạn, ngành CNTT có thuận lợi về cơ hội thăng tiến và phát triển sự nghiệp so với các ngành nghề khác không?');
/*!40000 ALTER TABLE `survey_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `title`
--

DROP TABLE IF EXISTS `title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `title` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `title`
--

LOCK TABLES `title` WRITE;
/*!40000 ALTER TABLE `title` DISABLE KEYS */;
INSERT INTO `title` VALUES (1,'giang vien'),(2,'nghien cuu sinh'),(3,'truong khoa'),(4,'pho khoa');
/*!40000 ALTER TABLE `title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typical_user`
--

DROP TABLE IF EXISTS `typical_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typical_user` (
  `user_id` int NOT NULL,
  `degree_id` int NOT NULL,
  `academic_rank_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `degree_id` (`degree_id`),
  KEY `academic_rank_id` (`academic_rank_id`),
  CONSTRAINT `typical_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `typical_user_ibfk_2` FOREIGN KEY (`degree_id`) REFERENCES `degree` (`id`),
  CONSTRAINT `typical_user_ibfk_3` FOREIGN KEY (`academic_rank_id`) REFERENCES `academic_rank` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typical_user`
--

LOCK TABLES `typical_user` WRITE;
/*!40000 ALTER TABLE `typical_user` DISABLE KEYS */;
INSERT INTO `typical_user` VALUES (11,1,1),(12,1,1),(17,2,1),(19,2,2),(22,2,2),(37,2,2),(38,3,2),(40,1,1),(43,2,NULL);
/*!40000 ALTER TABLE `typical_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) NOT NULL,
  `avatar` longtext NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` longtext NOT NULL,
  `user_role_id` int NOT NULL,
  `dob` date NOT NULL,
  `email` longtext NOT NULL,
  `phone` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `background` longtext NOT NULL,
  `theme` tinytext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (11,'Mã Siếu TIên','https://res.cloudinary.com/dymtveeni/image/upload/v1716743482/zw3qjwxs7b9eon4gydxb.jpg','sieutien','$2a$10$3Z9nQ76ZG9dcnZVIau9sreagjGg8ul9f/qKJ3PJOVwOzRqPU2QDZi',2,'2024-04-29','2154050297tien@ou.edu.vn','564683181','2024-06-19 07:24:45','2024-06-23 22:32:11','https://res.cloudinary.com/dymtveeni/image/upload/v1719069374/irithyll_of_the_boreal_valley__2__by_twin_humanities_darpdcw_qcb6zj.jpg','#eb9123'),(12,'Mã Siếu TTIên','https://res.cloudinary.com/dymtveeni/image/upload/v1716832239/b9kzdlx4dynss6jihfzd.png','ssieutien','$2a$10$eEQM2miv8ur6om1JeZKYKOt2CcpMcfpacw1cpPU/rjZ9yIeuILI8m',2,'2024-04-29','2154050287tien@ou.edu.vn','564683181','2024-06-19 07:24:45','2024-06-23 22:32:11','https://res.cloudinary.com/dymtveeni/image/upload/v1719069374/irithyll_of_the_boreal_valley__2__by_twin_humanities_darpdcw_qcb6zj.jpg','#eb9123'),(17,'Lê Tấn Tài','https://res.cloudinary.com/dymtveeni/image/upload/v1716902196/vac6f40p4n0qmsj4oxu8.png','tantai','$2a$10$lUEsq3mnOawdbSI6.v5RyesjYDZv7gAZrH3m641KDGP1o4ksrGzla',2,'2024-04-29','letantai@gmail.com','564683181','2024-06-19 07:24:45','2024-06-23 22:32:11','https://res.cloudinary.com/dymtveeni/image/upload/v1719069374/irithyll_of_the_boreal_valley__2__by_twin_humanities_darpdcw_qcb6zj.jpg','#eb9123'),(19,'Lưu Danh','https://res.cloudinary.com/dymtveeni/image/upload/v1716902760/rloybymx8z4wbou35scw.jpg','luudanh','$2a$10$RiPmhh7W63tz6LOA32/4q.Iv0Q0l0JnvqF2gjbA1FIU0wq/3ue9mu',2,'2024-04-29','luudanh@gmail.com','564683181','2024-06-19 07:24:45','2024-06-23 22:32:11','https://res.cloudinary.com/dymtveeni/image/upload/v1719069374/irithyll_of_the_boreal_valley__2__by_twin_humanities_darpdcw_qcb6zj.jpg','#eb9123'),(22,'Trần Quốc Tuấn','https://res.cloudinary.com/dymtveeni/image/upload/v1718432758/mah0gq6snmeisnrwxkb0.png','tranquoctuan','$2a$10$GSSO/eaQtvkxcc.awYlfZelpnMlEVT3wzAkleghyVQpINVBnx6gAa',2,'2003-10-07','tranquoctuan@gmail.com','321986488','2024-06-19 07:24:45','2024-06-23 22:32:11','https://res.cloudinary.com/dymtveeni/image/upload/v1719069374/irithyll_of_the_boreal_valley__2__by_twin_humanities_darpdcw_qcb6zj.jpg','#eb9123'),(23,'Trần Lưu Quốc Tuấn','https://res.cloudinary.com/dymtveeni/image/upload/v1718895138/cwryuzl7jvcdeugnmu9j.jpg','tranluuquoctuan','$2a$10$l3RaYpMp8lR5r.jYrc9M4u25LWKuSFD67MIGi6dJGbb..W38Agscy',1,'2003-10-29','2151010419tuan@ou.edu.vn','0869189954','2024-06-19 07:24:45','2024-06-23 22:32:11','https://res.cloudinary.com/dymtveeni/image/upload/v1719069374/irithyll_of_the_boreal_valley__2__by_twin_humanities_darpdcw_qcb6zj.jpg','#eb9123'),(37,'Tuấn The Lecturer','https://res.cloudinary.com/dymtveeni/image/upload/v1719082544/hvnokvd0gwz8lgacjai8.jpg','lecturertuan','$2a$10$RrC2TerSvqJdC.rBldpQ7eF0r/.yox9xWItiUvEd4ROqVcs8a1ZvS',3,'2003-09-28','tranluuquoctuan191191@gmail.com','0869189954','2024-06-19 07:24:45','2024-06-23 22:32:11','https://res.cloudinary.com/dymtveeni/image/upload/v1719077995/pqgq03rtycrr298qy6pk.jpg','#eb9123'),(38,'Trương Bùi Anh Tuấn','https://res.cloudinary.com/dymtveeni/image/upload/v1719062064/m9enmjfqamwq1fegnaux.jpg','truongbuianhtuan','$2a$10$Nj.sLPETDIfZVPQumBVKHOfcS9zhKSJf8Q9hU0PNWcwWpjH8bLnpa',3,'2003-04-28','2151010421tuan@ou.edu.vn','0377822815','2024-06-22 13:14:26','2024-06-23 22:32:11','https://res.cloudinary.com/dymtveeni/image/upload/v1719069374/irithyll_of_the_boreal_valley__2__by_twin_humanities_darpdcw_qcb6zj.jpg','#eb9123'),(40,'Võ Quốc Huy','https://res.cloudinary.com/dymtveeni/image/upload/v1719129620/yunic5e2ny5exixr1id7.jpg','voquochuy','$2a$10$TviyXU66pRfZWNXI9DYdpO89T7bOUe5iPWPYkA9RZh5Iz.Ir0Wueq',3,'2003-06-30','2151013029huy@ou.edu.vn','215642168','2024-06-23 08:00:18','2024-06-23 23:47:38','https://res.cloudinary.com/dymtveeni/image/upload/v1719069374/irithyll_of_the_boreal_valley__2__by_twin_humanities_darpdcw_qcb6zj.jpg','#0dd9c1'),(43,'Nguyễn Hữu Tú','https://res.cloudinary.com/dymtveeni/image/upload/v1719131832/atovr2fsi1ojaqzi4nsb.png','nguyenhuutu','$2a$10$mKmKdv5u/k35VLQvo1V24O8Y79wCu9V93kKv2zTDHUElTPNB8raYy',2,'2003-10-30','2151010429tu@ou.edu.vn','0387498332','2024-06-23 08:37:10','2024-06-23 22:32:11','https://res.cloudinary.com/dymtveeni/image/upload/v1719069374/irithyll_of_the_boreal_valley__2__by_twin_humanities_darpdcw_qcb6zj.jpg','#eb9123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'admin'),(2,'alumnus'),(3,'lecturer');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote`
--

DROP TABLE IF EXISTS `vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vote` (
  `user_id` int NOT NULL,
  `survey_question_id` int NOT NULL,
  `choice_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`survey_question_id`),
  KEY `survey_question_id` (`survey_question_id`),
  KEY `choice_id` (`choice_id`),
  CONSTRAINT `vote_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `vote_ibfk_2` FOREIGN KEY (`survey_question_id`) REFERENCES `survey_question` (`id`) ON DELETE CASCADE,
  CONSTRAINT `vote_ibfk_3` FOREIGN KEY (`choice_id`) REFERENCES `choice` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote`
--

LOCK TABLES `vote` WRITE;
/*!40000 ALTER TABLE `vote` DISABLE KEYS */;
INSERT INTO `vote` VALUES (40,8,29),(40,15,41),(40,10,43),(40,16,47);
/*!40000 ALTER TABLE `vote` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-24 11:56:43
