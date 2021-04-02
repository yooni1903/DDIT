2020-11-24-01
3)UPPER, LOWER
 - �־��� ���ڿ��� ���Ե� ���ڸ� �빮��(UPPER) �Ǵ� �ҹ���(LOWER)�� ��ȯ�Ͽ� ��ȯ
 (�������)
 UPPER(c), LOWER(c) -- c�� ���ڿ�
 
 ��) ȸ�����̺��� 'R001'ȸ�� ������ ��ȸ�Ͻÿ�.
    Alias�� ȸ����ȣ, ȸ����, ����, ���ϸ����̴�.
    
    SELECT MEM_ID AS ȸ����ȣ, 
           MEM_NAME AS ȸ����, 
           MEM_JOB AS ����,
           MEM_MILEAGE AS ���ϸ���
        FROM MEMBER
        WHERE UPPER(MEM_ID) = 'R001';
        
��) �з� ���̺��� 'P200'�� ���� �з��ڵ带 ��ȸ�Ͻÿ�.
    Alias�� �з��ڵ�, �з����̴�.
    
SELECT LPROD_GU AS �з��ڵ�, 
       LPROD_NM AS �з���
       FROM LPROD
       WHERE UPPER(LPROD_GU) LIKE 'P2%';
       
4) ASCII, CHR
 - ASCII : �־��� ���� �ڷḦ ASCII �ڵ� ������ ��ȯ
 - CHR : �־��� ����(����,1~65535)�� �����ϴ� ���� ��ȯ
 (�������)
 ASCII(c), CHR(n)
 
 ��)
    SELECT ASCII ('Oracle'),
           ASCII ('���ѹα�'),
           CHR(95)
        FROM DUAL;
        
5)LPAD, RPAD
 - Ư�����ڿ�(����)�� ������ �� ���
 (�������)
 LPAD(c, n [, pattern]),RPAD(c,n [pattern])
  - �־��� ���ڿ� 'c'�� ���� 'n'�� ��� ������ ���ʺ��� ä���(RPAD)| ���� ������ 'pattern'���� ���ǵ� ���ڿ��� ä��
  - 'pattern'�� �����Ǹ� �������� ä����
  ��) ȸ�� ���̺��� ȸ���� ��ȣ�� 10�ڸ������� ���������ϰ� ���� ������ '$'�� �����Ͻÿ�. '�ɷ���'
    Alias ȸ����ȣ, ȸ����, ��ȣ
    SELECT MEM_ID AS ȸ����ȣ,
           MEM_NAME AS ȸ����,
           MEM_PASS AS ��ȣ1,
           LPAD(MEM_PASS, 10, '$') AS ��ȣ2
        FROM MEMBER;
        
��) ���� ���̺��� 2005�� 2�� ���Խ�û�� ��ȸ�Ͻÿ�.
    Alias ��¥, ���Ի�ǰ�ڵ�, ����, �ܰ��̴�.
    �� ���Դ��ڴ� 9�ڸ��� ����ϵ� ���� ���� ������ '***'�� �����Ͽ� ����Ͻÿ�.
SELECT BUY_DATE AS ��¥, 
       BUY_PROD AS ���Ի�ǰ�ڵ�, 
       LPAD(BUY_COST, 9) AS ����, 
       LPAD(BUY_COST, 9, '*') AS �ܰ�
    FROM BUYPROD
    WHERE BUY_DATE BETWEEN '20050201' AND '20050228';
    
6)LTRIM, BTRIM, TRIM
 - �־��� ���ڿ����� ����(LTRIM) �Ǵ� ������(RTRIM)�� �����ϴ� ���ڿ��� ã�� ������ �� ���
 - ���ʿ� �����ϴ� ������ ������ ���� TRIM ���
 (�������)
 LTRIM(c1 [,c2]), RTRIM(c1 [, c2]), TRIM(c1)
  - c2�� �����Ǹ� ������ ����
  
  ��) ��� ���̺��� ������� CHAR(80)���� �����Ͻÿ�.
 ALTER TABLE EMPLOYEES
    MODIFY EMP_NAME CHAR(80);
    
��) ��� ���̺��� 'Steven King' ��� ������ ��ȸ�Ͻÿ�.
    Alias�� �����ȣ, �����, �μ��ڵ�, �Ի���
SELECT EMPLOYEE_ID AS �����ȣ, 
       TRIM(EMP_NAME) AS �����, 
       DEPARTMENT_ID AS �μ��ڵ�, 
       HIRE_DATE AS �Ի���
    FROM EMPLOYEES
    WHERE TRIM(EMP_NAME) = 'Steven King';
    
��) ��ǰ ���̺��� '���'�� �����ϴ� ��ǰ�� �� '���'�� �����ϰ� ����Ͻÿ�.
    Alias�� ��ǰ�ڵ�, ��ǰ��, �з��ڵ�, �ŷ�ó�ڵ� �̴�.
SELECT PROD_ID AS ��ǰ�ڵ�, 
       PROD_NAME AS ��ǰ��1,
       LTRIM(PROD_NAME, '��� ') AS ��ǰ��2,
       PROD_LGU AS �з��ڵ�, 
       PROD_BUYER AS �ŷ�ó�ڵ�
    FROM PROD
    WHERE PROD_NAME LIKE '���%';
    
