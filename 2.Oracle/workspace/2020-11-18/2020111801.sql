2020-11-18-01 오라클 테이블 타입
 - 오라클에서 제공하는 데이터 타입은 문자열, 숫자, 날짜, 2진 자료형이 있음
 
 1. 문자열 데이터 타입
  - ''로 묶인 자료를 문자열 자료라 한다.
  - CHAR, VARCHAR, VARCHAR2, NVARCHAR, NCHAR, LONG, RAW, CLOB 등이 존재한다.
  
  1)CHAR
   . 고정길이 문자열을 취급
   . 최대 2000BYTE 저장 가능
   . 기억장소가 남으면 오른쪽에 공백이 삽입
   . 기억공간보다 큰 데이터는 저장 오류
   . 한글 한 글자는 3BYTE로 저장된다.
   . 기본키 등에 사용
   
(사용형식)
컬럼명 CHAR(크기[BYTE|CHAR])
 . '크기[BYTE|CHAR]' : 기억공간의 크기(BYTE)나 글자 수(CHAR) 정의
                2000BYTE 초과 불가
예)
CREATE TABLE TEMP01(
    COL1 CHAR(20),
    COL2 CHAR(20 BYTE),
    COL3 CHAR(20 CHAR));
    
INSERT INTO TEMP01(COL1, COL2, COL3)
    VALUES('대한민국', 'IL POSTINO', '대전광역시 중구 대흥동 500-1번지');
    
SELECT LENGTHB(COL1), LENGTHB(COL2), LENGTHB(COL3) -- LENGTH는 글자수, LENGTHB는 바이트길이수(B가 BYTE)를 뜻함.
    FROM TEMP01;

SELECT * FROM TEMP01;

 2) VARCHAR2
  . 가변길이 문자열 처리
  . 최대 4000BYTE 처리 가능
  . 정의된 기억공간에서 데이터의 길이만큼 사용하고 남는 공간은 시스템에 반납
  . VARCHAR와 같은 기능(오라클은 VARCHAR2 사용을 권고)
  (사용형식)
  컬럼명 VARCHAR2(크기[BYTE|CHAR])
   '[BYTE|CHAR]' : 생략되면 BYTE로 취급
   
예)
CREATE TABLE TEMP02(
    COL1 VARCHAR2(20),
    COL2 VARCHAR2(20 CHAR));
    
INSERT INTO TEMP02
    VALUES('대전시', '대한민국은');
INSERT INTO TEMP02
    VALUES('ABCDEFG', 'SAGHDSAGHDSASSWWRRRR');
    
SELECT * FROM TEMP02;

SELECT LENGTHB(COL1), LENGTHB(COL2) FROM TEMP02;
