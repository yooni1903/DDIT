2020-12-01-01
2. CUBE(col1, col2, ...)
 - 다양한 집계처리(2^사용된 컬럼수)
 - ROLLUP과 사용방식은 같으나 레벨이 적용되지 않는다.
 예) 대출잔액테이블(KOR_LOAN_STATUS)에서 기간, 지역, 대출구분 항목을 이용하여 조합 가능한 모든 집계를 조회
 SELECT PERIOD, REGION, GUBUN, SUM(LOAN_JAN_AMT)
   FROM KOR_LOAN_STATUS
 GROUP BY CUBE(PERIOD, REGION, GUBUN)
 ORDER BY 1,2;