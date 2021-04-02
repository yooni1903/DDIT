2020-11-24-01
3)UPPER, LOWER
 - 주어진 문자열에 포함된 글자를 대문자(UPPER) 또는 소문자(LOWER)로 변환하여 반환
 (사용형식)
 UPPER(c), LOWER(c) -- c는 문자열
 
 예) 회원테이블에서 'R001'회원 정보를 조회하시오.
    Alias는 회원번호, 회원명, 직업, 마일리지이다.
    
    SELECT MEM_ID AS 회원번호, 
           MEM_NAME AS 회원명, 
           MEM_JOB AS 직업,
           MEM_MILEAGE AS 마일리지
        FROM MEMBER
        WHERE UPPER(MEM_ID) = 'R001';
        
예) 분류 테이블에서 'P200'번 대의 분류코드를 조회하시오.
    Alias는 분류코드, 분류명이다.
    
SELECT LPROD_GU AS 분류코드, 
       LPROD_NM AS 분류명
       FROM LPROD
       WHERE UPPER(LPROD_GU) LIKE 'P2%';
       
4) ASCII, CHR
 - ASCII : 주어진 문자 자료를 ASCII 코드 값으로 변환
 - CHR : 주어진 숫자(정수,1~65535)에 대응하는 문자 반환
 (사용형식)
 ASCII(c), CHR(n)
 
 예)
    SELECT ASCII ('Oracle'),
           ASCII ('대한민국'),
           CHR(95)
        FROM DUAL;
        
5)LPAD, RPAD
 - 특정문자열(패털)을 삽입할 때 사용
 (사용형식)
 LPAD(c, n [, pattern]),RPAD(c,n [pattern])
  - 주어진 문자열 'c'를 길이 'n'의 기억 공간에 왼쪽부터 채우고(RPAD)| 남는 공간은 'pattern'으로 정의된 문자열을 채움
  - 'pattern'이 생략되면 공백으로 채워짐
  예) 회원 테이블에서 회원의 암호를 10자리공간에 우측정렬하고 남는 공간에 '$'를 삽입하시오. '걸레질'
    Alias 회원번호, 회원명, 암호
    SELECT MEM_ID AS 회원번호,
           MEM_NAME AS 회원명,
           MEM_PASS AS 암호1,
           LPAD(MEM_PASS, 10, '$') AS 암호2
        FROM MEMBER;
        
예) 매입 테이블에서 2005년 2월 매입신청을 조회하시오.
    Alias 날짜, 매입상품코드, 수량, 단가이다.
    단 매입단자는 9자리에 출력하되 남은 왼쪽 공간에 '***'을 삽입하여 출력하시오.
SELECT BUY_DATE AS 날짜, 
       BUY_PROD AS 매입상품코드, 
       LPAD(BUY_COST, 9) AS 수량, 
       LPAD(BUY_COST, 9, '*') AS 단가
    FROM BUYPROD
    WHERE BUY_DATE BETWEEN '20050201' AND '20050228';
    
6)LTRIM, BTRIM, TRIM
 - 주어진 문자열에서 왼쪽(LTRIM) 또는 오른쪽(RTRIM)에 존재하는 문자열을 찾아 삭제할 때 사용
 - 양쪽에 존재하는 공백을 제거할 때는 TRIM 사용
 (사용형식)
 LTRIM(c1 [,c2]), RTRIM(c1 [, c2]), TRIM(c1)
  - c2가 생략되면 공백을 삭제
  
  예) 사원 테이블의 사원명을 CHAR(80)으로 변경하시오.
 ALTER TABLE EMPLOYEES
    MODIFY EMP_NAME CHAR(80);
    
예) 사원 테이블에서 'Steven King' 사원 정보를 조회하시오.
    Alias는 사원번호, 사원명, 부서코드, 입사일
SELECT EMPLOYEE_ID AS 사원번호, 
       TRIM(EMP_NAME) AS 사원명, 
       DEPARTMENT_ID AS 부서코드, 
       HIRE_DATE AS 입사일
    FROM EMPLOYEES
    WHERE TRIM(EMP_NAME) = 'Steven King';
    
예) 상품 테이블에서 '대우'로 시작하는 상품명 중 '대우'를 삭제하고 출력하시오.
    Alias는 상품코드, 상품명, 분류코드, 거래처코드 이다.
SELECT PROD_ID AS 상품코드, 
       PROD_NAME AS 상품명1,
       LTRIM(PROD_NAME, '대우 ') AS 상품명2,
       PROD_LGU AS 분류코드, 
       PROD_BUYER AS 거래처코드
    FROM PROD
    WHERE PROD_NAME LIKE '대우%';
    
