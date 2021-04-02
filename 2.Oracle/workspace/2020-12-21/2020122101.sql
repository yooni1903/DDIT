2020-12-21-01) 함수(user defined function : FUNCTION)
 - 프로시져와 비슷한 구조(단, 반환값이 존재해야 함)
 - 일반 내장함수처럼 사용 가능 
 (사용 형식)
 CREATE [OR REPLACE] FUNCTION 함수명(
    매개변수 [IN | OUT | INOUT] 타입명 [:= DEFAULT 값],     -- 타입의 크기를 정해줘서는 안된다.
                :
    매개변수 [IN | OUT | INOUT] 타입명 [:= DEFAULT 값]
    
    RETURN 타입명
 IS | AS
    선언부
 BEGIN
    실행부
    RETURN 값; -- 위의 RETURN 값의 타입과 반드시 같아야 한다.
 END;
     . 실행부에 반드시 하나 이상의 RETURN 문이 존재해야 함.
    
    
예) 상품테이블에서 상품코드를 입력받아 해당 상품의 2005년 매입수량을 조회하는 함수를 작성하시오
(함수처리영역 : 입력받은 상품코드에 해당하는 상품의 2005년 매입수량을 조회)
CREATE OR REPLACE FUNCTION FN_BUYQTY(
    P_CODE IN PROD.PROD_ID%TYPE)
    
    RETURN NUMBER
IS
    V_AMT  NUMBER(5) := 0; -- 2005년도 상품별 매입수량 집계
BEGIN
    SELECT SUM(BUY_QTY) INTO V_AMT
      FROM BUYPROD
     WHERE BUY_PROD = P_CODE
       AND BUY_DATE BETWEEN '20050101' AND '20051231';
    RETURN V_AMT;
END;
(실행)
SELECT PROD_ID AS 상품코드,
       PROD_NAME AS 상품명,
       FN_BUYQTY(PROD_ID) AS 매입수량
  FROM PROD
 WHERE FN_BUYQTY(PROD_ID) >= 100;

예) 상품테이블에서 상품코드를 입력받아 해당 상품의 2005년 매입수량과 매입금액을 조회하는 함수를 작성하시오  
    
CREATE OR REPLACE FUNCTION FN_BUYQTY(
    P_CODE IN PROD.PROD_ID%TYPE)
    
    RETURN VARCHAR2
IS
    V_AMT  NUMBER(5) := 0; -- 2005년도 상품별 매입수량 집계
    V_SUM  NUMBER := 0; -- 2005년 상품별 매입금액 합계
    V_RES  VARCHAR2(50); -- 결과가 들어갈 기억공간
BEGIN
    SELECT SUM(BUY_QTY), SUM(BUY_QTY * BUY_COST) INTO V_AMT, V_SUM
      FROM BUYPROD
     WHERE BUY_PROD = P_CODE
       AND BUY_DATE BETWEEN '20050101' AND '20051231';
    V_RES := '매입수량 : ' || TO_CHAR(V_AMT) || ', ' || '매입금액 : ' || TO_CHAR(V_SUM);
    RETURN V_RES;
END;
(실행)
SELECT PROD_ID AS 상품코드,
       PROD_NAME AS 상품명,
       FN_BUYQTY(PROD_ID) AS 매입현황
  FROM PROD;


예) 거주지가 충남인 회원들의 2005년도 상반기 매출액을 조회하시오
    (함수영역 :입력된 회원들의 2005년도 상반기 매출액을 조회)
CREATE OR REPLACE FUNCTION FN_CART01(
    P_MEMID IN MEMBER.MEM_ID%TYPE)
    
    
    RETURN NUMBER
IS
    V_SUM NUMBER := 0;
BEGIN
    SELECT SUM(CART_QTY * PROD_PRICE) INTO V_SUM
      FROM CART, PROD
     WHERE CART_PROD = PROD_ID
       AND CART_MEMBER = P_MEMID
       AND SUBSTR(CART_NO, 1, 6) BETWEEN '200501' AND '200506';
       
    RETURN V_SUM;
END;
(실행 : 거주지가 충남인 회원번호 검색)
SELECT MEM_NAME AS 회원명,
       NVL(FN_CART01(MEM_ID),0) AS 구입액합계
  FROM MEMBER
 WHERE MEM_ADD1 LIKE '충남%';
 
 
 
예)현재 계정에 존재하는 사용자 이름을 출력하는 함수를 작성하시오.
CREATE OR REPLACE FUNCTION FN_GET_USER
    RETURN VARCHAR2
IS
    V_NAME VARCHAR2(50);
BEGIN 
    SELECT USER INTO V_NAME
      FROM DUAL;
    RETURN V_NAME;
END;

(실행)
SELECT FN_GET_USER, FN_GET_USER()  FROM DUAL;
