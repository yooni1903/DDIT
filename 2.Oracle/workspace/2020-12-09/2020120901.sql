2020-12-09-01
������(��-ȭ)

SQL / java : ���α׷��� ���
java : ������ ����

SQL(Structured Query Language) : ����ȭ�� ���� ���
 - RDBMS�� ����ϱ� ���� ������ ����

 
RDBMS - ������ ������ ����

������, NO-SQL : NOT ONLY SQL
���� - ���̾� ���̽�, Mongo db

--���� ���� �޿��� �޴� ����� EMPLOYEE_ID ���ϱ�
SELECT EMPLOYEE_ID, MAX(SALARY)
  FROM EMPLOYEES 
 GROUP BY EMPLOYEE_ID
