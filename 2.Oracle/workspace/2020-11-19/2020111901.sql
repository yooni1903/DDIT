2020-11-19-01
3) LONG ������ Ÿ��
 - �������� ���ڿ��� ����
 - �ִ� 2GB���� ���� ����
 - LONGŸ���� �ϳ��� ���̺��� 1���� ��� ����
 - CLOB�� ��ü(��� ���׷��̵� �ߴ�) -- LOB(LARGE OBJECT) �տ� C�� ������ CHARACTER��� ���� �ٴ´�. (B�� ������ BANARY)
 - SELECT ���� SELECT��, UPDATE ���� SET��, INSERT ���� VALUES������ ��� ����
 (�������)
 �÷��� LONG
 
 
 ��)
CREATE TABLE TEMP03(
    COL1 LONG,
    COL2 VARCHAR2(2000));
    
INSERT INTO TEMP03 VALUES('SADHSDAGHSDA', 'SAGHSAGHSA');

SELECT * FROM TEMP03;
SELECT SUBSTR(COL2,2,5) FROM TEMP03;

4) CLOB (Character Large OBject) ������ Ÿ��
 - ������ ���ڿ� �ڷḦ ó���ϱ� ���� ������ Ÿ��
 - �ִ� 4GB���� ó�� ����
 - �������� ���� �ڷ���
 - �� ���̺��� ���� �� �ִ� CLOB�� ������ ������ ����.
 - �Ϻ� ��ɵ��� DBMS_LOB API�� ������ �޾ƾ� �Ѵ�.
(�������)
�÷��� CLOB

��)
CREATE TABLE TEMP04(
    COL1 CLOB,
    COL2 CLOB,
    COL3 CLOB;
    
INSERT INTO TEMP04 
    VALUES('���ѹα��� ���� ��ȭ���̴�.', '������ �߱� ���ﵿ 500 ���κ��� 3��','Oracle Modeling�� SQL');
    
SELECT * FROM TEMP04;

SELECT LENGTHB(COL2) FROM TEMP04;

SELECT DBMS_LOB.SUBSTR(COL1, 5,3),
    DBMS_LOB.GETLENGTH(COL2), -- LENGTH �� ���� ��, LENGTHB�� ����Ʈ ��
    LENGTH(COL3)
    FROM TEMP04;
    
    
5) NCLOB, NVARCHAR2 -- N�� NATIONAL�̶�� ��
 - �ٱ��� ���� ���·� ���� �ڷ�����
 - UTF-8, UTF-16 ������ ENCODING
 - ������ ����� CLOB, VARCHAR2�� ����
 
 2.�����ڷ���
  - ����(fixed point)�� �Ǽ�(floating point) ������ ���� -- ����Ŭ�� ���� �켱!
  - NUMBER Ÿ�� ����
  (�������)
  NUMBER[(���е�|*[,������])] -- ���ȣ�� ��������
   - ǥ���� �� �ִ� ���� ���� : 1.0e-130 ~ 9.999..9e125
   - ���е� : ��ü �ڸ���(1~38)
   - ������ : �Ҽ��� ������ �ڸ���
   ex) NUMBER(5,2) : ��ü 5�ڸ��� Ȯ��(���� �κ� 3�ڸ�, �Ҽ��� ���� 3�ڸ����� �ݿø��Ͽ� 2��° �ڸ����� ǥ��)
   - ���е� ��� '*'�� ����� �� �ִµ�, ����ϸ� 38�ڸ� �̳����� �ڵ��Ҵ�(�����κ�)
   ex)NUMBER(*,2) : �Ҽ��� ���� 3�ڸ����� �ݿø��Ͽ� 2��° �ڸ����� ǥ��. ��, �����κ��� 38�ڸ� �̳����� ũ�⸸ŭ �ڵ� �Ҵ�.
   - �������� �����Ǹ� 0���� ����
   - �������� �����̸� �����κп��� �ݿø�
��)
--------------------------------------------------------
�Է°�              ����               ����� ��
--------------------------------------------------------
12345678.7896      NUMBER            12345678.7896
12345678.7896      NUMBER(*,2)       12345678.79
12345678.7896      NUMBER(8)         12345679
12345678.7896      NUMBER(7,2)       ����  -- �Է°��� 8�ڸ��ε� ������ 7�ڸ��� ��û�����ν� �ڸ��� �������� ������ ����
12345678.7896      NUMBER(10,-2)     12345700

** ���е� < ������ �� ��� -- �̷� ���� ���� ���� ����.
    - ���е��� 0�� �ƴ� ��ȿ������ ��
    - (������-���е�) : �Ҽ��� ���Ͽ� �����ؾ��� 0�� ����
    - ������ : �Ҽ��� ������ �������� ��
    
--------------------------------------------------------------------
�Է°�             ����                  ����� ��
--------------------------------------------------------------------
1.234             NUMBER(4,5)           ����
0.23              NUMBER(3,5)           ����
0.0123            NUMBER(3,4)           0.0123
0.0012345         NUMBER(3,5)           0.00123
0.00125676        NUMBER(3,5)           0.00126


3. ��¥�ڷ���
 - ��¥�ڷ�(��, ��, ��, ��, ��)�� �����ϱ� ���� ������ Ÿ��
 - �⺻���� DATE, �ð���(TIMEZONE)������ 10����� 1�� ������ �ð� ������ �����ϴ� ������ Ÿ������ TIMESTAMP�� ����
1)DATE Ÿ��
 - �⺻ ��¥��
 - '+', '-' ������ ���
 **SYSDATE : �ý����� �����ϴ� ��¥ ������ �����ϴ� �Լ�
 (�������)
 �÷��� DATE
    
��)
CREATE TABLE TEMP05(
    COL1 DATE,
    COL2 DATE,
    COL3 DATE);
    
INSERT INTO TEMP05 VALUES(SYSDATE, '20101029',SYSDATE+30);

SELECT * FROM TEMP05;
SELECT COL1 -10 FROM TEMP05;

SELECT MOD(TO_DATE('20100101')-TO_DATE('00010101')-1,7) FROM DUAL; -- DUAL : �ý����� �����ϴ� ������ ���̺�

SELECT 12345*346545646 FROM DUAL;

SELECT TO_CHAR(COL1, 'YYYY"��" MM"��" DD"��" AM HH24:MI:SS')FROM TEMP05;

2) TIMESTAMP Ÿ��
 - �ð��� ������ ������ �ð� ������ �ʿ��� �� ���
 (�������)
 �÷��� TIMESTAMP -- �ð��� ���� ����
 �÷��� TIMESTAMP WITH TIME ZONE -- �ð��� ����(�����/������) ����
 �÷��� TIMESTAMP WITH LOCAL TIME ZONE -- ������ ��ġ�� �ð�������, ���� TIMESTAMP�� ����
 
 ��)
 CREATE TABLE TEMP06(
    COL1 DATE,
    COL2 TIMESTAMP,
    COL3 TIMESTAMP WITH TIME ZONE,
    COL4 TIMESTAMP WITH LOCAL TIME ZONE);
    
INSERT INTO TEMP06
    VALUES(SYSDATE, SYSDATE, SYSDATE, SYSDATE);
    
    SELECT*FROM TEMP06;