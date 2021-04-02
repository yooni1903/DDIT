2020-11-30-01
2. AVG(column)
 - 'column'을 기준으로 그룹으로 묶인 집단에 대한 평균값 반환
 예)사원테이블에서 각 부서별 평균 급여를 소수 1자리까지 구하시오.
 SELECT DEPARTMENT_ID AS 부서, 
        ROUND(AVG(SALARY),1) AS 평균급여
   FROM EMPLOYEES
   GROUP BY DEPARTMENT_ID
   ORDER BY 1;
   
예)상품테이블에서 상품분류별 평균매입가를 구하시오.
SELECT PROD_LGU AS 상품, 
       ROUND(AVG(PROD_COST),-1) AS 평균매입가
  FROM PROD
  GROUP BY PROD_LGU
  ORDER BY 1;

예제) 2005년 월별 제품별 평균매입수량과 매입금액합계를 구하시오.
SELECT EXTRACT(MONTH FROM BUY_DATE)||'월' AS 월, 
       BUY_PROD AS 제품, 
       ROUND(AVG(BUY_QTY)) AS 평균매입수량,
       SUM(BUY_COST*BUY_QTY) AS 매입금액합계
  FROM BUYPROD
 WHERE EXTRACT(YEAR FROM BUY_DATE) = 2005
 GROUP BY EXTRACT(MONTH FROM BUY_DATE), BUY_PROD
 ORDER BY 1,2;
 
 예제)2005년 5월 일자별 평균판매 수량을 구하시오.
 SELECT TO_DATE(SUBSTR(CART_NO,1,8)) AS 일자, 
        ROUND(AVG(CART_QTY)) AS 평균판매수량
   FROM CART
  WHERE CART_NO LIKE '200505%'
  GROUP BY TO_DATE(SUBSTR(CART_NO,1,8))
  ORDER BY 1;
  
예제) 사원테이블에서 각 부서별 평균급여보다 많은 급여를 받는 사원정보를 출려하시오.
     Alias는 사원번호, 사원명, 부서코드, 부서명, 평균급여
     
(각 부서별 평균급여)
SELECT DEPARTMENT_ID AS DID, -- AS를 영문으로 해준다면 참조가 가능하다
       AVG(SALARY) AS ASAL
  FROM EMPLOYEES
GROUP BY DEPARTMENT_ID;

SELECT A.EMPLOYEE_ID AS 사원번호, 
       A.EMP_NAME AS 사원명, 
       A.SALARY AS 급여,
       A.DEPARTMENT_ID AS 부서코드, 
       B.DEPARTMENT_NAME AS 부서명, 
       ROUND(C.ASAL) AS 평균급여
  FROM EMPLOYEES A, DEPARTMENTS B,
        (SELECT DEPARTMENT_ID AS DID,
                AVG(SALARY) AS ASAL
           FROM EMPLOYEES
       GROUP BY DEPARTMENT_ID) C
 WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
   AND A.DEPARTMENT_ID = C.DID
   AND A.SALARY>=C.ASAL
   ORDER BY 4,3;
  
3.COUNT(*|column)
 - 그룹으로 묶인 각 그룹에 포함된 자료 수(행의 수)
 - 외부조인(아우터조인)에 COUNT함수를 사용할 경우 '*' 대신 컬럼명을 사용해야한다.
 
 예)사원테이블에서 각 부서별 인원수를 구하시오.
 SELECT DEPARTMENT_ID AS 부서, 
        COUNT(*) AS 인원수,
        COUNT(EMP_NAME) AS 사원수
   FROM EMPLOYEES
   GROUP BY DEPARTMENT_ID
   ORDER BY 1;
   
예) 2005월 6월 제품별 판매건수를 조회하시오.
SELECT CART_PROD AS 상품코드,
       COUNT(*) AS 판매건수
  FROM CART
  WHERE CART_NO LIKE '200506%'
  GROUP BY CART_PROD
  ORDER BY 1;
  
예)2005년 6월 모든 제품별 판매건수를 조회하시오.
    Alias는 상품코드,상품명, 판매건수
   SELECT PROD_ID AS 상품코드,
        PROD_NAME AS 상품명,
       COUNT(CART_MEMBER) AS 판매건수
  FROM CART
  RIGHT OUTER JOIN PROD ON(CART_PROD=PROD_ID
        AND CART_NO LIKE '200506')
  GROUP BY PROD_ID, PROD_NAME
  ORDER BY 1;

예제)상품테이블에서 각 분류별 상품의 수를 조회하시오.
SELECT PROD_LGU AS 상품분류, 
       COUNT(*) AS "상품의 수"
  FROM PROD
  GROUP BY PROD_LGU
  ORDER BY 1;

예제)회원테이블에서 각 연령대별 회원 수를 조회하시오.
SELECT TRUNC(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM MEM_BIR),-1)||'대' AS 연령, 
       COUNT(*) AS 회원수
  FROM MEMBER
 GROUP BY TRUNC(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM MEM_BIR),-1)
 ORDER BY 1;
 
예제)회원테이블에서 직업 종류별 회원수를 구하시오.
SELECT MEM_JOB AS 직업,
       COUNT(*) AS "종류별 회원수",
  FROM MEMBER
 GROUP BY MEM_JOB
 ORDER BY 1;
     
예제) 회원테이블에서 직업의 종류를 출력하시오.
SELECT DISTINCT MEM_JOB AS "직업 종류" --DISTINCT 여러 개가 중복되면 최소한의 한 개만 출력
  FROM MEMBER
  GROUP BY MEM_JOB;
  
4. MAX(column), MIN(column)
 - 'column'컬럼에 저장된 값 중 최대값과 최소값을 구하여 반환
 - 내부적으로 계산하는 방식은 'column'을 기준으로 오름차순 정렬(MIN),또는 내림차순 정렬 후 그 중 첫번째 행의 값을 반환
   따라서 처리시간이 다소 많이 소요된다.
** 의사컬럼 ROWNUM
 - 쿼리 결과(뷰)의 각 행에 부여된 순번 값
 - 상위 5개 또는 하위 5개 등 필요한 갯수의 결과만을 출력할 때 사용한다.(다른 DBMS에서는 TOP 함수로 제공됨)
 
 예)회원별 마일리지 중 최대 마일리지 값을 구하시오.
 SELECT MAX(MEM_MILEAGE) AS "최대 마일리지"
   FROM MEMBER;
   
 SELECT A.MILE
   FROM (SELECT MEM_MILEAGE AS MILE
            FROM MEMBER 
            ORDER BY 1 DESC) A
   WHERE ROWNUM = 1;
   
예) 사원 테이블에서 부서별 최대급여와 최소급여를 조회하시오.
SELECT DEPARTMENT_ID AS 부서, 
       MAX(SALARY) AS 최대급여, 
       MIN(SALARY) AS 최소급여
  FROM EMPLOYEES
  GROUP BY DEPARTMENT_ID
  ORDER BY 1;
  