7) SUBSTR(c,n1[,n2]) -- 자바에도 있으니 헷갈리지 않게 조심하기!
 - 주어진 문자열에서 n1에서 시작하여 n2(개수)만큼의 부분 문자열을 추출하여 반환
 - n2가 생략되면 n1 이후의 모든 문자열을 추출하여 반환
 - n1가 음수이면 뒤에서부터 처리됨
 - n1은 1부터 counting
예)
    SELECT SUBSTR('IL POSTINO', 3,4), --3번째 공백부터 공백POS가 출력
           SUBSTR('IL POSTINO', 3), --3번째 이후로 다
           SUBSTR('IL POSTINO', -3,4) --음수는 오른쪽에서 왼쪽으로 카운팅하고 오른쪽에서 세번째 자리에서 4글자를 출력한다.
        FROM DUAL;
예) 상품 테이블에서 분류코드'P201'에 속한 상품의 가지 수를 출력하시오.
    SELECT COUNT(*) AS 상품의수
        FROM PROD
       WHERE UPPER(SUBSTR(PROD_ID,1,4)) ='P201'
예) 장바구니 테이블에서 2005년 3월에 판매된 상품정보를 상품 별로 출력하시오.
    Alias는 상품코드, 상품명, 수량합계, 판매금액
    SELECT A.CART_PROD AS 상품코드, 
           B.PROD_NAME AS 상품명, 
           NVL(SUM(A.CART_QTY),0) AS 수량합계, 
           NVL(SUM(A.CART_QTY *B.PROD_PRICE), 0) AS 판매금액
      FROM CART A, PROD B
     WHERE SUBSTR(CART_NO),1,6) = '200503' -- CART_NO LIKE '200502%'
       AND A.CART_PROD=B.PROD_ID
    GROUP BY A.CART_PROD, V
    
예제) 회원 테이블에서 '대전'에 살고잇는 회원정보를 조회하시오.
    Alias 회원번호, 회원명, 주소, 직업, 마일리지
    단, LIKE 연산자를 사용하지 말것
SELECT MEM_ID AS 회원번호, 
       MEM_NAME AS 회원명, 
       MEM_ADD1||' '||MEM_ADD2 AS 주소, 
       MEM_JOB AS 직업, 
       MEM_MILEAGE AS 마일리지
  FROM MEMBER
 WHERE SUBSTR(MEM_ADD1,1,2) = '대전';
 
예제)오늘이 2005년 7월 28일이라고 가정하고 CART_NO를 자동으로 생성하는 코드를 작성하시오.
1. 2005년 7월20일 최대 순번 MAX
SELECT TO_NUMBER(MAX(SUBSTR(CART_NO,9))+1)
  FROM CART
 WHERE SUBSTR(CART_NO,1,8)='20050728';
2. 날짜와 1번에서 구한 순번 +1을 결합
SELECT TO_CHAR(SYSDATE,'YYYYMMDD')||
       TRIM(TO_CHAR(TO_NUMBER(MAX(SUBSTR(CART_NO,9))+1),'00000')) -- TO_NUMBER 생략하면 안된다. 오류 생길 수도 있음! 자동 형변환을 믿지 마라
  FROM CART
 WHERE SUBSTR(CART_NO,1,8)='20050728';
 
SELECT MAX(CART_NO)+1 
  FROM CART
 WHERE SUBSTR(CART_NO,1,8)='20050728';
예제]분류 테이블에서 '여성정장' 분류코드를 신규로 등록시킬 때 'P200'번대의 코드를 생성하시오.
SELECT 'P'||((MAX(SUBSTR(LPROD_GU,2))+1))
  FROM LPROD
 WHERE LPROD_GU LIKE 'P2%';

8) REPLACE(c1, c2[,c3])
 - 주어진 문자열 c1에서 c2를 c3로 대치(치환)시킴
 - c3가 생략되면 c2를 제거함
 
예) 상품 테이블에서 상품명 중 '대우'를 찾아 'Apple'로 변경하시오.
SELECT PROD_ID,
       PROD_NAME,
       REPLACE(PROD_NAME, '대우', 'Apple')
  FROM PROD
 WHERE PROD_NAME LIKE '%대우%';
 
SELECT PROD_ID,
       PROD_NAME,
       REPLACE(PROD_NAME, '대우')
  FROM PROD
 WHERE PROD_NAME LIKE '%대우%';
 
SELECT PROD_ID,
       PROD_NAME,
       REPLACE(PROD_NAME, ' ') -- 단어 내부의 공백을 지우는 방법 : REPLACE
  FROM PROD
 WHERE PROD_NAME LIKE '%대우%';
 
9) LENGTH(c), LENGTHB(c)
 - 주어진 문자열에서 글자 수 (LENGTH)또는 기억공간의 크기(BYTE수, LENGTHB)를 반환