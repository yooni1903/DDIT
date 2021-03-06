2020-11-26-02) 형 변환 함수
1. CAST(expr AS 타입[(크기)])
 - 'expr'로 제공되는 자료를 '타입[(크키)]' 형식으로 형변환
 - 형변환은 CAST 가 사용된 위치에서 일시적으로 변환
 예) 분류테이블의 LPROD_ID 컬럼명 데이터 타입을 NUMBER(7)에서 CHAR(10)으로 변경 출력하시오.
SELECT CAST(LPROD_ID AS CHAR(10)) AS 순번,
       LPROD_GU AS 분류코드,
       LPROD_NM AS 분류명
  FROM LPROD;
  --문자열은 항상 왼쪽 정렬, 숫자열을 항상 오른쪽 정렬
예) 사원테이블에서 부서코드 50에 속한 사원들의 근속년수를 조회하시오.
    Alias는 사원번호, 사원명, 입사일,근속년수이며, 근속년수는 'XX년'형식의 문자열로 변환출력하시오.
SELECT EMPLOYEE_ID AS 사원번호,
       EMP_NAME AS 사원명, 
       HIRE_DATE AS 입사일 ,
       CAST(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) AS CHAR(2)) ||'년' AS 근속년수
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID = 50;
 
 2. TO_CHAR(c) : CHAR, CLOB 타입의 자료 'c'를 VARCHAR2 타입의 문자열로 변환
    TO_CHAR(d[,fmt]) : 날짜 자료 d를 fmt 형식의 문자열로 변환
    TO_CHAR(n[,fmt]) : 숫자 자료 'n'을 fmt 형식의 문자열로 변환
     - 날짜형식 지정문자열
  ------------------------------------------------------------------------
    형식문자열                   의미                    사용예
  ------------------------------------------------------------------------
  AD, BC, CC                   세기를 출력               TO_CHAR(SYSDATE,'CC')
    ex) SELECT TO_CHAR(SYSDATE, 'CC'),
               TO_CHAR(SYSDATE, 'AD'),
               TO_CHAR(SYSDATE, 'BC')
               FROM DUAL;
               
  YYYY,YYY,YY,Y                년도 출력                 TO_CHAR(SYSDATE,'YY')
    ex) SELECT TO_CHAR(SYSDATE, 'YYYY'),
               TO_CHAR(SYSDATE, 'YYY'),
               TO_CHAR(SYSDATE, 'Y')
               FROM DUAL;   
               
  Q                            분기                     TO_CHAR(SYSDATE,'YY Q')
    ex) SELECT TO_CHAR(SYSDATE, 'MM"월은" Q"분기"') FROM DUAL;
  MONTH,MON,MM RM              월                       TO_CHAR(SYSDATE,'YYYY-MM')
    ex) SELECT TO_CHAR(SYSDATE, 'YYYY-MM'),
               TO_CHAR(SYSDATE, 'YYYY-RM'),
               TO_CHAR(SYSDATE, 'YYYY-MON'),
               TO_CHAR(SYSDATE, 'YYYY-MONTH')
               FROM DUAL;
               
  W,WW, IW                      주차
    ex) SELECT TO_CHAR(SYSDATE, 'W'), --해당 월에서 경과되어진 주차수
               TO_CHAR(SYSDATE, 'WW'), -- 1월1일에서부터 경과되어진 주차수
               TO_CHAR(SYSDATE, 'IW')
               FROM DUAL;
               
  D,DD,DDD,J                    일
    D : 해당 일이 속한 주에서 경과된 일수
    DD : 해당 일이 속한 월에서 경과된 일수
    DDD : 해당 일이 속한 년에서 경과된 일수
    J : 기원전 4712년 이후 경과된 일수
    ex) SELECT TO_CHAR(SYSDATE,'D'),
               TO_CHAR(SYSDATE,'DD'),
               TO_CHAR(SYSDATE,'DDD'),
               TO_CHAR(SYSDATE,'J')
               FROM DUAL;
               
   DAY,DY                       주의 요일
    ex) SELECT TO_CHAR(SYSDATE, 'DAY'),
               TO_CHAR(SYSDATE, 'DY')
               FROM DUAL;
               
   HH,HH24,HH12                 시간 (HH와 HH12는 같은 형식)
   AM, PM, A.M, P.M             오전, 오후 표현
   MI                           분
   SS,SSSSS                     초('SSSSS' : 자정 이후 경과된 시간을 초로 반환)
   기타                          사용자가 임의로 정한 문자열은 반드시 " " 안에 기술하여야 한다.
   ---------------------------------------------------------------------------------------------
   SELECT TO_CHAR(MEM_BIR),
          TO_CHAR(MEM_BIR, 'CC"세기 "YYYY-MM-DD DAY')
    FROM MEMBER;
 - 숫자형식 지정문자열
  ------------------------------------------------------------------------
    형식문자열                의미                    
  ------------------------------------------------------------------------
        9                    대응되는 위치의 값이 유효한 값이면 데이터를 출력하고 
                             무효의 0은 공백처리
        0                    대응되는 위치의 값이 유효한 값이면 데이터를 출력하고 
                             무효의 0도 출력
        $,L(미국 외의 화폐)    화폐기호를 출력
        MI                   음수 출력인 경우 우측에 '-'부호 출력
        PR                   음수 출력인 경우 '-'대신 '< >' 안에 출력
    ,(콤마), .(소수점)     
   -----------------------------------------------------------------------
   예) 2005년 2월 제품별 매입현황을 조회하시오.
       Alias는 제품코드, 매입수량,매입금액
       SELECT BUY_PROD AS 제품코드,
              TO_CHAR(SUM(BUY_QTY), '999') AS 매입수량,
              TO_CHAR(SUM(BUY_QTY*BUY_COST),'L99,999,999') AS 매입금액
         FROM BUYPROD
        WHERE BUY_DATE BETWEEN '20050201' AND LAST_DAY('20050201')
        GROUP BY BUY_PROD
        ORDER BY 1;
   000890000
   999,999,999.99
   000,000,000.00
   --------------
       890,000.00
   000,890,000.00
   
   
   
   
   
   