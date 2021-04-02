실습 1) - printtemp procedure 생성
        - param : empno
        - logic : empno에 해당하는 사원의 정보를 조회하여
                  사원이름, 부서이름을 화면에 출력

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