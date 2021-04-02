2020-12-03-01
- ���������� ���������� �������� �ʴ� ��(ROW)�� �˻����� ����
- �ܺ������� ������ ���̺� NULL������ ä���� ���� �����Ͽ� ���� ����
- �������� ����� ������ ���̺� ���Ե� �÷��ڿ� �ܺ����� ������ '(+)'�� ��� : �Ϲ� �ܺ�����
- �ϳ� �̻��� ���������� �ܺ����εǴ� ��� ��� ���� ���ǿ� '(+)'������ ���
- �ϳ��� ���̺��� ���ÿ� Ȯ��Ǵ� �ܺ������� ������ ���� 
  ��, A,B,C ���̺��� �ܺ������ϴ� ���, A�� �������� B�� �ܺ������ϴ� ���ÿ� C�� �������� B�� �ܺ��������� ����
  ex) WHERE A.Col = B.col(+)
        AND C.Col = B.Col(+) -- ��� �ȵ�
- �Ϲ������� �߰��� �ܺ������� �Ϲݿܺ������� ����ϸ� �������� ����� ��ȯ --> ANSI �ܺ������̳� subquery�� �̿��ؾ� ��

(ANSI �ܺ����� �������)
SELECT �÷�list
  FROM ���̺��1 [��Ī]
  FULL|RIGHT|LEFT OUTER JOIN ���̺��2[��Ī] ON(�������� [AND �Ϲ�����])
  [FULL|RIGHT|LEFT OUTER JOIN ���̺��3[��Ī] ON(�������� [AND �Ϲ�����])]
  [WHERE �Ϲ�����]
  .FULL : ���� ���̺� ��� Ȯ��
  .RIGHT : ���̺��2�� ���̺��1���� �� ���� ������ �ڷᰡ �ִ� ���(���̺��1�� Ȯ��Ǵ� ���)
  .LEFT : ���̺��1�� ���̺��2���� �� ���� ������ �ڷᰡ �ִ� ���(���̺��2�� Ȯ��Ǵ� ���)
  .�Ϲ�����1, �Ϲ�����2�� �ش� ���̺�鿡�� ����Ǵ� ����
  .WHERE ���� �Ϲ������� ��ü�� ����� ����
  
��)��� ȸ���鿡 ���� �������踦 ��ȸ�Ͻÿ�
   Alias�� ȸ����ȣ, ȸ����, ������հ�
(�Ϲ�����)
SELECT A.CART_MEMBER AS ȸ����ȣ, 
       B.MEM_NAME AS ȸ����, 
       NVL(SUM(A.CART_QTY*C.PROD_PRICE),0) AS ������հ�
  FROM CART A, MEMBER B, PROD C
 WHERE B.MEM_ID = A.CART_MEMBER(+)
   AND A.CART_PROD = C.PROD_ID(+)
 GROUP BY A.CART_MEMBER, B.MEM_NAME
 ORDER BY 1;
 
 (ADSI ����)
SELECT A.CART_MEMBER AS ȸ����ȣ, 
       B.MEM_NAME AS ȸ����, 
       NVL(SUM(A.CART_QTY*C.PROD_PRICE),0) AS ������հ�
  FROM CART A
  RIGHT OUTER JOIN MEMBER B ON(B.MEM_ID = A.CART_MEMBER)
  LEFT OUTER JOIN PROD C ON(A.CART_PROD = C.PROD_ID)
 GROUP BY A.CART_MEMBER, B.MEM_NAME
 ORDER BY 1;

��)2005�� 2�� ��� ��ǰ�� �������踦 ��ȸ�Ͻÿ�.
    Alias�� ��ǰ�ڵ�, ��ǰ��, ���Լ���, ���Աݾ�
    
(�Ϲ�����)
SELECT A.BUY_PROD AS ��ǰ�ڵ�, 
       B.PROD_NAME AS ��ǰ��, 
       SUM(A.BUY_QTY) AS ���Լ���, 
       SUM(A.BUY_QTY*A.BUY_COST) AS ���Աݾ�
  FROM BUYPROD A, PROD B
 WHERE A.BUY_PROD(+) = B.PROD_ID
 GROUP BY A.BUY_PROD, B.PROD_NAME
 ORDER BY 1;
 
 (ANDSI ����)
SELECT B.PROD_ID AS ��ǰ�ڵ�, 
       B.PROD_NAME AS ��ǰ��, 
       NVL(SUM(A.BUY_QTY),0) AS ���Լ���, 
       NVL(SUM(A.BUY_QTY*A.BUY_COST),0) AS ���Աݾ�
  FROM BUYPROD A
  RIGHT OUTER JOIN PROD B ON(A.BUY_PROD = B.PROD_ID
    AND A.BUY_DATE BETWEEN '20050201' AND '20050228')
 GROUP BY B.PROD_ID, B.PROD_NAME
 ORDER BY 1;
    
