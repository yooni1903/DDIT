2020-11-26-01 날짜형 함수
1. SYSDATE
 - 시스템이 제공하는 기본 날짜형
 - 년, 월, 일, 시, 분, 초 정보제공
 - '+'와 '-' 연산의 대상
 예)
 SELECT SYSDATE, SYSDATE+10, SYSDATE-200 FROM DUAL;
 
 2. ADD_MONTHS(d,n)
 - 'd'로 주어진 날짜에서 'n'월수를 더한 날짜를 반환
 예) 회원 테이블에서 MEM_MEMORIALDAY 컬럼이 가입일이라고 가정했을 때, 
     모든 회원의 유효기간이 3개월이며 모두 재등록할 경우 재등록 날짜 10일 전에 문자데이터를 전송하고자 한다.
     각 회원의 문자 전송 시작일을 구하시오.
     Alias는 회원번호, 회원명, 가입일,종료일,문자전송일
     
     SELECT MEM_ID AS 회원번호, 
            MEM_NAME AS 회원명, 
            MEM_MEMORIALDAY AS 가입일,
            ADD_MONTHS(MEM_MEMORIALDAY, 3) AS 종료일,
            ADD_MONTHS(MEM_MEMORIALDAY, 3) - 10 AS 문자전송일
       FROM MEMBER;
       
    SELECT ADD_MONTHS(SYSDATE, -4) FROM DUAL;
    
3. NEXT_DAY(d,char)
 - 주어진 날짜 'd' 이후 처음 char요일을 만나는 날짜
 SELECT NEXT_DAY(SYSDATE, '금') FROM DUAL;
  SELECT NEXT_DAY(SYSDATE, '목요일') FROM DUAL;
  
4. LAST_DAY(d)
 - 주어진 날짜 'd'의 월에 해당하는 마지막 일자를 반환
 SELECT LAST_DAY(SYSDATE), LAST_DAY('20000210') FROM DUAL;
 
5. MONTHS_BETWEEN(d1, d2)
 - 두 날짜 자료 'dL'과 'd2'사이의 개월수를 반환
 SELECT ROUND(MONTHS_BETWEEN(TRUNC(SYSDATE), '00010101')) FROM DUAL;
 
6. EXTRACT(fmt, FROM d)
 - 주어진 날짜 데이터 'd'에서 fmt로 정의된 것을 추출하여 반환
 - fmt는 YEAR,MONTH,DAY,HOUR,MINUTE,SECOND 이다.
 - 반환 데이터 타입은 숫자형식
 예)매입테이블에서 2005년도 월별 매입정보를 조회하시오.
    Alias는 월, 매입수량, 매입금액
SELECT EXTRACT(MONTH FROM BUY_DATE) AS 월, 
       COUNT(*) AS 건수,
       SUM(BUY_QTY) AS 매입수량, 
       SUM(BUY_QTY * BUY_COST) AS 매입금액
  FROM BUYPROD
 WHERE EXTRACT(YEAR FROM BUY_DATE) = 2005
 GROUP BY EXTRACT(MONTH FROM BUY_DATE)
 ORDER BY 1;
 
 예제) 대출잔액테이블(KOR_LOAN_STATUS)에서 2013년 대출현황을 조회하시오.
      Alias는 월, 지역, 구분, 대출잔액 -- 월은 일자가 없기 때문에 날짜 타입이 아니라 문자타입이다!
SELECT SUBSTR(PERIOD, 5) AS 월, 
       REGION AS 지역, 
       GUBUN AS 구분, 
       LOAN_JAN_AMT AS 대출잔액
  FROM KOR_LOAN_STATUS
 WHERE SUBSTR(PERIOD,1,4) = '2013'
 ORDER BY 1,3;

예) 사원테이블에서 11월에 입사한 사원정보를 조회하시오.
    Alias는 사원번호, 사원명, 부서코드, 이메일이다.
SELECT EMPLOYEE_ID AS 사원번호,
       EMP_NAME AS 사원명, 
       DEPARTMENT_ID AS 부서코드, 
       EMAIL AS 이메일
  FROM EMPLOYEES
 WHERE EXTRACT(MONTH FROM HIRE_DATE) = EXTRACT(MONTH FROM SYSDATE)
 ORDER BY 1,3;