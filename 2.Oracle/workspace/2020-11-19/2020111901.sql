2020-11-19-01
3) LONG 데이터 타입
 - 가변길이 문자열을 저장
 - 최대 2GB까지 저장 가능
 - LONG타입은 하나의 테이블에서 1개만 사용 가능
 - CLOB로 대체(기능 업그레이드 중단) -- LOB(LARGE OBJECT) 앞에 C가 붙으면 CHARACTER라는 뜻이 붙는다. (B가 붙으면 BANARY)
 - SELECT 문의 SELECT절, UPDATE 문의 SET절, INSERT 문의 VALUES절에서 사용 가능
 (사용형식)
 컬럼명 LONG
 
 
 예)
CREATE TABLE TEMP03(
    COL1 LONG,
    COL2 VARCHAR2(2000));
    
INSERT INTO TEMP03 VALUES('SADHSDAGHSDA', 'SAGHSAGHSA');

SELECT * FROM TEMP03;
SELECT SUBSTR(COL2,2,5) FROM TEMP03;

4) CLOB (Character Large OBject) 데이터 타입
 - 데이터 문자열 자료를 처리하기 위한 데이터 타입
 - 최대 4GB까지 처리 가능
 - 가변길이 문자 자료형
 - 한 테이블에서 사용될 수 있는 CLOB의 갯수에 제한이 없다.
 - 일부 기능들은 DBMS_LOB API의 지원을 받아야 한다.
(사용형식)
컬럼명 CLOB

예)
CREATE TABLE TEMP04(
    COL1 CLOB,
    COL2 CLOB,
    COL3 CLOB;
    
INSERT INTO TEMP04 
    VALUES('대한민국은 민주 공화국이다.', '대전시 중구 대흥동 500 영민빌딩 3층','Oracle Modeling과 SQL');
    
SELECT * FROM TEMP04;

SELECT LENGTHB(COL2) FROM TEMP04;

SELECT DBMS_LOB.SUBSTR(COL1, 5,3),
    DBMS_LOB.GETLENGTH(COL2), -- LENGTH 는 글자 수, LENGTHB는 바이트 수
    LENGTH(COL3)
    FROM TEMP04;
    
    
5) NCLOB, NVARCHAR2 -- N은 NATIONAL이라는 뜻
 - 다국어 지원 형태로 문자 자료저장
 - UTF-8, UTF-16 형식의 ENCODING
 - 나머지 기능은 CLOB, VARCHAR2와 동일
 
 2.숫자자료형
  - 정수(fixed point)와 실수(floating point) 데이터 저장 -- 오라클은 숫자 우선!
  - NUMBER 타입 제공
  (사용형식)
  NUMBER[(정밀도|*[,스케일])] -- 대괄호는 생략가능
   - 표현할 수 있는 값의 범위 : 1.0e-130 ~ 9.999..9e125
   - 정밀도 : 전체 자리수(1~38)
   - 스케일 : 소숫점 이하의 자리수
   ex) NUMBER(5,2) : 전체 5자리가 확보(정수 부분 3자리, 소수점 이하 3자리에서 반올림하여 2번째 자리까지 표현)
   - 정밀도 대신 '*'를 사용할 수 있는데, 사용하면 38자리 이내에서 자동할당(정수부분)
   ex)NUMBER(*,2) : 소수점 이하 3자리에서 반올림하여 2번째 자리까지 표현. 단, 정수부분을 38자리 이내에서 크기만큼 자동 할당.
   - 스케일이 생략되면 0으로 간주
   - 스케일이 음수이면 정수부분에서 반올림
예)
--------------------------------------------------------
입력값              선언               저장된 값
--------------------------------------------------------
12345678.7896      NUMBER            12345678.7896
12345678.7896      NUMBER(*,2)       12345678.79
12345678.7896      NUMBER(8)         12345679
12345678.7896      NUMBER(7,2)       오류  -- 입력값은 8자리인데 선언은 7자리를 요청함으로써 자리수 부족으로 오류가 생김
12345678.7896      NUMBER(10,-2)     12345700

** 정밀도 < 스케일 인 경우 -- 이런 경우는 보통 많이 없다.
    - 정밀도는 0이 아닌 유효숫자의 수
    - (스케일-정밀도) : 소수점 이하에 존재해야할 0의 갯수
    - 스케일 : 소수점 이하의 데이터의 수
    
--------------------------------------------------------------------
입력값             선언                  저장된 값
--------------------------------------------------------------------
1.234             NUMBER(4,5)           오류
0.23              NUMBER(3,5)           오류
0.0123            NUMBER(3,4)           0.0123
0.0012345         NUMBER(3,5)           0.00123
0.00125676        NUMBER(3,5)           0.00126


3. 날짜자료형
 - 날짜자료(년, 월, 시, 분, 초)를 저장하기 위한 데이터 타입
 - 기본형은 DATE, 시간대(TIMEZONE)정보와 10억분의 1초 단위의 시각 정보를 제공하는 데이터 타입으로 TIMESTAMP가 제공
1)DATE 타입
 - 기본 날짜형
 - '+', '-' 연산의 대상
 **SYSDATE : 시스템이 제공하는 날짜 정보를 제공하는 함수
 (사용형식)
 컬럼명 DATE
    
예)
CREATE TABLE TEMP05(
    COL1 DATE,
    COL2 DATE,
    COL3 DATE);
    
INSERT INTO TEMP05 VALUES(SYSDATE, '20101029',SYSDATE+30);

SELECT * FROM TEMP05;
SELECT COL1 -10 FROM TEMP05;

SELECT MOD(TO_DATE('20100101')-TO_DATE('00010101')-1,7) FROM DUAL; -- DUAL : 시스템이 제공하는 가상의 테이블

SELECT 12345*346545646 FROM DUAL;

SELECT TO_CHAR(COL1, 'YYYY"년" MM"월" DD"일" AM HH24:MI:SS')FROM TEMP05;

2) TIMESTAMP 타입
 - 시간대 정보와 정교한 시각 정보를 필요할 때 사용
 (사용형식)
 컬럼명 TIMESTAMP -- 시간대 정보 없음
 컬럼명 TIMESTAMP WITH TIME ZONE -- 시간대 정보(대륙명/도서명) 포함
 컬럼명 TIMESTAMP WITH LOCAL TIME ZONE -- 서버가 위치한 시간대정보, 보통 TIMESTAMP와 동일
 
 예)
 CREATE TABLE TEMP06(
    COL1 DATE,
    COL2 TIMESTAMP,
    COL3 TIMESTAMP WITH TIME ZONE,
    COL4 TIMESTAMP WITH LOCAL TIME ZONE);
    
INSERT INTO TEMP06
    VALUES(SYSDATE, SYSDATE, SYSDATE, SYSDATE);
    
    SELECT*FROM TEMP06;