2020-11-30-01
2. AVG(column)
 - 'column'�� �������� �׷����� ���� ���ܿ� ���� ��հ� ��ȯ
 ��)������̺��� �� �μ��� ��� �޿��� �Ҽ� 1�ڸ����� ���Ͻÿ�.
 SELECT DEPARTMENT_ID AS �μ�, 
        ROUND(AVG(SALARY),1) AS ��ձ޿�
   FROM EMPLOYEES
   GROUP BY DEPARTMENT_ID
   ORDER BY 1;
   
��)��ǰ���̺��� ��ǰ�з��� ��ո��԰��� ���Ͻÿ�.
SELECT PROD_LGU AS ��ǰ, 
       ROUND(AVG(PROD_COST),-1) AS ��ո��԰�
  FROM PROD
  GROUP BY PROD_LGU
  ORDER BY 1;

����) 2005�� ���� ��ǰ�� ��ո��Լ����� ���Աݾ��հ踦 ���Ͻÿ�.
SELECT EXTRACT(MONTH FROM BUY_DATE)||'��' AS ��, 
       BUY_PROD AS ��ǰ, 
       ROUND(AVG(BUY_QTY)) AS ��ո��Լ���,
       SUM(BUY_COST*BUY_QTY) AS ���Աݾ��հ�
  FROM BUYPROD
 WHERE EXTRACT(YEAR FROM BUY_DATE) = 2005
 GROUP BY EXTRACT(MONTH FROM BUY_DATE), BUY_PROD
 ORDER BY 1,2;
 
 ����)2005�� 5�� ���ں� ����Ǹ� ������ ���Ͻÿ�.
 SELECT TO_DATE(SUBSTR(CART_NO,1,8)) AS ����, 
        ROUND(AVG(CART_QTY)) AS ����Ǹż���
   FROM CART
  WHERE CART_NO LIKE '200505%'
  GROUP BY TO_DATE(SUBSTR(CART_NO,1,8))
  ORDER BY 1;
  
����) ������̺��� �� �μ��� ��ձ޿����� ���� �޿��� �޴� ��������� ����Ͻÿ�.
     Alias�� �����ȣ, �����, �μ��ڵ�, �μ���, ��ձ޿�
     
(�� �μ��� ��ձ޿�)
SELECT DEPARTMENT_ID AS DID, -- AS�� �������� ���شٸ� ������ �����ϴ�
       AVG(SALARY) AS ASAL
  FROM EMPLOYEES
GROUP BY DEPARTMENT_ID;

SELECT A.EMPLOYEE_ID AS �����ȣ, 
       A.EMP_NAME AS �����, 
       A.SALARY AS �޿�,
       A.DEPARTMENT_ID AS �μ��ڵ�, 
       B.DEPARTMENT_NAME AS �μ���, 
       ROUND(C.ASAL) AS ��ձ޿�
  FROM EMPLOYEES A, DEPARTMENTS B,
        (SELECT DEPARTMENT_ID AS DID,
                AVG(SALARY) AS ASAL
           FROM EMPLOYEES
       GROUP BY DEPARTMENT_ID) C
 WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
   AND A.DEPARTMENT_ID = C.DID
   AND A.SALARY>=C.ASAL
   ORDER BY 4,3;
  
3.COUNT(*|column)
 - �׷����� ���� �� �׷쿡 ���Ե� �ڷ� ��(���� ��)
 - �ܺ�����(�ƿ�������)�� COUNT�Լ��� ����� ��� '*' ��� �÷����� ����ؾ��Ѵ�.
 
 ��)������̺��� �� �μ��� �ο����� ���Ͻÿ�.
 SELECT DEPARTMENT_ID AS �μ�, 
        COUNT(*) AS �ο���,
        COUNT(EMP_NAME) AS �����
   FROM EMPLOYEES
   GROUP BY DEPARTMENT_ID
   ORDER BY 1;
   
