2020-12-09-01
이진우(수-화)

SQL / java : 프로그래밍 언어
java : 로직을 제어

SQL(Structured Query Language) : 구조화된 질의 언어
 - RDBMS과 통신하기 위한 유일한 수단

 
RDBMS - 집합적 개념을 차용

빅데이터, NO-SQL : NOT ONLY SQL
구글 - 파이어 베이스, Mongo db

--가장 많은 급여를 받는 사람의 EMPLOYEE_ID 구하기
SELECT EMPLOYEE_ID, MAX(SALARY)
  FROM EMPLOYEES 
 GROUP BY EMPLOYEE_ID
