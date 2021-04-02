2020-12-21-02) 트리거(TRIGGER)
 - 어떤 이벤트가 발생되면 그 이벤트로 인하여 
   다른 테이블의 값이 자동으로 변경(삽입/UPDATE, 삭제) 되도록 구성된 프로시져
 - 데이터 무결성 유지를 위해 사용
 (사용 형식)
 CREATE TRIGGER 트리거명
    [BEFORE | AFTER] [INSERT | UPDATE | DELETE]
    ON 테이블명
    [FOR EACH ROW]
    [WHEN 조건]
[DECLARE
    선언부]
BEGIN
    트리거 처리문;
END;
. 'BEFORE | AFTER' : 트리거의 타이밍(TIMMING), 생략하면 AFTER로 간주
                     트리거 수행(트리거 처리문)이 이벤트 발생 전이면 BEFORE,
                                                  이벤트 발생 후면 AFTER 를 기술
. 'INSERT | UPDATE | DELETE' : 트리거 이벤트, 트리거를 발생시키는 원인으로 OR 연산자를 이용하여 복수 개의 정의 가능
                               (EX. INSERT OR DELETE)
. 'FOR EACH ROW' : 행 단위 트리거 발생시 기술, 생략하면 문장단위 트리거
. 'WHEN 조건' : 행 단위 트리거에서만 사용하며 트리거 이벤트에서 정의된 테이블에 이벤트가 발생할 때보다 
                구체적인 데이터 검색 조건 부여시 사용
** 행 단위와 문장 단위 트리거
  (1) 문장단위 트리거 : 이벤트 발생시 오직 한번만 트리거 발생(많이 사용하지 않음)
  (2) 행 단위 트리거 : 'FOR EACH ROW' 기술 
                       이벤트 결과 각 행마다 트리거 수행,
                       의사 레코드(PESUDO RECORD)인 :NEW, :OLD 사용 가능
                       대부분의 트리거가 속함
                       단, 한 트리거 수행이 완료되지 않은 상태에서 또 다른 트리거를 호출할 경우, 시스템에 의해 트리거를 강제 종료

예) 분류테이블에 새로운 자료를 입력하고 입력이 정상적으로 처리되었으면 
    '신규 분류자료가 정상 입력되었습니다!!'라고  메시지를 출력하는 트리거를 작성하시오.
    [자료]
    분류코드 : P502
    순번 : 12
    분류명 : 농산물
    
CREATE TRIGGER TG_LPOD01
    AFTER INSERT ON LPROD
BEGIN
    DBMS_OUTPUT.PUT_LINE('신규 분류자료가 정상 입력되었습니다!!');
END;


INSERT INTO LPROD
    VALUES(12, 'P502', '농산물');
SELECT * FROM LPROD;

예) 입고테이블(BUYPROD)에서 2월과 3월 입고된 상품별 매입수량을 조회하여 재고수불테이블을 수정하시오.
(서브 쿼리 : 2월 3월에 발생되어진 상품별 매입수량을 조회)
SELECT BUY_PROD,
       SUM(BUY_QTY)
  FROM BUYPROD
 WHERE BUY_DATE BETWEEN '20050201' AND '20050331'
 GROUP BY BUY_PROD
 ORDER BY 1;
 
(메인 쿼리 : REMAIN 테이블 UPDATE)
UPDATE REMAIN A
   SET (A.REMAIN_I, A.REMAIN_J_99, A.REMAIN_DATE) =
       (SELECT A.REMAIN_I + B.IAMT, A.REMAIN_J_99 + B.IAMT, TO_DATE('20050331')
          FROM (SELECT BUY_PROD AS BID,
                       SUM(BUY_QTY) AS IAMT
                  FROM BUYPROD
                 WHERE BUY_DATE BETWEEN '20050201' AND '20050331'
              GROUP BY BUY_PROD)B
         WHERE A.REMAIN_PROD = B.BID)
 WHERE A.REMAIN_YEAR = '2005'
   AND A.REMAIN_PROD IN (SELECT DISTINCT BUY_PROD
                           FROM BUYPROD
                          WHERE BUY_DATE BETWEEN '20050201' AND '20050331');
 
 SELECT * FROM REMAIN;
 
 ROLLBACK;
 
예) 오늘이 2005년 4월1일이라고 가정하고 다음 자료를 장바구니 테이블에 입력하시오
    장바구니 테이블에 입력된 후 재고수불테이블을 수정하시오.
    
    입력자료 : (29, 21, 0, 50, 2005-01-31 : REMAIN 테이블)
        구매회원 : c001
        구매상품 : P302000014
        구매수량 : 5
   --------------------------
(트리거 작성)
CREATE OR REPLACE TRIGGER TG_CART_INSERT
    AFTER INSERT ON CART -- CART테이블에 자료가 삽입되어졌을 때 이후 트리거 발생
    FOR EACH ROW
BEGIN
    UPDATE REMAIN
       SET REMAIN_O = REMAIN_O + :NEW.CART_QTY,
           REMAIN_J_99 = REMAIN_J_99 - :NEW.CART_QTY,
            REMAIN_DATE = '20050401'
     WHERE REMAIN_PROD = :NEW.CART_PROD
       AND REMAIN_YEAR = '2005';
END;

DROP TRIGGER TG_CART_INSERT;

(실행 : CART테이블에 자료가 삽입된 후)
INSERT INTO CART   
   SELECT 'c001', MAX(CART_NO) + 1, 'P302000014', 5
     FROM CART
    WHERE SUBSTR(CART_NO, 1, 8) = '20050401';

SELECT * FROM REMAIN;
SELECT * FROM CART;

ROLLBACK;