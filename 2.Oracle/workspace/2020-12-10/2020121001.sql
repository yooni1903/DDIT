2020-12-10-01
SELECT EMPNO, ENAME, DEPTNO
  FROM EMP;
  
SELECT *
  FROM DEPT;
  
SELECT *
  FROM EMP,DEPT; --�̷��� �������� ���� ��ȸ�� �Ѵٸ�, ��� ����� ���� ����ϱ� ������ EMP ���̺��� ���(14)�� DEPT�� ���(4)��
                 --���� ����ŭ ������� ��µȴ�.
                 
SELECT *
  FROM EMP, DEPT
  WHERE EMP.DEPTNO != DEPT.DEPTNO; -- 56���� 14���� ����� ���� ������� ���´�(42��)

����
�м��Լ�
1. ���� : OVER, PARTITION BY, ORDER BY
   �Լ� : ���� - RANK, DENSE_RANK, ROW_NUMBER
          �����Լ� - SUM, AVG, MAX, MIN, COUNT
          

�м��Լ� / WINDOW �Լ�(�׷쳻 �� ����)
1. LAG(col)
 - ��Ƽ�� �� �����쿡�� ���� ���� �÷���
2. LEAD(col)
 - ��Ƽ�� �� �����쿡�� ���� ���� �÷���
==> ����/���� ���� Ư�� �÷��� �����ϴ� �Լ���.

�����ȣ, ����̸�, �Ի�����, �޿�, �ڽź��� �׿� ������ �Ѵܰ� ���� ����� �޿�
�޿� ���� : 1. �޿��� ���� ���
            2. �޿��� ���� �� �Ի� ���ڰ� ���� ���
SELECT EMPNO,
       ENAME,
       HIREDATE,
       SAL,
       LEAD(SAL) OVER (ORDER BY SAL DESC, HIREDATE ASC) AS LEAD,
       LEAD(SAL, 3) OVER (ORDER BY SAL DESC, HIREDATE ASC) AS LEAD2
  FROM EMP
ORDER BY SAL DESC, HIREDATE;  

�ǽ�5) WINDOW FUNCTION�� �̿��Ͽ� 
        ��� ����� ���� �����ȣ, ����̸�, �Ի� ����, �޿�, ��ü ��� �� �޿� ������ 1�ܰ� ���� ����� �޿��� 
        ��ȸ�ϴ� ������ �ۼ��ϼ���.
        (�޿��� ���� ��� �Ի����� ���� ����� ���� ����)
SELECT EMPNO,
       ENAME,
       HIREDATE,
       SAL,
       LAG(SAL) OVER (ORDER BY SAL DESC, HIREDATE ASC) AS LAG
  FROM EMP
 ORDER BY SAL DESC, HIREDATE;
 
 �ǽ� 5-1) WINDOW FUNCTION�� ������� �ʰ�, �ǽ�5�� ����� ����Ͻÿ�.
SELECT EMPNO,
       ENAME,
       HIREDATE,
       SAL,
       RANK() OVER (ORDER BY SAL DESC, HIREDATE) AS RM
  FROM EMP
 ORDER BY SAL DESC, HIREDATE;
 
 
SELECT A.EMPNO,
       A.ENAME,
       A.HIREDATE,
       A.SAL,
       B.SAL
  FROM (SELECT EMPNO,
               ENAME,
               HIREDATE,
               SAL,
               RANK() OVER (ORDER BY SAL DESC, HIREDATE) AS RN
          FROM EMP) A, (SELECT EMPNO,
                               ENAME,
                               HIREDATE,
                               SAL,
                               RANK() OVER (ORDER BY SAL DESC, HIREDATE) AS RN
                          FROM EMP) B
 WHERE A.RN -1 = B.RN(+)
 ORDER BY A.SAL DESC;
 
 --������ ���
 (ANSI OUTER JOIN)
 SELECT A.EMPNO,
       A.ENAME,
       A.HIREDATE,
       A.SAL,
       B.SAL
  FROM (SELECT EMPNO,
               ENAME,
               HIREDATE,
               SAL,
               RANK() OVER (ORDER BY SAL DESC, HIREDATE) AS RN
          FROM EMP) A 
  LEFT OUTER JOIN (SELECT EMPNO,
                          ENAME,
                          HIREDATE,
                          SAL,
                          RANK() OVER (ORDER BY SAL DESC, HIREDATE) AS RN
                     FROM EMP) B ON(A.RN = B.RN+1)
ORDER BY A.RN;

 �ǽ�6) WINDOW FUNCTION�� �̿��Ͽ� ��� ����� ����
        �����ȣ, ����̸�, �Ի�����, ����(JOB), �޿� ������ ������ �� �޿� ������ 1�ܰ� ���� ����� �޿��� ��ȸ�ϴ� ������ �ۼ��ϼ���.
        (�޿��� ���� ��� �Ի����� ���� ����� ���� ����)
 SELECT EMPNO,
        ENAME,
        HIREDATE,
        JOB,
        SAL,
        LAG(SAL) OVER (PARTITION BY JOB ORDER BY SAL DESC, HIREDATE) AS LAG_SAL
   FROM EMP;
    

  
