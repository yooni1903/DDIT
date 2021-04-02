2020-11-20-01 연산자와 함수
SELECT 문
- 테이터를 조회하는 명령
- SQL 명령 중 가장 많이 사용되는 명령
(사용형식)
 SELECT (DISTINCT)|*|컬럼명 [AS]["]컬럼별칭["],
        |컬럼명 [AS]["]컬럼별칭["],                        --컴럼 별칭 : 
                :
        |컬럼명 [AS]["]컬럼별칭["]
    FROM 테이블명
 [WHERE 조건] -- WHERE, GROUP BY, HAVING, ORDER BY 순서는 틀려서는 안됨. 문법적 오류.
 [GROUP BY 컬럼명[, 컬럼명,...]]
[HAVING 조건]
 [ORDER BY 컬럼명|컬럼인덱스[ASC(오름차순)|DESC(내림차순)][,컬럼명|컬럼인덱스[ABC|DESC],...];
 
 . '[DISTINCT]' : 중복된 자료를 배제할 때 사용
 . '컬럼별칭' : 컬럼에 부여된 또 다른 이름
  - 컬럼명 AS 별칭
  - 컬럼명  별칭
  - 컬럼명 AS "별칭" : 별칭에 특수문자(공백포함)가 포함된 경우 반드시 " "로 묶어 사용해야한다.
  - 컬럼명  "별칭"
  - '컬럼인덱스' : SELECT 절에서 기술된해당 컬럼의 순번(1부터 COUNTING)
  - 'ASC|DESC' : 정렬방법(ASC : 오름차순이며 기본값이다. DESC : 내림차순)
  - SELECT문의 실행 순서 : FROM절 -> WHERE절 이하 -> SELECT절
 
 예) 회원테이블에서 회원 번호와 회원 명을 조회하시오.
 SELECT MEM_ID AS "회원번호",
   MEM_NAME AS "회원 이름"
 FROM MEMBER;
 
 SELECT *
 FROM LPROD; 
 
 1. 연산자
  - 산술연산자(+, -, *, /)
  - 관계연산자(>, <, <=, >=, =, !=(<>)) -- 크다의 반대는 작거나 같다, 작다의 반대는 크거나 같다. 나머지연산자(%)는 오라클에선 사용하지 않는다.
  - 논리연산자(AND, OR, NOT) -- 자바와 달리 기호가 아니라 그냥 단어로 사용한다.
  
 2. 함수(FUCTION)
  - 특정 기능을 수행하여 하나의 결과를 반환하도록 설계된 모듈
  - 컴파일되어 실행 가능한 상태로 제공
  - 문자열, 숫자, 날짜, 변환, 집계함수의 형태로 제공

  1) 문자열 함수
   - 문자열 조작한 결과를 반환
** 문자열 연산자 '||'
   자바의 문자열 연산자 '+'와 같이 두 문자열을 결합하여 하나의 문자열을 반환
   
예)
SELECT 'Oracle' ||', '|| 'Modeling' FROM DUAL;

예)회원 테이블에서 회원번호, 회원명, 주민번호를 조회하시오.
    단, 주민번호는 'XXXXXX-XXXXXXX'형식으로 출력하시오.
SELECT MEM_ID AS 회원번호,
       MEM_NAME AS 회원명,
       MEM_REGNO1||'-'||MEM_REGNO2 AS 주민번호
    FROM MEMBER;
    
예)회원 테이블에서 여성회원들의 정보를 조회하시오.
    Alias는 회원번호, 회원명, 주소, 마일리지
SELECT MEM_ID AS 회원번호,
       MEM_NAME AS 회원명,
       MEM_ADD1 ||' '|| MEM_ADD2 AS 주소,
       MEM_MILEAGE AS 마일리지
    FROM MEMBER
    WHERE SUBSTR(MEM_REGNO1,1,1)='2' OR SUBSTR(MEM_REGNO2,1,1)='4'
    ORDER BY 4 DESC;
    
1) CONCAT
 - 주어진 두 개의 문자열을 결합하여 하나의 문자열을 반환
 - '||'연산자와 같은 기능
 (사용형식)
 CONCAT(c1,c2)
  . c1과 c2를 결합하여 결과를 반환
예)회원 테이블에서 회원번호, 회원명, 주민번호를 조회하시오.
    단, 주민번호는 'XXXXXX-XXXXXXX'형식으로 출력하되 CONCAT 함수 사용
    SELECT MEM_ID AS 회원번호,
       MEM_NAME AS 회원명,
       CONCAT(CONCAT(MEM_REGNO1,'-'), MEM_REGNO2) AS 주민번호
    FROM MEMBER;

2) INITCAP
 - 단어의 선두 문자만 대문자로 출력
 - 보통 이름 출력 시 사용
 (사용형식)
 INITCAP(cap1)
  . c1에 포함된 단어의 첫 글자를 대문자로 변환
  
예)
UPDATE EMPLOYEES
    SET EMP_NAME=LOWER(EMP_NAME);
    
SELECT EMP_NAME FROM EMPLOYEES;

SELECT INITCAP(EMP_NAME) FROM EMPLOYEES;

ROLLBACK;
COMMIT;