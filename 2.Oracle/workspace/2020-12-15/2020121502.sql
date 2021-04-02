2020-12-15-02) 커서(CURSOR)
 - 쿼리의 실행에 영향받은 행들의 집합
 - 묵시적 커서와 명시적 커서로 구분
 
1) 묵시적 커서(IMPLICIT CURSOR)
  . SQL 명령이 실행되면 자동으로 생성되는 커서
  . 익명커서
  . 실행결과의 출력이 종료됨과 동시에 CLOSE되어 사용자가 접근할 수 없음
  . 커서 속성
  ------------------------------------------------------------------------------------
   속성명           설명
  ------------------------------------------------------------------------------------
   SQL%FOUND        커서에 하나의 행이라도 존재하면 참(TRUE) 반환
   SQL%NOTFOUND     커서에 하나의 행이라도 존재하면 거짓(FALSE) 반환
   SQL%ISOPEN       커서가 OPEN 되었으면 참(익명커서는 항상 FALSE)
   SQL%ROWCOUNT     커서에 포함된 행의 수
   
예) 회원 테이블에서 거주지가 '대전'인 회원의 이름을 자신의 이름으로 변경하고
    몇 건이 처리되었는지 확인하는 익명블록을 작성하시오
DECLARE

BEGIN
    UPDATE MEMBER
       SET MEM_NAME = MEM_NAME
     WHERE MEM_ADD1 LIKE '대전%';
     
     DBMS_OUTPUT.PUT_LINE('처리 건수 : ' || SQL%ROWCOUNT);
END;
 
 SELECT COUNT(*)
   FROM MEMBER
  WHERE MEM_ADD1 LIKE '대전%';

** 선언부에서 선언할 수 있는 3가지 : 변수, 상수, 커서   


2) 명시적 커서(EXPLICIT CURSOR)
   . 사용자가 선언부에서 선언한 커서
   . 커서의 사용 단계는 생성 -> OPEN ->FETCH -> CLOSE 이다.
   . 커서 결과 집합을 행 단위로 접근하여 참조된 데이터를 이용한 조작처리가 목적 (SELECT 문에 의한 커서 생성)
   
(1) 생성
   (선언 형식)
   CURSOR 커서명[(매개변수 [,매개변수, ...])]
      IS SELECT 문;

예) 부서번호를 입력받아 해당 부서에 소속된 사원번호와 사원이름을 출력하는 커서를 정의하시오.
DECLARE
    CURSOR CUR_EMP01(P_DEPTID DEPARTMENTS.DEPARTMENT_ID%TYPE)
    IS
        SELECT EMPLOYEE_ID,
               EMP_NAME
          FROM EMPLOYEES
         WHERE DEPARTMENT_ID = P_DEPTID;
    
    
(2) OPEN
    . 커서를 사용하기 전에 반드시(FOR 문 제외) OPEN 해야함
    . OPEN 명령은 실행부(BEGIN ~ END)에서 작성
        
    (사용 형식)
    OPEN 커서명[(매개변수 [, 매개변수, ...])];
        
예) 2005년 1월 분류코드별 매입수량과 매입금액합계를 구하는 커서
    DECLARE
        V_GU LPROD.LPROD_GU%TYPE; -- 분류코드
        V_NAME LPROD.LPROD_NM%TYPE; --분류명
        V_AMT NUMBER := 0; --매입수량
        V_SUM NUMBER := 0; -- 매입금액합계
    
        CURSOR CUR_BUY01
        IS
            SELECT LPROD_GU, 
                   LPROD_NM
              FROM LPROD;
    BEGIN
        OPEN CUR_BUY01;        
        LOOP
            FETCH CUR_BUY01 INTO V_GU, V_NAME;
            EXIT WHEN CUR_BUY01%NOTFOUND;
            SELECT NVL(SUM(BUY_QTY * BUY_COST),0), NVL(SUM(BUY_QTY),0) INTO V_SUM, V_AMT
              FROM BUYPROD, PROD
             WHERE BUY_PROD = PROD_ID
               AND PROD_LGU = V_GU
               AND BUY_DATE BETWEEN '20050101' AND '20050131';
            
            DBMS_OUTPUT.PUT_LINE(V_NAME || '->' || V_AMT || ', ' || V_SUM);
            DBMS_OUTPUT.PUT_LINE('-----------------------------------');
        END LOOP;
        DBMS_OUTPUT.PUT_LINE('처리건수 : ' || CUR_BUY01%ROWCOUNT);
        CLOSE CUR_BUY01;
    END;

















    