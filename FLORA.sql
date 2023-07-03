-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: numshop
-- ------------------------------------------------------
-- Server version	5.7.38-log

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `adressee` varchar(50) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `completedaddress` varchar(255) DEFAULT NULL,
  `isdefault` int(11) NOT NULL DEFAULT '0' COMMENT '默认地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (4,5,'海梅鱼','12345678911','云南省昆明市五华区龙泉路云南财经大学',1),(5,5,'江江','16687466895','云南省昆明市五华区龙泉路云南财经大学',0),(6,5,'飞飞','15125534163','云南省昆明市五华区龙泉路云南财经大学',0),(10,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `details`
--

DROP TABLE IF EXISTS `details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `orderid` int(11) DEFAULT NULL COMMENT '订单',
  `productid` int(11) DEFAULT NULL COMMENT '商品',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `productno` varchar(255) DEFAULT NULL COMMENT '商品编码',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='订单详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `details`
--

LOCK TABLES `details` WRITE;
/*!40000 ALTER TABLE `details` DISABLE KEYS */;
INSERT INTO `details` VALUES (1,1,12,1,'Fri May 13 20:48:46 CST 20220',50.00),(2,1,12,1,'Fri May 13 20:48:46 CST 20220',50.00),(3,2,1,1,'Fri May 13 20:49:30 CST 20220',50.00),(4,3,1,1,'Fri May 13 20:49:32 CST 20220',50.00),(5,4,1,1,'Fri May 13 20:49:32 CST 20220',50.00),(6,5,1,1,'Fri May 13 20:49:32 CST 20220',50.00),(7,6,1,1,'Fri May 13 20:49:33 CST 20220',50.00),(8,7,12,1,'Fri May 13 20:56:36 CST 20220',50.00),(9,7,12,1,'Fri May 13 20:56:36 CST 20220',50.00),(10,7,12,1,'Fri May 13 20:56:36 CST 20220',50.00),(11,8,12,1,'Fri May 13 20:59:02 CST 20220',50.00),(12,8,12,1,'Fri May 13 20:59:02 CST 20220',50.00),(13,9,12,1,'Fri May 13 20:59:04 CST 20220',50.00),(14,9,12,1,'Fri May 13 20:59:04 CST 20220',50.00),(15,10,12,1,'Fri May 13 20:59:34 CST 20220',50.00),(16,10,12,1,'Fri May 13 20:59:34 CST 20220',50.00),(17,11,12,1,'Fri May 13 20:59:56 CST 20220',50.00),(18,8,10,1,'NO16527518063940',50.00),(19,9,12,1,'NO16528418629560',50.00),(20,10,12,1,'NO16825657946220',NULL),(21,11,12,1,'NO16825658125490',NULL),(22,11,12,1,'NO16825658125520',NULL),(23,11,12,1,'NO16825658125550',NULL),(24,11,12,1,'NO16825658125580',NULL),(25,12,12,1,'NO16825658236480',NULL),(26,12,12,1,'NO16825658236520',NULL),(27,12,12,1,'NO16825658236550',NULL),(28,12,12,1,'NO16825658236580',NULL),(29,13,12,1,'NO16825658830980',NULL),(30,13,12,1,'NO16825658831010',NULL),(31,13,12,1,'NO16825658831040',NULL),(32,13,12,1,'NO16825658831070',NULL),(33,14,12,1,'NO16825658856860',NULL),(34,14,12,1,'NO16825658856900',NULL),(35,14,12,1,'NO16825658856930',NULL),(36,14,12,1,'NO16825658856950',NULL),(37,15,12,1,'NO16825664210730',NULL),(38,15,12,1,'NO16825664216380',NULL),(39,16,12,1,'NO16825664930400',NULL),(40,17,12,1,'NO16825669338650',NULL),(41,17,12,1,'NO16825669338670',NULL),(42,18,12,1,'NO16825669463950',NULL),(43,18,12,1,'NO16825669463980',NULL),(44,19,6,1,'NO16825721213550',NULL),(45,20,13,1,'NO16825968706090',NULL),(46,21,13,1,'NO16825969293520',NULL),(47,22,6,1,'NO16826037939060',NULL),(48,22,6,1,'NO16826037939130',NULL),(49,22,6,1,'NO16826037939160',NULL),(50,23,13,1,'NO16826876954020',NULL),(51,24,13,1,'NO16826878089640',NULL);
/*!40000 ALTER TABLE `details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `createtime` varchar(255) DEFAULT NULL COMMENT '发送时间',
  `phone` varchar(255) DEFAULT NULL COMMENT '发送手机',
  `validitytime` int(11) DEFAULT NULL COMMENT '有效期',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='短信';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notes`
--

LOCK TABLES `notes` WRITE;
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `userid` int(11) DEFAULT NULL COMMENT '购买者',
  `createtime` varchar(255) DEFAULT NULL COMMENT '购买时间',
  `paytime` varchar(255) DEFAULT NULL COMMENT '支付时间',
  `paytype` varchar(255) DEFAULT NULL COMMENT '支付方式',
  `status` varchar(255) DEFAULT NULL COMMENT '支付状态',
  `reserved` varchar(255) DEFAULT NULL COMMENT 'out_trade_no',
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,3,'2022-05-13 20:48:46',NULL,NULL,'已完成',NULL,NULL,NULL,NULL,NULL),(2,3,'2022-05-13 20:49:30',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL),(3,3,'2022-05-13 20:49:32',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL),(4,3,'2022-05-13 20:49:32',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL),(5,3,'2022-05-13 20:49:32',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL),(6,3,'2022-05-13 20:49:33',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL),(7,3,'2022-05-13 20:56:36',NULL,NULL,'已完成',NULL,NULL,NULL,NULL,NULL),(8,3,'2022-05-17 09:43:26',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL),(9,3,'2022-05-18 10:44:22',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL),(10,5,'2023-04-27 11:23:14',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL),(19,5,'2023-04-27 13:08:41',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL),(20,5,'2023-04-27 20:01:10',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL),(21,5,'2023-04-27 20:02:09',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL),(22,5,'2023-04-27 21:56:33',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL),(23,5,'2023-04-28 21:14:55',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL),(24,5,'2023-04-28 21:16:48',NULL,NULL,'待支付',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `count` int(11) DEFAULT NULL COMMENT '库存数量',
  `imageurl` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `otherurl` varchar(255) DEFAULT NULL COMMENT '详情图片',
  `tag` varchar(255) DEFAULT NULL COMMENT '标签',
  `createtime` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `shoptime` varchar(255) DEFAULT NULL COMMENT '售卖时间',
  `userid` int(11) DEFAULT NULL COMMENT '用户',
  `description` text COMMENT '商品描述',
  `tokenId` varchar(255) DEFAULT NULL COMMENT '链Token',
  `price` varchar(255) DEFAULT NULL COMMENT '单价',
  `isdelete` varchar(255) DEFAULT '1' COMMENT '是否删除(1-未删除 2-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'银莲花',1000,'http://localhost:8080/flora/getImage/product-01.jpg','http://localhost:8080/flora/getImage/large-01.jpg','早春时节',NULL,'2022-05-12 11:05:04',NULL,NULL,NULL,'50','1'),(2,'郁香忍冬',100,'http://localhost:8080/flora/getImage/product-02.jpg','http://localhost:8080/flora/getImage/large-02.jpg','捧花',NULL,'2022-05-12 11:10:08',1,NULL,NULL,'100','1'),(3,'飞燕草',1000,'http://localhost:8080/flora/getImage/product-03.jpg','http://localhost:8080/flora/getImage/large-03.jpg','捧花','2022-05-12 11:30:55','2022-05-12 11:05:04',1,NULL,NULL,'50','1'),(4,'喜林草',1000,'http://localhost:8080/flora/getImage/product-04.jpg','http://localhost:8080/flora/getImage/large-04.jpg','猫爪草','2022-05-12 11:30:58','2022-05-12 11:05:04',1,NULL,NULL,'50','1'),(5,'毛地黄',1000,'http://localhost:8080/flora/getImage/product-05.jpg','http://localhost:8080/flora/getImage/large-05.jpg','捧花','2022-05-12 11:31:00','2022-05-12 11:05:04',1,NULL,NULL,'50','1'),(6,'黑种草',1000,'http://localhost:8080/flora/getImage/product-06.jpg','http://localhost:8080/flora/getImage/large-06.jpg','捧花','2022-05-12 11:31:03','2022-05-12 11:05:04',1,NULL,NULL,'50','1'),(7,'笑靥花',1000,'http://localhost:8080/flora/getImage/product-07.jpg','http://localhost:8080/flora/getImage/large-07.jpg','热带花','2022-05-12 11:31:05','2022-05-12 11:05:04',1,NULL,NULL,'50','1'),(8,'四照花',1000,'http://localhost:8080/flora/getImage/product-08.jpg','http://localhost:8080/flora/getImage/large-08.jpg','球状','2022-05-12 11:31:07','2022-05-12 11:05:04',1,NULL,NULL,'50','1'),(9,'紫珠',1000,'http://localhost:8080/flora/getImage/product-09.jpg','http://localhost:8080/flora/getImage/large-09.jpg','灌木','2022-05-12 11:31:09','2022-05-12 11:05:04',1,NULL,NULL,'50','1'),(10,'铃兰',1000,'http://localhost:8080/flora/getImage/product-10.jpg','http://localhost:8080/flora/getImage/large-10.jpg','早春时节','2022-05-12 11:31:11','2022-05-12 11:05:04',1,NULL,NULL,'50','1'),(11,'龙胆花',1000,'http://localhost:8080/flora/getImage/product-11.jpg','http://localhost:8080/flora/getImage/large-11.jpg','中药','2022-05-12 11:31:13','2022-05-12 11:05:04',1,NULL,NULL,'50','1'),(12,'蜀葵花',1000,'http://localhost:8080/flora/getImage/product-12.jpg','http://localhost:8080/flora/getImage/large-12.jpg','一丈红','2022-05-12 11:31:15','2022-05-12 11:05:04',1,NULL,NULL,'50','1'),(13,'红廖',1000,'http://localhost:8080/flora/getImage/product-10.jpg','http://localhost:8080/flora/getImage/small-01.jpg','捧花','2022-05-12 11:31:17','2022-08-12 11:05:04',1,NULL,NULL,'50','1'),(14,'鲜花速递混搭花束',5000,'http://localhost:8080/flora/getImage/FLORA_product1.jpg','http://localhost:8080/flora/getImage/FLORA_product1_intro.jpg','花束','2022-05-12 11:31:17','2022-08-12 11:05:04',1,NULL,NULL,'59','1'),(15,'鲜花速递混搭花束',5000,'http://localhost:8080/flora/getImage/FLORA_product2.jpg','http://localhost:8080/flora/getImage/FLORA_product2_intro.jpg','花束','2022-05-12 11:31:17','2022-08-12 11:05:04',1,NULL,NULL,'69','1'),(16,'鲜花速递混搭花束',5000,'http://localhost:8080/flora/getImage/FLORA_product3.jpg','http://localhost:8080/flora/getImage/FLORA_product3_intro.jpg','花束','2022-05-12 11:31:17','2022-08-12 11:05:04',1,NULL,NULL,'69','1'),(17,'鲜花速递混搭花束',5000,'http://localhost:8080/flora/getImage/FLORA_product4.jpg','http://localhost:8080/flora/getImage/FLORA_product4_intro.jpg','花束','2022-05-12 11:31:17','2022-08-12 11:05:04',1,NULL,NULL,'69','1'),(18,'鲜花速递混搭花束',5000,'http://localhost:8080/flora/getImage/FLORA_product5.jpg','http://localhost:8080/flora/getImage/FLORA_product5_intro.jpg','花束','2022-05-12 11:31:17','2022-08-12 11:05:04',1,NULL,NULL,'69','1');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcar`