�м��Լ�/WINDOWING �Լ�(�׷쳻 �� ����)
3.WINDOWING
 - WINDOW �Լ��� ����� �Ǵ� ���� ����
 - UNBOUNDED PRECEDING : ���� �� ���� ��� ���� ��
   .[n PRECEDING : ���� �� ���� ��� ������] 
 - CURRENT ROW : ������
 - UNBOUNDED FOLLOWING : ���� �� ���� ���� n��
   .[n FOLLOWING] : ���� �� ���� ���� n��
 
SELECT EMPNO,
       ENAME,
       SAL,
       SUM(SAL) OVER (ORDER BY SAL ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS SUM -- WINDOWING�� �������� ������ �����ϴ� ����� �ڱ� �ڽű����� �⺻ �����̴�.
  FROM EMP;
  
�ǽ� 7) �����ȣ, ����̸�, �μ���ȣ, �޿� ������
        �μ����� �޿�, �����ȣ�� ������������ �������� ��,
        �ڽ��� �޿��� �����ϴ� ������� �޿� ���� ��ȸ�ϴ� ������ �ۼ��ϼ���(WINDOW �Լ� ���)

SELECT EMPNO,
       ENAME,
       DEPTNO,
       SAL,
       SUM(SAL) OVER (PARTITION BY DEPTNO ORDER BY SAL, EMPNO ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS SUM
  FROM EMP;

�м��Լ�/WINDOW �Լ�
 - ���� ����(WINDOWING) - �⺻��
  . RANGE UNBOUNDED PRECEDING
  . RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
  
ROWS/RANGE ����
 - �м��Լ����� ORDER BY �� ���Ŀ� WINDOWING ���� ������ ��� ������ WINDOWING�� �⺻���� ����ȴ�.
 - RANGE UNBOUNDED PRECEDING == RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
 ROWS : �������� ���� ����
 RANGE : ������ ���� ����
 
 
 SELECT EMPNO,
        ENAME,
        SAL,
        SUM(SAL) OVER (ORDER BY SAL ROWS UNBOUNDED PRECEDING) ROWS_SUM,
        SUM(SAL) OVER (ORDER BY SAL RANGE UNBOUNDED PRECEDING) RANGE_SUM,
        SUM(SAL) OVER (ORDER BY SAL) DEFAULT_SUM
   FROM EMP;
 
 
 ROWNUM : SELECT ������� ��ȯ�� ���� ��ȣ�� 1���� �ο����ִ� �Լ�
  - WHERE �������� ����� �����ϴ�
  - ���� �ǳʶٴ� ���·� ��� �Ұ�
    ==> ROWNUM�� 1���� ���������� ���� ��쿡�� ��밡��
        WHERE ROWNUM = 1; (O)
        WHERE ROWNUM = 2; (X) -- 1�� �ǳʶپ��� ������ ���������� ��ȸ���� ����
        WHERE ROWNUM < 5; (O) 1~4
        WHERE ROWNUM > 5; (O) 1~4�� ���� �ʰ� �ǳʶ� 
 
SELECT ROWNUM,
       EMPNO,
       ENAME
  FROM EMP
 WHERE ROWNUM = 1;
 
 SELECT ROWNUM,
       EMPNO,
       ENAME
  FROM EMP
 WHERE ROWNUM = 2; -- ������ ������ ��� �ȳ���(������ �ȳ���)
 
 SELECT ROWNUM,
       EMPNO,
       ENAME
  FROM EMP
 WHERE ROWNUM < 5;
 
 SELECT ROWNUM,
       EMPNO,
       ENAME
  FROM EMP
 WHERE ROWNUM > 5;
 
 SELECT EMPNO,
        ENAME,
        HIREDATE
   FROM EMP
  ORDER BY HIREDATE DESC;
  
SELECT ROWNUM,
       A.*
  FROM (SELECT EMPNO,
        ENAME,
        HIREDATE
   FROM EMP
  ORDER BY HIREDATE DESC) A
 WHERE ROWNUM BETWEEN 1 AND 5;


SELECT *
FROM
( SELECT ROWNUM RN,
       A.*
  FROM (SELECT EMPNO,
        ENAME,
        HIREDATE
   FROM EMP
  ORDER BY HIREDATE DESC) A)
WHERE RN BETWEEN 11 AND 20;

SELECT *
FROM
( SELECT ROWNUM RN,
       A.*
  FROM (SELECT EMPNO,
        ENAME,
        HIREDATE
   FROM EMP
  ORDER BY HIREDATE DESC) A)
WHERE RN BETWEEN :ST AND :ED; -- ���۹�ȣ�� ����ȣ�� ������ ���� ����� ������ ���ϴ� ��ȣ�� �����Ͽ� ����� �� �ִ�.

������ ����� 10�϶�,
1 PAGE : 1 ~10
2 PAGE : 11~20
n PAGE : (:page-1) * (:pageSize) +1 ~ :page * (:pageSize)

SELECT *
FROM
( SELECT ROWNUM RN,
       A.*
  FROM (SELECT EMPNO,
        ENAME,
        HIREDATE
   FROM EMP
  ORDER BY HIREDATE DESC) A)
WHERE RN BETWEEN (:page-1) * (:pageSize) +1 AND :page * (:pageSize);
