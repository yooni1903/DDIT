2020-12-22-01)
**�ǻ� ���ڵ�
    1) :new
     - insert�� update���� ���
     - �����Ͱ� ����(����)�� �� ���� �Էµ� ���� ��Ī��
     - delete �ÿ��� ��� �÷��� null���� ��
     
    2) :old
     - delete�� update���� ���
     - �����Ͱ� ����(����)�� �� ����Ǿ� �ִ� ��
     - insert �ÿ��� ��� �÷��� null���� ��
     
**Ʈ���� �Լ�
 - Ʈ���Ÿ� �˹߽�Ų event�� ������ �Ǵ��� �� ���
 1) inserting : Ʈ���ŵ� ������ insert�̸� true
 2) uppdating : Ʈ���ŵ� ������ update�̸� true
 3) deleting : Ʈ���ŵ� ������ delete�̸� true
 ex)
CREATE OR REPLACE TRIGGER TG_CART_INSERT
    AFTER INSERT OR UPDATE OR DELETE ON CART -- CART���̺� �ڷᰡ ���ԵǾ����� �� ���� Ʈ���� �߻�
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

��) ������ 2005�� 4�� 20���̶� �����ϰ� ���Ի�ǰ 'P201000001'�� ���Լ����� 15������ 25���� �����Ͻÿ�
    ���� �� ���������̺��� �ڷᵵ ����� �� �ֵ��� Ʈ���Ÿ� �ۼ��Ͻÿ�
(Ʈ���� ����)
CREATE OR REPLACE TRIGGER TG_BUYPROD_UPDATE
    AFTER INSERT OR UPDATE OR DELETE ON BUYPROD
    FOR EACH ROW
DECLARE
    V_QTY NUMBER := 0; --��ǰ ���� ����
    V_PROD PROD.PROD_ID%TYPE; -- ��ǰ �ڵ�
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
       
       DBMS_OUTPUT.PUT_LINE('�߰� ���� ����: ' || V_QTY);
       
       EXCEPTION 
        WHEN OTHERS THEN
         DBMS_OUTPUT.PUT_LINE('���� �߻� : ' || SQLERRM);
END;

(������ ���ŶǴ� ����)
DECLARE
    V_CNT NUMBER := 0; -- 2005�� 4�� 20�� 'P201000001'��ǰ���� ���� ���� �Ǵ�
    V_QTY NUMBER := 10;
BEGIN
    SELECT COUNT(*) INTO V_CNT
      FROM BUYPROD
     WHERE BUY_PROD = 'P201000001'
       AND BUY_DATE = TO_DATE('20050420'); -- �� ��¥�� �Ǹ��� ��ǰ�� �ִٸ� COUNT�� 1�̵ǰ� (���� �� ��ǰ �ߺ� ���Ű� �ȵǱ⶧��) ������ 0��
       
    IF V_CNT = 1 THEN --UPDATE�ʿ�
        UPDATE BUYPROD
           SET BUY_QTY = BUY_QTY + V_QTY
         WHERE BUY_PROD = 'P201000001'
           AND BUY_DATE = TO_DATE('20050420');
    ELSE
        INSERT INTO BUYPROD
            VALUES ('20050420', 'P201000001', V_QTY,21000);
    END IF;
END;

**remain ���̺� 