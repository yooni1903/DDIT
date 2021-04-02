2020-12-04-01 ��������
 - ���� �ȿ� ���Ե� �� �ٸ� ������ ����������� ��
 - ���������� '( )'�ȿ� ���
 - �� ������ ���� ���� �����
 - �����ڿ� ���� ���� ���
 - �����ڿ� ���� ���� ��� ������ �����ʿ� ����ؾ� ��
 (���������� ����)
 - ���� ������ ������ ���ο� ���� : ������ �ִ� ��������(Correlated Subquery)�� �񿬰��� ��������(Noncorrelated Subquery)�� ����
 - ���Ǵ� ��ġ�� ���� : �Ϲݼ�������(Select ���� ���), �ζ��� ��������(From ���� ���), ��ø��������(Where���� ���)�� ����
 - ��ȯ�ϴ� ��/���� ���� ���� : ������/���Ͽ�, ������/���Ͽ�, ������/���߿� ������ ���еǸ� �̴� ����ϴ� �����ڿ� ���� ������
 
 1.������ ���� ��������
 - ���������� �������� ���̿� ������ �߻����� �ʴ� ��������
 ��) ������̺��� ��ձ޿����� ���� �޿��� �޴� ������� ��ȸ
    Alias�� �����ȣ �����, �μ��ڵ�, �޿�
( �������� : �����ȣ �����, �μ��ڵ�, �޿�)
 SELECT EMPLOYEE_ID AS �����ȣ,
        EMP_NAME AS �����, 
        DEPARTMENT_ID AS �μ��ڵ�, 
        SALARY AS �޿�
   FROM EMPLOYEES
  WHERE SALARY >= (��ձ޿�);

(�������� : ��ձ޿�)
SELECT ROUND(AVG(SALARY))
  FROM EMPLOYEES;
  
(����)
 SELECT EMPLOYEE_ID AS �����ȣ,
        EMP_NAME AS �����, 
        DEPARTMENT_ID AS �μ��ڵ�, 
        SALARY AS �޿�
   FROM EMPLOYEES
  WHERE SALARY >= (SELECT ROUND(AVG(SALARY))
                    FROM EMPLOYEES);
                    
            
��)�θ�μ��ڵ�(PARENTS_ID)�� NULL�� �μ��� �Ҽӵ� ��������� ��ȸ�Ͻÿ�
    Alias �����ȣ, �����, �μ��ڵ�, ��å�ڵ�(JOB_ID)
(�������� : ������̺��� ��������� ��ȸ)
SELECT EMPLOYEE_ID AS �����ȣ, 
       EMP_NAME AS �����, 
       DEPARTMENT_ID AS �μ��ڵ�, 
       JOB_ID AS ��å�ڵ�
  FROM EMPLOYEES 
 WHERE DEPARTMENT_ID =(�θ�μ��ڵ尡 NULL�� �μ��ڵ�);
 
 (�������� : �μ����̺��� NULL�� �ƴ� �ڵ�)
 �μ����̺��� �θ�����ڵ�(PARENT_ID)���� NULL
 SELECT DEPARTMENT_ID
   FROM DAPARTMENTS
  WHERE PARENT_ID IS NULL;
   
   **�μ����̺��� �μ��ڵ� 60(IT)�� �θ�μ��ڵ带 NULL�� �ٲٽÿ�.
   UPDATE DEPARTMENTS
      SET PARENT_ID = NULL
    WHERE DEPARTMENT_ID =(60);
    
(����)
SELECT EMPLOYEE_ID AS �����ȣ, 
       EMP_NAME AS �����, 
       DEPARTMENT_ID AS �μ��ڵ�, 
       JOB_ID AS ��å�ڵ�
  FROM EMPLOYEES 
 WHERE DEPARTMENT_ID IN (SELECT DEPARTMENT_ID
                          FROM DEPARTMENTS
                          WHERE PARENT_ID IS NULL);

(�������ִ� ��������)
SELECT A.EMPLOYEE_ID AS �����ȣ, 
       A.EMP_NAME AS �����, 
       A.DEPARTMENT_ID AS �μ��ڵ�, 
       A.JOB_ID AS ��å�ڵ�
  FROM EMPLOYEES A
 WHERE EXISTS (SELECT DEPARTMENT_ID
                          FROM DEPARTMENTS B
                          WHERE PARENT_ID IS NULL
                            AND A.DEPARTMENT_ID = B.DEPARTMENT_ID);

2.������ ��������
 - ���������� �������� ���̿� ������ �߻��Ǵ� ��������
 
��) �����̷����̺�(JOB_HISTORY)�� ��������� ��ȸ�Ͻÿ�.
    Alias�� �����ȣ, �����, �μ��ڵ�, �μ����̴�.(Subquery ���)
