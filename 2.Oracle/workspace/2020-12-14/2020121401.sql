2020-12-14-01

invalid identifier -> �ش� ���̺��� ��ȸ�� �÷��� ã�� �� ���ٴ� ��

�ǽ� 2) - registdept_test procedure ����
        - param : deptno, dname, loc
        - logic : �Է¹��� �μ� ������ dept ���̺� �ű� �Է�
        - exec registdept_test (99, 'ddit', 'daejeon')
        - dept ���̺� ���������� �ԷµǾ����� Ȯ��(SQL - ������)

 
CREATE OR REPLACE PROCEDURE registdept_test(deptno dept.deptno%TYPE,
                                            dname dept.dname%TYPE,
                                            loc dept.loc%TYPE) IS
BEGIN
    INSERT INTO DEPT
        VALUES (deptno, dname, loc);
    COMMIT;
    
END;

EXEC registdept_test (99, 'ddit', '����');

SELECT *
  FROM DEPT;



CTAS : CHECK ������ ������ ������ �������ǵ��� ������� �ʴ´�.(���� ���̺�� ���谡 �����ϴ�.)
        WHERE���� ������ ������ ������ ������ �÷���� ������ Ÿ�Ը� ���� �� ���̺��� ������ �� �ֵ�.

CREATE TABLE DEPT_TEST AS -- AS �ڿ� �ִ� SELECT���� ����� DEPT_TEST��� ���̺��� ���� �����ȴ�.
SELECT *
  FROM DEPT;
  
  
�ǽ� 3) - UPDATEdept_test procedure ����
        - param :  deptno, dname, loc
        - logic :  �Է¹��� �μ� ������ dept ���̺� ���� ����
        - exec UPDATEdept_test (99, 'ddit_m', 'daejeon');
        - dept ���̺� ���������� ���� �Ǿ����� Ȯ�� (SQL ������)
        
CREATE OR REPLACE PROCEDURE UPDATEdept_test(p_deptno dept.deptno%TYPE,
                                            p_dname dept.dname%TYPE,
                                            p_loc dept.loc%TYPE) IS
BEGIN 
    UPDATE DEPT
       SET DNAME = p_dname,
           LOC = p_loc
     WHERE DEPTNO = p_deptno;
END;
/

EXEC UPDATEdept_test (98, 'ddit_m', 'daejeon');

SELECT *
  FROM DEPT;
  
  
 SELECT ROWID, DEPT.*
   FROM DEPT;

--���� �ߺ��Ǿ� �÷������� �����ϱ� ����ﶧ���� ROWID�� ��ȸ�� ���� ���� �ּҸ� �̿��Ͽ� ���� �����ϸ� �ȴ�.
DELETE DEPT
 WHERE ROWID = 'AAAFCAAABAAALjRAAF';
 
COMMIT;



�м��Լ� ���ߴ� �� �ǽ�
��� �޿��� �´� �޿������ ��ȸ�Ͻÿ�.

SELECT *
  FROM SALGRADE;
  
 
SELECT A.EMPNO,
       A.ENAME,
       A.SAL,
       B.GRADE
  FROM EMP A, SALGRADE B
 WHERE B.LOSAL <= A.SAL AND A.SAL <= B.HISAL;
 
SELECT A.EMPNO,
       A.ENAME,
       A.SAL,
       B.GRADE
  FROM EMP A, SALGRADE B
 WHERE A.SAL BETWEEN B.LOSAL AND B.HISAL;
 
�ǽ� 3) - ��� ����� ���� �����ȣ, ����̸�, �Ի�����, �޿��� �޿��� ���� ������ ��ȸ�غ���.
        - �޿��� ������ ���, �����ȣ�� ���� ����� �켱������ ����.
        - �켱������ ���� ���� ������� ���α����� �޿� ���� ���ο� �÷����� ����
        - WINDOW�Լ� ���� 
        
SELECT EMPNO,
       ENAME,
       SAL
  FROM EMP
 ORDER BY SAL, EMPNO;
 
SELECT A.EMPNO,
       A.ENAME,
       A.SAL,
       SUM(B.SAL)
  FROM (SELECT EMPNO,
               ENAME,
               SAL
          FROM EMP
          ORDER BY SAL, EMPNO) A, (SELECT EMPNO,
                                          ENAME,
                                          SAL
                                     FROM EMP
                                    ORDER BY SAL, EMPNO) B
 WHERE A.SAL >= B.SAL
 GROUP BY A.EMPNO, A.ENAME, A.SAL
 ORDER BY A.SAL;
 
SELECT A.EMPNO,
       A.ENAME,
       A.SAL,
       SUM(B.SAL)
  FROM EMP A, EMP B
 WHERE A.SAL >= B.SAL
 GROUP BY A.EMPNO, A.ENAME, A.SAL
 ORDER BY A.SAL;


SELECT A.EMPNO, A.ENAME, A.SAL, SUM(B.SAL) AS C_SUM
  FROM (SELECT A.*,
               ROWNUM RN
          FROM (SELECT EMPNO, ENAME, SAL
                  FROM EMP
                 ORDER BY SAL, EMPNO) A) A, (SELECT A.*,
                                                    ROWNUM RN
                                               FROM (SELECT EMPNO, ENAME, SAL
                                                       FROM EMP
                                                      ORDER BY SAL, EMPNO) A) B
 WHERE A.RN >= B.RN
 GROUP BY A.EMPNO, A.ENAME, A.SAL
 ORDER BY A.SAL;
 
 �м��Լ� �ǽ� 0)
  - ����� �μ��� �޿��� ���� ���ϱ�
  - EMP ���̺� ���
 1.���� ���Ƿ� ����
 
 SINGLE ROW FUNCTION :
 MULTI ROW FUNCTION : 
 
 SELECT *
   FROM DUAL;
   
SELECT DUAL.*, LEVEL
  FROM DUAL
CONNECT BY LEVEL <= (SELECT COUNT(*)
                       FROM EMP);
10 3
20 5
30 6

SELECT  A.empno, a.sal, a.deptno, B.rn
  FROM (SELECT ROWNUM r, a.*
          FROM (SELECT empno, sal, deptno
                  FROM emp
                 ORDER BY deptno, sal, empno) a) a, (SELECT ROWNUM r, b.*
                                                       FROM (SELECT a.deptno, b.rn
                                                               FROM (SELECT deptno, COUNT(*) cnt
                                                                       FROM emp
                                                                      GROUP BY deptno) a, (SELECT LEVEL rn
                                                                                             FROM dual
                                                                                          CONNECT BY LEVEL <= (SELECT COUNT(*) FROM emp)) b
                                                                     WHERE a.cnt >= b.rn
                                                                     ORDER BY a.deptno, b.rn) b ) b
WHERE a.r = b.r;