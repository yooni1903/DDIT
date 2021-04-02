2020-12-16-01)
2) 반복문
 - 애플리케이션 개발언어의 반복문과 같은 기능 제공
 - loop, while, for 문이 제공됨
 
 (1) loop문
   . 반복문 중 가장 기본적인 구조 제공
   . 반복문 중 가장 기본적인 구조 제공
   (사용 형식)
   LOOP
    반복처리문(들);
    EXIT [THEN 조건];
        :
   END LOOP;
    - 'EXIT [WHEN 조건];' : 조건이 참이라면 LOOP문을 벗어나고, 거짓이면 다음 명령 수행
    
 예) 구구단의 5단을 출력하는 로직을 LOOP구문을 사용하여 작성하시오.
 
 DECLARE
    V_BASE NUMBER := 5; -- 단을 보관하는 변수
    V_CNT NUMBER := 0; -- 곱해지는 수(1~9) 보관
    V_RES NUMBER := 0; -- 결과 보관
 BEGIN
    LOOP
      V_CNT := V_CNT + 1;
      EXIT WHEN V_CNT > 9;
      V_RES := V_BASE * V_CNT;
      DBMS_OUTPUT.PUT_LINE(V_BASE || '*' || V_CNT || '=' || V_RES);
    END LOOP;
 END;
 
 
 예) 부서번호를 입력 받아 해당 부서에 소속된 사원번호와 사원이름을 출력하는 커서를 정의하시오.
 
 ACCEPT P_ID PROMPT '부서번호 : '
 DECLARE
  V_EMPID EMPLOYEES.EMPLOYEE_ID%TYPE;
  V_NAME EMPLOYEES.EMP_NAME%TYPE; 
  CURSOR CUR_EMP02(P_DEPT EMPLOYEES.DEPARTMENT_ID%TYPE)
  IS
    SELECT EMPLOYEE_ID, EMP_NAME
      FROM EMPLOYEES
     WHERE DEPARTMENT_ID = P_DEPT;
 BEGIN
  OPEN CUR_EMP02('&P_ID');
  
  LOOP
   FETCH CUR_EMP02 INTO V_EMPID, V_NAME;
   EXIT WHEN CUR_EMP02%NOTFOUND;
   
   DBMS_OUTPUT.PUT_LINE(V_EMPID || ', ' || V_NAME);
   DBMS_OUTPUT.PUT_LINE('------------------------------------');
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('처리 건수 : ' || CUR_EMP02%ROWCOUNT);
  CLOSE CUR_EMP02;
 END;
  
  
  
  (2) WHILE 문
    . 개발언어의 WHILE 과 같은 기능 제공
    (사용형식)
    WHILE 조건 LOOP
        반복처리 명령문(들);
            :
    END LOOP;
      .'조건'이 참이면 반복 수행, '조건'이 거짓이면 LOOP를 벗어남
      
      
    예) 구구단의 5단을 WHILE을 이용하여 작성
    
    DECLARE
      V_CNT NUMBER := 1;
    BEGIN
      WHILE V_CNT <= 9 LOOP
        DBMS_OUTPUT.PUT_LINE('5*' || V_CNT || '=' || 5 * V_CNT);
        V_CNT := V_CNT + 1;
      END LOOP;
    END;

    예) 1~50 사이에 존재하는 FIBONACCI NUMBER를 인쇄하시오.
        PIBONACCI NUMBER : 1, 1은 주어지고 그 이후 수는 전 두 수의 합이 현재 수인 수열이다
  
  DECLARE
    VP_NUM NUMBER := 1; -- 전(前) 수
    VPP_NUM NUMBER := 1; -- 전전 수
    VCUR_NUM NUMBER := 0; -- 현재 수
    V_RES VARCHAR2(100); 
  BEGIN
    V_RES := '1, 1, '; -- 결과
    WHILE TRUE LOOP
      VCUR_NUM := VP_NUM+VPP_NUM;
      IF VCUR_NUM > 50 THEN
        EXIT;
      ELSE
         V_RES := V_RES || (TO_CHAR(VCUR_NUM) || ', ');
         VPP_NUM := VP_NUM;
         VP_NUM := VCUR_NUM;
      END IF;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('피보나치 수 : ' || V_RES);
  END;
  
  
  예) 회원 테이블에서 거주지가 서울인 회원을 찾아그 회원의 2005년 매출 정보를 조회하시오.
  DECLARE 
    V_ID MEMBER.MEM_ID%TYPE;
    V_NAME MEMBER.MEM_NAME%TYPE;
    V_TOT NUMBER := 0; -- 구매 총합계
    CURSOR CUR_CART01
    IS
      SELECT MEM_ID, MEM_NAME
        FROM MEMBER
       WHERE MEM_ADD1 LIKE '충남%';
  BEGIN
    OPEN CUR_CART01;
    FETCH CUR_CART01 INTO V_ID, V_NAME;
    WHILE CUR_CART01%FOUND LOOP
      -- V_ID에 저장된 회원의 매출액 계산
      SELECT SUM(A.CART_QTY * B.PROD_PRICE) INTO V_TOT
        FROM CART A, PROD B
       WHERE A.CART_PROD = B.PROD_ID
         AND A.CART_MEMBER = V_ID
         AND A.CART_NO LIKE '2005%';
    
    DBMS_OUTPUT.PUT_LINE('회원번호 : ' || V_ID);
    DBMS_OUTPUT.PUT_LINE('회원명 : ' || V_NAME);
    DBMS_OUTPUT.PUT_LINE('구매금액 : ' || V_TOT);
    DBMS_OUTPUT.PUT_LINE('--------------------------------');
    
    FETCH CUR_CART01 INTO V_ID, V_NAME; -- 다음 사람 정보를 읽어온다.
    END LOOP;
    
  END;
  
  
  (3) FOR 문
    . 제어변수(INDEX)를 이용한 반복 수행
    . 제어변수(INDEX)는 시스템에서 자동설정(선언 불필요)
    (사용 형식)
    FOR index IN [REVERSE] 초기값..최종값 LOOP
      반복처리 명령문(들);
    EMD LOOP;
    
