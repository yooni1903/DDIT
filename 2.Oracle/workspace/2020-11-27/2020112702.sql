2020-11-27-02 집계함수
 - 한 테이블의 데이터들을 특정 컬럼을 기준으로 그룹화하고 
   각 그룹 내의 데이터에 대하여 집계처리(합계, 평균, 개수, 최대값, 최소값)
 - SUM, AVG, COUNT, MIN, MAX
 - SELECT 절에 집계함수와 일반컬럼이 같이 사용되면 반드시 GROUP BY절이 기술되어야 한다.
 (사용형식)
 SELECT [컬럼명1,..,]
        SUM(컬럼명)|COUNT(*|컬럼명)|AVG(컬럼명)|MAX(컬럼명)|MIN(컬럼명)
   FROM 테이블명
 [WHERE 조건]
 [GROUP BY 컬럼명1, ...]
 [HAVING 조건]
 [ORDER BY 컬럼명|컬럼인덱스];
 . SELECT 절에서 '[컬럼명1,..,]'이 생략되면 GROUP BY절이 필요없음. (테이블 전체가 하나의 그룹)
 . '[GROUP BY 컬럼명1,..]'에 기술되는 컬럼명은 SELECT절에서 사용된 집계함수 이외의 컬럼명을 기술하고, 필요에 따라 SELECT절에서 기술하지 않은 컬럼도 기술 가능하다.
 . 'GROUP BY' 다음에 기술되는 컬럼의순서는 그룹핑 되는 순서이다.
 . '[HAVING 조건]' : 집계함수 자체에 조건이 부여된 경우 사용
 
 1. SUM(column)
  - 'column'에 저장된 각 그룹별 합계를 구하여 반환한다.
  예) 사원테이블에서 전체 사원들의 급여 합계를 구하시오.
  SELECT SUM(SALARY)
    FROM EMPLOYEES;
  예) 사원테이블에서 각 부서별 사원들의 급여 합계를 구하시오.
  SELECT DEPARTMENT_ID AS 부서,
         COUNT(*),
         SUM(SALARY)
    FROM EMPLOYEES
   GROUP BY DEPARTMENT_ID
   ORDER BY 1;

예)회원테이블에서 남녀 회원별 마일리지 합계를 조회하시오.
SELECT CASE WHEN SUBSTR(MEM_REGNO2,1,1) = '1' OR SUBSTR(MEM_REGNO2,1,1) = '3' THEN '남성회원' 
            ELSE '여성회원' END AS 성별,
       SUM(MEM_MILEAGE) AS 마일리지합계
  FROM MEMBER
  GROUP BY CASE WHEN SUBSTR(MEM_REGNO2,1,1) = '1' OR SUBSTR(MEM_REGNO2,1,1) = '3' THEN '남성회원' 
            ELSE '여성회원' END;
            
예)2005년 2-3월 제품별 매입현황을 조회하시오.
 SELECT BUY_PROD AS 제품코드,
        SUM(BUY_QTY) AS 매입수량,
        TO_CHAR(SUM(BUY_QTY*BUY_COST),'99,999,999') AS 매입금액
    FROM BUYPROD
   WHERE BUY_DATE BETWEEN TO_DATE('20050201') AND TO_DATE('20050331')
   GROUP BY BUY_PROD
   ORDER BY 1;
   
예) 2005년 2~3월 제품별 매입현황을 조회하되 매입수량이 100개 이상인 제품만 조회하시오.
SELECT BUY_PROD AS 제품코드,
        SUM(BUY_QTY) AS 매입수량,
        TO_CHAR(SUM(BUY_QTY*BUY_COST),'99,999,999') AS 매입금액
    FROM BUYPROD
   WHERE BUY_DATE BETWEEN TO_DATE('20050201') AND TO_DATE('20050331')
   GROUP BY BUY_PROD
   HAVING SUM(BUY_QTY) >= 100
   ORDER BY 1;
   
