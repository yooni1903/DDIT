2020-12-22-01)
**의사 레코드
    1) :new
     - insert와 update에서 사용
     - 데이터가 삽입(갱신)될 때 새로 입력된 값을 지칭함
     - delete 시에는 모든 컬럼이 null값이 됨
     
    2) :old
     - delete와 update에서 사용
     - 데이터가 삭제(갱신)될 때 저장되어 있던 값
     - insert 시에는 모든 컬럼이 null값이 됨
     
**트리거 함수
 - 트리거를 촉발시킨 event의 종류를 판단할 때 사용
 1) inserting : 트리거된 문장이 insert이면 true
 2) uppdating : 트리거된 문장이 update이면 true
 3) deleting : 트리거된 문장이 delete이면 true
 ex)
CREATE OR REPLACE TRIGGER TG_CART_INSERT
    AFTER INSERT OR UPDATE OR DELETE ON CART -- CART테이블에 자료가 삽입되어졌을 때 이후 트리거 발생
    FOR EACH ROW
BEGIN
    IF INSERTING THEN
     UPDATE REMAIN
       SET REMAIN_O = REMAIN_O + :NEW.CART_QTY,
           REMAIN_J_99 = REMAIN_J_99 - :NEW.CART_QTY,
            REMAIN_DATE = '20050401'
     WHERE REMAIN_PROD = :NEW.CART_PROD
       AND REMAIN_YEAR = '2005';
    ELSIF UPDATING THEN
    ELSE DELETING 
    END IF; 
END;

예) 오늘이 2005년 4월 20일이라 가정하고 매입상품 'P201000001'의 매입수량을 15개에서 25개로 수정하시오
    수정 후 재고수불테이블의 자료도 변경될 수 있도록 트리거를 작성하시오
(트리거 생성)
CREATE OR REPLACE TRIGGER TG_BUYPROD_UPDATE
    AFTER INSERT OR UPDATE OR DELETE ON BUYPROD
    FOR EACH ROW
DECLARE
    V_QTY NUMBER := 0; --제품 매입 수량
    V_PROD PROD.PROD_ID%TYPE; -- 상품 코드
BEGIN
    IF INSERTING THEN
        V_QTY := NVL(:NEW.BUY_QTY, 0);
        V_PROD := :NEW.BUY_PROD;
    ELSIF UPDATING THEN
        V_QTY := :NEW.BUY_QTY - :OLD.BUY_QTY;
        V_PROD := :NEW.BUY_PROD;
    ELSE 
        V_QTY := :OLD.BUY_QTY;
        V_PROD := :OLD.BUY_PROD;
    END IF;
    
    UPDATE REMAIN 
       SET REMAIN_I = REMAIN_I + V_QTY,
           REMAIN_J_99 = REMAIN_J_99 + V_QTY
     WHERE REMAIN_YEAR = '2005'
       AND REMAIN_PROD = V_PROD;
       
       DBMS_OUTPUT.PUT_LINE('추가 변경 수량: ' || V_QTY);
       
       EXCEPTION 
        WHEN OTHERS THEN
         DBMS_OUTPUT.PUT_LINE('예외 발생 : ' || SQLERRM);
END;

(매입장 갱신또는 삽입)
DECLARE
    V_CNT NUMBER := 0; -- 2005년 4월 20일 'P201000001'상품정보 존재 여부 판단
    V_QTY NUMBER := 10;
BEGIN
    SELECT COUNT(*) INTO V_CNT
      FROM BUYPROD
     WHERE BUY_PROD = 'P201000001'
       AND BUY_DATE = TO_DATE('20050420'); -- 이 날짜에 판매한 상품이 있다면 COUNT는 1이되고 (같은 날 상품 중복 구매가 안되기때문) 없으면 0임
       
    IF V_CNT = 1 THEN --UPDATE필요
        UPDATE BUYPROD
           SET BUY_QTY = BUY_QTY + V_QTY
         WHERE BUY_PROD = 'P201000001'
           AND BUY_DATE = TO_DATE('20050420');
    ELSE
        INSERT INTO BUYPROD
            VALUES ('20050420', 'P201000001', V_QTY,21000);
    END IF;
END;

**remain 테이블에 