2020-12-10-01
SELECT EMPNO, ENAME, DEPTNO
  FROM EMP;
  
SELECT *
  FROM DEPT;
  
SELECT *
  FROM EMP,DEPT; --이렇게 조인조건 없이 조회를 한다면, 모든 경우의 수를 출력하기 때문에 EMP 테이블의 결과(14)와 DEPT의 결과(4)를
                 --곱한 수만큼 결과값이 출력된다.
                 
SELECT *
  FROM EMP, DEPT
  WHERE EMP.DEPTNO != DEPT.DEPTNO; -- 56에서 14개의 결과가 빠진 결과값이 나온다(42행)

복습
분석함수
1. 문법 : OVER, PARTITION BY, ORDER BY
   함수 : 순위 - RANK, DENSE_RANK, ROW_NUMBER
          집계함수 - SUM, AVG, MAX, MIN, COUNT
          

분석함수 / WINDOW 함수(그룹내 행 순서)
1. LAG(col)
 - 파티션 별 윈도우에서 이전 행의 컬럼값
2. LEAD(col)
 - 파티션 별 윈도우에서 이후 행의 컬럼값
==> 이전/이후 행의 특정 컬럼을 참조하는 함수다.

사원번호, 사원이름, 입사일자, 급여, 자신보다 그여 순위가 한단계 낮은 사람의 급여
급여 순위 : 1. 급여가 놓은 사람
            2. 급여가 같을 때 입사 일자가 빠른 사람
SELECT EMPNO,
       ENAME,
       HIREDATE,
       SAL,
       LEAD(SAL) OVER (ORDER BY SAL DESC, HIREDATE ASC) AS LEAD,
       LEAD(SAL, 3) OVER (ORDER BY SAL DESC, HIREDATE ASC) AS LEAD2
  FROM EMP
ORDER BY SAL DESC, HIREDATE;  

실습5) WINDOW FUNCTION을 이용하여 
        모든 사원에 대해 사원번호, 사원이름, 입사 일자, 급여, 전체 사원 중 급여 순위가 1단계 높은 사람의 급여를 
        조회하는 쿼리를 작성하세요.
        (급여가 같을 경우 입사일이 빠른 사람이 높은 순위)
SELECT EMPNO,
       ENAME,
       HIREDATE,
       SAL,
       LAG(SAL) OVER (ORDER BY SAL DESC, HIREDATE ASC) AS LAG
  FROM EMP
 ORDER BY SAL DESC, HIREDATE;
 
 실습 5-1) WINDOW FUNCTION을 사용하지 않고, 실습5의 결과를 출력하시오.
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
 
 --선생님 답안
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

 실습6) WINDOW FUNCTION을 이용하여 모든 사원에 대해
        사원번호, 사원이름, 입사일자, 직군(JOB), 급여 정보와 담당업무 별 급여 순위가 1단계 높은 사람의 급여를 조회하는 쿼리를 작성하세요.
        (급여가 같은 경우 입사일이 빠른 사람이 높은 순위)
 SELECT EMPNO,
        ENAME,
        HIREDATE,
        JOB,
        SAL,
        LAG(SAL) OVER (PARTITION BY JOB ORDER BY SAL DESC, HIREDATE) AS LAG_SAL
   FROM EMP;
    

  
분석함수/WINDOWING 함수(그룹내 행 순서)
3.WINDOWING
 - WINDOW 함수에 대상이 되는 행을 지정
 - UNBOUNDED PRECEDING : 현재 행 기준 모든 이전 행
   .[n PRECEDING : 현재 행 기준 모든 이전행] 
 - CURRENT ROW : 현재형
 - UNBOUNDED FOLLOWING : 현재 행 기준 이전 n행
   .[n FOLLOWING] : 현재 행 기준 이후 n행
 
SELECT EMPNO,
       ENAME,
       SAL,
       SUM(SAL) OVER (ORDER BY SAL ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS SUM -- WINDOWING을 지정하지 않으면 선행하는 행부터 자기 자신까지가 기본 설정이다.
  FROM EMP;
  
실습 7) 사원번호, 사원이름, 부서번호, 급여 정보를
        부서별로 급여, 사원번호를 오름차순으로 정렬했을 때,
        자신의 급여와 선행하는 사원들의 급여 합을 조회하는 쿼리를 작성하세요(WINDOW 함수 사용)

SELECT EMPNO,
       ENAME,
       DEPTNO,
       SAL,
       SUM(SAL) OVER (PARTITION BY DEPTNO ORDER BY SAL, EMPNO ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS SUM
  FROM EMP;

분석함수/WINDOW 함수
 - 범위 설정(WINDOWING) - 기본값
  . RANGE UNBOUNDED PRECEDING
  . RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
  
ROWS/RANGE 차이
 - 분석함수에서 ORDER BY 절 이후에 WINDOWING 절을 생략할 경우 다음의 WINDOWING이 기본으로 적용된다.
 - RANGE UNBOUNDED PRECEDING == RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
 ROWS : 물리적인 행의 단위
 RANGE : 논리적인 행의 단위
 
 
 SELECT EMPNO,
        ENAME,
        SAL,
        SUM(SAL) OVER (ORDER BY SAL ROWS UNBOUNDED PRECEDING) ROWS_SUM,
        SUM(SAL) OVER (ORDER BY SAL RANGE UNBOUNDED PRECEDING) RANGE_SUM,
        SUM(SAL) OVER (ORDER BY SAL) DEFAULT_SUM
   FROM EMP;
 
 
 ROWNUM : SELECT 순서대로 반환된 행의 번호를 1부터 부여해주는 함수
  - WHERE 절에서도 사용이 가능하다
  - 행을 건너뛰는 형태로 사용 불가
    ==> ROWNUM이 1부터 순차적으로 사용된 경우에만 사용가능
        WHERE ROWNUM = 1; (O)
        WHERE ROWNUM = 2; (X) -- 1을 건너뛰었기 때문에 정상적으로 조회되지 않음
        WHERE ROWNUM < 5; (O) 1~4
        WHERE ROWNUM > 5; (O) 1~4를 읽지 않고 건너뜀 
 
SELECT ROWNUM,
       EMPNO,
       ENAME
  FROM EMP
 WHERE ROWNUM = 1;
 
 SELECT ROWNUM,
       EMPNO,
       ENAME
  FROM EMP
 WHERE ROWNUM = 2; -- 실행은 되지만 결과 안나옴(에러는 안나옴)
 
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
WHERE RN BETWEEN :ST AND :ED; -- 시작번호와 끝번호에 변수를 놓고 사용할 때마다 원하는 번호를 지정하여 사용할 수 있다.

페이지 사이즈가 10일때,
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
