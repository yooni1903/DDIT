2020-12-15-01) ���
    - ���α׷��� ���� ������ ����
    - IF ���� ����
  1) IF ��
   . ���ø����̼� ����� IF���� ���� ���

   (��� ����1)
   IF ���ǽ�1 THEN
     ���1;
   [ELSE
     ���2;]
   END IF;
   
   (��� ����2)
   IF ���ǽ�1 THEN
     ���1;
   [ELSIF ���ǽ�2 THEN
     ���2;
    ELSE
     ���3;]
   END IF;
   
   (��� ����3)
   IF ���ǽ�1 THEN
     ���1;
        IF ���ǽ�2 THEN
          ���2;
        ELSE
          ���3;
        END IF;
    ELSE
     ���4;
   END IF;
   
   
��) Ű����� �⵵�� �Է¹޾� �������� ������� �Ǻ��ϴ� ���α׷��� �ۼ��Ͻÿ�.

ACCEPT P_YEAR PROMPT '�⵵ �Է� :' -- Ű���� �Է� �޴� ���
DECLARE
  V_YEAR NUMBER := 0; --> �Է¹��� �⵵ ����
  --���� ������ ���� �ʱ�ȭ���� ������ NULL���� �ȴ�.
  V_MESSAGE VARCHAR2(30); --> ��� ����
  
BEGIN
  V_YEAR := TO_NUMBER('&P_YEAR');
  -- �������� ������� �Ǻ�
  -- ����(4�� ����̸鼭 100�� ����� �ƴϰų�(OR), 400�� ����� �Ǵ� �⵵) �Ǻ�
  IF (MOD(V_YEAR, 4) = 0 AND MOD(V_YEAR, 100) <> 0) OR (MOD(V_YEAR, 400) = 0)  THEN
    V_MESSAGE := V_YEAR || '�� �����Դϴ�.';
  ELSE
    V_MESSAGE := V_YEAR || '�� ����Դϴ�.';
  END IF;
  
  DBMS_OUTPUT.PUT_LINE(V_MESSAGE);
  
  EXCEPTION WHEN OTHERS   THEN
     DBMS_OUTPUT.PUT_LINE('���ܹ߻� : ' || SQLERRM);
    
END;
/

��) ������ ������ (1~100) �Է��Ͽ� ¦������ Ȧ������ �Ǵ�
DECLARE
    V_NUM NUMBER := 0;
    V_RES VARCHAR(50);
BEGIN
    V_NUM := ROUND(DBMS_RANDOM.VALUE(1, 100));
    IF MOD(V_NUM,2) = 0 THEN
        V_RES := V_NUM || '�� ¦��';
    ELSE
        V_RES := V_NUM || '�� Ȧ��';
    END IF;
    
    DBMS_OUTPUT.PUT_LINE(V_RES);
END;


��) LPROD ���̺� ���� �����͸� �Է��Ͻÿ�
    �з��ڵ� : P501
    ������ : '��갡����ǰ'
DECLARE
    V_CNT NUMBER := 0; -- SELECT���� ���(VIEW)�� ���� ��
BEGIN
    SELECT COUNT(*) INTO V_CNT
      FROM LPROD
     WHERE LPROD_GU = 'P501';
     
     IF V_CNT = 0 THEN
        INSERT INTO LPROD
            SELECT MAX(LPROD_ID) + 1, 'P501', '��갡����ǰ'
              FROM LPROD;
     END IF;
     COMMIT;
     
     EXCEPTION WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('���ܹ߻�' || SQLERRM);
END;


SELECT *
  FROM LPROD;

����1) �� �������� 'P501' �з��ڵ忡 �з����� '�ӻ깰'�� �Է��Ͻÿ�
        ��, �ڷᰡ �����ϸ� �����Ͻÿ�.

DECLARE
    V_CNT NUMBER := 0; -- SELECT���� ���(VIEW)�� ���� ��
BEGIN
    SELECT COUNT(*) INTO V_CNT
      FROM LPROD
     WHERE LPROD_GU = 'P501';
     
     IF V_CNT = 0 THEN
        INSERT INTO LPROD
            SELECT MAX(LPROD_ID) + 1, 'P501', '��갡����ǰ'
              FROM LPROD;
     ELSIF V_CNT = 1 THEN
        UPDATE LPROD
           SET LPROD_NM = '�ӻ깰'
         WHERE LPROD_GU = 'P501';
     END IF;
   
     
     EXCEPTION WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('���ܹ߻�' || SQLERRM);
END;

--(�̸��� ���� ������ �����Ͽ� �ٲ��� ��)
DECLARE
    V_CNT NUMBER := 0; -- SELECT���� ���(VIEW)�� ���� ��
    V_LPROD_NM LPROD.LPROD_NM%TYPE := '�ӻ깰'; -- ������Ÿ���� �������̴�.
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
        DBMS_OUTPUT.PUT_LINE('���ܹ߻�' || SQLERRM);
END;

