예) 오늘의 요일을 구하는 프로그램을 작성
    1) 서기 1년 1월 1일부터 전년(2019년) 12월 31일까지 경과된 일 수
    2) 올해 1월 1일부터 전월 마지막 일까지 일 수
    3) 금월 1일부터 오늘까지 일 수
    -----------------------------------------------------------------
    1)2)3)의 합계를 7로 나눈 나머지 계산
    
DECLARE
  V_TOT NUMBER := 0;
  V_YEAR NUMBER := EXTRACT(YEAR FROM SYSDATE);
  V_MONTH NUMBER := EXTRACT(MONTH FROM SYSDATE);
  V_DATE NUMBER := EXTRACT(DAY FROM SYSDATE);
  V_MI VARCHAR2(50); -- 요일 저장
BEGIN
--          1) 서기 1년 1월 1일부터 전년(2019년) 12월 31일까지 경과된 일 수
  FOR Y IN 1..V_YEAR-1 LOOP
    IF MOD(Y, 4) = 0 AND MOD(Y, 100) <> 0 OR MOD(Y,400) = 0 THEN
        V_TOT := V_TOT + 366; -- 윤년
    ELSE
        V_TOT := V_TOT + 365; -- 평년
    END IF;
  END LOOP;
  
--           2) 올해 1월 1일부터 전월 마지막 일까지 일 수
  FOR M IN 1..V_MONTH-1 LOOP
    IF M=1 OR M=3 OR M=5 OR M=7 OR M=8 OR M=10 OR M=12 THEN
        V_TOT := V_TOT + 31;
    ELSIF M=4 OR M=6 OR M=9 OR M=11 THEN
        V_TOT := V_TOT + 30;
    ELSE -- 2월
        IF (MOD(V_YEAR,4) = 0 AND MOD(V_YEAR,100) <> 0) OR MOD(V_YEAR, 400) = 0 THEN
            V_TOT := V_TOT + 29;
        ELSE
            V_TOT := V_TOT + 28;
        END IF;
    END IF;
  END LOOP;
  
--           3) 금월 1일부터 오늘까지 일 수
  V_TOT := V_TOT + V_DATE;
  CASE MOD(V_TOT, 7) WHEN 1 THEN V_MI := SYSDATE || '는 월요일'; -- CASE WHEN MOD(V_TOT, 7) = 1 THEN V_MI := SYSDATE || '는 월요일'; 이라고 작성할 수도 있음.
                     WHEN 2 THEN V_MI := SYSDATE || '는 화요일';
                     WHEN 3 THEN V_MI := SYSDATE || '는 수요일';
                     WHEN 4 THEN V_MI := SYSDATE || '는 목요일';
                     WHEN 5 THEN V_MI := SYSDATE || '는 금요일';
                     WHEN 6 THEN V_MI := SYSDATE || '는 토요일';
                     ELSE V_MI := SYSDATE || '는 일요일';
  END CASE;
  DBMS_OUTPUT.PUT_LINE(V_MI);

  
END;

**커서와 사용되는 FOR 문
 (1) 사용형식
   FOR 레코드명 IN 커서명 [(매개변수), ...] LOOP
       반복처리문;
   END LOOP;
   . '레코드명'은 시스템이 자동으로 할당함
   . 커서 내의 컬럼 접근은 '레코드명.컬럼명' 형식으로 접근한다.
   . OPEN, FETCH, CLOSE 문이 불필요함

예) 부서번호를 입력 받아 사원번호, 사원명, 부서명을 출력하는 익명블록 작성
DECLARE
  CURSOR CUR_EMP03(P_DID EMPLOYEES.DEPARTMENT_ID%TYPE)
  IS
    SELECT A.EMPLOYEE_ID AS EID, A.EMP_NAME AS ENAME, B.DEPARTMENT_NAME AS DNAME
      FROM EMPLOYEES A, DEPARTMENTS B
     WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
       AND A.DEPARTMENT_ID = P_DID;
BEGIN
  FOR REC IN CUR_EMP03(60) LOOP
    DBMS_OUTPUT.PUT_LINE(REC.EID || ', ' || REC.ENAME || ', ' || REC.DNAME);
  END LOOP;
END;
   
 ** 커서와 사용되는 INLINE FOR문   
 사용형식
   FOR 레코드명 IN 커서정의문 LOOP
       반복처리문;
   END LOOP;
   . '레코드명'은 시스템이 자동으로 할당함
   . 커서 내의 컬럼 접근은 '레코드명.컬럼명' 형식으로 접근한다.
   . OPEN, FETCH, CLOSE 문이 불필요함

ACCEPT P_DID PROMPT '부서번호(10~110) : ' 
DECLARE

BEGIN
  FOR REC IN (SELECT A.EMPLOYEE_ID AS EID,               -- 커서명도 필요없다
                     A.EMP_NAME AS ENAME, 
                     B.DEPARTMENT_NAME AS DNAME
                FROM EMPLOYEES A, DEPARTMENTS B
               WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
                 AND A.DEPARTMENT_ID = '&P_DID') LOOP
    DBMS_OUTPUT.PUT_LINE(REC.EID || ', ' || REC.ENAME || ', ' || REC.DNAME);
  END LOOP;
END;
    