(��������)
SELECT A.EMPLOYEE_ID AS �����ȣ, 
       (SELECT B.EMP_NAME
          FROM EMPLOYEES B
         WHERE A.EMPLOYEE_ID = B.EMPLOYEE_ID) AS �����, 
        A.DEPARTMENT_ID AS  �μ��ڵ�, 
        (SELECT C.DEPARTMENT_NAME
           FROM DEPARTMENTS C
          WHERE A.DEPARTMENT_ID = C.DEPARTMENT_ID) AS �μ��� 
  FROM JOB_HISTORY A;
  
����) ��ǰ���̺��� ���������� ����Ͽ� 'P300'������ ��ǰ�� ��ǰ�ڵ�, ��ǰ��, �з��ڵ�, �з����� ����Ͻÿ�.
SELECT A.PROD_ID AS ��ǰ�ڵ�, 
       A.PROD_NAME AS ��ǰ��, 
       A.PROD_LGU AS �з��ڵ�, 
       (SELECT B.LPROD_NM
          FROM LPROD B
        WHERE A.PROD_LGU = B.LPROD_GU) AS �з���
  FROM PROD A
 WHERE UPPER(A.PROD_LGU) LIKE 'P3%';
 
 
 ��) ������� ��ձ޿��� ����Ͽ� ��ձ޿����� �� ���� �޿��� �޴� ������� �Ҽӵ� �μ��ڵ�� �μ����� ����Ͻÿ�.(Subquery ���)
 SELECT A.DEPARTMENT_ID AS �μ��ڵ�, 
        A.DEPARTMENT_NAME AS �μ���
   FROM DEPARTMENTS A
  WHERE A.DEPARTMENT_ID IN (��ձ޿����� �� ���� �޿��� �޴� ������� �Ҽӵ� �μ�)
  
(��������1 : ������̺��� ��ձ޿����� �� ���� �޿��� �޴� ������� �Ҽӵ� �μ�)
SELECT  B.DEPARTMENT_ID
  FROM EMPLOYEES B
 WHERE B.SALARY > (��� �޿�);
 
 (��������2 : ��ձ޿�)
 SELECT AVG(SALARY)
   FROM EMPLOYEES;

(���� : IN ������ ���)
 SELECT A.DEPARTMENT_ID AS �μ��ڵ�, 
        A.DEPARTMENT_NAME AS �μ���
   FROM DEPARTMENTS A
  WHERE A.DEPARTMENT_ID IN (SELECT  B.DEPARTMENT_ID
                              FROM EMPLOYEES B
                             WHERE B.SALARY > (SELECT AVG(SALARY)
                                                 FROM EMPLOYEES))
  ORDER BY 1;
 
 (���� : EXISTS ������ ���)
 SELECT A.DEPARTMENT_ID AS �μ��ڵ�, 
        A.DEPARTMENT_NAME AS �μ���
   FROM DEPARTMENTS A
  WHERE EXISTS (SELECT 1
                 FROM EMPLOYEES B
                WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
                  AND B.SALARY > (SELECT AVG(SALARY)
                                    FROM EMPLOYEES))
  ORDER BY 1;
 
��) �����μ��� 90���μ�(��ȹ��)�� ������� �޿��� �ڽ��� �����ִ� �μ��� ��ձ޿��� 10%�� �λ��Ͻÿ�.

(�����μ��� 90���� �μ��� ��ձ޿�)
SELECT A.DEPARTMENT_ID,   
       AVG(A.SALARY)
  FROM EMPLOYEES A, DEPARTMENTS B
 WHERE A.DEPARTMENT_ID=B.DEPARTMENT_ID
   AND B.PARENT_ID = 90
 GROUP BY A.DEPARTMENT_ID
 
 
CREATE OR REPLACE VIEW V_EMP01
AS
    SELECT A.EMP_NAME,
           A.SALARY,
           A.DEPARTMENT_ID
     FROM EMPLOYEES A
    WHERE A.DEPARTMENT_ID IN (SELECT DEPARTMENT_ID
                                FROM DEPARTMENTS
                               WHERE PARENT_ID = 90)
    ORDER BY 3
    WITH READ ONLY; --�б�����

SELECT * FROM V_EMP01;
 

--(UPDATE)
UPDATE EMPLOYEES C
   SET C.SALARY =(SELECT ROUND(C.SALARY + TBLA.SAL)
                    FROM (SELECT A.DEPARTMENT_ID AS DEPTID,   
                                 AVG(A.SALARY)*0.1 AS SAL
                            FROM EMPLOYEES A, DEPARTMENTS B
                           WHERE A.DEPARTMENT_ID=B.DEPARTMENT_ID
                             AND B.PARENT_ID = 90
                           GROUP BY A.DEPARTMENT_ID) TBLA
                    WHERE C.DEPARTMENT_ID=TBLA.DEPTID)
 WHERE C.DEPARTMENT_ID IN (SELECT DEPARTMENT_ID 
                             FROM DEPARTMENTS
                            WHERE PARENT_ID=90);
 SELECT * FROM V_EMP01;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 