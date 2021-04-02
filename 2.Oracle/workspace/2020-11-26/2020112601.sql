2020-11-26-01 ��¥�� �Լ�
1. SYSDATE
 - �ý����� �����ϴ� �⺻ ��¥��
 - ��, ��, ��, ��, ��, �� ��������
 - '+'�� '-' ������ ���
 ��)
 SELECT SYSDATE, SYSDATE+10, SYSDATE-200 FROM DUAL;
 
 2. ADD_MONTHS(d,n)
 - 'd'�� �־��� ��¥���� 'n'������ ���� ��¥�� ��ȯ
 ��) ȸ�� ���̺��� MEM_MEMORIALDAY �÷��� �������̶�� �������� ��, 
     ��� ȸ���� ��ȿ�Ⱓ�� 3�����̸� ��� ������ ��� ���� ��¥ 10�� ���� ���ڵ����͸� �����ϰ��� �Ѵ�.
     �� ȸ���� ���� ���� �������� ���Ͻÿ�.
     Alias�� ȸ����ȣ, ȸ����, ������,������,����������
     
     SELECT MEM_ID AS ȸ����ȣ, 
            MEM_NAME AS ȸ����, 
            MEM_MEMORIALDAY AS ������,
            ADD_MONTHS(MEM_MEMORIALDAY, 3) AS ������,
            ADD_MONTHS(MEM_MEMORIALDAY, 3) - 10 AS ����������
       FROM MEMBER;
       
    SELECT ADD_MONTHS(SYSDATE, -4) FROM DUAL;
    
3. NEXT_DAY(d,char)
 - �־��� ��¥ 'd' ���� ó�� char������ ������ ��¥
 SELECT NEXT_DAY(SYSDATE, '��') FROM DUAL;
  SELECT NEXT_DAY(SYSDATE, '�����') FROM DUAL;
  
4. LAST_DAY(d)
 - �־��� ��¥ 'd'�� ���� �ش��ϴ� ������ ���ڸ� ��ȯ
 SELECT LAST_DAY(SYSDATE), LAST_DAY('20000210') FROM DUAL;
 
5. MONTHS_BETWEEN(d1, d2)
 - �� ��¥ �ڷ� 'dL'�� 'd2'������ �������� ��ȯ
 SELECT ROUND(MONTHS_BETWEEN(TRUNC(SYSDATE), '00010101')) FROM DUAL;
 
6. EXTRACT(fmt, FROM d)
 - �־��� ��¥ ������ 'd'���� fmt�� ���ǵ� ���� �����Ͽ� ��ȯ
 - fmt�� YEAR,MONTH,DAY,HOUR,MINUTE,SECOND �̴�.
 - ��ȯ ������ Ÿ���� ��������
 ��)�������̺��� 2005�⵵ ���� ���������� ��ȸ�Ͻÿ�.
    Alias�� ��, ���Լ���, ���Աݾ�
SELECT EXTRACT(MONTH FROM BUY_DATE) AS ��, 
       COUNT(*) AS �Ǽ�,
       SUM(BUY_QTY) AS ���Լ���, 
       SUM(BUY_QTY * BUY_COST) AS ���Աݾ�
  FROM BUYPROD
 WHERE EXTRACT(YEAR FROM BUY_DATE) = 2005
 GROUP BY EXTRACT(MONTH FROM BUY_DATE)
 ORDER BY 1;
 
 ����) �����ܾ����̺�(KOR_LOAN_STATUS)���� 2013�� ������Ȳ�� ��ȸ�Ͻÿ�.
      Alias�� ��, ����, ����, �����ܾ� -- ���� ���ڰ� ���� ������ ��¥ Ÿ���� �ƴ϶� ����Ÿ���̴�!
SELECT SUBSTR(PERIOD, 5) AS ��, 
       REGION AS ����, 
       GUBUN AS ����, 
       LOAN_JAN_AMT AS �����ܾ�
  FROM KOR_LOAN_STATUS
 WHERE SUBSTR(PERIOD,1,4) = '2013'
 ORDER BY 1,3;

��) ������̺��� 11���� �Ի��� ��������� ��ȸ�Ͻÿ�.
    Alias�� �����ȣ, �����, �μ��ڵ�, �̸����̴�.
SELECT EMPLOYEE_ID AS �����ȣ,
       EMP_NAME AS �����, 
       DEPARTMENT_ID AS �μ��ڵ�, 
       EMAIL AS �̸���
  FROM EMPLOYEES
 WHERE EXTRACT(MONTH FROM HIRE_DATE) = EXTRACT(MONTH FROM SYSDATE)
 ORDER BY 1,3;