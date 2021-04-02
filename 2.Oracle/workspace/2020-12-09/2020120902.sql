SELECT *
FROM EMP;

SELECT *
FROM DEPT;

제약조건
0. UNIQUE : 값에 중복을 허용하지 않음. 단 NULL은 가능
1.Primary key == UNIQUE + NOT NULL
    ==> 해당컬럼 값이 테이블에서 유일함을 보장. 또한, NULL값이 들어갈 수 없다.
2.FK(외래키) : 참조 무결성
3.CHECK 

SELECT *
FROM USER_CONSTRAINTS;  -- 사용자 제약조건

SELECT *
FROM USER_CONS_COLUMNS
WHERE CONSTRAINT_NAME = 'PK_LPROD';


SELECT *
FROM USER_CONSTRAINTS
WHERE TABLE_NAME IN ('EMP', ' DEPT');


1.분석함수(= WINDOW 함수)
 - 행간 연산 지원을 해주는 함수
 SELECT 분석함수이름([인자]) OVER (영역설정 순서설정 범위설정)
   FROM ...
.영역설정 : PARTITION BY 컬럼
.순서설정 : ORDER BY 컬럼
.범위설정 : PARTITION 내에서 특정 행까지 범위를 지정...(나중에 다시 설명하실 예정)

하려고 하는 것 : EMP 테이블을 이용하여 부서번호별로 급여 랭크를 계산
                (급여 랭크의 기준 : SAL값이 높은 사람이 랭크가 높은 것으로 계산)
영역 설정 : DEPTNO
순서설정 : SAL DESC

사원의 부서별 급여(SAL)별 순위 구하기
SELECT ENAME, 
       SAL,
       DEPTNO,
       RANK() OVER (PARTITION BY DEPTNO ORDER BY SAL DESC) SAL_RANK, -- AS 안붙여도 별칭은 설정된다.
       DENSE_RANK() OVER (PARTITION BY DEPTNO ORDER BY SAL DESC) SAL_DENSE_RANK,
       ROW_NUMBER() OVER (PARTITION BY DEPTNO ORDER BY SAL DESC) SAL_ROW_NUMBER
  FROM EMP;
  
  (해설)
  PARTITION BY DEPTNO : 같은 부서코드를 갖는 ROW를 그룹으로 묶는다.
  ORDER BY SAL DESC : 그룹내에서 SAL로 ROW의 순서를 정한다(DESC : 내림차순)
  RANK() : 순위 구하기

실습1) 사원의 전체 급여 순위를 RANK, DENSE_RANK, ROW_NUMBER를 이용하여 구하세요
        단, 급여가 동일할 경우 사번이 빠른 사람이 높은 순위가 되도록 작성하세요.
SELECT EMPNO,
       ENAME,
       DEPTNO,
       RANK() OVER (ORDER BY SAL DESC, EMPNO) AS SAL_RANK,
       DENSE_RANK() OVER (ORDER BY SAL DESC, EMPNO) AS SAL_DENSE_RANK,
       ROW_NUMBER() OVER (ORDER BY SAL DESC, EMPNO) AS SAL_ROW_NUM
  FROM EMP;

       



