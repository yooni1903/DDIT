�ǽ� 1) - printtemp procedure ����
        - param : empno
        - logic : empno�� �ش��ϴ� ����� ������ ��ȸ�Ͽ�
                  ����̸�, �μ��̸��� ȭ�鿡 ���

CREATE OR REPLACE PROCEDURE printtemp(p_empno emp.empno%TYPE) IS
    v_ename emp.ename%TYPE;
    v_dname dept.dname%TYPE;
BEGIN
    SELECT A.ename, B.dname INTO v_ename, v_dname
      FROM EMP A, DEPT B
     WHERE A.DEPTNO = B.DEPTNO
       AND A.EMPNO = p_empno;

       
    DBMS_OUTPUT.PUT_LINE(v_ename || ' / ' || v_dname);
END;
/

EXEC printtemp(7369);