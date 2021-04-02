2020-11-23-01 ������
 4. ��Ÿ������
  1)IN ������
   - ���� Ž���� ���� 2�� �̻��� ǥ����(�׸�)�� ����
   - OR �����ڷ� ��ȯ ����
   (�������)
   �÷��� IN(ǥ����1, ǥ����2,....)
   . '�÷���'�� ����� ���� ( )�ȿ� ����� ǥ���� �� ��� �ϳ��� ��ġ�ϸ� ��ü ������ ���̴�.

��)��� ���̺��� �μ��ڵ尡 20,60,80�� �μ��� ���� ����� �˻��Ͻÿ�.
   Alias�� �����ȣ, �����, �μ��ڵ�, �޿�
   
(IN �����ڸ� ������� �ʴ� ���)
SELECT EMPLOYEE_ID AS �����ȣ,
       EMP_NAME AS �����,
       DEPARTMENT_ID AS �μ��ڵ�, 
       SALARY AS �޿�
    FROM EMPLOYEES
    WHERE DEPARTMENT_ID=20 OR DEPARTMENT_ID=60 OR DEPARTMENT_ID=80
    ORDER BY DEPARTMENT_ID ASC --(ASC�� ��������)
    
(IN �����ڸ� ����ϴ� ���)
SELECT EMPLOYEE_ID AS �����ȣ,
       EMP_NAME AS �����,
       DEPARTMENT_ID AS �μ��ڵ�, 
       SALARY AS �޿�
    FROM EMPLOYEES
    WHERE DEPARTMENT_ID IN (20,60,80)
    ORDER BY 3;
    
  2)SOME, ANY ������
   - �⺻ ����� IN �����ڿ� ����
   (�������)
   �÷��� ���迬����ANY|SOME (ǥ����1, ǥ����2,...)
   . IN �����ڴ� ���ϼ��� �Ǵ�(=ANY, =SOME)
   . ANY, SOME�� ũ�� �񱳵� ����
��) - ANY ������ ���
SELECT EMPLOYEE_ID AS �����ȣ,
       EMP_NAME AS �����,
       DEPARTMENT_ID AS �μ��ڵ�, 
       SALARY AS �޿�
    FROM EMPLOYEES
    WHERE DEPARTMENT_ID =SOME (20,60,80)
    ORDER BY 3;
    
��) ȸ�����̺��� ������ �������� ȸ���� ���� ���ϸ������� �� ���� ���ϸ����� ������ ȸ���� ��ȸ�Ͻÿ�.
    Alias ȸ����ȣ, ȸ����, ����, ���ϸ���
SELECT MEM_NAME, MEM_MILEAGE
    FROM MEMBER
    WHERE MEM_JOB = '������'
    ORDER BY MEM_MILEAGE DESC;
    
SELECT MEM_ID AS ȸ����ȣ, 
        MEM_NAME AS ȸ����, 
        MEM_JOB AS ����, 
        MEM_MILEAGE AS ���ϸ���
    FROM MEMBER
    WHERE MEM_MILEAGE >ANY (SELECT MAX(MEM_MILEAGE)
                            FROM MEMBER
                            WHERE MEM_JOB = '������')
                            
����) ������̺��� �μ���ȣ�� 30,50,80�μ��� ������ ���� ����� ��ȸ�Ͻÿ�.
    Alias�� �����ȣ, �����, �μ���ȣ, �Ի���
SELECT EMPLOYEE_ID AS �����ȣ, 
       EMP_NAME AS �����, 
       DEPARTMENT_ID AS �μ���ȣ, 
       HIRE_DATE AS �Ի���
    FROM EMPLOYEES
    WHERE NOT DEPARTMENT_ID =ANY (30,50,80)
    --WHERE DEPARTMENT_ID NOT IN (30,50,80) �̶�� �ص� ��. NOT ��ġ �� ����!
    ORDER BY 3;
    
    
����) ȸ�����̺��� �����ڷ��� �ֹι�ȣ�� �����Ͻÿ�.
    ȸ����ȣ : 'e001', �ֹι�ȣ : '750501-2406017' => '010501-4406017'
    ȸ����ȣ : 'n001', �ֹι�ȣ : '750323-1011014' => '000323-3011014'
UPDATE MEMBER
    SET MEM_REGNO1 = '010501',
        MEM_REGNO2 = '4406017'
    WHERE MEM_ID = 'e001';
UPDATE MEMBER
    SET MEM_REGNO1 = '000323',
        MEM_REGNO2 = '3011014'
    WHERE MEM_ID = 'n001';
SELECT MEM_NAME, MEM_REGNO1||'-'||MEM_REGNO2
    FROM MEMBER
    WHERE MEM_ID = 'e001' OR MEM_ID = 'n001';
    
