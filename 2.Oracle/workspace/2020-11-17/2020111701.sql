2020-11-17-01 SQL(STRUCTURE QUERY LANGUAGE)
1. DDL (DATA DEFINITION LANGUAGE)
 - ������ ���Ǿ�(����Ŭ ��ü ����, ����, ����)
 - CREATE, DROP, ALTER
 
 1) ���̺� ���� ���
  - ����Ŭ �����ͺ��̽��� �⺻ ��ü�� ���̺� ����
  - CREATE TABLE ��� ����
  (�������)
  CREATE TABLE ���̺�� (
   �÷���1 Ÿ�Ը�[(ũ��[BYTE|CHAR])] [NOT NULL][DEFAULT ��],
   �÷���2 Ÿ�Ը�[(ũ��[BYTE|CHAR])] [NOT NULL][DEFAULT ��],
   
   �÷���n Ÿ�Ը�[(ũ��[BYTE|CHAR])] [NOT NULL][DEFAULT ��],
   [CONSTRAINT �⺻Ű������ PRIMARY KEY(�÷���1[,�÷���2,...]),]
   [CONSTRAINT �ܷ�Ű������ FOREIGN KEY(�÷���1[,�÷���2,...]
    REFERENCES ���̺��(�÷���1[,�÷���2, ...])]
. �⺻Ű������ : �ش� ���̺� �����̽����� ������ ��Ī�̾�� �Ѵ�.
. �ܷ�Ű������ : �ش� ���̺� �����̽����� ������ ��ġ�̾�� �Ѵ�.
. '���̺��'(�÷���1) : �ܷ�Ű�� �����ϴ� ���� ���̺��̸�
            '(�÷���1)'�� ���� ���̺��� ���� �÷���
            
��) �ѱ��Ǽ� ����erd�� ���ʷ� ���̺��� �����Ͻÿ�.
(1) EMP ���̺� ����
 CREATE TABLE EMP(
    EMP_ID CHAR(5),
    EMP_NAME VARCHAR2(30) NOT NULL,
    ADDR VARCHAR(80),
    TEL_NO VARCHAR2(50),
    JOB_GRADE VARCHAR2(50),
    DEPT_NAME VARCHAR2(50),
    
    CONSTRAINT pk_emp PRIMARY KEY(EMP_ID));
    
(2)SITE ���̺� ����
 CREATE TABLE SITE(
    SITE_NO CHAR(8), --�⺻Ű
    SITE_NAME VARCHAR2(50) NOT NULL,
    SITE_TEL_NO VARCHAR2(20),
    CONS_AMT NUMBER(12) DEFAULT 0,
    INPUT_PER NUMBER(4) DEFAULT 0,
    START_DATE DATE DEFAULT SYSDATE,
    P_COM_DATE DATE,
    COM_ DATE,
    REMARKS VARCHAR2(100),
    
    CONSTRAINT pk_site PRIMARY KEY(SITE_NO));
    
(3) MATERIALS ���̺� ����
CREATE TABLE MATERIALS(
    MAT_ID CHAR(10),
    MAT_NAME VARCHAR2(50) NOT NULL,
    MAT_QTY NUMBER(4) DEFAULT 0,
    MAT_PRICE NUMBER(8),
    PUR_DATE DATE,
    SITE_NO CHAR(8),
    
    CONSTRAINT pk_materials PRIMARY KEY(MAT_ID),
    CONSTRAINT fk_site FOREIGN KEY(SITE_NO)
        REFERENCES SITE(SITE_NO));
        
(4)WORK TABLE ����
CREATE TABLE WORK(
    EMP_ID CHAR(5),
    SITE_NO CHAR(8),
    WSK_DATE DATE,
    
    CONSTRAINT pk_work PRIMARY KEY(EMP_ID,SITE_NO),
    CONSTRAINT fk_work_emp FOREIGN KEY(EMP_ID)
        REFERENCES EMP(EMP_ID),
    CONSTRAINT fk_work_site FOREIGN KEY(SITE_NO)
        REFERENCES SITE(SITE_NO));
        
    COMMIT;