��) 2005�� 6�� ��ǰ�� �ǸŰǼ��� ��ȸ�Ͻÿ�.
SELECT CART_PROD AS ��ǰ�ڵ�,
       COUNT(*) AS �ǸŰǼ�
  FROM CART
  WHERE CART_NO LIKE '200506%'
  GROUP BY CART_PROD
  ORDER BY 1;
  
��)2005�� 6�� ��� ��ǰ�� �ǸŰǼ��� ��ȸ�Ͻÿ�.
    Alias�� ��ǰ�ڵ�,��ǰ��, �ǸŰǼ�
   SELECT PROD_ID AS ��ǰ�ڵ�,
        PROD_NAME AS ��ǰ��,
       COUNT(CART_MEMBER) AS �ǸŰǼ�
  FROM CART
  RIGHT OUTER JOIN PROD ON(CART_PROD=PROD_ID
        AND CART_NO LIKE '200506')
  GROUP BY PROD_ID, PROD_NAME
  ORDER BY 1;

����)��ǰ���̺��� �� �з��� ��ǰ�� ���� ��ȸ�Ͻÿ�.
SELECT PROD_LGU AS ��ǰ�з�, 
       COUNT(*) AS "��ǰ�� ��"
  FROM PROD
  GROUP BY PROD_LGU
  ORDER BY 1;

����)ȸ�����̺��� �� ���ɴ뺰 ȸ�� ���� ��ȸ�Ͻÿ�.
SELECT TRUNC(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM MEM_BIR),-1)||'��' AS ����, 
       COUNT(*) AS ȸ����
  FROM MEMBER
 GROUP BY TRUNC(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM MEM_BIR),-1)
 ORDER BY 1;
 
����)ȸ�����̺��� ���� ������ ȸ������ ���Ͻÿ�.
SELECT MEM_JOB AS ����,
       COUNT(*) AS "������ ȸ����",
  FROM MEMBER
 GROUP BY MEM_JOB
 ORDER BY 1;
     
����) ȸ�����̺��� ������ ������ ����Ͻÿ�.
SELECT DISTINCT MEM_JOB AS "���� ����" --DISTINCT ���� ���� �ߺ��Ǹ� �ּ����� �� ���� ���
  FROM MEMBER
  GROUP BY MEM_JOB;
  
4. MAX(column), MIN(column)
 - 'column'�÷��� ����� �� �� �ִ밪�� �ּҰ��� ���Ͽ� ��ȯ
 - ���������� ����ϴ� ����� 'column'�� �������� �������� ����(MIN),�Ǵ� �������� ���� �� �� �� ù��° ���� ���� ��ȯ
   ���� ó���ð��� �ټ� ���� �ҿ�ȴ�.
** �ǻ��÷� ROWNUM
 - ���� ���(��)�� �� �࿡ �ο��� ���� ��
 - ���� 5�� �Ǵ� ���� 5�� �� �ʿ��� ������ ������� ����� �� ����Ѵ�.(�ٸ� DBMS������ TOP �Լ��� ������)
 
 ��)ȸ���� ���ϸ��� �� �ִ� ���ϸ��� ���� ���Ͻÿ�.
 SELECT MAX(MEM_MILEAGE) AS "�ִ� ���ϸ���"
   FROM MEMBER;
   
 SELECT A.MILE
   FROM (SELECT MEM_MILEAGE AS MILE
            FROM MEMBER 
            ORDER BY 1 DESC) A
   WHERE ROWNUM = 1;
   
��) ��� ���̺��� �μ��� �ִ�޿��� �ּұ޿��� ��ȸ�Ͻÿ�.
SELECT DEPARTMENT_ID AS �μ�, 
       MAX(SALARY) AS �ִ�޿�, 
       MIN(SALARY) AS �ּұ޿�
  FROM EMPLOYEES
  GROUP BY DEPARTMENT_ID
  ORDER BY 1;
  