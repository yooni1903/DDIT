2020-11-18-01 ����Ŭ ���̺� Ÿ��
 - ����Ŭ���� �����ϴ� ������ Ÿ���� ���ڿ�, ����, ��¥, 2�� �ڷ����� ����
 
 1. ���ڿ� ������ Ÿ��
  - ''�� ���� �ڷḦ ���ڿ� �ڷ�� �Ѵ�.
  - CHAR, VARCHAR, VARCHAR2, NVARCHAR, NCHAR, LONG, RAW, CLOB ���� �����Ѵ�.
  
  1)CHAR
   . �������� ���ڿ��� ���
   . �ִ� 2000BYTE ���� ����
   . �����Ұ� ������ �����ʿ� ������ ����
   . ���������� ū �����ʹ� ���� ����
   . �ѱ� �� ���ڴ� 3BYTE�� ����ȴ�.
   . �⺻Ű � ���
   
(�������)
�÷��� CHAR(ũ��[BYTE|CHAR])
 . 'ũ��[BYTE|CHAR]' : �������� ũ��(BYTE)�� ���� ��(CHAR) ����
                2000BYTE �ʰ� �Ұ�
��)
CREATE TABLE TEMP01(
    COL1 CHAR(20),
    COL2 CHAR(20 BYTE),
    COL3 CHAR(20 CHAR));
    
INSERT INTO TEMP01(COL1, COL2, COL3)
    VALUES('���ѹα�', 'IL POSTINO', '���������� �߱� ���ﵿ 500-1����');
    
SELECT LENGTHB(COL1), LENGTHB(COL2), LENGTHB(COL3) -- LENGTH�� ���ڼ�, LENGTHB�� ����Ʈ���̼�(B�� BYTE)�� ����.
    FROM TEMP01;

SELECT * FROM TEMP01;

 2) VARCHAR2
  . �������� ���ڿ� ó��
  . �ִ� 4000BYTE ó�� ����
  . ���ǵ� ���������� �������� ���̸�ŭ ����ϰ� ���� ������ �ý��ۿ� �ݳ�
  . VARCHAR�� ���� ���(����Ŭ�� VARCHAR2 ����� �ǰ�)
  (�������)
  �÷��� VARCHAR2(ũ��[BYTE|CHAR])
   '[BYTE|CHAR]' : �����Ǹ� BYTE�� ���
   
��)
CREATE TABLE TEMP02(
    COL1 VARCHAR2(20),
    COL2 VARCHAR2(20 CHAR));
    
INSERT INTO TEMP02
    VALUES('������', '���ѹα���');
INSERT INTO TEMP02
    VALUES('ABCDEFG', 'SAGHDSAGHDSASSWWRRRR');
    
SELECT * FROM TEMP02;

SELECT LENGTHB(COL1), LENGTHB(COL2) FROM TEMP02;
