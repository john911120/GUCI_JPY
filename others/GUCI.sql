SELECT u.userId, u.userName, u.regDate, g.gdsPrice, o.cartStock  
FROM guci_user u, guci_order o, guci_goods g
WHERE o.gdsNo = g.gdsNo AND u.userId='user01';

SELECT stock, sum from guci_user where userId='user01';

SELECT u.stock, u.sum, g.gdsPrice, o.cartStock
FROM guci_user u, guci_goods g, guci_order o
WHERE u.userId=o.userId AND g.gdsNo = o.gdsNo AND u.userId='user01';

SELECT SUM(g.gdsPrice)
FROM guci_order o, guci_goods g
WHERE o.gdsNo = g.gdsNo AND userId = 'user01';

SELECT SUM(cartStock)
FROM guci_order
WHERE userId = 'user01';

--------------------------------------------------------------------
--SQL최종
--계정 생성
CREATE USER guci IDENTIFIED BY guci DEFAULT TABLESPACE
users TEMPORARY TABLESPACE temp PROFILE default;

GRANT CONNECT,Resource TO guci;
Grant create view,create synonym to guci;

--테이블 만들기
--회원 테이블
CREATE TABLE guci_user(
    userId VARCHAR2(30) NOT NULL,
    userPw VARCHAR2(100) NOT NULL,
    userName VARCHAR2(20) NOT NULL,
    userPhone VARCHAR2(50) NOT NULL,
    userEmail VARCHAR2(100) NOT NULL,
    userAddr1 VARCHAR2(100) NOT NULL,
    userAddr2 VARCHAR2(100) NOT NULL,
    userAddr3 VARCHAR2(100) NOT NULL,
    regDate DATE DEFAULT SYSDATE,
    CONSTRAINT PK_guci_user PRIMARY KEY(userId)
);

INSERT INTO guci_user VALUES('user','user','user','010-1234-5678','user@a.com','12345','서울시','마포구',sysdate,0);

-- 상품 테이블
create table guci_goods(
gdsNo NUMBER not null,
gdsName VARCHAR2(50) not null,
gdsPrice NUMBER not null,
gdsDes VARCHAR2(1000) null,
gdsSize VARCHAR2(20)not null,
gdsDate date DEFAULT sysdate,
gdsViews NUMBER DEFAULT 0,
cateCode VARCHAR2(20) NOT NULL,
CONSTRAINT PK_guci_goods primary key(gdsNo, gdsName)
);


--상품 2차분류 테이블
CREATE TABLE goods_category(
    cateCode VARCHAR2(20) NOT NULL,
    cateName VARCHAR2(50) NOT NULL,
    cateCodeRef VARCHAR2(30) NULL,
    CONSTRAINT pk_goods_category PRIMARY KEY(cateCode),
    FOREIGN KEY(cateCodeRef) REFERENCES goods_category(cateCode)
);

ALTER TABLE guci_goods ADD CONSTRAINT fk_goods_category
    FOREIGN KEY (cateCode) REFERENCES goods_category(cateCode);

CREATE SEQUENCE seq_guci_goods INCREMENT BY 1 START WITH 0 minvalue 1;

--상품이미지
create table goodsImg(
uuid varchar2(100),
fileName varchar2(100) not null,
uploadPath varchar2(200) not null,
fileType char(1) default 1,
gdsNo number not null,
gdsName varchar2(50) not null,
CONSTRAINT pk_goodsImg PRIMARY KEY(uuid),
constraint fk_goodsImg foreign key (gdsNO, gdsName) references guci_goods(gdsNO, gdsName)
);

--리뷰
CREATE TABLE review (
    revNo NUMBER NOT NULL,
    gdsNo NUMBER NOT NULL,
    gdsName VARCHAR2(50) NOT NULL,
    userId VARCHAR2(30) NOT NULL,
    revCon VARCHAR2(1000) NOT NULL,
    revDate DATE DEFAULT SYSDATE,
    score VARCHAR2(20) NOT NULL,
    CONSTRAINT pk_review PRIMARY KEY(revNo),
    CONSTRAINT fk_rev_gdsNo FOREIGN KEY (gdsNo,gdsname) REFERENCES guci_goods(gdsNo,gdsname),
    CONSTRAINT fk_rev_userId FOREIGN KEY (userId) REFERENCES guci_user(userId)
);

