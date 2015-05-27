-- THIS FILE WAS BEEN GENERATED USING REVERSE ENGENERING, THE COMMAND USED IS THE FOWLING
-- > mysqldump -u root -p --databases marketplacerepository > 0_create_data_base.sql
-- > # mysqldump -u root -p[root_password] [database_name] > dumpfilename.sql

-- TO RESTORE THE DATA BASE USING THIS DUMP FILE WE CAN USE THE NEXT COMMAND
-- > # mysql -u root -p[root_password] [database_name] < dumpfilename.sql
 
-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: localhost    Database: marketplacerepository
-- ------------------------------------------------------
create database marketplacerepository;

use marketplacerepository;



    drop table if exists Product;
 
    drop table if exists category;
 
    drop table if exists client;
 
    drop table if exists client_address;
 
    drop table if exists permission;
 
    drop table if exists requisition;
 
    drop table if exists requisition_item;
 
    drop table if exists user;
 
    drop table if exists user_permission;
 
    create table Product (
        id bigint not null auto_increment,
        name varchar(80) not null,
        sku varchar(20) not null,
        Stock_Qty integer not null,
        Unit_Value decimal(10,2) not null,
        category_id bigint not null,
        primary key (id)
    );
 
    create table category (
        id bigint not null auto_increment,
        description varchar(60) not null,
        masterCategory bigint,
        primary key (id)
    );
 
    create table client (
        id bigint not null auto_increment,
        client_type varchar(32) not null,
        cnjp varchar(50) not null,
        email varchar(100) not null,
        name varchar(100) not null,
        primary key (id)
    );
 
    create table client_address (
        id bigint not null auto_increment,
        city varchar(50) not null,
        country varchar(50) not null,
        doorNumber varchar(8),
        street varchar(155),
        zipCode varchar(14) not null,
        client_id bigint not null,
        primary key (id)
    );
 
    create table permission (
        id bigint not null auto_increment,
        description varchar(255),
        name varchar(100) not null,
        primary key (id)
    );
 
    create table requisition (
        id bigint not null auto_increment,
        creation_Date datetime,
        delivery_city varchar(100) not null,
        delivery_country varchar(100) not null,
        delivery_name varchar(200) not null,
        delivery_number_door varchar(15),
        delivery_street varchar(200),
        delivery_zip_code varchar(15) not null,
        delivery_date date not null,
        notes varchar(1024),
        payment_method varchar(20) not null,
        rebate_value decimal(10,2) not null,
        shipping_value decimal(10,2) not null,
        status varchar(20) not null,
        total_value decimal(10,2) not null,
        client_id bigint not null,
        seller_id bigint not null,
        primary key (id)
    );
 
    create table requisition_item (
        id bigint not null auto_increment,
        qty integer not null,
        unit_value decimal(10,2) not null,
        product_id bigint not null,
        requisition_id bigint not null,
        primary key (id)
    );
 
    create table user (
        id bigint not null auto_increment,
        email varchar(80) not null,
        password varchar(32) not null,
        username varchar(50) not null,
        OPTLOCK integer,
        primary key (id)
    );
 
    create table user_permission (
        user_id bigint not null,
        permission_id bigint not null,
        primary key (user_id, permission_id)
    );
 
    alter table Product 
        add constraint UK_epc2gbv2w8k1p76rugng6xre3  unique (sku);
 
    alter table client 
        add constraint UK_2anfbbua40ojwdtsaknupnabk  unique (cnjp);
 
    alter table client 
        add constraint UK_bfgjs3fem0hmjhvih80158x29  unique (email);
 
    alter table permission 
        add constraint UK_2ojme20jpga3r4r79tdso17gi  unique (name);
 
    alter table user 
        add constraint UK_sb8bbouer5wak8vyiiy4pf2bx  unique (username);
 
    alter table user 
        add constraint UK_ob8kqyqqgmefl0aco34akdtpe  unique (email);
 
    alter table Product 
        add constraint FK_b7afq93qsn7aoydaftixggf14 
        foreign key (category_id) 
        references category (id);
 
    alter table category 
        add constraint FK_ikyv5d38q282kl2utnromtrta 
        foreign key (masterCategory) 
        references category (id);
 
    alter table client_address 
        add constraint FK_cwn3pv9oxxk3hqx39kn2im57e 
        foreign key (client_id) 
        references client (id);
 
    alter table requisition 
        add constraint FK_9rfw2eooq0i0g27x8te7u0vit 
        foreign key (client_id) 
        references client (id);
 
    alter table requisition 
        add constraint FK_4hkbefnqetoxvh7a8dnjbx775 
        foreign key (seller_id) 
        references user (id);
 
    alter table requisition_item 
        add constraint FK_9vm73gg3g6sgmg6utfd9qntoq 
        foreign key (product_id) 
        references Product (id);
 
    alter table requisition_item 
        add constraint FK_nqkl9ia49ejixo3keoftnu02g 
        foreign key (requisition_id) 
        references requisition (id);
 
    alter table user_permission 
        add constraint FK_5jfoyhv0p0tg0d5dwf8oo4rjh 
        foreign key (permission_id) 
        references permission (id);
 
    alter table user_permission 
        add constraint FK_hd22i4a2n0s2ntvvv09c14r7y 
        foreign key (user_id) 
        references user (id);