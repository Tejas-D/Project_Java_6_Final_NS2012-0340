/********************************************************************
*       Filename: Create_Database_forum_db							*
*       Author: Tejas Dwarkaram                                     *
*       Date: 20 July 2012											*
*       Description: Creating the database for the forum website,   *
*					as well as adding the tables					*
********************************************************************/
USE master
GO

IF EXISTS(SELECT name FROM master.dbo.sysdatabases
		WHERE name = 'forum_db')
		DROP DATABASE forum_db
		GO

--Creating the database 
CREATE DATABASE forum_db
ON PRIMARY
(
	NAME = 'forum_data',
	FILENAME = 'C:\forum_db.mdf', 
	SIZE = 10,
	MAXSIZE = UNLIMITED,
	FILEGROWTH = 2
)
--creating the log file for the database
LOG ON
(
	
	NAME = 'forum_log',
	FILENAME = 'C:\forum_db.ldf',
	SIZE =10,
	MAXSIZE = UNLIMITED,
	FILEGROWTH = 2
)
GO

USE forum_db
GO

--creating all of the tables for the database
CREATE TABLE topics
(
	topic_id int NOT NULL IDENTITY(1, 1),
	topic_name VARCHAR(500) NOT NULL,
	topic_decs VARCHAR(1000) NOT NULL,
	topic_user VARCHAR(40) NOT NULL,
	topic_date VARCHAR(12) NOT NULL,
	CONSTRAINT prim_topics_id PRIMARY KEY(topic_id)
)
GO

CREATE TABLE comment
(
	comment_id int NOT NULL IDENTITY(1,1),
	users_name VARCHAR(40) NOT NULL,
	user_text VARCHAR(1000) NOT NULL,
	comment_date VARCHAR(12) NOT NULL,
	topic_id int NOT NULL,
	CONSTRAINT for_comment_id FOREIGN KEY(topic_id) REFERENCES topics(topic_id),
	CONSTRAINT prim_comment_id PRIMARY KEY(comment_id)
)
GO

CREATE TABLE replies
(
	users_name VARCHAR(40) NOT NULL,
	user_reply VARCHAR(1000) NOT NULL,
	reply_date VARCHAR(12) NOT NULL,
	comment_id int NOT NULL, 
	CONSTRAINT for_reply_id FOREIGN KEY(comment_id) REFERENCES comment(comment_id)
)

CREATE TABLE users
(
	users_id int NOT NULL IDENTITY(1,1),
	users_name VARCHAR(15) NOT NULL,
	users_surname VARCHAR(40) NOT NULL,
	users_dateOfBirth DATE NOT NULL,
	users_password VARCHAR(20) NOT NULL,
	users_email VARCHAR(30) NOT NULL, 
	user_secQ VARCHAR(50) NOT NULL, 
	user_secA VARCHAR(20) NOT NULL,
	CONSTRAINT prim_user_id PRIMARY KEY(users_id)
)
GO

