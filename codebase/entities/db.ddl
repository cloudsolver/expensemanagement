################################INIT#####################################################
DROP DATABASE mst;
CREATE DATABASE mst;
USE MST;
#SET FOREIGN_KEY_CHECKS=1;
################################CREATE THE TABLES ####################################

CREATE TABLE CUSTOMER
(
ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
AGE_GROUP_CD VARCHAR(10),
GENDER_CD VARCHAR(10),
INTERNET_YEARS_CD VARCHAR (11),
INTERNET_HOURS_PER_WEEK_CD VARCHAR(10),
LEVEL_OF_EDUCATION_CD VARCHAR(10)
) TYPE=INNODB;

CREATE TABLE USER
(
ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
USER_NAME VARCHAR(20) NOT NULL UNIQUE,
PASSWORD VARCHAR(20) NOT NULL,
CUSTOMER_ID INTEGER REFERENCES CUSTOMER
) TYPE=INNODB;

CREATE TABLE EXPENSE
(
ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
EXP_NAME VARCHAR(45),
EXP_COST_CENTER VARCHAR(10),
EXP_PURPOSE VARCHAR(60),
EXP_APPROVER VARCHAR(80),
EXP_DATE DATE,
EXP_STATUS CHAR (1),
EXP_TASK_DURATION BIGINT,
EXP_APP_TYPE CHAR(1),
EXP_APP_VERSION INT,
EXP_CUSTOMER_ID INTEGER REFERENCES CUSTOMER
)TYPE=INNODB;

CREATE TABLE EXPENSE_LINE_ITEM
(
ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
LI_TYPE VARCHAR(4),
LI_EXP_TX_TYPE CHAR(10),
LI_EXP_QUALIFIED CHAR(1),
LI_EXP_TX_DATE DATE,
LI_EXP_TX_AMT DOUBLE,
LI_EXP_TX_JUSTIFICATION VARCHAR(255),
LI_EDU_START_DATE DATE,
LI_EDU_END_DATE DATE,
LI_EDU_SCHOOL_NAME VARCHAR(80),
LI_EDU_COURSE_NAME VARCHAR(80),
LI_EDU_GRADE VARCHAR(1),
LI_EDU_LEVEL VARCHAR(10),
EXPENSE_ID INTEGER REFERENCES EXPENSE
)TYPE=INNODB;

CREATE TABLE SURVEY
(
ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
Q1 VARCHAR(5),
Q2 VARCHAR(5),
Q3 VARCHAR(5),
Q4 VARCHAR(5),
Q5 VARCHAR(5),
Q6 VARCHAR(5),
Q7 VARCHAR(5),
Q8 VARCHAR(5),
EXPENSE_ID INTEGER REFERENCES EXPENSE
)TYPE=INNODB;
