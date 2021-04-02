2020-12-14-01

invalid identifier -> 해당 테이블에서 조회할 컬럼을 찾을 수 없다는 뜻

실습 2) - registdept_test procedure 생성
        - param : deptno, dname, loc
        - logic : 입력받은 부서 정보를 dept 테이블에 신규 입력
        - exec registdept_test (99, 'ddit', 'daejeon')
        - dept 테이블에 정상적으로 입력되었는지 확인(SQL - 눈으로)

 
CREATE OR REPLACE PROCEDURE registdept_test(deptno dept.deptno%TYPE,
                                            dname dept.dname%TYPE,
                                            loc dept.loc%TYPE) IS
BEGIN
    INSERT INTO DEPT
        VALUES (deptno, dname, loc);
    COMMIT;
    
END;

EXEC registdept_test (99, 'ddit', '대전');

SELECT *
  FROM DEPT;



CTAS : CHECK 제약을 제외한 나머지 제약조건들은 복사되지 않는다.(기존 테이블과 관계가 무관하다.)
        WHERE절에 무조건 거짓인 조건을 넣으면 컬럼명과 데이터 타입만 같은 빈 테이블을 생성할 수 있따.

CREATE TABLE DEPT_TEST AS -- AS 뒤에 있는 SELECT문의 결과로 DEPT_TEST라는 테이블이 새로 생성된다.
SELECT *
  FROM DEPT;
  
  
실습 3) - UPDATEdept_test procedure 생성
        - param :  deptno, dname, loc
        - logic :  입력받은 부서 정보를 dept 테이블에 정보 수정
        - exec UPDATEdept_test (99, 'ddit_m', 'daejeon');
        - dept 테이블에 정상정으로 갱신 되었는지 확인 (SQL 눈으로)
        
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

--값이 중복되어 컬럼명으로 선택하기 어려울때에는 ROWID를 조회해 행의 고유 주소를 이용하여 행을 삭제하면 된다.
DELETE DEPT
 WHERE ROWID = 'AAAFCAAABAAALjRAAF';
 
COMMIT;



분석함수 못했던 거 실습
사원 급여에 맞는 급여등급을 조회하시오.

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
 
실습 3) - 모든 사원에 대해 사원번호, 사원이름, 입사일자, 급여를 급여가 낮은 순으로 조회해보자.
        - 급여가 동일할 경우, 사원번호가 빠른 사람이 우선순위가 높다.
        - 우선순위가 가장 낮은 사람부터 본인까지의 급여 합을 새로운 컬럼으로 생성
        - WINDOW함수 없이 
        
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
 
 분석함수 실습 0)
  - 사원의 부서별 급여별 순위 구하기
  - EMP 테이블 사용
 1.행을 임의로 생성
 
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