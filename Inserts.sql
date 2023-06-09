INSERT INTO ROOM(ROOMID, BUILDING, ROOMTYPE, CAPACITY) 
VALUES('HU-213','HU','C',25);
INSERT INTO ROOM(ROOMID, BUILDING, ROOMTYPE, CAPACITY) 
VALUES('CN-109','CN','C',23);
INSERT INTO ROOM(ROOMID, BUILDING, ROOMTYPE, CAPACITY) 
VALUES('CN-201','CN','C',23);
INSERT INTO ROOM(ROOMID, BUILDING, ROOMTYPE, CAPACITY) 
VALUES('HU-106','HU','C',28);
INSERT INTO ROOM(ROOMID, BUILDING, ROOMTYPE, CAPACITY) 
VALUES('CN-120','CN','L',15);
-- SEASON
INSERT INTO SEASON(SEASON, YEAR_, START_DATE, END_DATE) 
VALUES('PRIMAVERA', 2023, '2023-01-09', '3-05-17');
INSERT INTO SEASON(SEASON, YEAR_, START_DATE, END_DATE) 
VALUES('VERANO', 2023, '2023-06-05', '2023-06-20');
INSERT INTO SEASON(SEASON, YEAR_, START_DATE, END_DATE) 
VALUES('OTOÑO', 2023, '2023-08-07', '2023-05-17');
-- COURSE
INSERT INTO COURSE(COURSEKEY, SECTION, TITLE, TEACHER) 
VALUES('LIS-2082',1,'Bases de Datos','José Luis Zechinelli');
INSERT INTO COURSE(COURSEKEY, SECTION, TITLE, TEACHER) 
VALUES('FIS-2012',1,'Física General II','Moises Chávez Huerta');
INSERT INTO COURSE(COURSEKEY, SECTION, TITLE, TEACHER) 
VALUES('FIS-2012',2,'Física General II','Moises Chávez Huerta');
INSERT INTO COURSE(COURSEKEY, SECTION, TITLE, TEACHER) 
VALUES('ART-0012',4,' ARTE, HISTORIA Y CULTURA','ADRIANA VALERIO LARA');
INSERT INTO COURSE(COURSEKEY, SECTION, TITLE, TEACHER) 
VALUES('FIS-2022',3,'  LABORATORIO DE FÍSICA GENERAL II','DULCE ISABEL GONZALEZ');
-- RESERVATION
INSERT INTO RESERVATION(COURSEKEY,DATE_,STARTTIME,ENDTIME,NAME_,ROOMID,DURATION) 
VALUES('FIS-2012','2023-01-12','11:45:00','13:10:00','Moises Chávez Huerta','HU-106',85);
-- SCHEDULE
 INSERT INTO SCHEDULE_(COURSEKEY,ROOMID, SECTION, WEEKDAY, STARTTIME, ENDTIME, SEMESTER, SEASON, YEAR_) 
 VALUES ('FIS-2012','CN-109',1,1,'10:05:00','11:30:00',3,'PRIMAVERA',2023 );