7) SUBSTR(c,n1[,n2]) -- �ڹٿ��� ������ �򰥸��� �ʰ� �����ϱ�!
 - �־��� ���ڿ����� n1���� �����Ͽ� n2(����)��ŭ�� �κ� ���ڿ��� �����Ͽ� ��ȯ
 - n2�� �����Ǹ� n1 ������ ��� ���ڿ��� �����Ͽ� ��ȯ
 - n1�� �����̸� �ڿ������� ó����
 - n1�� 1���� counting
��)
    SELECT SUBSTR('IL POSTINO', 3,4), --3��° ������� ����POS�� ���
           SUBSTR('IL POSTINO', 3), --3��° ���ķ� ��
           SUBSTR('IL POSTINO', -3,4) --������ �����ʿ��� �������� ī�����ϰ� �����ʿ��� ����° �ڸ����� 4���ڸ� ����Ѵ�.
        FROM DUAL;
��) ��ǰ ���̺��� �з��ڵ�'P201'�� ���� ��ǰ�� ���� ���� ����Ͻÿ�.
    SELECT COUNT(*) AS ��ǰ�Ǽ�
        FROM PROD
       WHERE UPPER(SUBSTR(PROD_ID,1,4)) ='P201'
��) ��ٱ��� ���̺��� 2005�� 3���� �Ǹŵ� ��ǰ������ ��ǰ ���� ����Ͻÿ�.
    Alias�� ��ǰ�ڵ�, ��ǰ��, �����հ�, �Ǹűݾ�
    SELECT A.CART_PROD AS ��ǰ�ڵ�, 
           B.PROD_NAME AS ��ǰ��, 
           NVL(SUM(A.CART_QTY),0) AS �����հ�, 
           NVL(SUM(A.CART_QTY *B.PROD_PRICE), 0) AS �Ǹűݾ�
      FROM CART A, PROD B
     WHERE SUBSTR(CART_NO),1,6) = '200503' -- CART_NO LIKE '200502%'
       AND A.CART_PROD=B.PROD_ID
    GROUP BY A.CART_PROD, V
    
����) ȸ�� ���̺��� '����'�� ����մ� ȸ�������� ��ȸ�Ͻÿ�.
    Alias ȸ����ȣ, ȸ����, �ּ�, ����, ���ϸ���
    ��, LIKE �����ڸ� ������� ����
SELECT MEM_ID AS ȸ����ȣ, 
       MEM_NAME AS ȸ����, 
       MEM_ADD1||' '||MEM_ADD2 AS �ּ�, 
       MEM_JOB AS ����, 
       MEM_MILEAGE AS ���ϸ���
  FROM MEMBER
 WHERE SUBSTR(MEM_ADD1,1,2) = '����';
 
����)������ 2005�� 7�� 28���̶�� �����ϰ� CART_NO�� �ڵ����� �����ϴ� �ڵ带 �ۼ��Ͻÿ�.
1. 2005�� 7��20�� �ִ� ���� MAX
SELECT TO_NUMBER(MAX(SUBSTR(CART_NO,9))+1)
  FROM CART
 WHERE SUBSTR(CART_NO,1,8)='20050728';
2. ��¥�� 1������ ���� ���� +1�� ����
SELECT TO_CHAR(SYSDATE,'YYYYMMDD')||
       TRIM(TO_CHAR(TO_NUMBER(MAX(SUBSTR(CART_NO,9))+1),'00000')) -- TO_NUMBER �����ϸ� �ȵȴ�. ���� ���� ���� ����! �ڵ� ����ȯ�� ���� ����
  FROM CART
 WHERE SUBSTR(CART_NO,1,8)='20050728';
 
SELECT MAX(CART_NO)+1 
  FROM CART
 WHERE SUBSTR(CART_NO,1,8)='20050728';
����]�з� ���̺��� '��������' �з��ڵ带 �űԷ� ��Ͻ�ų �� 'P200'������ �ڵ带 �����Ͻÿ�.
SELECT 'P'||((MAX(SUBSTR(LPROD_GU,2))+1))
  FROM LPROD
 WHERE LPROD_GU LIKE 'P2%';

8) REPLACE(c1, c2[,c3])
 - �־��� ���ڿ� c1���� c2�� c3�� ��ġ(ġȯ)��Ŵ
 - c3�� �����Ǹ� c2�� ������
 
��) ��ǰ ���̺��� ��ǰ�� �� '���'�� ã�� 'Apple'�� �����Ͻÿ�.
SELECT PROD_ID,
       PROD_NAME,
       REPLACE(PROD_NAME, '���', 'Apple')
  FROM PROD
 WHERE PROD_NAME LIKE '%���%';
 
SELECT PROD_ID,
       PROD_NAME,
       REPLACE(PROD_NAME, '���')
  FROM PROD
 WHERE PROD_NAME LIKE '%���%';
 
SELECT PROD_ID,
       PROD_NAME,
       REPLACE(PROD_NAME, ' ') -- �ܾ� ������ ������ ����� ��� : REPLACE
  FROM PROD
 WHERE PROD_NAME LIKE '%���%';
 
9) LENGTH(c), LENGTHB(c)
 - �־��� ���ڿ����� ���� �� (LENGTH)�Ǵ� �������� ũ��(BYTE��, LENGTHB)�� ��ȯ