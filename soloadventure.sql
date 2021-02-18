-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: localhost    Database: soloadventure
-- ------------------------------------------------------
-- Server version	8.0.21-0ubuntu0.20.04.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `links`
--

DROP TABLE IF EXISTS `links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `links` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `target_id` int unsigned DEFAULT NULL,
  `story_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `story_id` (`story_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `links`
--

LOCK TABLES `links` WRITE;
/*!40000 ALTER TABLE `links` DISABLE KEYS */;
INSERT INTO `links` VALUES (1,'Jag ska vara försiktig',2,1),(2,'Sluta vara så orolig',3,1),(3,'Tack, jag mår bra.',4,2),(4,'Det angår inte dig!',5,2),(5,'Okej.',4,2),(6,'Tack, jag mår bra.',4,3),(7,'Det angår inte dig!',5,3),(8,'Okej.',4,3),(9,'Till min mormor.',6,4),(10,'Vart jag ska har du inte med att göra',6,4),(11,'Jag vill inte berätta för dig',6,4),(12,'Till min mormor.',6,5),(13,'Vart jag ska har du inte med att göra',6,5),(14,'Jag vill inte berätta för dig',6,5),(15,'Kaka och vin. Igår var det bak-dag och min stackars sjuka mormor skall få något gott så att hon blir bättre.',7,6),(16,'Ingenting.',7,6),(17,'Ingenting. Gå nu annars skjuter jag dig nästa gång jag ser dig.',8,6),(18,'Hon bor en bra bit in i skogen. Hennes hus står under tre stora ekträd. Du känner säkert till det.',9,7),(19,'Varför undrar du?',9,7),(20,'Mamma har sagt att jag inte ska prata med främlingar',10,7),(21,'Hon bor en bra bit in i skogen. Hennes hus står under tre stora ekträd. Du känner säkert till det.',9,8),(22,'Varför undrar du?',9,8),(23,'Mamma har sagt att jag inte ska prata med främlingar',10,8),(24,'Ja, det skulle säkert göra mormor glad.',11,9),(25,'Nej jag vill inte göra det.',12,9),(26,'Glöm det din dumskalle.',13,9),(27,'Ja, det skulle säkert göra mormor glad.',11,10),(28,'Nej jag vill inte göra det.',12,10),(29,'Glöm det din dumskalle.',13,10);
/*!40000 ALTER TABLE `links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meeps`
--

DROP TABLE IF EXISTS `meeps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meeps` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned DEFAULT NULL,
  `body` text NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meeps`
--

LOCK TABLES `meeps` WRITE;
/*!40000 ALTER TABLE `meeps` DISABLE KEYS */;
INSERT INTO `meeps` VALUES (1,0,'Hello world!','2020-09-25 08:57:33','2020-09-25 08:57:33'),(3,0,'Lorem ipsum dolor sit amet consectetur adipisicing elit. Quod doloremque cumque corporis repellendus, enim, excepturi harum commodi rem esse recusandae quam similique praesentium tempore itaque dolorem, veritatis tenetur hic? Eligendi.','2020-09-29 10:47:12','2020-09-29 10:47:12');
/*!40000 ALTER TABLE `meeps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `story`
--

DROP TABLE IF EXISTS `story`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `story` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story`
--

LOCK TABLES `story` WRITE;
/*!40000 ALTER TABLE `story` DISABLE KEYS */;
INSERT INTO `story` VALUES (1,'En gång för länge sedan fanns det en liten flicka, som alla tyckte mycket om, men mest av alla hennes mormor.\r\nHon skulle kunna ge detta barn vad som helst.\r\nEn gång gav hon henne en liten röd luva, som passade henne så bra att hon aldrig ville ha på sig något annat.\r\nHon kallades därför för Rödluvan.\r\nEn dag sa hennes mor till henne ”Kom Rödluvan, här är en bit av den nybakade kakan och en flaska vin.\r\nTag med dem till din mormor, hon är lite sjuk och det skulle göra henne gott. Gå innan det blir för varmt.\r\nGå försiktigt och lämna inte vägen, för då kan du ramla och slå sönder flaskan.\r\nNär du kommer in i hennes rum, skall du inte glömma att säga god morgon, innan du springer runt i varje hörn.”\r\nVad ska Rödluvan svara?'),(2,'Rödluvan gav sig iväg.\r\nMormor bodde ute i skogen långt från byn där Rödluvan bodde.\r\nJust när Rödluvan gick in i skogen mötte en varg henne. Rödluvan visste inte vad det var för en konstig figur och blev inte alls rädd för honom.\r\n”God dag, lilla Rödluvan, hur mår du min lilla vän”, sa han.\r\nVad vill du att Rödluvan ska svara vargen?'),(3,'Rödluvans mamma blev arg och gick därifrån.\r\nMormor bodde ute i skogen långt från byn där Rödluvan bodde. Just när Rödluvan gick in i skogen mötte en varg henne. Rödluvan visste inte vad det var för en konstig figur och blev inte alls rädd för honom.\r\n”God dag, lilla Rödluvan, hur mår du min lilla vän”, sa han.\r\nVad vill du att Rödluvan ska svara vargen?'),(4,'\"Okej\" sa vargen. \"Vart skall du ta vägen så tidigt?” frågade vargen nyfikt.\r\nVad vill du att Rödluvan ska svara vargen?'),(5,'Vargen fnös och visade framtänderna. \"Vart skall du ta vägen så tidigt?” frågade vargen surt.\r\nVad vill du att Rödluvan ska svara vargen?'),(6,'\"Så trevligt\" sa vargen. \"Vad har du i korgen?”, frågade vargen.\r\nVad vill du att Rödluvan ska svara vargen?'),(7,' \"Var bor din mormor?”, frågade vargen.'),(8,'Vargens ansiktsuttryck blev rödare och Rödluvan såg på honom att vargen höll på att bli rasande.  \"Var bor din mormor?”, frågade vargen.'),(9,'”Vilken späd liten varelse”, tänkte vargen. ”Vilken fin, fyllig munsbit hon kommer att bli.\r\nHon kommer att bli mycket godare än den gamla kvinnan.\r\nJag måste vara listig så att jag kan fånga båda två.”\r\nSå vargen promenerade en stund vid Rödluvans sida och sa sedan:\r\n”Se så vackra blommor det växer här runt omkring.\r\nSkall du inte se dig lite omkring, lyssna på fåglarna och plocka en bukett blommor till din mormor?'),(10,'Kommer sen'),(11,'Kommer sen'),(12,'Kommer sen'),(13,'Kommer sen');
/*!40000 ALTER TABLE `story` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (0,'Default',' ',NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-31 20:19:59