**상품테이블에서 재고량을 변경하시오.
  재고량은 적정재고량의 130%이며 정수이다.
  UPDATE PROD
     SET PROD_TOTALSTOCK = PROD_PROPERSTOCK * 1.3;
     
  SELECT PROD_TOTALSTOCK AS 재고량,
         PROD_PROPERSTOCK AS 적정재고량
    FROM PROD;
      
   
예) 상품테이블에서 상품분류별 재고 합계를 구하시오. 또, 재고가 150개 이상 남아있는 자료를 조회하시오.
SELECT PROD_LGU AS 상품분류코드,
       SUM(PROD_TOTALSTOCK) AS "상품분류별 재고 합계"
  FROM PROD
  GROUP BY PROD_LGU
  ORDER BY 1;
  
  SELECT PROD_LGU AS 상품분류코드,
       SUM(PROD_TOTALSTOCK) AS "상품분류별 재고 합계"
  FROM PROD
  GROUP BY PROD_LGU
  HAVING SUM(PROD_TOTALSTOCK) >= 250 
  ORDER BY 1;
  
  예) 장바구니 테이블에서 2005년 5월 회원별 매출집계를 구하시오.
    Alias는 회원번호,매출수량합계
  SELECT CART_MEMBER AS 회원번호,
         SUM(CART_QTY) AS 매출수량합계
    FROM CART
   WHERE SUBSTR(CART_NO,1,6) = '200505' -- CART_NO LIKE '200505%'
   GROUP BY CART_MEMBER
   ORDER BY 1;
    
  예) 장바구니 테이블에서 2005년 5월 제품별 매출집계를 구하시오.
    Alias는 제품코드, 매출수량합계
  SELECT CART_PROD AS  제품코드,
         SUM(CART_QTY) AS 매출수량합계
    FROM CART
    WHERE SUBSTR(CART_NO,1,6) = '200505' -- CART_NO LIKE '200505'
    GROUP BY CART_PROD
    ORDER BY 1;
    
  예) 장바구니 테이블에서 2005년 5월 일자별 회원별 매출집계를 구하시오.
      Alias는 날짜, 회원번호, 매출수량합계
    SELECT TO_DATE(SUBSTR(CART_NO,1,8)) AS 날짜,
           CART_MEMBER AS 회원번호, 
           SUM(CART_QTY) AS 매출수량합계
      FROM CART
      WHERE SUBSTR(CART_NO,1,6) = '200505' -- CART_NO LIKE '200505%'
      GROUP BY TO_DATE(SUBSTR(CART_NO,1,8)), CART_MEMBER
      ORDER BY 1, 2;
      
 ----------------------------------------------  과 제  ---------------------------------------------------------------------     
예제) 2005년 상반기(1~6월) 거래처별 매입현황을 조회하시오. (왠지 BETWEEN 들어갈것같음)
     Alias는 거래처코드, 거래처명, 매입수량, 매입금액
     SELECT A.BUYER_ID AS 거래처코드, 
            A.BUYER_NAME AS 거래처명, 
            B.BUY_QTY AS 매입수량, 
            B.BUY_COST AS 매입금액
       FROM BUYER A, BUYPROD B
      WHERE A.BUYER_LGU = SUBSTR(B.BUY_PROD,1,4)
        AND EXTRACT(YEAR FROM B.BUY_DATE) = 2005 AND EXTRACT(MONTH FROM B.BUY_DATE) BETWEEN 01 AND 06
      ORDER BY 1,2;
-----------------------------------------------------------------------------------------------------------------------------       
     
     
예제) 사원테이블을 이용하여 부서별 급여합계를 구하시오.
      Alias는 부서코드, 부서명, 급여합계
      SELECT A.DEPARTMENT_ID AS 부서코드, 
             B.DEPARTMENT_NAME AS 부서명, 
             SUM(A.SALARY) AS 급여합계
        FROM EMPLOYEES A, DEPARTMENTS B
       WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID --두 테이블을 조인시키는 조건 : 동일한 항목을 갖고있다.
       GROUP BY A.DEPARTMENT_ID,  B.DEPARTMENT_NAME
       ORDER BY 1;