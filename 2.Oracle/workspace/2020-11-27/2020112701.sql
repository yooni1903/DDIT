2020-11-27-01
3. TO_NUMBER(c[,fmt])
 - ���� �������� ������ ���ڿ��� ���������� ��ȯ
 - ���Ǵ� fmt�� TO_CHAR���� ���Ǵ� �������� ���ڿ��� ����
 ��)������ 2005�� 7�� 28���̰� 'h001'�� ȸ���� ��ǰ 'P201000003'�� 3�� �������� �� CART���̺� �ش� ������ �����Ͻÿ�.
 CART_NO : �����+����(5�ڸ� ��)
          =>'20050728'
          => SELECT '20050728'||TRIM(TO_CHAR(TO_NUMBER(MAX(SUBSTR(CART_NO, 9)))+1,'00000'))
                FROM CART
               WHERE CART_NO LIKE '20050728%';
  INSERT INTO CART
    SELECT 'h001', 
           '20050728'||TRIM(TO_CHAR(TO_NUMBER(MAX(SUBSTR(CART_NO, 9)))+1,'00000')),
           'P201000003',
            3
      FROM CART
      WHERE CART_NO LIKE '20050728%';
      
��) ������̺��� ��ȭ��ȣ�� ���ڷ� ��ȯ
    SELECT EMP_NAME, TO_NUMBER(REPLACE(PHONE_NUMBER,'.'))
      FROM EMPLOYEES;
      
    SELECT EMP_NAME, TO_NUMBER(REPLACE(TO_CHAR(HIRE_DATE),'/'))
      FROM EMPLOYEES;
      
4. TO_DATE(c,[,fmt])
 - ��¥ ������ ���ڿ��� ��¥Ÿ������ ��ȯ
 - �������� ���ڿ��� TO_CHAR�� �������� ���ڿ��� ����
 ��) 2005�� 6�� 13�� �Ǹ��Ϻ��� �ۼ��Ͻÿ�.
    Alias�� ��¥, ��ǰ�ڵ�, �Ǹż���, ������
SELECT TO_DATE(SUBSTR(CART_NO,1,8)) AS ��¥, 
       CART_PROD AS ��ǰ�ڵ�,
       CART_QTY AS �Ǹż���,
       CART_MEMBER AS ������
  FROM CART
 WHERE TO_DATE(SUBSTR(CART_NO,1,8)) = '20050613';
 