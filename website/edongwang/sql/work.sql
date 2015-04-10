# Host: localhost  (Version: 5.0.87-community-nt)
# Date: 2015-04-10 18:23:24
# Generator: MySQL-Front 5.3  (Build 4.13)

/*!40101 SET NAMES utf8 */;

#
# Source for table "admin_auth_res"
#

DROP TABLE IF EXISTS `admin_auth_res`;
CREATE TABLE `admin_auth_res` (
  `id` int(11) NOT NULL auto_increment,
  `parentId` int(11) default NULL,
  `name` varchar(255) default '' COMMENT '资源名',
  `path` varchar(255) default '' COMMENT '资源路径',
  `sortIndex` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='后台需要权限控制的资源映射表';

#
# Data for table "admin_auth_res"
#

/*!40000 ALTER TABLE `admin_auth_res` DISABLE KEYS */;
INSERT INTO `admin_auth_res` VALUES (1,NULL,'首页','/admin/frame/main.htm,/admin/frame/left.htm,/admin/frame/right.htm',1),(3,NULL,'用户','/admin/frame/user_main.htm,/admin/frame/user_left.htm,/admin/frame/user_right.htm',2),(5,3,'添加','/admin/department/save.htm,/admin/role/save.htm,/admin/admin_user/save.htm,/admin/res/save.htm',2),(6,NULL,'配置','/admin/frame/config_main.htm,/admin/frame/config_left.htm,/admin/frame/config_right.htm',3),(8,3,'修改','/admin/department/update.htm,/admin/role/update.htm,/admin/admin_user/update.htm,/admin/res/update.htm',2.2),(9,3,'删除','/admin/department/delete.htm,/admin/role/delete.htm,/admin/admin_user/delete.htm,/admin/res/delete.htm',2.3),(11,NULL,'班级','/admin/classManager/list.htm',4),(12,11,'修改','/admin/classManager/update.htm',4.1),(14,11,'添加','/admin/classManager/add.htm',4),(15,5,'收入','/admin/inCome/list.htm',5),(24,6,'sfsd','fds',1),(25,1,'会员管理','/',3);
/*!40000 ALTER TABLE `admin_auth_res` ENABLE KEYS */;

#
# Source for table "admin_menu"
#

DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu` (
  `id` int(11) NOT NULL auto_increment,
  `parentId` int(11) default NULL,
  `menuName` varchar(255) default '',
  `linkUrl` varchar(255) default '',
  `orderIndex` double(11,0) unsigned default '0',
  `icon` varchar(64) default NULL,
  `state` int(11) default '1',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

#
# Data for table "admin_menu"
#

/*!40000 ALTER TABLE `admin_menu` DISABLE KEYS */;
INSERT INTO `admin_menu` VALUES (1,NULL,'控制台','admin/main',1,'icon-dashboard',1),(2,NULL,'后台基础配置','',2,'icon-cog',1),(3,2,'用户列表','admin/adminuser/list',1,'icon-user',1),(4,2,'添加用户','admin/adminuser/add',2,'icon-calendar',1),(5,2,'角色列表','admin/adminrole/list',3,'icon-edit',1),(6,2,'添加角色','admin/adminrole/add',4,'icon-zoom-in',1),(7,NULL,'资源映射配置','',3,'icon-edit',1),(8,7,'添加资源映射','admin/adminauthres/add',2,'icon-legal',1),(9,7,'映射列表','admin/adminauthres/list',1,'icon-edit',1),(10,NULL,'会员管理','',3,'icon-cog',1),(11,10,' 会员列表','admin/member/list',1,'icon-user',1),(12,NULL,'楼盘管理','',4,'icon-cog',1),(13,12,'楼盘列表','admin/house/list',1,'icon-list',1),(14,12,'添加楼盘','admin/house/doSave',2,'icon-edit',1);
/*!40000 ALTER TABLE `admin_menu` ENABLE KEYS */;

#
# Source for table "admin_role"
#

DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `state` int(11) default NULL,
  `priority` int(11) default '0',
  `isSuper` tinyint(1) default '1',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "admin_role"
#

/*!40000 ALTER TABLE `admin_role` DISABLE KEYS */;
INSERT INTO `admin_role` VALUES (1,'系统管理员','系统管理员',1,1,1),(2,'管理员','管理员',1,1,1);
/*!40000 ALTER TABLE `admin_role` ENABLE KEYS */;

#
# Source for table "admin_user"
#

DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL auto_increment,
  `adminRoleId` int(128) NOT NULL default '0',
  `selfAdmin` int(11) default '0' COMMENT '受限管理员,只能管理自己的数据',
  `viewOnlyAdmin` int(11) default '0' COMMENT '只读管理员',
  `userName` varchar(64) NOT NULL default '',
  `state` int(11) default '1',
  `pwd` varchar(128) NOT NULL default '',
  `recordCreateTime` int(11) default '0',
  `lastLoginTime` int(11) default '0',
  `loginTime` int(11) default '0',
  `loginIp` varchar(64) default '0',
  `lastLoginIp` varchar(64) default '0',
  `contact` varchar(64) default NULL,
  `email` varchar(64) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='后台用户';

#
# Data for table "admin_user"
#

/*!40000 ALTER TABLE `admin_user` DISABLE KEYS */;
INSERT INTO `admin_user` VALUES (1,1,0,0,'t',1,'96e79218965eb72c92a549dd5a330112',0,1428389018,1428389560,'0:0:0:0:0:0:0:1','192.168.0.124','dddd','rplees.i.ly@gmail.com'),(2,2,0,0,'admin',1,'96e79218965eb72c92a549dd5a330112',0,1428658843,1428658882,'0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','dddd','rplees.i.ly@gmail.com'),(15,2,0,0,'test_auto_add',1,'ddd',0,1427792437,0,'0','fff','2345',NULL),(16,2,1,1,'yy',1,'9cafeef08db2dd477098a0293e71f90a',1428054589,0,0,'0','0','gffff','yy@gmail.com'),(18,2,1,1,'testr',1,'96e79218965eb72c92a549dd5a330112',1428164727,0,0,'0','0','11','11@11.c');
/*!40000 ALTER TABLE `admin_user` ENABLE KEYS */;

#
# Source for table "dictionary_setting"
#

DROP TABLE IF EXISTS `dictionary_setting`;
CREATE TABLE `dictionary_setting` (
  `variable` varchar(255) NOT NULL default '',
  `value` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  `sortIndex` int(11) default NULL,
  PRIMARY KEY  (`variable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "dictionary_setting"
#

INSERT INTO `dictionary_setting` VALUES ('admin.name','AdminTitle','list',0);

#
# Source for table "e_house"
#

DROP TABLE IF EXISTS `e_house`;
CREATE TABLE `e_house` (
  `Id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `propertyType` varchar(255) default NULL,
  `areaCode` varchar(255) default NULL,
  `surfaceType` varchar(255) default NULL,
  `totalPrice` varchar(255) default NULL,
  `recommendPrice` varchar(255) default NULL,
  `bargainPrice` varchar(255) default NULL,
  `houseImg` varchar(255) default NULL,
  `declareHouse` varchar(255) default NULL,
  `introHouse` varchar(255) default NULL,
  `houseTypeImg` varchar(255) default NULL,
  `protocolBeginTime` bigint(20) default NULL,
  `protocolEndTime` bigint(20) default NULL,
  `createTime` bigint(20) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "e_house"
#


#
# Source for table "e_member"
#

DROP TABLE IF EXISTS `e_member`;
CREATE TABLE `e_member` (
  `Id` int(11) NOT NULL auto_increment,
  `telNumber` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `sex` int(5) default NULL,
  `realName` varchar(255) default NULL,
  `headImg` varchar(255) default NULL,
  `callingCardImg` varchar(255) default NULL,
  `bankName` varchar(255) default NULL,
  `bankAdrees` varchar(255) default NULL,
  `bankNumber` varchar(255) default NULL,
  `registerDate` bigint(20) default NULL,
  `status` int(11) default NULL,
  `isLeader` int(11) default NULL,
  `auditStatus` int(11) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

#
# Data for table "e_member"
#

INSERT INTO `e_member` VALUES (1,'13568689898','123456',1,'张三','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463281155,0,1,2),(2,'13568689898','123456',1,'李四','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463399394,0,1,1),(3,'13568689898','123456',1,'王五','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463404586,0,1,1),(4,'13568689898','123456',1,'随悟空','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463405309,1,1,0),(5,'13568689898','123456',1,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463405766,0,1,1),(6,'13568689898','123456',1,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463406102,1,1,0),(7,'13568689898','123456',1,'三藏','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463406289,0,1,1),(8,'13568689898','123456',0,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463406456,0,1,1),(9,'13568689898','123456',1,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463406782,1,1,2),(10,'13568689898','123456',1,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463406945,0,0,0),(11,'13568689898','123456',1,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463407107,0,0,2),(12,'13568689898','123456',1,'如来','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463407275,0,0,0),(13,'13568689898','123456',0,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463407455,0,1,2),(14,'13568689898','123456',1,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463407608,1,1,1),(15,'13568689898','123456',1,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463407777,0,1,2),(16,'13568689898','123456',1,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463407939,0,1,0),(17,'13568689898','123456',2,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463408269,0,1,1),(18,'13568689898','123456',1,'耶稣','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428463901768,0,0,0),(19,'13568689898','123456',12,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428464783877,0,0,0),(20,'13568689898','123456',1,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428464807890,1,0,0),(21,'13568689898','123456',1,'三墩','http://www.badiu.com/touxian.jpg','http://www.baidu.com/mingpian.jpg','中国农业银行','杭州市三墩支行','6222222222222',1428472595417,0,0,0);

#
# Source for table "role_admin_auth_res"
#

DROP TABLE IF EXISTS `role_admin_auth_res`;
CREATE TABLE `role_admin_auth_res` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `adminRoleId` int(11) NOT NULL,
  `adminAuthResId` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "role_admin_auth_res"
#


#
# Source for table "test"
#

DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "test"
#

INSERT INTO `test` VALUES (345);
