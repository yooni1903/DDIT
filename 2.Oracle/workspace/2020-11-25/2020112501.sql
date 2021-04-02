2020-11-25-01 숫자함수
1. 수학적 함수
 - ABS(n) : n의 절대값 반환
 - SIGN(n) : n의 부호에 따라 0인 경우 0, 양수이면 1, 음수이면, -1을 반환
 - SQRT(n) : n의 평방근(ROOT 루트) 
 - POWER(n1, n2) : n1의 n2승 값을 반환
 
 예)상품 테이블에서 상품의 매입단가와 할인판매단가를 비교하여 이익정도를 나타낼 수 있도록 조회하시오.
   Alias는 상품코드, 상품명, 매입단가, 할인판매가, 비고
   * 비고에는 이익이 발생되면 '정상', 이익이 없으면 '원가판매상품', 손해가 발생되면 '재고처분상품'이라고 출력하시오.
UPDATE PROD
   SET PROD_SALE = PROD_COST
 WHERE PROD_ID LIKE 'P101%'

UPDATE PROD
   SET PROD_SALE = PROD_COST*0.5
 WHERE PROD_ID LIKE 'P102%'


SELECT PROD_ID AS 상품코드, 
       PROD_NAME AS 상품명, 
       PROD_COST AS 매입단가, 
       PROD_SALE AS 할인판매가,
       CASE WHEN SIGN(PROD_SALE-PROD_COST) = 1 THEN '정상제품'
            WHEN SIGN(PROD_SALE-PROD_COST) = 0 THEN '원가판매상품'
            ELSE '재고처분상품' END AS 비고
  FROM PROD;
  
 ** 표현식 (CASE WHEN THEN ~ END)
    - 조건을 판단하여 처리할 명령을 다르게 선택할 때 사용(IF문과 비슷한 기능)
    - SELECT절에서 사용
    (사용형식)
    CASE WHEN 조건1 THEN
              명령1
         WHEN 조건2 THEN
              명령2
               :
         ELSE
              명령n
    END                         -- END 잊지 말것! 반드시 들어가야한다!!
    
예)회원테이블에서 주민번호를 이용하여 성별을 구별하시오. 단,  대전 지역에 거주하는 회원정보만을 조회하시오.
    Alias는 회원번호, 회원명, 주소, 성별
SELECT MEM_ID AS 회원번호, 
       MEM_NAME AS 회원명, 
       MEM_ADD1||' '||MEM_ADD2 AS 주소, 
       CASE WHEN SUBSTR(MEM_REGNO2,1,1) = '2' OR SUBSTR(MEM_REGNO2,1,1) = '4' THEN '여성회원'
            WHEN SUBSTR(MEM_REGNO2,1,1) = '1' OR SUBSTR(MEM_REGNO2,1,1) = '3' THEN '남성회원' 
            ELSE '데이터 오류'
        END AS 성별
  FROM MEMBER
 WHERE MEM_ADD1 LIKE '대전%';
 
 2. GREATEST(n1, n2 [,n3 ,...]), LEAST(n1, n2, [n3,...])
  - GREATSET : 주어진 수 n1, n2 [,n3 ,...] 중 제일 큰 수를 반환
  - LEAST : 주어진 수 n1, n2 [,n3 ,...] 중 제일 작은 수를 반환
  
  예)
  SELECT GREATEST(20, -15, 70), LEAST('탁원재', '오성순', '정은실')
    FROM DUAL;
  예제) 회원테이블에서 마일리지가 1000미만인 회원들의 마일리지를 1000으로 부여하려 한다. 이를 구현하시오(GRERATEST사용)
        Alias는 회원번호, 회원명, 마일리지
SELECT MEM_ID AS 회원번호, 
       MEM_NAME AS 회원명, 
       CASE WHEN GREATEST(MEM_MILEAGE) < 1000 THEN 1000
            ELSE GREATEST(MEM_MILEAGE) END AS 마일리지
  FROM MEMBER -- 내가 한거
  
SELECT MEM_ID AS 회원번호,
       MEM_NAME AS 회원명, 
       GREATEST(MEM_MILEAGE,1000) AS 마일리지
  FROM MEMBER -- 선생님이 하신거
  
  3. ROUND(n [,l]), TRUNC(n [,l])               -- 파란색은 영어 L, 초록색은 숫자 1이다! 헷갈리지말것
   - ROUND : 주어진 자료 n을 l+1번째 자리에서 반올림하여 l자리까지 표현
   - TRUNC : 주어진 자료 n을 l+1번째 자리에서 자리버림하여 l자리까지 표현
   - l이 음수이면 정수부분 l자리에서 반올림(ROUND), 자리버림(TRUNC)
   - l이 생략되면 0으로 간주 
   
예)사원 테이블에서 각 부서별 평균임금을 조회하시오.
    평균임금은 소수 2자리까지 출력하시오.
    Alias는 부서코드, 부서명, 평균임금
SELECT A.DEPARTMENT_ID AS 부서코드, 
       DEPARTMENTS.DEPARTMENT_NAME AS 부서명, 
       TO_CHAR(ROUND(AVG(A.SALARY),2),'99,999.99') AS 평균임금
    FROM EMPLOYEES A, DEPARTMENTS
  WHERE A.DEPARTMENT_ID=DEPARTMENTS.DEPARTMENT_ID
  GROUP BY A.DEPARTMENT_ID, DEPARTMENTS.DEPARTMENT_NAME
  ORDER BY 1;
    
예제] 사원 테이블을 이용하여 사원들의 이번달 급여를 지급하려한다.
      지금액은 보너스+급여-세금이고, 보너스는 영업실적(COMMISSION_PCT)*급여이다.
      또, 세금은 보너스+급여의 3%이다.
      Alias은 사원번호, 사원명, 부서코드, 급여,보너스, 세금, 지급액이며
              소수 첫째자리까지 나타내시오.
SELECT EMPLOYEE_ID AS 사원번호, 
       EMP_NAME AS 사원명, 
       DEPARTMENT_ID AS 부서코드, 
       SALARY AS 급여,
       ROUND(NVL(COMMISSION_PCT*SALARY,0),1) AS 보너스, 
       TRUNC((SALARY+NVL(COMMISSION_PCT*SALARY,0))*0.03,1) AS 세금, 
       SALARY+ROUND(NVL(COMMISSION_PCT*SALARY,0),1)-
       TRUNC((SALARY+NVL(COMMISSION_PCT*SALARY,0))*0.03,1) AS 지급액
  FROM EMPLOYEES;