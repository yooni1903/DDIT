2020-12-01-02) NULL ó���Լ�
 - ���꿡 ���Ǵ� �׸� �� NULL�� �����ϴ� ��� ����� NULL�� ��ȯ
 - IS [NOT] NULL, NVL, NVL2, NULLIF ���� �Լ�(������)�� ���� ��
  1. IS [NOT] NULL
   - NULL �� ���θ� ���ϱ� ���Ͽ� '=' ����� ������ ����
   - �ݵ�� IS [NOT] NULL �����ڸ� ����ؾ� ��
��) ������̺��� �������� �ڵ� (COMMISSION_PCT)�� NULL�� �ƴ� ����� ��ȸ�Ͻÿ�.
    Alias�� �����ȣ, �����, �μ�, ��������
    SELECT EMPLOYEE_ID AS �����ȣ, 
              EMP_NAME AS �����, 
              DEPARTMENT_ID AS �μ�, 
              COMMISSION_PCT AS ��������
      FROM EMPLOYEES
      WHERE COMMISSION_PCT IS NOT NULL;
      
 2. NVL(c, val)
  - 'c'�� ���� NULL�̸� 'val'�� ��ȯ�ϰ� NULL�� �ƴϸ� 'c'�� ���� ��ȯ
  - ���꿡 ���Ǵ� ���� �׸��� ���� NULL�� ������ ������ �ִ� ��� ���
  
  ��) CREATE TABLE TEMP(
            COL1 NUMBER(6),
            COL2 VARCHAR2(20) NOT NULL,
            COL3 DATE);
        
        INSERT INTO TEMP(COL2) VALUES('������');
        INSERT INTO TEMP VALUES(10,  '�߱�', SYSDATE);
        
  ��)������̺��� ���������� ���� ���ʽ��� ��ȸ�Ͻÿ�
     Alias�� �����ȣ, �����, ��������, ���ʽ�
    
 (NVL�� ������� ���� ���)
 SELECT EMPLOYEE_ID AS �����ȣ,
        EMP_NAME AS �����, 
        COMMISSION_PCT AS ��������, 
        COMMISSION_PCT*SALARY AS ���ʽ�
   FROM EMPLOYEES;
 (NVL�� ����� ���)
 SELECT EMPLOYEE_ID AS �����ȣ,
        EMP_NAME AS �����, 
        COMMISSION_PCT AS ��������, 
        NVL(COMMISSION_PCT*SALARY,0) AS ���ʽ�
   FROM EMPLOYEES;
   
   
**ȸ�����̺��� ȸ����ȣ 'r001', 'd001', 'k001' ȸ���� ���ϸ����� NULL�� �ٲٽÿ�.
UPDATE MEMBER
   SET MEM_MILEAGE = NULL
 WHERE LOWER(MEM_ID) IN ('r001', 'd001', 'k001');
 
 ����) ȸ���鿡�� 500���ϸ����� �߰��� �����Ϸ��Ѵ�
      ȸ����ȣ, ȸ����, �������ϸ���, ���渶�ϸ����� ���
      SELECT MEM_ID AS ȸ����ȣ,
             MEM_NAME AS ȸ����,
             MEM_MILEAGE AS �������ϸ���,
             NVL(MEM_MILEAGE,0) + 500 AS ���渶�ϸ���
        FROM MEMBER;
 ����)�������̺��� 2005�� 3���� ���(OUTER�����̱���) ��ǰ�� ������Ȳ�� ��ȸ�Ͻÿ�.
    Alias�� ��ǰ�ڵ�, ��ǰ��, ���Լ���, ���Աݾ�
    SELECT B.PROD_ID AS ��ǰ�ڵ�, 
           B.PROD_NAME AS ��ǰ��, 
           SUM(A.BUY_QTY) AS ���Լ���, 
           NVL(SUM(A.BUY_QTY*A.BUY_COST),0) AS ���Աݾ�
      FROM BUYPROD A
      RIGHT OUTER JOIN PROD B ON (A.BUY_PROD = B.PROD_ID
        AND A.BUY_DATE BETWEEN '20050301' AND '20050331')
        GROUP BY B.PROD_ID, B.PROD_NAME
        ORDER BY 1;

��)ȸ�����̺��� ���ϸ����� NULL�� ȸ���� ��ȸ�Ͽ� ����� 'Ż��ȸ��'�� NULL�� �ƴ� ȸ���� '����ȸ��'�� ����Ͻÿ�
    Alias�� ȸ����ȣ, ȸ����, ���ϸ���, ���
    SELECT MEM_ID AS ȸ����ȣ, 
              MEM_NAME AS ȸ����, 
              MEM_MILEAGE AS ���ϸ���, 
              NVL2(TO_CHAR(MEM_MILEAGE),  '����ȸ��', 'Ż��ȸ��') AS ���
      FROM MEMBER;  

3. NVL2(c, val1, val2)
 - 'c'�� ���� NULL�̸� val2�� ��ȯ�ϰ�, NULL�� �ƴϸ� val1�� ��ȯ��
 - ���꿡 ���Ǵ� ���� �׸��� ���� NULL�� ������ ������ �ִ� ��� ���
 - 'val1'�� 'val2'�� ���� ������ Ÿ���̾����
 - NVL�� ���� ����� �� ����
 
 4. NULLIF(c1, c2)
  - 'c1'�� 'c2'�� ������ NULL, �ٸ� ���̸� c1�� ��ȯ
 
 **��ǰ���̺��� �з��ڵ尡 'P201'�� ��ǰ�� �����ǸŰ����� ���԰������� �����Ͻÿ�.
 UPDATE PROD
       SET PROD_SALE = PROD_SALE
  WHERE UPPER(PROD_LGU) = 'P201';
 
 ��) ��ǰ���̺��� ���԰��ݰ� �����Ǹ� ������ ���� ��ǰ�� '����ó����ǰ', ���� �ٸ��� '�����ǰ'�� ������ ����Ͻÿ�.
     Alias�� ��ǰ�ڵ�, ��ǰ��, ���԰���, �����ǸŰ���, ���(NVL2, NULLIF ���)
 SELECT PROD_ID AS ��ǰ�ڵ�, 
            PROD_NAME AS ��ǰ��, 
            PROD_COST AS ���԰���, 
            PROD_SALE AS �����ǸŰ���, 
            NVL2(NULLIF(PROD_COST, PROD_SALE), '�����ǰ', '����ó����ǰ') AS ���
    FROM PROD;
    
    
    
    