(subquery)
SELECT B.PROD_ID AS ��ǰ�ڵ�, 
       B.PROD_NAME AS ��ǰ��, 
       NVL(A.CNT,0) AS ���Լ���, 
       NVL(A.AMT,0) AS ���Աݾ�
  FROM (SELECT BUY_PROD AS BID,
               NVL(SUM(BUY_QTY),0) AS CNT, 
               NVL(SUM(BUY_QTY*BUY_COST),0) AS AMT
          FROM BUYPROD
          WHERE BUY_DATE BETWEEN '20050201' AND '20050228'
          GROUP BY BUY_PROD) A, PROD B
  WHERE A.BID(+) = B.PROD_ID
  ORDER BY 1;



(4���� �Ǹŵ� ��ǰ�� ����)
SELECT COUNT(DISTINCT BUY_PROD)
  FROM BUYPROD
 WHERE BUY_DATE BETWEEN '20050201' AND '20050228';
 
 
 
����) ��� �з��� ��ǰ�� ���� ��ȸ�Ͻÿ�.
    Alias�� ��ǰ�з��ڵ�, �з���, ��ǰ�Ǽ�
SELECT COUNT(*) FROM LPROD;
SELECT COUNT(DISTINCT PROD_LGU) FROM PROD;

(ANSI ����)    
SELECT B.LPROD_ID AS ��ǰ�з��ڵ�, 
       B.LPROD_NM AS �з���, 
       COUNT(A.PROD_ID) AS ��ǰ�Ǽ�
  FROM PROD A
  RIGHT OUTER JOIN LPROD B ON(A.PROD_LGU = B.LPROD_GU)
 GROUP BY B.LPROD_ID, B.LPROD_NM
 ORDER BY 1;


(�Ϲ� ����)
SELECT B.LPROD_ID AS ��ǰ�з��ڵ�, 
       B.LPROD_NM AS �з���, 
       COUNT(A.PROD_ID) AS ��ǰ�Ǽ�
  FROM PROD A , LPROD B
 WHERE A.PROD_LGU(+) = B.LPROD_GU
 GROUP BY B.LPROD_ID, B.LPROD_NM
 ORDER BY 1;

����) ������̺��� ��� �μ��� ��ձ޿��� ����Ͻÿ�.
      ��ձ޿��� �Ҽ��� ���� ����ϰ�, �μ��ڵ�, �μ���, ��ձ޿��� ����� ��
SELECT COUNT(DEPARTMENT_ID) FROM DEPARTMENTS;
SELECT COUNT(DISTINCT DEPARTMENT_ID) FROM EMPLOYEES;

(ANSI ����)
SELECT A.DEPARTMENT_ID AS �μ��ڵ�, 
       NVL(A.DEPARTMENT_NAME, '����') AS �μ���, 
       NVL(ROUND(AVG(B.SALARY)),0) AS ��ձ޿�
  FROM DEPARTMENTS A
  FULL OUTER JOIN EMPLOYEES B ON(A.DEPARTMENT_ID = B.DEPARTMENT_ID)
 GROUP BY A.DEPARTMENT_ID, A.DEPARTMENT_NAME
 ORDER BY 1;
  
(�Ϲ� ���� - FULL OUTER JOIN�� �Ϲ��������� ǥ���� ���� ����.)
SELECT A.DEPARTMENT_ID AS �μ��ڵ�,
       A.DEPARTMENT_NAME AS �μ���,
       NVL(ROUND(AVG(B.SALARY)),0) AS ��ձ޿�
  FROM DEPARTMENTS A, EMPLOYEES B
 WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID(+)
 GROUP BY A.DEPARTMENT_ID, A.DEPARTMENT_NAME
 ORDER BY 1;
 
 
����) 2005�� 6�� ��� ��ǰ�� ����� ��Ȳ�� ��ȸ�Ͻÿ�.
      Alias�� ��ǰ�ڵ�, ��ǰ��, �԰����, ���Ծ�, ������, �����

SELECT C.PROD_ID AS ��ǰ�ڵ�, 
       C.PROD_NAME AS ��ǰ��, 
       NVL(SUM(A.BUY_QTY),0) AS �԰����, 
       NVL(SUM(A.BUY_QTY*C.PROD_COST),0) AS ���Ծ�, 
       NVL(SUM(B.CART_QTY),0) AS ������, 
       NVL(SUM(B.CART_QTY*C.PROD_PRICE),0) AS �����
  FROM BUYPROD A
 RIGHT OUTER JOIN PROD C ON(A.BUY_PROD=C.PROD_ID 
   AND A.BUY_DATE BETWEEN '20050601' AND '20050630')
  LEFT OUTER JOIN CART B ON(B.CART_PROD=C.PROD_ID
   AND SUBSTR(B.CART_NO,1,6) = '200506')
 GROUP BY C.PROD_ID, C.PROD_NAME
 ORDER BY 1;
