CREATE TABLE USERS (
	USER_NO    VARCHAR2(20)  NOT NULL, -- ȸ����ȣ
	USER_NAME  VARCHAR2(50)  NOT NULL, -- ȸ����
	USER_ID    VARCHAR2(30)  NOT NULL, -- ���̵�
	USER_PW    VARCHAR2(30)   NOT NULL, -- ��й�ȣ
	USER_BIRTH NUMBER        NOT NULL, -- �������
	ADD1       VARCHAR2(100) NOT NULL, -- �ּ�1
	ADD2       VARCHAR2(50)  NOT NULL, -- �ּ�2
	TEL        VARCHAR2(30)  NOT NULL  -- ��ȭ��ȣ
);

CREATE TABLE BOOK (
	BOOK_NO   VARCHAR2(20) NOT NULL, -- ������ȣ
	ISBN      VARCHAR2(20)   NOT NULL, -- û����ȣ
	WRITER    VARCHAR2(50) NOT NULL, -- ����
	BOOK_NAME VARCHAR2(50) NOT NULL, -- ������
	BOOK_PUB  VARCHAR2(50) NOT NULL, -- ���ǻ�
	BOOK_DATE DATE         NOT NULL, -- ������
	AGE       NUMBER       NOT NULL  -- �̿뿬��
);

CREATE TABLE RENT (
	RENT_NO   NUMBER       NOT NULL, -- �����ȣ
	BOOK_NO   VARCHAR2(20) NOT NULL, -- ������ȣ
	USER_NO   VARCHAR2(20) NOT NULL, -- ȸ����ȣ
	RENT_DATE DATE         NOT NULL, -- ��������
	RETURN    DATE         NULL,     -- �ݳ�����
	REAL_TRUN DATE         NOT NULL, -- �ݳ�������
	EXDATE    DATE         NULL      -- ������
);

CREATE TABLE ADMIN (
	ADMIN_NO   VARCHAR2(20) NOT NULL, -- �����ڹ�ȣ
	ADMIN_NAME VARCHAR2(50)  NOT NULL, -- �����ڸ�
	ADMIN_ID   VARCHAR2(20) NOT NULL, -- ���̵�
	ADMIN_PW   VARCHAR2(30)  NOT NULL  -- ��й�ȣ
);

CREATE TABLE NOTICE (
	NOTICE_NO    VARCHAR2(20)   NOT NULL, -- �۹�ȣ
	ADMIN_NO     VARCHAR2(20)   NOT NULL, -- �����ڹ�ȣ
	NOTICE_TITLE VARCHAR2(50)   NOT NULL, -- ������
	NOTICE_CON   VARCHAR2(2000) NOT NULL, -- �۳��� 
	NOTICE_DATE  DATE           NOT NULL  -- �ۼ���
);

CREATE TABLE BOARD (
	BOARD_NO VARCHAR2(20)   NOT NULL, -- �۹�ȣ
	USER_NO  VARCHAR2(20)   NOT NULL, -- ȸ����ȣ
	TITLE    VARCHAR2(20)   NOT NULL, -- ������
	CONTENT  VARCHAR2(1000) NOT NULL, -- �۳��� 
	REG_DATE DATE           NOT NULL  -- �ۼ���
);

CREATE TABLE DELAY (
	DELAY_NO   VARCHAR2(20) NOT NULL, -- ��ü��ȣ
	RENT_NO    NUMBER       NOT NULL, -- �����ȣ
	DELAY_DATE DATE         NOT NULL  -- ��ü��
);

CREATE TABLE RESERVE (
	RES_NO   VARCHAR2(20) NOT NULL, -- �����ȣ
	USER_NO  VARCHAR2(20) NOT NULL, -- ȸ����ȣ
	BOOK_NO  VARCHAR2(20) NOT NULL, -- ������ȣ
	RES_END  DATE         NULL,     -- ��������
	RES_DATE DATE         NULL      -- ������
);

CREATE TABLE REVIEW (
	REVIEW_NO  NUMBER        NOT NULL, --  �����ȣ
	BOOK_NO    VARCHAR2(20)  NULL,     -- ������ȣ
	REVIEW_CON VARCHAR2(200) NOT NULL  -- ���䳻��
);

-- ȸ�� �⺻Ű
CREATE UNIQUE INDEX PK_USERS
	ON USERS ( -- ȸ��
		USER_NO ASC -- ȸ����ȣ
	);

-- ȸ��
ALTER TABLE USERS
	ADD
		CONSTRAINT PK_USERS -- ȸ�� �⺻Ű
		PRIMARY KEY (
			USER_NO -- ȸ����ȣ
		);

ALTER TABLE RESERVE
	ADD
		CONSTRAINT PK_USERS -- ȸ�� �⺻Ű
		PRIMARY KEY (
			USER_NO -- ȸ����ȣ
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
        