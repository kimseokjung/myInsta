
---------------------------------------------------------------------------------------------------------------------------------------------------

create table userlist(
userid varchar2(15) primary key,
pwd varchar2(12) not null,
name varchar2(20) not null,
email varchar2(100) not null,
address varchar2(160),
profileimg varchar2(200),
chk varchar2(12));

insert into userlist(userid,pwd,name,email,address,profileimg,chk) values('admin','1111','어드민',' ',' ','unimg.jpg','master');
insert into userlist(userid,pwd,name,email,address,profileimg,chk) values('hi','1111','일반인',' ',' ','unimg.jpg','user');
insert into userlist(userid,pwd,name,email,address,profileimg,chk) values('pro','1111','장사꾼',' ',' ','unimg.jpg','product');

delete from userlist where userid='admin'
delete from rec where userid='admin'

select * from userlist;

select * from userlist where userid='admin' and pwd='1111';
update userlist set email='adsd@naver.com',address='용산구 우리집' where userid='admin';
---------------------------------------------------------------------------------------------------------------------------------------------------

create table boardlist(
idx number(6) primary key,		--글번호
userid varchar2(15) not null,	--유져이름
writeuserimg varchar2(80),		--작성자 프로필 이미지
imgpath varchar2(80),   -- 이미지 경로
content varchar2(200),		--글내용
logtime DATE DEFAULT SYSDATE);

select * from BOARDLIST;


create sequence board_seq increment by 1 start with 1 nocycle nocache;
         
insert into boardlist(idx,userid,writeuserimg,imgpath,content,logtime)
				values(board_seq.nextval,'admin','unimg.jpg','1.jpg','testupload',SYSDATE);
insert into boardlist(idx,userid,writeuserimg,imgpath,content,logtime)
values(board_seq.nextval,'admin','unimg.jpg','7.jpg','testupload2',SYSDATE);
insert into boardlist(idx,userid,writeuserimg,imgpath,content,logtime)
values(board_seq.nextval,'hi','unimg.jpg','8.jpg','testupload3',SYSDATE);
insert into boardlist(idx,userid,writeuserimg,imgpath,content,logtime)
				values(board_seq.nextval,'admin','unimg.jpg','9.jpg','testupload',SYSDATE);
insert into boardlist(idx,userid,writeuserimg,imgpath,content,logtime)
values(board_seq.nextval,'admin','unimg.jpg','13.jpg','testupload21',SYSDATE);
insert into boardlist(idx,userid,writeuserimg,imgpath,content,logtime)
values(board_seq.nextval,'hi','unimg.jpg','12.jpg','testupload32',SYSDATE);


alter table boardList add writeuserimg varchar2(80);
update boardList set writeuserimg='unimg.jpg' where idx=17;

---------------------------------------------------------------------------------------------------------------------------------------------------


create table reply(
idx number(6) primary key,	
boardidx number(6) not null ,
writeid varchar2(15) not null,
content varchar2(200),
logtime DATE DEFAULT SYSDATE);

---------------------------------------------------------------------------------------------------------------------------------------------------

create sequence reply_seq increment by 1 start with 1 nocycle nocache;


---------------------------------------------------------------------------------------------------------------------------------------------------

select * from userlist ;
select * from boardlist;
select * from reply;

select * from userlist where userid = 'admin';


SELECT IDX,USERID,IMGPATH,CONTENT,TO_CHAR(LOGTIME,'YYYY.MM.DD')AS LOGTIME 
         FROM (SELECT ROWNUM RN, AA.* 
               FROM (SELECT * FROM BOARDLIST ORDER BY IDX DESC)AA)
         WHERE RN>=1 AND RN<=21

SELECT USERID FROM USERLIST where userid not in ('hi') ORDER BY USERID;

 SELECT IDX,USERID,IMGPATH,CONTENT,TO_CHAR(LOGTIME,'YYYY.MM.DD')AS LOGTIME 
         FROM (SELECT ROWNUM RN, AA.* 
               FROM (SELECT * FROM BOARDLIST ORDER BY IDX DESC)AA)
         WHERE RN>=1 AND RN<=19;
         
UPDATE userlist SET profileimg= 'dog.jpg' WHERE userid='hi';


SELECT USERID FROM USERLIST ORDER BY USERID;
SELECT * FROM REPLY WHERE boardidx=3 ORDER BY IDX DESC;



insert into boardlist(idx,userid,imgpath,content,logtime)
            values(board_seq.nextval,'admin','1.jpg','testupload',SYSDATE);
insert into boardlist(idx,userid,imgpath,content,logtime)
values(board_seq.nextval,'admin','2.jpg','testupload2',SYSDATE);
insert into boardlist(idx,userid,imgpath,content,logtime)
values(board_seq.nextval,'admin','3.jpg','testupload3',SYSDATE);


insert into boardlist(idx,userid,imgpath,content,logtime)
            values(board_seq.nextval,'admin','4.jpg','testupload',SYSDATE);
insert into boardlist(idx,userid,imgpath,content,logtime)
values(board_seq.nextval,'admin','5.jpg','testupload2',SYSDATE);
insert into boardlist(idx,userid,imgpath,content,logtime)
values(board_seq.nextval,'admin','6.jpg','testupload3',SYSDATE);

insert into boardlist(idx,userid,imgpath,content,logtime)
            values(board_seq.nextval,'admin','7.jpg','testupload',SYSDATE);
insert into boardlist(idx,userid,imgpath,content,logtime)
values(board_seq.nextval,'admin','8.jpg','testupload2',SYSDATE);
insert into boardlist(idx,userid,imgpath,content,logtime)
values(board_seq.nextval,'admin','9.jpg','testupload3',SYSDATE);

drop table userlist;

drop table boardlist purge;
drop sequence board_seq;

drop table reply;
drop sequence reply_seq;

create table rec(
userid varchar2(40),
idx number(6) 
)

		SELECT COUNT(*) FROM REC WHERE userid='aa' AND idx=9


select * from rec;


drop table rec