CREATE SEQUENCE seq_review INCREMENT BY 1 START WITH 0 MINVALUE 1;


--장바구니
create table guci_cart(
cartNo number not null,
userId VARCHAR2(30) not null,
gdsNo NUMBER not null,
gdsName VARCHAR2(30) NOT NULL,
cartStock NUMBER not null,
cartDate date DEFAULT sysdate,
uuid VARCHAR2(100),
selsize VARCHAR2(10),
CONSTRAINT pk_guci_cart primary key(cartNo, userId),
CONSTRAINT fk_cart1 FOREIGN key (userId) REFERENCES guci_user(userId),
CONSTRAINT fk_cart2 FOREIGN key (gdsNo, gdsName) REFERENCES guci_goods(gdsNo, gdsName),
CONSTRAINT fk_cart3 FOREIGN KEY (uuid) REFERENCES goodsImg(uuid)
);

CREATE SEQUENCE seq_guci_cart INCREMENT BY 1 START WITH 0 MINVALUE 1;

--주문정보
CREATE TABLE guci_orderInfo(
    userId VARCHAR2(30) NOT NULL,
    orderRec VARCHAR2(20) NOT NULL,
    orderAddr1 VARCHAR2(100) NOT NULL,
    orderAddr2 VARCHAR2(100) NOT NULL,
    orderAddr3 VARCHAR2(100) NOT NULL,
    orderPhone VARCHAR2(30) NOT NULL
);


--주문 테이블
CREATE TABLE guci_order(
    orderId VARCHAR2(100) NOT NULL,
    userId VARCHAR2(30) NOT NULL,
    orderRec VARCHAR2(20) NOT NULL,
    orderAddr1 VARCHAR2(100) NOT NULL,
    orderAddr2 VARCHAR2(100) NOT NULL,
    orderAddr3 VARCHAR2(100) NOT NULL,
    orderPhone VARCHAR2(30) NOT NULL,
    orderDate DATE DEFAULT SYSDATE,
    orderStock VARCHAR(10) NOT NULL,
    delivery VARCHAR2(20) DEFAULT '주문완료',
    gdsNo NUMBER,
    cartNo NUMBER,
    gdsName VARCHAR2(50) NOT NULL,
    CONSTRAINT pk_guci_order PRIMARY KEY(orderId),
    CONSTRAINT fk_guci_order1 FOREIGN key (userId) REFERENCES guci_user(userId),
    CONSTRAINT fk_guci_order2 FOREIGN key (gdsNo, gdsName) REFERENCES guci_goods(gdsNo, gdsName),
    CONSTRAINT fk_guci_order3 FOREIGN key (cartNo ,userId) REFERENCES guci_cart(cartNo, userId)
);

CREATE SEQUENCE seq_guci_order INCREMENT BY 1 START WITH 0 MINVALUE 1;

--문의하기 테이블
create table guci_question(
    quesNo number,
    quesCateCode VARCHAR2(50) not null,
    quesTit VARCHAR2(500) not null,
    quesCon VARCHAR2(1000) not null,
    quesWri VARCHAR2(30) not null,
    quesDate date DEFAULT sysdate,
    replyCnt NUMBER DEFAULT 0,
    CONSTRAINT pk_guci_question PRIMARY KEY(quesNo)
);

CREATE SEQUENCE seq_guciquestion INCREMENT BY 1 START WITH 0 MINVALUE 1;

-- 댓글
create table reply(
rno number,
quesNo number not null,
reply VARCHAR2(1000) not null,
replyer VARCHAR2(30) default '관리자',
replyDate date default sysdate,
updateDate DATE DEFAULT SYSDATE,
CONSTRAINT pk_reply PRIMARY KEY(rno),
constraint fk_reply foreign key (quesNo) references guci_question(quesNo) on delete cascade
);

CREATE SEQUENCE seq_reply INCREMENT BY 1 START WITH 0 MINVALUE 1;

--FAQ 테이블
create table guci_faq(
faqNo number,
faqCate VARCHAR2(50) not null,
faqTit VARCHAR2(500) not null,
faqCon VARCHAR2(1000) not null,
faqWri VARCHAR2(30) not null,
faqDate date DEFAULT sysdate,
CONSTRAINT pk_guci_faq PRIMARY KEY(faqNo)
);

