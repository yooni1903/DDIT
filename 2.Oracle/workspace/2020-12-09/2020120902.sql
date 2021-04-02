SELECT *
FROM EMP;

SELECT *
FROM DEPT;

��������
0. UNIQUE : ���� �ߺ��� ������� ����. �� NULL�� ����
1.Primary key == UNIQUE + NOT NULL
    ==> �ش��÷� ���� ���̺��� �������� ����. ����, NULL���� �� �� ����.
2.FK(�ܷ�Ű) : ���� ���Ἲ
3.CHECK 

SELECT *
FROM USER_CONSTRAINTS;  -- ����� ��������

SELECT *
FROM USER_CONS_COLUMNS
WHERE CONSTRAINT_NAME = 'PK_LPROD';


SELECT *
FROM USER_CONSTRAINTS
WHERE TABLE_NAME IN ('EMP', ' DEPT');


1.�м��Լ�(= WINDOW �Լ�)
 - �ణ ���� ������ ���ִ� �Լ�
 SELECT �м��Լ��̸�([����]) OVER (�������� �������� ��������)
   FROM ...
.�������� : PARTITION BY �÷�
.�������� : ORDER BY �÷�
.�������� : PARTITION ������ Ư�� ����� ������ ����...(���߿� �ٽ� �����Ͻ� ����)

�Ϸ��� �ϴ� �� : EMP ���̺��� �̿��Ͽ� �μ���ȣ���� �޿� ��ũ�� ���
                (�޿� ��ũ�� ���� : SAL���� ���� ����� ��ũ�� ���� ������ ���)
���� ���� : DEPTNO
�������� : SAL DESC

����� �μ��� �޿�(SAL)�� ���� ���ϱ�
SELECT ENAME, 
       SAL,
       DEPTNO,
       RANK() OVER (PARTITION BY DEPTNO ORDER BY SAL DESC) SAL_RANK, -- AS �Ⱥٿ��� ��Ī�� �����ȴ�.
       DENSE_RANK() OVER (PARTITION BY DEPTNO ORDER BY SAL DESC) SAL_DENSE_RANK,
       ROW_NUMBER() OVER (PARTITION BY DEPTNO ORDER BY SAL DESC) SAL_ROW_NUMBER
  FROM EMP;
  
  (�ؼ�)
  PARTITION BY DEPTNO : ���� �μ��ڵ带 ���� ROW�� �׷����� ���´�.
  ORDER BY SAL DESC : �׷쳻���� SAL�� ROW�� ������ ���Ѵ�(DESC : ��������)
  RANK() : ���� ���ϱ�

�ǽ�1) ����� ��ü �޿� ������ RANK, DENSE_RANK, ROW_NUMBER�� �̿��Ͽ� ���ϼ���
        ��, �޿��� ������ ��� ����� ���� ����� ���� ������ �ǵ��� �ۼ��ϼ���.
SELECT EMPNO,
       ENAME,
       DEPTNO,
       RANK() OVER (ORDER BY SAL DESC, EMPNO) AS SAL_RANK,
       DENSE_RANK() OVER (ORDER BY SAL DESC, EMPNO) AS SAL_DENSE_RANK,
       ROW_NUMBER() OVER (ORDER BY SAL DESC, EMPNO) AS SAL_ROW_NUM
  FROM EMP;

       



