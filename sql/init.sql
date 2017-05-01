drop table app.USER_FAVOURITES;
drop table app.episode;
drop table app.client;
drop table app.series;

CREATE TABLE app.CLIENT (
		ID INTEGER NOT NULL primary key,
		USERNAME VARCHAR(24) NOT NULL unique,
		PASSWORD VARCHAR(24) NOT NULL,
		TOKEN VARCHAR(50) unique,
		ADMIN INTEGER NOT NULL
);

CREATE TABLE app.SERIES (
		ID INTEGER NOT NULL primary key,
		NAME VARCHAR(100) NOT NULL unique,
		DESCRIPTION VARCHAR(200) NOT NULL
	);

CREATE TABLE app.EPISODE (
		ID INTEGER NOT NULL primary key,
		NAME VARCHAR(100) NOT NULL,
		DESCRIPTION VARCHAR(200) NOT NULL,
		SERIESID INTEGER NOT NULL references app.series(id) ON DELETE CASCADE,
		SEASON INTEGER NOT NULL,
		EPISODE INTEGER NOT NULL
	);
	
CREATE TABLE app.USER_FAVOURITES (
		ID INTEGER NOT NULL primary key,
		USER_ID INTEGER NOT NULL references app.client(id) ON DELETE CASCADE,
		SERIESID INTEGER NOT NULL references app.series(id) ON DELETE CASCADE
	);
	
	
insert into series values (100000,'Series1','Desc1');
insert into series values (100001,'Series2','Desc2');
insert into series values (100002,'Series3','Desc3');
insert into series values (100003,'Series4','Desc4');
insert into series values (100004,'Series5','Desc5');

insert into episode values (100000,'Episode1','Desc1',100001,1,1);
insert into episode values (100001,'Episode2','Desc2',100001,1,2);
insert into episode values (100002,'Episode3','Desc3',100001,1,3);
insert into episode values (100003,'Episode4','Desc4',100001,1,4);
insert into episode values (100004,'Episode5','Desc5',100001,1,5);

insert into CLIENT values (1,'admin','admin',null, 1);
insert into CLIENT values (2,'user','user',null, 0);

insert into USER_FAVOURITES values (20000,1,100001);
insert into USER_FAVOURITES values (20001,1,100002);
insert into USER_FAVOURITES values (20002,1,100003);
insert into USER_FAVOURITES values (20003,1,100004);
	
--CREATE SEQUENCE generated_value
--START WITH 1 INCREMENT BY 1;