CREATE SEQUENCE seq_faq INCREMENT BY 1 START WITH 0 MINVALUE 1;


--공지사항 테이블
create table guci_notice(
noticeNo number,
noticeTit VARCHAR2(500) not null,
noticeCon VARCHAR2(1000) not null,
noticeWri VARCHAR2(30) not null,
noticeDate date DEFAULT sysdate,
CONSTRAINT pk_guci_notice PRIMARY KEY(noticeNo)
);

CREATE SEQUENCE seq_notice INCREMENT BY 1 START WITH 0 MINVALUE 1;

--이미지 처리 테이블


--리뷰이미지
create table reviewImg(
uuid varchar2(100),
fileName varchar2(100) not null,
uploadPath varchar2(200) not null,
fileType char(1) default 1,
revNo number not null,
CONSTRAINT pk_reviewImg PRIMARY KEY(uuid),
constraint fk_reviewImg foreign key (revNo) references review(revNO)
);


--문의이미지
create table guci_question_attach(
uuid varchar2(100),
fileName varchar2(100) not null,
uploadPath varchar2(200) not null,
fileType char(1) default 1,
quesNo number not null,
CONSTRAINT pk_quesImg PRIMARY KEY(uuid),
constraint fk_quesImg foreign key (quesNo) references guci_question(quesNO)
);

-- 카테고리 고정 데이터
INSERT INTO goods_category (cateName, cateCode) VALUES ('남성', '100');
INSERT INTO goods_category (cateName, cateCode, cateCodeRef) VALUES ('상의', '101', '100');
INSERT INTO goods_category (cateName, cateCode, cateCodeRef) VALUES ('하의', '102', '100');
INSERT INTO goods_category (cateName, cateCode, cateCodeRef) VALUES ('아우터', '103', '100');

INSERT INTO goods_category (cateName, cateCode) VALUES ('여성', '200');
INSERT INTO goods_category (cateName, cateCode, cateCodeRef) VALUES ('상의', '201', '200');
INSERT INTO goods_category (cateName, cateCode, cateCodeRef) VALUES ('하의', '202', '200');
INSERT INTO goods_category (cateName, cateCode, cateCodeRef) VALUES ('아우터', '203', '200');
INSERT INTO goods_category (cateName, cateCode, cateCodeRef) VALUES ('원피스', '204', '200');

INSERT INTO goods_category (cateName, cateCode) VALUES ('공용', '300');
INSERT INTO goods_category (cateName, cateCode, cateCodeRef) VALUES ('상의', '301', '300');
INSERT INTO goods_category (cateName, cateCode, cateCodeRef) VALUES ('하의', '302', '300');
INSERT INTO goods_category (cateName, cateCode, cateCodeRef) VALUES ('아우터', '303', '300');


commit;


----------------------------------------------------------
SELECT COUNT(*) FROM guci_question WHERE replyCnt = 0;

delete from guci_user where userId = 'user';


 select sum(gg.gdsPrice * gc.cartStock) from guci_cart gc, guci_goods gg,guci_user gu
where gc.gdsNo=gg.gdsNo and gc.userid='user01';



CREATE TABLE a (
    name VARCHAR2(10),
    id VARCHAR2(10),
    pw VARCHAR2(10)
);

INSERT INTO a VALUES('name1','id','pw1');
INSERT INTO a VALUES('name2','id','pw2');
INSERT INTO a VALUES('name3','id','pw3');

CREATE TABLE b(
    id VARCHAR2(10),
    hobby VARCHAR2(10),
    job VARCHAR2(10)
);

INSERT INTO b VALUES('id','hobby1','job1');

COMMIT;

CREATE TABLE c(
    name VARCHAR2(10),
    id VARCHAR2(10),
    pw VARCHAR2(10),
    hobby VARCHAR2(10),
    job VARCHAR2(10)
);

INSERT INTO c (name, id, pw, hobby, job)
SELECT a.name, a.id, a.pw, b.hobby, b.job
FROM a a, b b
WHERE a.id = b.id AND a.id = 'id';

select * from c;