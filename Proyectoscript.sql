drop database Proyecto;
create database Proyecto;
use Proyecto;
create table ROOM( 
ROOMID VARCHAR(6) NOT NULL,
BUILDING ENUM('IA', 'CS', 'SL','HU','CN'),
ROOMTYPE ENUM('C', 'L', 'A'),
CAPACITY INTEGER(3),
PRIMARY KEY (ROOMID, ROOMTYPE));
           
CREATE TABLE SEASON (
SEASON ENUM('PRIMAVERA', 'VERANO', 'OTOÑO') NOT NULL,
YEAR_ INTEGER (4) NOT NULL,
START_DATE DATE NOT NULL,
END_DATE DATE NOT NULL,
PRIMARY KEY(YEAR_,season));

create table COURSE ( 
COURSEKEY VARCHAR(9) NOT NULL,
SECTION	INTEGER(2) NOT NULL,
TITLE VARCHAR(60) NOT NULL,
TEACHER VARCHAR(30),
PRIMARY KEY (COURSEKEY, section));
           
create table Reservation(
COURSEKEY VARCHAR(9) NOT NULL,
Date_ date not null,
STARTTIME time not null,
ENDTIME TIME not null,
NAME_ VARCHAR(50) not null, -- NOMBRE DEL QUE RESERVA
ROOMID VARCHAR(6) NOT NULL,
DURATION INTEGER (3),
primary key (Date_,STARTTIME,ENDTIME),
foreign key (ROOMID) references ROOM (ROOMID),
foreign key(COURSEKEY) references COURSE (COURSEKEY));
                        
create table SCHEDULE_ (
COURSEKEY VARCHAR(9) NOT NULL,
ROOMID VARCHAR(6) NOT NULL,
SECTION	INTEGER(2) NOT NULL,
WEEKDAY INTEGER(2) NOT NULL, 
STARTTIME TIME,
ENDTIME TIME,
SEMESTER INTEGER(2), -- semestre de los estudiantes que cursan el curso
SEASON ENUM('PRIMAVERA', 'VERANO', 'OTOÑO') NOT NULL,
YEAR_ INTEGER (4) NOT NULL,
PRIMARY KEY (ROOMID,COURSEKEY,WEEKDAY,STARTTIME,ENDTIME),
foreign key (year_,season) references season (year_,season),
foreign key (COURSEKEY,section) references course (COURSEKEY,section),
foreign key (ROOMID) references ROOM (ROOMID));
