2020-12-17-01) ���� ���ν���(Stored Procedure : Procedure)
 - Ư�� ����� �����Ͽ� �ϳ��� ����� �����ϱ� ���� ����� ����
 - �̸� �����ϵǾ� ������ ����
 - ����ڴ� ���� ������ ���ν����� ȣ��(��Ʈ���� Ʈ���� ����, ����ȿ�� ����, ����Ȯ��)
 - ��ȯ���� ����
 (��� ����)
 CREATE [OR REPLACE] PROCEDURE ���ν�����[(
    �Ű����� [IN | OUT | INOUT] Ÿ�Ը� [:=|DEFAULT ��],
                :
    �Ű����� [IN | OUT | INOUT] Ÿ�Ը� [:=|DEFAULT ��]
    IS | AS -- ���� �Ұ���
        �����;
    BEGIN
        �����
        [EXCEPTION
            ����ó����;
        ]
    END;
    
    (���๮ �������)
    EXEC | EXECUTE ���ν�����(�Ű�����, ...); ---�ܵ�����
    
    �͸����̳� �ٸ� �Լ� ����� 
    ���ν�����(�Ű�����, ...); -- EXEC�� EXECUTE�� �����Ѵ�. ������ ������!
    
    
    
**���� ���̺��� �����Ͻÿ�.
  ���̺�� : REMAIN
--======================================================================================
  �÷���             ������Ÿ��         NULLABLE          PF/FK ����     DEFAULT VALUE
  --------------------------------------------------------------------------------------
  REMAIN_YEAR        CHAR(4)              N.N               FK
  REMAIN_PROD        VARCHAR2(10)         N.N               PK/FK
  REMAIN_J_00        NUMBER(5)                                                  0   
  REMAIN_I           NUMBER(5)                                                  0
  REMAIN_O           NUMBER(5)                                                  0
  REMAIN_J_99        NUMBER(5)                                                  0
  REMAIN_DATE         DATE
  
  CREATE TABLE REMAIN(
    REMAIN_YEAR        CHAR(4),           
    REMAIN_PROD        VARCHAR2(10),    
    REMAIN_J_00        NUMBER(5)  DEFAULT 0,
    REMAIN_I           NUMBER(5)  DEFAULT 0,
    REMAIN_O           NUMBER(5)  DEFAULT 0,
    REMAIN_J_99        NUMBER(5)  DEFAULT 0,
    REMAIN_DATE         DATE,
    
    CONSTRAINT pk_remain PRIMARY KEY (REMAIN_YEAR, REMAIN_PROD),
    CONSTRAINT fk_ramain FOREIGN KEY (REMAIN_PROD)
        REFERENCES PROD(PROD_ID));
   
** PROD ���̺��� ��ǰ��ȣ�� �������(PROD_PROPERSTOCK)�� 
   REMAIN ���̺��� REMAIN_PROD�� REMAIN_J_00(�������)�� �����Ͻÿ�. 
   �� REMAIN_YEAR�� '2005'�̰� ��¥�� '2005/01/01'�̴�.
   
   INSERT INTO REMAIN(REMAIN_YEAR, REMAIN_PROD, REMAIN_J_OO, REMAIN_J_99, REMAIN_DATE)
    SELECT '2005', PROD_ID, PROD_PROPERSTOCK, PROD_PROPERSTOCK, TO_DATE('20050101')
      FROM PROD;
  
  SELECT * FROM REMAIN;
  
  
  ��)2005�� 1�� ��ǰ�� ���Լ����� ��ȸ�ϰ� ���������̺��� UPDATE�Ͻÿ�.
    PROCEDURE ���
    
(��� ��ǰ�� ���Լ���)
SELECT PROD_ID, NVL(SUM(BUY_QTY), 0)
  FROM PROD
  LEFT OUTER JOIN BUYPROD ON(PROD_ID = BUY_PROD
   AND BUY_DATE BETWEEN '20050101' AND '20050131')
 GROUP BY PROD_ID
 ORDER BY 1;
    
    
(���ν��� ����)
CREATE OR REPLACE PROCEDURE  PROC_BUYPROD01(
    P_ID IN PROD.PROD_ID%TYPE,
    P_QTY IN NUMBER)
IS

BEGIN
    UPDATE REMAIN
       SET REMAIN_I = REMAIN_I + P_QTY,
           REMAIN_J_99 = REMAIN_J_99 + P_QTY,
           REMAIN_DATE = TO_DATE('20050131')
     WHERE REMAIN_PROD = P_ID
       AND REMAIN_YEAR = '2005';       
END;
            
(����)
DECLARE
    CURSOR CUR_BUYPROD01
    IS 
        SELECT PROD_ID, NVL(SUM(BUY_QTY), 0) AS AMT
          FROM PROD
          LEFT OUTER JOIN BUYPROD ON(PROD_ID = BUY_PROD
           AND BUY_DATE BETWEEN '20050101' AND '20050131')
         GROUP BY PROD_ID
         ORDER BY 1;
BEGIN
    FOR REC_BUYPROD IN CUR_BUYPROD01 LOOP
        PROC_BUYPROD01(REC_BUYPROD.PROD_ID, REC_BUYPROD.AMT);
    END LOOP;
END;

SELECT * FROM REMAIN;
  
  
��) �Ѹ��� ȸ�� ID�� �Է¹޾� ȸ���� �̸��� �ּ�, ���ϸ����� ����ϴ� ���ν��� �ۼ��Ͻÿ�.
CREATE OR REPLACE PROCEDURE PROC_MEM01 (
    P_ID MEMBER.MEM_ID%TYPE)
