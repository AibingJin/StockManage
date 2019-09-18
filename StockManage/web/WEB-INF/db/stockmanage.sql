# Host: localhost  (Version: 5.6.17)
# Date: 2019-09-10 17:08:00
# Generator: MySQL-Front 5.3  (Build 2.53)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

DROP DATABASE IF EXISTS `stockmanage`;
CREATE DATABASE `stockmanage` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `stockmanage`;

#
# Source for table "cart"
#

DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "cart"
#


#
# Source for table "details"
#

DROP TABLE IF EXISTS `details`;
CREATE TABLE `details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `details_name` varchar(255) NOT NULL DEFAULT '',
  `type_id` int(11) NOT NULL DEFAULT '0',
  `model_list` varchar(255) NOT NULL DEFAULT '""',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "details"
#


#
# Source for table "in_stock"
#

DROP TABLE IF EXISTS `in_stock`;
CREATE TABLE `in_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `details_id` int(11) NOT NULL DEFAULT '0',
  `model` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `count` int(11) NOT NULL DEFAULT '1',
  `unit` varchar(10) NOT NULL DEFAULT '个',
  `type_id` int(11) NOT NULL DEFAULT '0',
  `in_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `position` varchar(255) DEFAULT NULL,
  `person_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "in_stock"
#


#
# Source for table "out_stock"
#

DROP TABLE IF EXISTS `out_stock`;
CREATE TABLE `out_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `details_id` int(11) NOT NULL DEFAULT '0',
  `model` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `count` int(11) NOT NULL DEFAULT '1',
  `unit` varchar(10) NOT NULL DEFAULT '个',
  `type_id` int(11) NOT NULL DEFAULT '0',
  `out_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `person_id` int(11) NOT NULL DEFAULT '0',
  `project_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "out_stock"
#


#
# Source for table "person"
#

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "person"
#


#
# Source for table "product"
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "product"
#


#
# Source for table "project"
#

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(100) NOT NULL DEFAULT '',
  `agent_name` varchar(100) NOT NULL DEFAULT '',
  `demand_name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "project"
#


#
# Source for table "stock_list"
#

DROP TABLE IF EXISTS `stock_list`;
CREATE TABLE `stock_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `details_id` int(11) NOT NULL DEFAULT '0',
  `model` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `stock` int(11) NOT NULL DEFAULT '1',
  `unit` varchar(10) NOT NULL DEFAULT '个',
  `type_id` int(11) NOT NULL DEFAULT '0',
  `in_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `position` varchar(255) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "stock_list"
#


#
# Source for table "type"
#

DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_namr` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "type"
#


/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
