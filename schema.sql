create database if not exists `loan`;

USE `loan`;

SET FOREIGN_KEY_CHECKS=0;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `shippingaddress` varchar(255) default NULL,
  `telephone` varchar(50) default NULL,
  `level` varchar(50) default NULL,
  `email` varchar(255) default NULL,
  `creditscore` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
LOCK TABLES `customer` WRITE;
INSERT INTO `customer` (id,name,shippingaddress,telephone,level,email,creditscore) VALUES
(1,'Garth Lara','3160 Nibh Av.','1-972-689-3830','Platinum','GarthLara@loanbank.com',798),
(2,'Ross Huber','743-1481 Iaculis Ave','1-639-354-0740','Platinum','RossHuber@loanbank.com',796),
(3,'Troy Phelps','262 Mauris Av.','1-324-295-9537','Platinum','TroyPhelps@loanbank.com',794),
(4,'Allistair Higgins','Ap #104-5263 Cursus Street','1-768-128-7635','Platinum','AllistairHiggins@loanbank.com',792),
(5,'Murphy Cleveland','666-6734 Urna Rd.','1-700-468-2889','Platinum','MurphyCleveland@loanbank.com',790),
(6,'Berk Bass','310-2196 Sagittis St.','1-792-782-4048','Platinum','BerkBass@loanbank.com',788),
(7,'Marsden Stanley','195-678 Mollis. Av.','1-971-860-6330','Platinum','MarsdenStanley@loanbank.com',786),
(8,'Levi Tyler','2991 Enim. Rd.','1-356-643-8760','Platinum','LeviTyler@loanbank.com',784),
(9,'Talon Moses','Ap #912-7099 Libero Avenue','1-652-484-9320','Platinum','TalonMoses@loanbank.com',782),
(10,'Ryan Middleton','P.O. Box 354 2085 Vitae Street','1-792-223-1651','Platinum','RyanMiddleton@loanbank.com',780),
(11,'Joel Singleton','Ap #658-7964 Sed Ave','1-113-315-7252','Gold','JoelSingleton@loanbank.com',778),
(12,'Graiden Singleton','802 Orci, Rd.','1-230-502-0635','Gold','GraidenSingleton@loanbank.com',776),
(13,'Guy Hurst','7409 Sed Ave','1-839-995-4563','Gold','GuyHurst@loanbank.com',774),
(14,'Aladdin Flowers','346-7790 Quam. Avenue','1-131-572-7420','Gold','AladdinFlowers@loanbank.com',772),
(15,'Noah Washington','702-2083 Accumsan Av.','1-798-298-9468','Gold','NoahWashington@loanbank.com',770),
(16,'Guy Reynolds','4367 Fusce St.','1-516-591-4168','Gold','GuyReynolds@loanbank.com',768),
(17,'Keefe Lynn','321 Magna St.','1-385-227-1621','Gold','KeefeLynn@loanbank.com',766),
(18,'Ira Lambert','2684 Magnis Avenue','1-164-465-4287','Gold','IraLambert@loanbank.com',764),
(19,'Levi Wells','Ap #579-955 Nullam Avenue','1-772-249-0773','Gold','LeviWells@loanbank.com',762),
(20,'Wing Lindsay','4056 Facilisis. St.','1-419-331-8677','Gold','WingLindsay@loanbank.com',760),
(21,'Benjamin Ashley','1425 Nunc Av.','1-734-573-1176','Gold','BenjaminAshley@loanbank.com',758),
(22,'Xavier Harris','696-5069 Eu Av.','1-287-346-7331','Gold','XavierHarris@loanbank.com',756),
(23,'Steel Clay','Ap #176-3277 Vitae Rd.','1-907-659-9390','Gold','SteelClay@loanbank.com',754),
(24,'Ivor Nicholson','513-1570 Ipsum. Avenue','1-117-830-5285','Gold','IvorNicholson@loanbank.com',752),
(25,'Barclay Pena','1119 Sapien. Avenue','1-179-894-6173','Gold','BarclayPena@loanbank.com',750),
(26,'Wylie Lamb','7488 Ullamcorper. Street','1-634-341-1009','Gold','WylieLamb@loanbank.com',748),
(27,'Xander Stark','P.O. Box 405 , 7407 Metus Avenue','1-924-125-4541','Gold','XanderStark@loanbank.com',746),
(28,'Henry Durham','P.O. Box 541 , 9867 Lacus. Ave','1-613-246-9869','Gold','HenryDurham@loanbank.com',744),
(29,'Darius Vargas','5605 Elit Street','1-128-246-5333','Gold','DariusVargas@loanbank.com',742),
(30,'Cody Hinton','Ap #284-8975 Sed, Rd.','1-358-100-3881','Gold','CodyHinton@loanbank.com',740),
(31,'Lance Payne','P.O. Box 280, 8309 Non Ave"','1-722-641-9848','Gold','LancePayne@loanbank.com',738),
(32,'Xavier Carver','569-382 Sit St.','1-497-813-4778','Gold','XavierCarver@loanbank.com',736),
(33,'Silas Wynn','"P.O. Box 512, 7337 Curabitur St."','1-722-198-3215','Gold','SilasWynn@loanbank.com',734),
(34,'Leonard Parrish','293-958 Integer St.','1-621-392-2150','Gold','LeonardParrish@loanbank.com',732),
(35,'Ryan Rodriquez','"P.O. Box 325, 2466 Adipiscing Rd."','1-210-397-7332','Gold','RyanRodriquez@loanbank.com',730),
(36,'Ian Lara','Ap #629-5476 Neque Road','1-876-966-1802','Gold','IanLara@loanbank.com',728),
(37,'Uriah Weber','979-2674 Molestie St.','1-733-988-4530','Gold','UriahWeber@loanbank.com',726),
(38,'William Watts','"P.O. Box 243, 6881 Magna. Av."','1-804-419-4040','Gold','WilliamWatts@loanbank.com',724),
(39,'Leonard Skinner','"P.O. Box 599, 8930 Tellus Rd."','1-785-109-7372','Gold','LeonardSkinner@loanbank.com',722),
(40,'Rooney Gross','Ap #416-898 Phasellus Ave','1-662-176-3392','Gold','RooneyGross@loanbank.com',720),
(41,'Clinton Blackwell','"P.O. Box 981, 5747 Donec St."','1-513-811-0518','Gold','ClintonBlackwell@loanbank.com',718),
(42,'Cruz Rush','769-5566 Ipsum. Avenue','1-940-368-8894','Gold','CruzRush@loanbank.com',716),
(43,'Rigel Hull','168-7616 Euismod Ave','1-805-498-8329','Gold','RigelHull@loanbank.com',714),
(44,'Abel Casey','"P.O. Box 924, 3500 Purus. St."','1-381-710-1147','Gold','AbelCasey@loanbank.com',712),
(45,'Plato Maddox','"651 Dui, Avenue"','1-531-454-0980','Gold','PlatoMaddox@loanbank.com',710),
(46,'Harper Mayo','"P.O. Box 205, 6002 Vel, Street"','1-340-586-9669','Gold','HarperMayo@loanbank.com',708),
(47,'Gavin Floyd','Ap #384-5165 Quis Street','1-719-441-7162','Gold','GavinFloyd@loanbank.com',706),
(48,'Keefe Potts','"P.O. Box 741, 373 Parturient St."','1-430-229-4824','Gold','KeefePotts@loanbank.com',704),
(49,'Honorato Mayo','240-180 Sed Street','1-595-532-7026','Gold','HonoratoMayo@loanbank.com',702),
(50,'Austin Hicks','Ap #493-1483 Sed Street','1-576-939-1741','Gold','AustinHicks@loanbank.com',700),
(51,'Bert Dalton','5361 Augue Road','1-689-648-8674','Silver','BertDalton@loanbank.com',698),
(52,'Jonas Barrett','Ap #500-4652 Nulla Ave','1-841-321-0990','Silver','JonasBarrett@loanbank.com',696),
(53,'Tiger Hubbard','8173 Dignissim St.','1-669-243-4901','Silver','TigerHubbard@loanbank.com',694),
(54,'Lars Poole','Ap #480-7145 Neque. Avenue','1-185-833-8115','Silver','LarsPoole@loanbank.com',692),
(55,'Kermit Jennings','9961 Sem Street','1-957-550-4510','Silver','KermitJennings@loanbank.com',690),
(56,'Travis Cantrell','5119 Bibendum Avenue','1-628-636-6198','Silver','TravisCantrell@loanbank.com',688),
(57,'Caesar Schwartz','"P.O. Box 250, 5672 Lectus St."','1-427-527-1469','Silver','CaesarSchwartz@loanbank.com',686),
(58,'Garth Pittman','"8777 Non, Street"','1-765-398-3969','Silver','GarthPittman@loanbank.com',684),
(59,'Darius Robertson','"Ap #952-2276 Non, Street"','1-645-361-0229','Silver','DariusRobertson@loanbank.com',682),
(60,'Lucius Warren','435-6883 In Av.','1-377-639-2782','Silver','LuciusWarren@loanbank.com',680),
(61,'Guy Guzman','"170-3805 Nec, Avenue"','1-324-426-0385','Silver','GuyGuzman@loanbank.com',678),
(62,'Hedley Donovan','9073 Diam Rd.','1-857-295-9370','Silver','HedleyDonovan@loanbank.com',676),
(63,'Adrian Moore','Ap #336-9266 Donec St.','1-368-435-0528','Silver','AdrianMoore@loanbank.com',674),
(64,'Dante Clayton','"756-4567 Tellus, Avenue"','1-587-835-3988','Silver','DanteClayton@loanbank.com',672),
(65,'Kyle Charles','Ap #114-7983 Phasellus Street','1-399-187-1447','Silver','KyleCharles@loanbank.com',670),
(66,'Ryder Copeland','Ap #375-1740 Enim Rd.','1-187-213-4607','Silver','RyderCopeland@loanbank.com',668),
(67,'Cyrus Harding','Ap #877-7903 Tortor Avenue','1-933-602-9875','Silver','CyrusHarding@loanbank.com',666),
(68,'Darius Reed','5622 Morbi Av.','1-719-603-7987','Silver','DariusReed@loanbank.com',664),
(69,'Linus Anderson','"P.O. Box 213, 2671 Semper Ave"','1-223-991-2434','Silver','LinusAnderson@loanbank.com',662),
(70,'Bert Soto','195-7017 Duis St.','1-947-680-3634','Silver','BertSoto@loanbank.com',660),
(71,'Keefe Sears','169-6250 Sociis Rd.','1-631-777-2553','Silver','KeefeSears@loanbank.com',658),
(72,'Ray Duke','580-7875 Porttitor Street','1-336-804-1213','Silver','RayDuke@loanbank.com',656),
(73,'Jermaine Larson','431 Quisque St.','1-681-435-6573','Silver','JermaineLarson@loanbank.com',654),
(74,'Vincent Rodriquez','Ap #632-2156 Malesuada Rd.','1-666-666-4991','Silver','VincentRodriquez@loanbank.com',652),
(75,'Amir Jensen','5575 Aliquam Ave','1-566-984-6768','Silver','AmirJensen@loanbank.com',650),
(76,'Troy Dorsey','Ap #683-545 Velit Road','1-681-453-8894','Silver','TroyDorsey@loanbank.com',648),
(77,'Lucas Lyons','"P.O. Box 445, 471 Nibh Avenue"','1-671-854-4650','Silver','LucasLyons@loanbank.com',646),
(78,'Wade Oliver','Ap #522-3687 Ornare St.','1-731-584-0132','Silver','WadeOliver@loanbank.com',644),
(79,'Hamish Mcneil','377-3973 Pede Ave','1-625-451-6282','Silver','HamishMcneil@loanbank.com',642),
(80,'Alden Waller','7456 Adipiscing Avenue','1-675-135-5560','Silver','AldenWaller@loanbank.com',640),
(81,'Sawyer Montoya','551-596 Viverra Street','1-868-252-6798','Silver','SawyerMontoya@loanbank.com',638),
(82,'Phelan Knapp','1277 Vitae St.','1-365-579-0065','Silver','PhelanKnapp@loanbank.com',636),
(83,'Magee Maldonado','Ap #811-7325 Suspendisse St.','1-776-943-9121','Silver','MageeMaldonado@loanbank.com',634),
(84,'Dustin Hines','"5610 Amet, Ave"','1-177-785-2615','Silver','DustinHines@loanbank.com',632),
(85,'Paki Parker','"P.O. Box 558, 3061 Fringilla Street"','1-869-876-9232','Silver','PakiParker@loanbank.com',630),
(86,'Colin Berry','635-7578 Duis Street','1-663-225-0964','Silver','ColinBerry@loanbank.com',628),
(87,'Harper Stanley','8949 Ipsum Avenue','1-288-430-5422','Silver','HarperStanley@loanbank.com',626),
(88,'Dillon Mayo','231-1696 Aliquam Rd.','1-929-278-0156','Silver','DillonMayo@loanbank.com',624),
(89,'Hamish Moody','"P.O. Box 415, 9254 Mauris Road"','1-877-631-5403','Silver','HamishMoody@loanbank.com',622),
(90,'Aidan Hensley','Ap #547-4708 Lorem Avenue','1-746-826-2535','Silver','AidanHensley@loanbank.com',620),
(91,'Mark Trujillo','964-3467 Quis Rd.','1-441-860-0992','Silver','MarkTrujillo@loanbank.com',618),
(92,'Barrett Stephenson','Ap #781-395 Felis. Avenue','1-171-760-2543','Silver','BarrettStephenson@loanbank.com',616),
(93,'Ross Humphrey','2794 Faucibus St.','1-972-560-3542','Silver','RossHumphrey@loanbank.com',614),
(94,'Adrian Cotton','"P.O. Box 833, 3769 Quisque Street"','1-442-276-0133','Silver','AdrianCotton@loanbank.com',612),
(95,'Daquan Clemons','7539 Sit Ave','1-273-153-9038','Silver','DaquanClemons@loanbank.com',610),
(96,'Hall Grimes','824-5976 Dignissim Road','1-664-888-7297','Silver','HallGrimes@loanbank.com',608),
(97,'Guy Alford','Ap #565-4517 Quisque Ave','1-497-348-0960','Silver','GuyAlford@loanbank.com',606),
(98,'Reed Bond','"P.O. Box 894, 4386 Odio. St."','1-458-820-4944','Silver','ReedBond@loanbank.com',604),
(99,'Barry Vaughn','635-7482 Pellentesque St.','1-215-376-9606','Silver','BarryVaughn@loanbank.com',602),
(100,'Kenyon Santos','401-2976 Massa. St.','1-492-757-0596','Silver','KenyonSantos@loanbank.com',600);
UNLOCK TABLES;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


--
-- Table structure for table `applications`
--

DROP TABLE IF EXISTS `applications`;
CREATE TABLE `applications` (
  `applicationid` CHAR(36) NOT NULL,
  `loantype` varchar(10) NOT NULL,
  `amount` bigint(100) default NULL,
  `customerid` bigint(10) default NULL,
  `applicationstatus` varchar(10) default NULL,
  PRIMARY KEY  (`applicationid`),
  UNIQUE KEY `applicationid` (`applicationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

commit;
