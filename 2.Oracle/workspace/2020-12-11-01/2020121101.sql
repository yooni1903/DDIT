2020-12-11-01)

--MEMBER 테이블에서 두 개의 컬럼만 조회 : MEM_ID, MEM_NAME
--이름(MEM_NAME)컬럼을 오름차순으로 조회된 결과를 페이징 처리하는 쿼리를 작성하세요

SELECT MEM_ID,
       MEM_NAME
  FROM MEMBER
 ORDER BY MEM_NAME;
 
SELECT ROWNUM,
       A.*
  FROM (SELECT MEM_ID,
               MEM_NAME
          FROM MEMBER
         ORDER BY MEM_NAME) A;
 
SELECT *
  FROM (SELECT ROWNUM AS RN,
               A.*
          FROM (SELECT MEM_ID,
                       MEM_NAME
                  FROM MEMBER
                 ORDER BY MEM_NAME) A)
 WHERE RN BETWEEN (:page -1) * :pageSize + 1 AND :page * : pageSize;
 
 
SQL 절차

동일한 SQL이라는 것은 문자열이 완벽하게 동일해야한다.
공백, 대소문자도 완벽하게 일치해야 동일한 SQL로 인식

SELECT  /*DDIT*/ MEM_ID,
                 MEM_NAME
  FROM MEMBER
 WHERE MEM_ID = 'a001';
 
 
실행계획 확인 방법
1. 실행할 SQL 위에 EXPLAIN PLAN FOR를 작성하고 실행
2. 실행 계획 조회
     SELECT *
     FROM TABLE(DBMS_XPLAN.DISPLAY);

EXPLAIN PLAN FOR
SELECT MEM_ID
  FROM MEMBER
 WHERE MEM_ID = 'a001';
  
SELECT *
  FROM TABLE(DBMS_XPLAN.DISPLAY);
  

  
실행계획 해석 방법
1. 위에서 아래로
2. 단 자식 노드(들여쓰기)가 있을 경우 자식부터. -- 1번부터 0번으로  
    --들여쓰기가 안되어있다면 형제노드라고 한다. 
----------------------------------------------------------------------------
| Id  | Operation         | Name   | Rows  | Bytes | Cost (%CPU)| Time     |
----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |        |    24 |  7152 |     2   (0)| 00:00:01 |
|   1 |  TABLE ACCESS FULL| MEMBER |    24 |  7152 |     2   (0)| 00:00:01 |
----------------------------------------------------------------------------



* 2 -> 1 -> 0
-----------------------------------------------------------------------------------------
| Id  | Operation                   | Name      | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------------------
|   0 | SELECT STATEMENT            |           |     1 |   298 |     1   (0)| 00:00:01 |
|   1 |  TABLE ACCESS BY INDEX ROWID| MEMBER    |     1 |   298 |     1   (0)| 00:00:01 |
|*  2 |   INDEX UNIQUE SCAN         | PK_MEM_ID |     1 |       |     1   (0)| 00:00:01 |
-----------------------------------------------------------------------------------------
 
Predicate Information (identified by operation id):
---------------------------------------------------
 
   2 - access("MEM_ID"='a001')
   
   
   
   
SELECT ROWID, -- ROWID는 C언어에서의 포인터라고 생각하면 쉽다. 고유 주소.
       MEM_ID
  FROM MEMBER
 ORDER BY MEM_ID;

EXPLAIN PLAN FOR 
SELECT *
  FROM MEMBER
 WHERE ROWID ='AAAE5mAABAAALGBAAG';
  
  
  
  
EXPLAIN PLAN FOR
SELECT *
  FROM PROD, LPROD
 WHERE PROD.PROD_LGU = LPROD_GU;
 
 SELECT *
   FROM TABLE(DBMS_XPLAN.DISPLAY);
2 -> 3 -> 1 -> 0