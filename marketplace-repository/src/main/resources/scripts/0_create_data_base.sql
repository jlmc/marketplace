CREATE DATABASE marketplacerepository;

USE marketplacerepository;

CREATE TABLE category (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(60) NOT NULL,
  masterCategory bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_ikyv5d38q282kl2utnromtrta (masterCategory),
  CONSTRAINT FK_ikyv5d38q282kl2utnromtrta FOREIGN KEY (masterCategory) REFERENCES category (id)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

CREATE TABLE permission (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  name varchar(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_m3j6m9ksltume23qomatoes1r (name)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE product (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(80) NOT NULL,
  sku varchar(20) NOT NULL,
  Stock_Qty int(11) NOT NULL,
  Unit_Value decimal(10,2) NOT NULL,
  category_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_epc2gbv2w8k1p76rugng6xre3 (sku),
  KEY FK_b7afq93qsn7aoydaftixggf14 (category_id),
  CONSTRAINT FK_b7afq93qsn7aoydaftixggf14 FOREIGN KEY (category_id) REFERENCES category (id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(128) DEFAULT NULL,
  password varchar(32) NOT NULL,
  username varchar(32) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_jreodf78a7pl5qidfh43axdfb (username),
  UNIQUE KEY UK_e6gkqunxajvyxl5uctpl2vl2p (email)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE user_permission (
  user_id bigint(20) NOT NULL,
  permission_id bigint(20) NOT NULL,
  PRIMARY KEY (user_id,permission_id),
  KEY FK_5jfoyhv0p0tg0d5dwf8oo4rjh (permission_id),
  CONSTRAINT FK_5jfoyhv0p0tg0d5dwf8oo4rjh FOREIGN KEY (permission_id) REFERENCES permission (id),
  CONSTRAINT FK_hd22i4a2n0s2ntvvv09c14r7y FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
