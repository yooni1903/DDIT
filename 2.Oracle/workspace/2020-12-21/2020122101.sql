2020-12-21-01) �Լ�(user defined function : FUNCTION)
 - ���ν����� ����� ����(��, ��ȯ���� �����ؾ� ��)
 - �Ϲ� �����Լ�ó�� ��� ���� 
 (��� ����)
 CREATE [OR REPLACE] FUNCTION �Լ���(
    �Ű����� [IN | OUT | INOUT] Ÿ�Ը� [:= DEFAULT ��],     -- Ÿ���� ũ�⸦ �����༭�� �ȵȴ�.
                :
    �Ű����� [IN | OUT | INOUT] Ÿ�Ը� [:= DEFAULT ��]
    
    RETURN Ÿ�Ը�
 IS | AS
    �����
 BEGIN
    �����
    RETURN ��; -- ���� RETURN ���� Ÿ�԰� �ݵ�� ���ƾ� �Ѵ�.
 END;
     . ����ο� �ݵ�� �ϳ� �̻��� RETURN ���� �����ؾ� ��.
    
    
��) ��ǰ���̺��� ��ǰ�ڵ带 �Է¹޾� �ش� ��ǰ�� 2005�� ���Լ����� ��ȸ�ϴ� �Լ��� �ۼ��Ͻÿ�
(�Լ�ó������ : �Է¹��� ��ǰ�ڵ忡 �ش��ϴ� ��ǰ�� 2005�� ���Լ����� ��ȸ)
CREATE OR REPLACE FUNCTION FN_BUYQTY(
    P_CODE IN PROD.PROD_ID%TYPE)
    
    RETURN NUMBER
IS
    V_AMT  NUMBER(5) := 0; -- 2005�⵵ ��ǰ�� ���Լ��� ����
BEGIN
    SELECT SUM(BUY_QTY) INTO V_AMT
      FROM BUYPROD
     WHERE BUY_PROD = P_CODE
       AND BUY_DATE BETWEEN '20050101' AND '20051231';
    RETURN V_AMT;
END;
(����)
SELECT PROD_ID AS ��ǰ�ڵ�,
       PROD_NAME AS ��ǰ��,
       FN_BUYQTY(PROD_ID) AS ���Լ���
  FROM PROD
 WHERE FN_BUYQTY(PROD_ID) >= 100;

��) ��ǰ���̺��� ��ǰ�ڵ带 �Է¹޾� �ش� ��ǰ�� 2005�� ���Լ����� ���Աݾ��� ��ȸ�ϴ� �Լ��� �ۼ��Ͻÿ�  
    
CREATE OR REPLACE FUNCTION FN_BUYQTY(
    P_CODE IN PROD.PROD_ID%TYPE)
    
    RETURN VARCHAR2
IS
    V_AMT  NUMBER(5) := 0; -- 2005�⵵ ��ǰ�� ���Լ��� ����
    V_SUM  NUMBER := 0; -- 2005�� ��ǰ�� ���Աݾ� �հ�
    V_RES  VARCHAR2(50); -- ����� �� ������
BEGIN
    SELECT SUM(BUY_QTY), SUM(BUY_QTY * BUY_COST) INTO V_AMT, V_SUM
      FROM BUYPROD
     WHERE BUY_PROD = P_CODE
       AND BUY_DATE BETWEEN '20050101' AND '20051231';
    V_RES := '���Լ��� : ' || TO_CHAR(V_AMT) || ', ' || '���Աݾ� : ' || TO_CHAR(V_SUM);
    RETURN V_RES;
END;
(����)
SELECT PROD_ID AS ��ǰ�ڵ�,
       PROD_NAME AS ��ǰ��,
       FN_BUYQTY(PROD_ID) AS ������Ȳ
  FROM PROD;


��) �������� �泲�� ȸ������ 2005�⵵ ��ݱ� ������� ��ȸ�Ͻÿ�
    (�Լ����� :�Էµ� ȸ������ 2005�⵵ ��ݱ� ������� ��ȸ)
CREATE OR REPLACE FUNCTION FN_CART01(
    P_MEMID IN MEMBER.MEM_ID%TYPE)
    
    
    RETURN NUMBER
IS
    V_SUM NUMBER := 0;
BEGIN
    SELECT SUM(CART_QTY * PROD_PRICE) INTO V_SUM
      FROM CART, PROD
     WHERE CART_PROD = PROD_ID
       AND CART_MEMBER = P_MEMID
       AND SUBSTR(CART_NO, 1, 6) BETWEEN '200501' AND '200506';
       
    RETURN V_SUM;
END;
(���� : �������� �泲�� ȸ����ȣ �˻�)
SELECT MEM_NAME AS ȸ����,
       NVL(FN_CART01(MEM_ID),0) AS ���Ծ��հ�
  FROM MEMBER
 WHERE MEM_ADD1 LIKE '�泲%';
 
 
 
��)���� ������ �����ϴ� ����� �̸��� ����ϴ� �Լ��� �ۼ��Ͻÿ�.
CREATE OR REPLACE FUNCTION FN_GET_USER
    RETURN VARCHAR2
IS
    V_NAME VARCHAR2(50);
BEGIN 
    SELECT USER INTO V_NAME
      FROM DUAL;
    RETURN V_NAME;
END;

(����)
SELECT FN_GET_USER, FN_GET_USER()  FROM DUAL;
