2020-12-11-02) PL/SQL

 Java
  변수타입 변수명 = 변수값;
 PL/SQL
  변수명 변수타입 := 변수값;

1. PL/SQL
 - Procedural Language/SQL
 - 오라클에서 제공하는 프로그래밍 언어
 - 집합적 성향이 강한 SQL에 일반 프로그래밍 언어 요소를 추가
    - SQL에서 지원하지 않는 반복문, 조건문을 지원
 - 데이터를 절차적으로 처리하는데 그 목적
 
 1)기본 구조
  - Declare(선언부)
    .변수, 상수를 선언
    .생략가능
  - Begin(실행부)
    .제어문, 반복문 등의 로직 실행(SQL, IF, LOOP)
  - Exception(예외 처리부(Java - try, catch))
    .실행 도중 에러 발생을 catch, 후속 조치
    .생략 가능
    
(1) 10번 부서의 DNAME, LOC 컬럼 두 개를 변수에 담는다.
(2) 두 개의 변수 값을 출력(System.out.println)

변수 선언 : 변수명 변수타입;
PL/SQL : 변수명 작성시 V_ 접두어 사용(V_XXX, V_ZZZ, etc)

SET SERVEROUTPUT ON; -- DBMS_OUTPUT.PUT_LINE();을 출력하려면 반드시 최초 1번은 실행해야한다.

DECLARE
    V_DNAME VARCHAR2(14);
    V_LOC VARCHAR2(13);
BEGIN
    SELECT DNAME, LOC INTO V_DNAME, V_LOC
      FROM DEPT
     WHERE DEPTNO = 10;
     
     -- System.out.println(v_dname + "/" + v_loc);
     DBMS_OUTPUT.PUT_LINE(V_DNAME || '/' || V_LOC);
END;
/ -- PL/SQL에서 ;역할을 한다. 종료를 의미한다.



VIEW는 쿼리다. (원본 데이터가 수정되면, 뷰에 있는 데이터도 같이 수정된다.)

참조변수 : 특정테이블의 컬럼의 데이터 타입을 자동으로 참조 ==> 컬럼의 데이터 타입이 바뀌어도 
                                                               PL/SQL 블록의 변수 선언부를 수정할 필요가 없어짐 :  유지보수에 유리
         - 사용형식 : 테이블명.컬럼명%TYPE
DECLARE
    V_DNAME DEPT.DNAME%TYPE;
    V_LOC DEPT.LOC%TYPE;
BEGIN
    SELECT DNAME, LOC INTO V_DNAME, V_LOC
      FROM DEPT
     WHERE DEPTNO = 10;
     
     -- System.out.println(v_dname + "/" + v_loc);
     DBMS_OUTPUT.PUT_LINE(V_DNAME || '/' || V_LOC);
END;
/

PL/SQL BLOCK 구분
1. 익명 블럭 : inline-view
2. Procedure : 오라클 서버에 저장한 PL/SQL 블럭, 리턴값은 없다.
3. Function : 오라클 서버에 저장한 PL/SQL 블럭, 리턴값이 있다.

오라클 객체
CREATE (오라클 객체 타입) (객체이름) 
CREATE TABLE 테이블명
CREATE [OR REPLACE VIEW] 뷰이름

CREATE OR REPLACE PROCEDURE PRINTDEPT IS 
    --선언부 : PROCEDURE는 별도의 선언부 명령어가 없고 그냥 쓰면 선언된다.
    v_dname dept.dname%TYPE;
    v_loc dept.loc%TYPE;
BEGIN
    SELECT DNAME, LOC INTO V_DNAME, V_LOC
      FROM DEPT
     WHERE DEPTNO = 10;
     
     DBMS_OUTPUT.PUT_LINE(V_DNAME || '/' || V_LOC);
END;
/


프로시저 실행
EXEC 프로시저명

EXEC PRINTDEPT;

현재 프로시저는 10번 부서의 정보만 조회가 되게끔 코드가 구성됨(hard coding)
procedure 인자로 조회하고 싶은 부서번호를 받로고 수정하여 코드를 유연하게 만들어보자!

프로시져를 생성할 때 인자를 프로시저명 뒤에 선언할 수 있음
인자는 메소드와 마찬가지로 여러 개를 받을 수 있음

수업시간에는 프로시져에서 인자 이름을 P_XXX 접두어를 사용하기로 합시다.

CREATE OR REPLACE PROCEDURE [(인자명 인자타입)]
CREATE OR REPLACE PROCEDURE printdept(p_deptno dept.deptno%TYPE) IS 
    --선언부 : PROCEDURE는 별도의 선언부 명령어가 없고 그냥 쓰면 선언된다.
    v_dname dept.dname%TYPE;
    v_loc dept.loc%TYPE;
BEGIN
    SELECT DNAME, LOC INTO V_DNAME, V_LOC
      FROM DEPT
     WHERE DEPTNO = p_deptno;
     
     DBMS_OUTPUT.PUT_LINE(V_DNAME || ' / ' || V_LOC);
END;
/

EXEC printdept(40);


실습 1) - printtemp procedure 생성
        - param : empno
        - logic : empno에 해당하는 사원의 정보를 조회하여
                  사원이름, 부서이름을 화면에 출력

CREATE OR REPLACE PROCEDURE printtemp(p_empno emp.empno%TYPE) IS
    v_ename emp.ename%TYPE;
    v_dname dept.dname%TYPE;
BEGIN
    SELECT A.ename, B.dname INTO v_ename, v_dname
      FROM EMP A, DEPT B
     WHERE A.DEPTNO = B.DEPTNO
       AND A.EMPNO = p_empno;

       
    DBMS_OUTPUT.PUT_LINE(v_ename || ' / ' || v_dname);
END;
/

EXEC printtemp(7369);