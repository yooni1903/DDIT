2020-12-11-01)

--MEMBER ���̺��� �� ���� �÷��� ��ȸ : MEM_ID, MEM_NAME
--�̸�(MEM_NAME)�÷��� ������������ ��ȸ�� ����� ����¡ ó���ϴ� ������ �ۼ��ϼ���

SELECT MEM_ID,
       MEM_NAME
  FROM MEMBER
 ORDER BY MEM_NAME;
 
SELECT ROWNUM,
       A.*
  FROM (SELECT MEM_ID,
               MEM_NAME
          FROM MEMBER
         ORDER BY MEM_NAME) A;
 
SELECT *
  FROM (SELECT ROWNUM AS RN,
               A.*
          FROM (SELECT MEM_ID,
                       MEM_NAME
                  FROM MEMBER
                 ORDER BY MEM_NAME) A)
 WHERE RN BETWEEN (:page -1) * :pageSize + 1 AND :page * : pageSize;
 
 
SQL ����

������ SQL�̶�� ���� ���ڿ��� �Ϻ��ϰ� �����ؾ��Ѵ�.
����, ��ҹ��ڵ� �Ϻ��ϰ� ��ġ�ؾ� ������ SQL�� �ν�

SELECT  /*DDIT*/ MEM_ID,
                 MEM_NAME
  FROM MEMBER
 WHERE MEM_ID = 'a001';
 
 
�����ȹ Ȯ�� ���
1. ������ SQL ���� EXPLAIN PLAN FOR�� �ۼ��ϰ� ����
2. ���� ��ȹ ��ȸ
     SELECT *
     FROM TABLE(DBMS_XPLAN.DISPLAY);

EXPLAIN PLAN FOR
SELECT MEM_ID
  FROM MEMBER
 WHERE MEM_ID = 'a001';
  
SELECT *
  FROM TABLE(DBMS_XPLAN.DISPLAY);
  

  
�����ȹ �ؼ� ���
1. ������ �Ʒ���
2. �� �ڽ� ���(�鿩����)�� ���� ��� �ڽĺ���. -- 1������ 0������  
    --�鿩���Ⱑ �ȵǾ��ִٸ� ��������� �Ѵ�. 
----------------------------------------------------------------------------
| Id  | Operation         | Name   | Rows  | Bytes | Cost (%CPU)| Time     |
----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |        |    24 |  7152 |     2   (0)| 00:00:01 |
|   1 |  TABLE ACCESS FULL| MEMBER |    24 |  7152 |     2   (0)| 00:00:01 |
----------------------------------------------------------------------------



* 2 -> 1 -> 0
-----------------------------------------------------------------------------------------
| Id  | Operation                   | Name      | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------------------
|   0 | SELECT STATEMENT            |           |     1 |   298 |     1   (0)| 00:00:01 |
|   1 |  TABLE ACCESS BY INDEX ROWID| MEMBER    |     1 |   298 |     1   (0)| 00:00:01 |
|*  2 |   INDEX UNIQUE SCAN         | PK_MEM_ID |     1 |       |     1   (0)| 00:00:01 |
-----------------------------------------------------------------------------------------
 
Predicate Information (identified by operation id):
---------------------------------------------------
 
   2 - access("MEM_ID"='a001')
   
   
   
   
SELECT ROWID, -- ROWID�� C������ �����Ͷ�� �����ϸ� ����. ���� �ּ�.
       MEM_ID
  FROM MEMBER
 ORDER BY MEM_ID;

EXPLAIN PLAN FOR 
SELECT *
  FROM MEMBER
 WHERE ROWID ='AAAE5mAABAAALGBAAG';
  
  
  
  
EXPLAIN PLAN FOR
SELECT *
  FROM PROD, LPROD
 WHERE PROD.PROD_LGU = LPROD_GU;
 
 SELECT *
   FROM TABLE(DBMS_XPLAN.DISPLAY);
2 -> 3 -> 1 -> 0