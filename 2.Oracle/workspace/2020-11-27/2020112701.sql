2020-11-27-01
3. TO_NUMBER(c[,fmt])
 - 숫자 형식으로 제공된 문자열을 숫자형으로 변환
 - 사용되는 fmt는 TO_CHAR에서 사용되는 형식지정 문자열과 동일
 예)오늘이 2005년 7월 28일이고 'h001'의 회원이 상품 'P201000003'을 3개 구입했을 때 CART테이블에 해당 내용을 삽입하시오.
 CART_NO : 년월일+순번(5자리 수)
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
      
예) 사원테이블의 전화번호를 숫자로 변환
    SELECT EMP_NAME, TO_NUMBER(REPLACE(PHONE_NUMBER,'.'))
      FROM EMPLOYEES;
      
    SELECT EMP_NAME, TO_NUMBER(REPLACE(TO_CHAR(HIRE_DATE),'/'))
      FROM EMPLOYEES;
      
4. TO_DATE(c,[,fmt])
 - 날짜 형식의 문자열을 날짜타입으로 변환
 - 형식지정 문자열은 TO_CHAR의 형식지정 문자열과 동일
 예) 2005년 6월 13일 판매일보를 작성하시오.
    Alias는 날짜, 상품코드, 판매수량, 구매자
SELECT TO_DATE(SUBSTR(CART_NO,1,8)) AS 날짜, 
       CART_PROD AS 상품코드,
       CART_QTY AS 판매수량,
       CART_MEMBER AS 구매자
  FROM CART
 WHERE TO_DATE(SUBSTR(CART_NO,1,8)) = '20050613';
 