--

DROP TABLE IF EXISTS `shoppingcar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shoppingcar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `count` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcar`
--

LOCK TABLES `shoppingcar` WRITE;
/*!40000 ALTER TABLE `shoppingcar` DISABLE KEYS */;
INSERT INTO `shoppingcar` VALUES (4,5,5,4),(18,5,12,1),(21,5,18,1);
/*!40000 ALTER TABLE `shoppingcar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slideshow`
--

DROP TABLE IF EXISTS `slideshow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slideshow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `imageurl` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `createtime` varchar(255) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='轮播图';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slideshow`
--

LOCK TABLES `slideshow` WRITE;
/*!40000 ALTER TABLE `slideshow` DISABLE KEYS */;
INSERT INTO `slideshow` VALUES (1,'http://localhost:8080/flora/getImage/slide-bg-1.jpg',1,'2022-07-13 19:58:47'),(2,'http://localhost:8080/flora/getImage/slide-bg-2.jpg',2,'2022-07-13 20:03:01'),(3,'http://localhost:8080/flora/getImage/slide-bg-3.jpg',1,'2022-07-13 20:03:08');
/*!40000 ALTER TABLE `slideshow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_tb`
--

DROP TABLE IF EXISTS `test_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `times` varchar(255) DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_tb`
--

LOCK TABLES `test_tb` WRITE;
/*!40000 ALTER TABLE `test_tb` DISABLE KEYS */;
INSERT INTO `test_tb` VALUES (1,'2022-05-14 12:00:00'),(2,'2022-05-01 11:00:00');
/*!40000 ALTER TABLE `test_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `realname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `openid` varchar(255) DEFAULT NULL COMMENT 'openid',
  `imageurl` varchar(255) DEFAULT NULL COMMENT '头像',
  `name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话号码',
  `cardno` varchar(50) DEFAULT NULL COMMENT '身份证号码',
  `authentication` int(1) DEFAULT '1' COMMENT '是否认证',
  `registertime` varchar(255) DEFAULT NULL COMMENT '注册时间',
  `authenticationtime` varchar(255) DEFAULT NULL COMMENT '认证时间',
  `address` varchar(255) DEFAULT NULL COMMENT '链地址',
  `privateKey` varchar(255) DEFAULT NULL COMMENT '私钥',
  `reserved` varchar(255) DEFAULT '用户' COMMENT '角色',
  `blockpassword` varchar(255) DEFAULT NULL COMMENT '链密码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `p_u` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','ecedf2ade10b695ed598cc07502f0d','黄丽鸟',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'管理员',NULL),(3,NULL,'13ac0783b08c23fe278a600ea2cdc3','用户_349567',NULL,NULL,'鲜云','18087557657','511321198909111470',2,'2022-07-14 22:41:36','2022-07-15 00:39:45','0x46Caa2877fAB01d1ACe8112B0249aC88D194cD7E','0x29a17895b5974237f31ee395ceab3f193e33e9eda71970888e246dd57f65121c','用户','47c940979b29b8941c8c22deb9a3f9'),(5,NULL,'ecedf2ade10b695ed598cc07502f0d','用户_137692','o-h_H6McrYQW1tkxcwFUPZbSrLEw',NULL,'海梅鱼','15687890811','530627200109167131',2,'2022-07-16 13:04:57','2023-04-27 11:14:34','0x7851F3565c8DCB896C98683102fb2d0E98262a64','0xa5f562c57ea4fbdb338cc8aeea4d177f37094968bf140aede4002a1aaa318615','用户','bd7e9f295aeb9e7b56c52c622cd173');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-28 23:21:51
