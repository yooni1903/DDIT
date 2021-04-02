2020-12-11-02) PL/SQL

 Java
  ����Ÿ�� ������ = ������;
 PL/SQL
  ������ ����Ÿ�� := ������;

1. PL/SQL
 - Procedural Language/SQL
 - ����Ŭ���� �����ϴ� ���α׷��� ���
 - ������ ������ ���� SQL�� �Ϲ� ���α׷��� ��� ��Ҹ� �߰�
    - SQL���� �������� �ʴ� �ݺ���, ���ǹ��� ����
 - �����͸� ���������� ó���ϴµ� �� ����
 
 1)�⺻ ����
  - Declare(�����)
    .����, ����� ����
    .��������
  - Begin(�����)
    .���, �ݺ��� ���� ���� ����(SQL, IF, LOOP)
  - Exception(���� ó����(Java - try, catch))
    .���� ���� ���� �߻��� catch, �ļ� ��ġ
    .���� ����
    
(1) 10�� �μ��� DNAME, LOC �÷� �� ���� ������ ��´�.
(2) �� ���� ���� ���� ���(System.out.println)

���� ���� : ������ ����Ÿ��;
PL/SQL : ������ �ۼ��� V_ ���ξ� ���(V_XXX, V_ZZZ, etc)

SET SERVEROUTPUT ON; -- DBMS_OUTPUT.PUT_LINE();�� ����Ϸ��� �ݵ�� ���� 1���� �����ؾ��Ѵ�.

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
/ -- PL/SQL���� ;������ �Ѵ�. ���Ḧ �ǹ��Ѵ�.



VIEW�� ������. (���� �����Ͱ� �����Ǹ�, �信 �ִ� �����͵� ���� �����ȴ�.)

�������� : Ư�����̺��� �÷��� ������ Ÿ���� �ڵ����� ���� ==> �÷��� ������ Ÿ���� �ٲ� 
                                                               PL/SQL ����� ���� ����θ� ������ �ʿ䰡 ������ :  ���������� ����
         - ������� : ���̺��.�÷���%TYPE
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

PL/SQL BLOCK ����
1. �͸� �� : inline-view
2. Procedure : ����Ŭ ������ ������ PL/SQL ��, ���ϰ��� ����.
3. Function : ����Ŭ ������ ������ PL/SQL ��, ���ϰ��� �ִ�.

����Ŭ ��ü
CREATE (����Ŭ ��ü Ÿ��) (��ü�̸�) 
CREATE TABLE ���̺��
CREATE [OR REPLACE VIEW] ���̸�

CREATE OR REPLACE PROCEDURE PRINTDEPT IS 
    --����� : PROCEDURE�� ������ ����� ��ɾ ���� �׳� ���� ����ȴ�.
    v_dname dept.dname%TYPE;
    v_loc dept.loc%TYPE;
BEGIN
    SELECT DNAME, LOC INTO V_DNAME, V_LOC
      FROM DEPT
     WHERE DEPTNO = 10;
     
     DBMS_OUTPUT.PUT_LINE(V_DNAME || '/' || V_LOC);
END;
/


���ν��� ����
EXEC ���ν�����

EXEC PRINTDEPT;

���� ���ν����� 10�� �μ��� ������ ��ȸ�� �ǰԲ� �ڵ尡 ������(hard coding)
procedure ���ڷ� ��ȸ�ϰ� ���� �μ���ȣ�� �޷ΰ� �����Ͽ� �ڵ带 �����ϰ� ������!

���ν����� ������ �� ���ڸ� ���ν����� �ڿ� ������ �� ����
���ڴ� �޼ҵ�� ���������� ���� ���� ���� �� ����

�����ð����� ���ν������� ���� �̸��� P_XXX ���ξ ����ϱ�� �սô�.

CREATE OR REPLACE PROCEDURE [(���ڸ� ����Ÿ��)]
CREATE OR REPLACE PROCEDURE printdept(p_deptno dept.deptno%TYPE) IS 
    --����� : PROCEDURE�� ������ ����� ��ɾ ���� �׳� ���� ����ȴ�.
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


�ǽ� 1) - printtemp procedure ����
        - param : empno
        - logic : empno�� �ش��ϴ� ����� ������ ��ȸ�Ͽ�
                  ����̸�, �μ��̸��� ȭ�鿡 ���

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