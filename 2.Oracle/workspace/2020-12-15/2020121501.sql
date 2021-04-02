2020-12-15-01) 제어문
    - 프로그램의 진행 순서를 변경
    - IF 문이 제공
  1) IF 문
   . 애플리케이션 언어의 IF문과 같은 기능

   (사용 형식1)
   IF 조건식1 THEN
     명령1;
   [ELSE
     명령2;]
   END IF;
   
   (사용 형식2)
   IF 조건식1 THEN
     명령1;
   [ELSIF 조건식2 THEN
     명령2;
    ELSE
     명령3;]
   END IF;
   
   (사용 형식3)
   IF 조건식1 THEN
     명령1;
        IF 조건식2 THEN
          명령2;
        ELSE
          명령3;
        END IF;
    ELSE
     명령4;
   END IF;
   
   
예) 키보드로 년도를 입력받아 윤년인지 평년인지 판별하는 프로그램을 작성하시오.

ACCEPT P_YEAR PROMPT '년도 입력 :' -- 키보드 입력 받는 명령
DECLARE
  V_YEAR NUMBER := 0; --> 입력받은 년도 보관
  --변수 선언할 때에 초기화하지 않으면 NULL값이 된다.
  V_MESSAGE VARCHAR2(30); --> 결과 저장
  
BEGIN
  V_YEAR := TO_NUMBER('&P_YEAR');
  -- 윤년인지 평년인지 판별
  -- 윤년(4의 배수이면서 100의 배수가 아니거나(OR), 400의 배수가 되는 년도) 판별
  IF (MOD(V_YEAR, 4) = 0 AND MOD(V_YEAR, 100) <> 0) OR (MOD(V_YEAR, 400) = 0)  THEN
    V_MESSAGE := V_YEAR || '는 윤년입니다.';
  ELSE
    V_MESSAGE := V_YEAR || '는 평년입니다.';
  END IF;
  
  DBMS_OUTPUT.PUT_LINE(V_MESSAGE);
  
  EXCEPTION WHEN OTHERS   THEN
     DBMS_OUTPUT.PUT_LINE('예외발생 : ' || SQLERRM);
    
END;
/

예) 임의의 정수를 (1~100) 입력하여 짝수인지 홀수인지 판단
DECLARE
    V_NUM NUMBER := 0;
    V_RES VARCHAR(50);
BEGIN
    V_NUM := ROUND(DBMS_RANDOM.VALUE(1, 100));
    IF MOD(V_NUM,2) = 0 THEN
        V_RES := V_NUM || '은 짝수';
    ELSE
        V_RES := V_NUM || '은 홀수';
    END IF;
    
    DBMS_OUTPUT.PUT_LINE(V_RES);
END;


예) LPROD 테이블에 다음 데이터를 입력하시오
    분류코드 : P501
    문류명 : '축산가공식품'
DECLARE
    V_CNT NUMBER := 0; -- SELECT문의 결과(VIEW)의 행의 수
BEGIN
    SELECT COUNT(*) INTO V_CNT
      FROM LPROD
     WHERE LPROD_GU = 'P501';
     
     IF V_CNT = 0 THEN
        INSERT INTO LPROD
            SELECT MAX(LPROD_ID) + 1, 'P501', '축산가공식품'
              FROM LPROD;
     END IF;
     COMMIT;
     
     EXCEPTION WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('예외발생' || SQLERRM);
END;


SELECT *
  FROM LPROD;

문제1) 위 예제에서 'P501' 분류코드에 분류명을 '임산물'로 입력하시오
        단, 자료가 존재하면 갱신하시오.

DECLARE
    V_CNT NUMBER := 0; -- SELECT문의 결과(VIEW)의 행의 수
BEGIN
    SELECT COUNT(*) INTO V_CNT
      FROM LPROD
     WHERE LPROD_GU = 'P501';
     
     IF V_CNT = 0 THEN
        INSERT INTO LPROD
            SELECT MAX(LPROD_ID) + 1, 'P501', '축산가공식품'
              FROM LPROD;
     ELSIF V_CNT = 1 THEN
        UPDATE LPROD
           SET LPROD_NM = '임산물'
         WHERE LPROD_GU = 'P501';
     END IF;
   
     
     EXCEPTION WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('예외발생' || SQLERRM);
END;

--(이름에 대한 변수를 선언하여 바꿔줄 때)
DECLARE
    V_CNT NUMBER := 0; -- SELECT문의 결과(VIEW)의 행의 수
    V_LPROD_NM LPROD.LPROD_NM%TYPE := '임산물'; -- 데이터타입은 참조형이다.
BEGIN
    SELECT COUNT(*) INTO V_CNT
      FROM LPROD
     WHERE LPROD_GU = 'P501';
     
     IF V_CNT = 0 THEN
        INSERT INTO LPROD
            SELECT MAX(LPROD_ID) + 1, 'P501', V_LPROD_NM
              FROM LPROD;
     ELSE
        UPDATE LPROD
           SET LPROD_NM = V_LPROD_NM
         WHERE LPROD_GU = 'P501';
    
     END IF;
     COMMIT;   
     EXCEPTION WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('예외발생' || SQLERRM);
END;

























