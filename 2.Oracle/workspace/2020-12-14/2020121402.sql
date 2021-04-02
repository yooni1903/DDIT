2020-12-14-02
PL/SQL 복합변수

%ROWTYPE
PL/SQL record


점(컬럼) - 선(행)    -     면(테이블)
%TYPE      %ROWTYPE        TABLE TYPE
           RECORD TYPE
           
           
ROWTYPE : 행 전체를 저장할 수 있는 변수
          컬럼이 많은 테이블의 값을 변수로 담을 때 컬럼별로 변수를 선언하지 않고 
          행 단위로 한 번만 선언을 하고 사용하므로 편의성이 증대된다.
          
DEPT 테이블의 10번 부서 정보를 담을 수 있는 ROWTYPE을 선언하여 활용

SET SERVEROUTPUT ON;

DECLARE
    --변수명 변수타입
    v_dept_row   DEPT%ROWTYPE;
BEGIN
    SELECT * INTO v_dept_row
      FROM DEPT
     WHERE DEPTNO = 10;
     
     --컬럼의 값 접근 :  ROWTYPE.컬럼명
     DBMS_OUTPUT.PUT_LINE(v_dept_row.dname || ' / ' || v_dept_row.loc);
END;
/


RECORD TYPE : 개발자가 저장하려고하는 컬럼을 직접 지정하여 행에 대한 타입을 선언
              (JAVA에서 클래스를 선언하는 것과 유사하다)
선언 방법
TYPE 타입명 IS RECORD(
    ename emp.ename%TYPE,
    dname dept.dname%TYPE
)

DECLARE
    TYPE name_row IS RECORD( -- 타입을 선언함
        ename emp.ename%TYPE,
        dname dept.dname%TYPE);
        
    --변수명 변수타입
    names name_row;
BEGIN
    SELECT ENAME, DNAME INTO names
      FROM EMP, DEPT
     WHERE EMP.DEPTNO = DEPT.DEPTNO
       AND EMPNO = 7839;
    
    DBMS_OUTPUT.PUT_LINE(names.ename || ' / ' || names.dname);
END;
/


TABLE TYPE : 여러 행을 저장할 수 있는 타입
선언 방법
TYPE 타입이름 IS RECORD
TYPE 타입이름 IS TABLE OF 레코드 타입 / %ROWTYPE INDEX BY BINARY_INTEGER         --INDEX BY BINARY_INTEGER 인덱스의 타입을 정의하는 부분


DECLARE
    TYPE name_row IS RECORD( -- 타입을 선언함
        ename emp.ename%TYPE,
        dname dept.dname%TYPE);
    
    -- 테이블 타입
    TYPE name_tab IS TABLE OF name_row INDEX BY BINARY_INTEGER;
   
    --변수명 변수타입
    names name_tab;
    
  -- java : name[1]; ==> pl/sql :  names(1)
BEGIN
    SELECT ENAME, DNAME BULK COLLECT INTO names
      FROM EMP, DEPT
     WHERE EMP.DEPTNO = DEPT.DEPTNO;
    
    FOR i IN 1..names.count LOOP -- 반복문
        DBMS_OUTPUT.PUT_LINE(names(i).ename || ' / ' || names(i).dname);
    END LOOP;
   
END;
/
