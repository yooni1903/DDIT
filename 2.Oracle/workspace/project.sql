CREATE TABLE USERS (
	USER_NO    VARCHAR2(20)  NOT NULL, -- 회원번호
	USER_NAME  VARCHAR2(50)  NOT NULL, -- 회원명
	USER_ID    VARCHAR2(30)  NOT NULL, -- 아이디
	USER_PW    VARCHAR2(30)   NOT NULL, -- 비밀번호
	USER_BIRTH NUMBER        NOT NULL, -- 생년월일
	ADD1       VARCHAR2(100) NOT NULL, -- 주소1
	ADD2       VARCHAR2(50)  NOT NULL, -- 주소2
	TEL        VARCHAR2(30)  NOT NULL  -- 전화번호
);

CREATE TABLE BOOK (
	BOOK_NO   VARCHAR2(20) NOT NULL, -- 도서번호
	ISBN      VARCHAR2(20)   NOT NULL, -- 청구기호
	WRITER    VARCHAR2(50) NOT NULL, -- 저자
	BOOK_NAME VARCHAR2(50) NOT NULL, -- 도서명
	BOOK_PUB  VARCHAR2(50) NOT NULL, -- 출판사
	BOOK_DATE DATE         NOT NULL, -- 발행일
	AGE       NUMBER       NOT NULL  -- 이용연령
);

CREATE TABLE RENT (
	RENT_NO   NUMBER       NOT NULL, -- 대출번호
	BOOK_NO   VARCHAR2(20) NOT NULL, -- 도서번호
	USER_NO   VARCHAR2(20) NOT NULL, -- 회원번호
	RENT_DATE DATE         NOT NULL, -- 대출일자
	RETURN    DATE         NULL,     -- 반납일자
	REAL_TRUN DATE         NOT NULL, -- 반납예정일
	EXDATE    DATE         NULL      -- 연장일
);

CREATE TABLE ADMIN (
	ADMIN_NO   VARCHAR2(20) NOT NULL, -- 관리자번호
	ADMIN_NAME VARCHAR2(50)  NOT NULL, -- 관리자명
	ADMIN_ID   VARCHAR2(20) NOT NULL, -- 아이디
	ADMIN_PW   VARCHAR2(30)  NOT NULL  -- 비밀번호
);

CREATE TABLE NOTICE (
	NOTICE_NO    VARCHAR2(20)   NOT NULL, -- 글번호
	ADMIN_NO     VARCHAR2(20)   NOT NULL, -- 관리자번호
	NOTICE_TITLE VARCHAR2(50)   NOT NULL, -- 글제목
	NOTICE_CON   VARCHAR2(2000) NOT NULL, -- 글내용 
	NOTICE_DATE  DATE           NOT NULL  -- 작성일
);

CREATE TABLE BOARD (
	BOARD_NO VARCHAR2(20)   NOT NULL, -- 글번호
	USER_NO  VARCHAR2(20)   NOT NULL, -- 회원번호
	TITLE    VARCHAR2(20)   NOT NULL, -- 글제목
	CONTENT  VARCHAR2(1000) NOT NULL, -- 글내용 
	REG_DATE DATE           NOT NULL  -- 작성일
);

CREATE TABLE DELAY (
	DELAY_NO   VARCHAR2(20) NOT NULL, -- 연체번호
	RENT_NO    NUMBER       NOT NULL, -- 대출번호
	DELAY_DATE DATE         NOT NULL  -- 연체일
);

CREATE TABLE RESERVE (
	RES_NO   VARCHAR2(20) NOT NULL, -- 예약번호
	USER_NO  VARCHAR2(20) NOT NULL, -- 회원번호
	BOOK_NO  VARCHAR2(20) NOT NULL, -- 도서번호
	RES_END  DATE         NULL,     -- 예약종료
	RES_DATE DATE         NULL      -- 예약일
);

CREATE TABLE REVIEW (
	REVIEW_NO  NUMBER        NOT NULL, --  리뷰번호
	BOOK_NO    VARCHAR2(20)  NULL,     -- 도서번호
	REVIEW_CON VARCHAR2(200) NOT NULL  -- 리뷰내용
);

-- 회원 기본키
CREATE UNIQUE INDEX PK_USERS
	ON USERS ( -- 회원
		USER_NO ASC -- 회원번호
	);

-- 회원
ALTER TABLE USERS
	ADD
		CONSTRAINT PK_USERS -- 회원 기본키
		PRIMARY KEY (
			USER_NO -- 회원번호
		);

ALTER TABLE RESERVE
	ADD
		CONSTRAINT PK_USERS -- 회원 기본키
		PRIMARY KEY (
			USER_NO -- 회원번호
		);
        
        
        
        
        
        
CREATE TABLE LBOOK (
	ISBN    VARCHAR2(20)  NOT NULL, 
	ISBN_NAME  VARCHAR2(50)  NOT NULL
);
        
DELETE RESERVE;
        

            
        
INSERT INTO RENT
    VALUES ('R' || TO_CHAR(SYSDATE,'YYYYMMDD')|| TRIM((SELECT TO_CHAR(COUNT(*) + 1, '00000')
                                                               FROM RENT)),
            'B2020122300001',
            'D00001',
            SYSDATE,
            NULL,
            SYSDATE+14,
            NULL);        
        

INSERT INTO RESERVE
    VALUES ('RE' || TO_CHAR(SYSDATE,'YYYYMMDD')|| TRIM((SELECT TO_CHAR(COUNT(*) + 1, '00000')
                                                               FROM RESERVE)),
            'D00002',
            'B2020122300001',
            (SELECT RETURN + 3
               FROM RENT),
            SYSDATE);
        
        
SELECT 'A'||TRIM(TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(ADMIN_NO,2,3))),0)+1, '000'))
  FROM ADMIN;
        
        
        
        
        commit;
        