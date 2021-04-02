2020-12-21-02) Ʈ����(TRIGGER)
 - � �̺�Ʈ�� �߻��Ǹ� �� �̺�Ʈ�� ���Ͽ� 
   �ٸ� ���̺��� ���� �ڵ����� ����(����/UPDATE, ����) �ǵ��� ������ ���ν���
 - ������ ���Ἲ ������ ���� ���
 (��� ����)
 CREATE TRIGGER Ʈ���Ÿ�
    [BEFORE | AFTER] [INSERT | UPDATE | DELETE]
    ON ���̺��
    [FOR EACH ROW]
    [WHEN ����]
[DECLARE
    �����]
BEGIN
    Ʈ���� ó����;
END;
. 'BEFORE | AFTER' : Ʈ������ Ÿ�̹�(TIMMING), �����ϸ� AFTER�� ����
                     Ʈ���� ����(Ʈ���� ó����)�� �̺�Ʈ �߻� ���̸� BEFORE,
                                                  �̺�Ʈ �߻� �ĸ� AFTER �� ���
. 'INSERT | UPDATE | DELETE' : Ʈ���� �̺�Ʈ, Ʈ���Ÿ� �߻���Ű�� �������� OR �����ڸ� �̿��Ͽ� ���� ���� ���� ����
                               (EX. INSERT OR DELETE)
. 'FOR EACH ROW' : �� ���� Ʈ���� �߻��� ���, �����ϸ� ������� Ʈ����
. 'WHEN ����' : �� ���� Ʈ���ſ����� ����ϸ� Ʈ���� �̺�Ʈ���� ���ǵ� ���̺� �̺�Ʈ�� �߻��� ������ 
                ��ü���� ������ �˻� ���� �ο��� ���
** �� ������ ���� ���� Ʈ����
  (1) ������� Ʈ���� : �̺�Ʈ �߻��� ���� �ѹ��� Ʈ���� �߻�(���� ������� ����)
  (2) �� ���� Ʈ���� : 'FOR EACH ROW' ��� 
                       �̺�Ʈ ��� �� �ึ�� Ʈ���� ����,
                       �ǻ� ���ڵ�(PESUDO RECORD)�� :NEW, :OLD ��� ����
                       ��κ��� Ʈ���Ű� ����
                       ��, �� Ʈ���� ������ �Ϸ���� ���� ���¿��� �� �ٸ� Ʈ���Ÿ� ȣ���� ���, �ý��ۿ� ���� Ʈ���Ÿ� ���� ����

��) �з����̺� ���ο� �ڷḦ �Է��ϰ� �Է��� ���������� ó���Ǿ����� 
    '�ű� �з��ڷᰡ ���� �ԷµǾ����ϴ�!!'���  �޽����� ����ϴ� Ʈ���Ÿ� �ۼ��Ͻÿ�.
    [�ڷ�]
    �з��ڵ� : P502
    ���� : 12
    �з��� : ��깰
    
CREATE TRIGGER TG_LPOD01
    AFTER INSERT ON LPROD
BEGIN
    DBMS_OUTPUT.PUT_LINE('�ű� �з��ڷᰡ ���� �ԷµǾ����ϴ�!!');
END;


INSERT INTO LPROD
    VALUES(12, 'P502', '��깰');
SELECT * FROM LPROD;

��) �԰����̺�(BUYPROD)���� 2���� 3�� �԰�� ��ǰ�� ���Լ����� ��ȸ�Ͽ� ���������̺��� �����Ͻÿ�.
(���� ���� : 2�� 3���� �߻��Ǿ��� ��ǰ�� ���Լ����� ��ȸ)
SELECT BUY_PROD,
       SUM(BUY_QTY)
  FROM BUYPROD
 WHERE BUY_DATE BETWEEN '20050201' AND '20050331'
 GROUP BY BUY_PROD
 ORDER BY 1;
 
(���� ���� : REMAIN ���̺� UPDATE)
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
 
��) ������ 2005�� 4��1���̶�� �����ϰ� ���� �ڷḦ ��ٱ��� ���̺� �Է��Ͻÿ�
    ��ٱ��� ���̺� �Էµ� �� ���������̺��� �����Ͻÿ�.
    
    �Է��ڷ� : (29, 21, 0, 50, 2005-01-31 : REMAIN ���̺�)
        ����ȸ�� : c001
        ���Ż�ǰ : P302000014
        ���ż��� : 5
   --------------------------
(Ʈ���� �ۼ�)
CREATE OR REPLACE TRIGGER TG_CART_INSERT
    AFTER INSERT ON CART -- CART���̺� �ڷᰡ ���ԵǾ����� �� ���� Ʈ���� �߻�
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

(���� : CART���̺� �ڷᰡ ���Ե� ��)
INSERT INTO CART   
   SELECT 'c001', MAX(CART_NO) + 1, 'P302000014', 5
     FROM CART
    WHERE SUBSTR(CART_NO, 1, 8) = '20050401';

SELECT * FROM REMAIN;
SELECT * FROM CART;

ROLLBACK;