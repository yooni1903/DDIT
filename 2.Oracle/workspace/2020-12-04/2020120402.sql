2020-12-04-02
��)������� ��ձ޿����� ���� �޿��� �޴� ����� ����Ͻÿ�
    Alias�� �����ȣ, �����, �޿�
    (WHERE���� Subquery ���)
SELECT EMPLOYEE_ID AS �����ȣ, 
       EMP_NAME AS �����, 
       SALARY AS �޿�
  FROM EMPLOYEES
 WHERE SALARY > (SELECT AVG(SALARY)
                   FROM EMPLOYEES)
 ORDER BY 3 DESC;
 
 (FROM ���� ���)
SELECT A.EMPLOYEE_ID AS �����ȣ, 
       A.EMP_NAME AS �����, 
       A.SALARY AS �޿�
  FROM EMPLOYEES A,(SELECT AVG(SALARY) AS SAL
                   FROM EMPLOYEES) B
 WHERE A.SALARY > B.SAL
 ORDER BY 3 DESC;