COMMIT;

����) ȸ�����̺��� ����ȸ���̰� ���ϸ����� 3000�̻��� ȸ�������� ��ȸ�Ͻÿ�.
    Alias�� ȸ����ȣ, ȸ����, �ֹι�ȣ, ����, ���ϸ���
SELECT MEM_ID AS ȸ����ȣ, 
       MEM_NAME AS ȸ����, 
       MEM_REGNO1||'-'||MEM_REGNO2 AS �ֹι�ȣ, 
       MEM_JOB AS ����, 
       MEM_MILEAGE AS ���ϸ���
    FROM MEMBER
    WHERE SUBSTR(MEM_REGNO2,1,1) IN (2,4) 
      AND MEM_MILEAGE >= 3000;
      
��) ��ǰ���̺�(PROD)���� �з��ڵ�(PROD_LGU)�� 'P102'�̰� �ǸŰ���(PROD_PRICE)�� 10���� �̻��� ��ǰ�� ��ȸ�Ͻÿ�.
    Alias�� ��ǰ�ڵ�, ��ǰ��, �з��ڵ�,�ǸŰ���
SELECT PROD_ID AS ��ǰ�ڵ�, 
       PROD_NAME AS ��ǰ��, 
       PROD_LGU AS �з��ڵ�,
       PROD_PRICE AS �ǸŰ���
    FROM PROD
    WHERE PROD_LGU IN 'P102' AND PROD_PRICE >= 100000;
    
��)��ٱ��� ���̺�(CART)���� 2005�� 7�� 1��~7�� 15�� �Ǹ������� ��ȸ�Ͻÿ�.
    Alias�� ��ٱ��� ��ȣ,ȸ����ȣ,����
    ��, ���ż����� ���� ������� ���
SELECT CART_NO AS ��ٱ��Ϲ�ȣ,
       CART_MEMBER AS ȸ����ȣ,
       CART_QTY AS ����
    FROM CART
    WHERE SUBSTR(CART_NO,1,8) >= '20050701' AND SUBSTR(CART_NO,1,8) <= '20050715'
    ORDER BY 3 DESC;
    
    
��)��� ���̺��� ����� �ټ� ����� ���ϰ� �ټ� ����� 15�� �̻��� ȸ���鿡�� ���ʽ��� �����Ϸ��Ѵ�. �̸� ��ȸ�Ͻÿ�. -- ��¥�����ʹ� ���ڵ�����ó�� SUBSTR�� ����Ͽ� �ڸ� �� ����. ������ �������
   ���ʽ� : �޿��� 15%
   ���޾� : �޿�+���ʽ�
   Alias�� �����ȣ, �����, �Ի���, �ټӳ��, ���ʽ�,�޿�,���޾��̴�.
SELECT EMPLOYEE_ID AS �����ȣ, 
       EMP_NAME AS �����, 
       HIRE_DATE AS �Ի���, 
       EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) AS �ټӳ��, 
       SALARY * 0.15 AS ���ʽ�,
       SALARY AS �޿�,
       SALARY * 0.15 + SALARY AS ���޾�
    FROM EMPLOYEES
    WHERE EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) >=15;

��)ȸ�� ���̺��� ȸ������ ���̸� �ֹι�ȣ�� �������� ����Ͻÿ�.
    Alias�� ȸ����ȣ, ȸ����, �ֹι�ȣ, ����
    
SELECT  MEM_ID AS ȸ����ȣ,
        MEM_NAME AS ȸ����, 
        MEM_REGNO1||'-'||MEM_REGNO2 AS �ֹι�ȣ,
        CASE WHEN SUBSTR(MEM_REGNO2,1,1)='1' OR SUBSTR(MEM_REGNO2,1,1)='2' THEN
             EXTRACT(YEAR FROM SYSDATE)-(1900+TO_NUMBER(SUBSTR(MEM_REGNO1,1,2)))
        ELSE EXTRACT(YEAR FROM SYSDATE)-(2000+TO_NUMBER(SUBSTR(MEM_REGNO1,1,2)))       
        END AS ����
    FROM MEMBER;


����) �������̺�(BUYPROD)���� 2005�� 1�� �����ڷḦ ��ȸ�Ͻÿ�.
    Alias�� ������, ���Ի�ǰ�ڵ�, ���Լ���, ���Դܰ�, ���Աݾ� �̴�.
    
SELECT BUY_DATE AS ������, 
       BUY_PROD AS "���Ի�ǰ �ڵ�", 
       BUY_QTY AS ���Լ���, 
       BUY_COST AS ���Դܰ�, 
       BUY_QTY * BUY_COST AS ���Աݾ�
    FROM BUYPROD
    WHERE BUY_DATE >= '20050101' AND BUY_DATE <= '20050131';