IS
    V_NAME MEMBER.MEM_NAME%TYPE;
    V_ADDR VARCHAR2(100);
    V_MILE MEMBER.MEM_MILEAGE%TYPE;
BEGIN
    SELECT MEM_NAME, MEM_ADD1 || ' ' || MEM_ADD2, MEM_MILEAGE INTO V_NAME, V_ADDR, V_MILE
      FROM MEMBER
     WHERE MEM_ID = P_ID;
     
     DBMS_OUTPUT.PUT_LINE('ȸ���� : ' || V_NAME);
     DBMS_OUTPUT.PUT_LINE('�ּ� : ' || V_ADDR);
     DBMS_OUTPUT.PUT_LINE('���ϸ��� : ' || V_MILE);
END;
  
(����) 
EXECUTE PROC_MEM01('a001',);


(�ι�° ���)  
CREATE OR REPLACE PROCEDURE PROC_MEM01 (
    P_ID IN MEMBER.MEM_ID%TYPE,
    P_NAME OUT MEMBER.MEM_NAME%TYPE,
    P_ADDR OUT VARCHAR2,
    P_MILE OUT MEMBER.MEM_MILEAGE%TYPE)
IS
BEGIN
    SELECT MEM_NAME, MEM_ADD1 || ' ' || MEM_ADD2, MEM_MILEAGE INTO P_NAME, P_ADDR, P_MILE
      FROM MEMBER
     WHERE MEM_ID = P_ID;
     
END;
  
(����) - �͸��� ���
DECLARE
    V_NAME MEMBER.MEM_NAME%TYPE;
    V_ADDR VARCHAR2(100);
    V_MILE MEMBER.MEM_MILEAGE%TYPE;
BEGIN
    PROC_MEM01('b001', V_NAME, V_ADDR, V_MILE);
    
    DBMS_OUTPUT.PUT_LINE('ȸ���� : ' || V_NAME);
    DBMS_OUTPUT.PUT_LINE('�ּ� : ' || V_ADDR);
    DBMS_OUTPUT.PUT_LINE('���ϸ��� : ' || V_MILE);
END;
  

 (���� : �������� �泲�� ȸ���� ���� ���ν��� ����) 
DECLARE
    V_NAME MEMBER.MEM_NAME%TYPE;
    V_ADDR VARCHAR2(100);
    V_MILE MEMBER.MEM_MILEAGE%TYPE;
    
    CURSOR CUR_MEM02
    IS
        SELECT MEM_ID FROM MEMBER
         WHERE MEM_ADD1 LIKE '�泲%';
BEGIN
    FOR RMEM IN CUR_MEM02 LOOP
     PROC_MEM01(RMEM.MEM_ID, V_NAME, V_ADDR, V_MILE);
    
     DBMS_OUTPUT.PUT_LINE('ȸ���� : ' || V_NAME);
     DBMS_OUTPUT.PUT_LINE('�ּ� : ' || V_ADDR);
     DBMS_OUTPUT.PUT_LINE('���ϸ��� : ' || V_MILE);
     DBMS_OUTPUT.PUT_LINE('---------------------------------------');
    END LOOP;
END;
  
-- CURSOR�� ���� ���� SELECT������ ��ȸ�� ���� �� ������� 2�� �̻��� ����̴�. (���ϴ� ���ǿ� ���� ������� �������϶�)

����] ��ǰ�ڵ�� ���� �Է� �޾� �ش��ǰ�� �԰������ �������� ��ȸ�Ͽ� ����ϴ� ���ν����� �ۼ��Ͻÿ�.
      (PROCEDURE �̸� : PROC_CB_INFO)

CREATE OR REPLACE PROCEDURE PROC_CB_INFO(
   P_CODE IN PROD.PROD_ID%TYPE,
   P_MONTH IN CHAR,
   P_OAMT OUT NUMBER,
   P_IAMT OUT NUMBER
   )
IS
    V_DATE DATE := TO_DATE('2005' || P_MONTH || '01');
BEGIN
    SELECT SUM(BUY_QTY) INTO P_IAMT
      FROM BUYPROD
     WHERE BUY_DATE BETWEEN V_DATE AND LAST_DAY(V_DATE)
       AND BUY_PROD = P_CODE;
       
    SELECT SUM(CART_QTY) INTO P_OAMT
      FROM CART
     WHERE SUBSTR(CART_NO, 1, 6) = SUBSTR(REPLACE(TO_CHAR(V_DATE), '/'), 1, 6)
       AND CART_PROD = P_CODE;
       
END;
       
  
  
DECLARE
    V_IAMT NUMBER := 0;
    V_OAMT NUMBER := 0;
    V_NAME PROD.PROD_NAME%TYPE;
BEGIN
    SELECT PROD_NAME INTO V_NAME
      FROM PROD
     WHERE PROD_ID = 'P101000006';
    
    PROC_CB_INFO('P101000006', '04', V_OAMT, V_IAMT);
    
    DBMS_OUTPUT.PUT_LINE('��ǰ�ڵ� : P101000006');
    DBMS_OUTPUT.PUT_LINE('��ǰ�� : ' || V_NAME);
    DBMS_OUTPUT.PUT_LINE('���� ���� : ' || V_OAMT);
    DBMS_OUTPUT.PUT_LINE('���� ���� : ' || V_IAMT);
END;  
  
  
  
  
  
  
  
  
  
  