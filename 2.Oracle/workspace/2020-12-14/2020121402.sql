2020-12-14-02
PL/SQL ���պ���

%ROWTYPE
PL/SQL record


��(�÷�) - ��(��)    -     ��(���̺�)
%TYPE      %ROWTYPE        TABLE TYPE
           RECORD TYPE
           
           
ROWTYPE : �� ��ü�� ������ �� �ִ� ����
          �÷��� ���� ���̺��� ���� ������ ���� �� �÷����� ������ �������� �ʰ� 
          �� ������ �� ���� ������ �ϰ� ����ϹǷ� ���Ǽ��� ����ȴ�.
          
DEPT ���̺��� 10�� �μ� ������ ���� �� �ִ� ROWTYPE�� �����Ͽ� Ȱ��

SET SERVEROUTPUT ON;

DECLARE
    --������ ����Ÿ��
    v_dept_row   DEPT%ROWTYPE;
BEGIN
    SELECT * INTO v_dept_row
      FROM DEPT
     WHERE DEPTNO = 10;
     
     --�÷��� �� ���� :  ROWTYPE.�÷���
     DBMS_OUTPUT.PUT_LINE(v_dept_row.dname || ' / ' || v_dept_row.loc);
END;
/


RECORD TYPE : �����ڰ� �����Ϸ����ϴ� �÷��� ���� �����Ͽ� �࿡ ���� Ÿ���� ����
              (JAVA���� Ŭ������ �����ϴ� �Ͱ� �����ϴ�)
���� ���
TYPE Ÿ�Ը� IS RECORD(
    ename emp.ename%TYPE,
    dname dept.dname%TYPE
)

DECLARE
    TYPE name_row IS RECORD( -- Ÿ���� ������
        ename emp.ename%TYPE,
        dname dept.dname%TYPE);
        
    --������ ����Ÿ��
    names name_row;
BEGIN
    SELECT ENAME, DNAME INTO names
      FROM EMP, DEPT
     WHERE EMP.DEPTNO = DEPT.DEPTNO
       AND EMPNO = 7839;
    
    DBMS_OUTPUT.PUT_LINE(names.ename || ' / ' || names.dname);
END;
/


TABLE TYPE : ���� ���� ������ �� �ִ� Ÿ��
���� ���
TYPE Ÿ���̸� IS RECORD
TYPE Ÿ���̸� IS TABLE OF ���ڵ� Ÿ�� / %ROWTYPE INDEX BY BINARY_INTEGER         --INDEX BY BINARY_INTEGER �ε����� Ÿ���� �����ϴ� �κ�


DECLARE
    TYPE name_row IS RECORD( -- Ÿ���� ������
        ename emp.ename%TYPE,
        dname dept.dname%TYPE);
    
    -- ���̺� Ÿ��
    TYPE name_tab IS TABLE OF name_row INDEX BY BINARY_INTEGER;
   
    --������ ����Ÿ��
    names name_tab;
    
  -- java : name[1]; ==> pl/sql :  names(1)
BEGIN
    SELECT ENAME, DNAME BULK COLLECT INTO names
      FROM EMP, DEPT
     WHERE EMP.DEPTNO = DEPT.DEPTNO;
    
    FOR i IN 1..names.count LOOP -- �ݺ���
        DBMS_OUTPUT.PUT_LINE(names(i).ename || ' / ' || names(i).dname);
    END LOOP;
   
END;
/
