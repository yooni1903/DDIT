2020-11-26-02) �� ��ȯ �Լ�
1. CAST(expr AS Ÿ��[(ũ��)])
 - 'expr'�� �����Ǵ� �ڷḦ 'Ÿ��[(ũŰ)]' �������� ����ȯ
 - ����ȯ�� CAST �� ���� ��ġ���� �Ͻ������� ��ȯ
 ��) �з����̺��� LPROD_ID �÷��� ������ Ÿ���� NUMBER(7)���� CHAR(10)���� ���� ����Ͻÿ�.
SELECT CAST(LPROD_ID AS CHAR(10)) AS ����,
       LPROD_GU AS �з��ڵ�,
       LPROD_NM AS �з���
  FROM LPROD;
  --���ڿ��� �׻� ���� ����, ���ڿ��� �׻� ������ ����
��) ������̺��� �μ��ڵ� 50�� ���� ������� �ټӳ���� ��ȸ�Ͻÿ�.
    Alias�� �����ȣ, �����, �Ի���,�ټӳ���̸�, �ټӳ���� 'XX��'������ ���ڿ��� ��ȯ����Ͻÿ�.
SELECT EMPLOYEE_ID AS �����ȣ,
       EMP_NAME AS �����, 
       HIRE_DATE AS �Ի��� ,
       CAST(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) AS CHAR(2)) ||'��' AS �ټӳ��
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID = 50;
 
 2. TO_CHAR(c) : CHAR, CLOB Ÿ���� �ڷ� 'c'�� VARCHAR2 Ÿ���� ���ڿ��� ��ȯ
    TO_CHAR(d[,fmt]) : ��¥ �ڷ� d�� fmt ������ ���ڿ��� ��ȯ
    TO_CHAR(n[,fmt]) : ���� �ڷ� 'n'�� fmt ������ ���ڿ��� ��ȯ
     - ��¥���� �������ڿ�
  ------------------------------------------------------------------------
    ���Ĺ��ڿ�                   �ǹ�                    ��뿹
  ------------------------------------------------------------------------
  AD, BC, CC                   ���⸦ ���               TO_CHAR(SYSDATE,'CC')
    ex) SELECT TO_CHAR(SYSDATE, 'CC'),
               TO_CHAR(SYSDATE, 'AD'),
               TO_CHAR(SYSDATE, 'BC')
               FROM DUAL;
               
  YYYY,YYY,YY,Y                �⵵ ���                 TO_CHAR(SYSDATE,'YY')
    ex) SELECT TO_CHAR(SYSDATE, 'YYYY'),
               TO_CHAR(SYSDATE, 'YYY'),
               TO_CHAR(SYSDATE, 'Y')
               FROM DUAL;   
               
  Q                            �б�                     TO_CHAR(SYSDATE,'YY Q')
    ex) SELECT TO_CHAR(SYSDATE, 'MM"����" Q"�б�"') FROM DUAL;
  MONTH,MON,MM RM              ��                       TO_CHAR(SYSDATE,'YYYY-MM')
    ex) SELECT TO_CHAR(SYSDATE, 'YYYY-MM'),
               TO_CHAR(SYSDATE, 'YYYY-RM'),
               TO_CHAR(SYSDATE, 'YYYY-MON'),
               TO_CHAR(SYSDATE, 'YYYY-MONTH')
               FROM DUAL;
               
  W,WW, IW                      ����
    ex) SELECT TO_CHAR(SYSDATE, 'W'), --�ش� ������ ����Ǿ��� ������
               TO_CHAR(SYSDATE, 'WW'), -- 1��1�Ͽ������� ����Ǿ��� ������
               TO_CHAR(SYSDATE, 'IW')
               FROM DUAL;
               
  D,DD,DDD,J                    ��
    D : �ش� ���� ���� �ֿ��� ����� �ϼ�
    DD : �ش� ���� ���� ������ ����� �ϼ�
    DDD : �ش� ���� ���� �⿡�� ����� �ϼ�
    J : ����� 4712�� ���� ����� �ϼ�
    ex) SELECT TO_CHAR(SYSDATE,'D'),
               TO_CHAR(SYSDATE,'DD'),
               TO_CHAR(SYSDATE,'DDD'),
               TO_CHAR(SYSDATE,'J')
               FROM DUAL;
               
   DAY,DY                       ���� ����
    ex) SELECT TO_CHAR(SYSDATE, 'DAY'),
               TO_CHAR(SYSDATE, 'DY')
               FROM DUAL;
               
   HH,HH24,HH12                 �ð� (HH�� HH12�� ���� ����)
   AM, PM, A.M, P.M             ����, ���� ǥ��
   MI                           ��
   SS,SSSSS                     ��('SSSSS' : ���� ���� ����� �ð��� �ʷ� ��ȯ)
   ��Ÿ                          ����ڰ� ���Ƿ� ���� ���ڿ��� �ݵ�� " " �ȿ� ����Ͽ��� �Ѵ�.
   ---------------------------------------------------------------------------------------------
   SELECT TO_CHAR(MEM_BIR),
          TO_CHAR(MEM_BIR, 'CC"���� "YYYY-MM-DD DAY')
    FROM MEMBER;
 - �������� �������ڿ�
  ------------------------------------------------------------------------
    ���Ĺ��ڿ�                �ǹ�                    
  ------------------------------------------------------------------------
        9                    �����Ǵ� ��ġ�� ���� ��ȿ�� ���̸� �����͸� ����ϰ� 
                             ��ȿ�� 0�� ����ó��
        0                    �����Ǵ� ��ġ�� ���� ��ȿ�� ���̸� �����͸� ����ϰ� 
                             ��ȿ�� 0�� ���
        $,L(�̱� ���� ȭ��)    ȭ���ȣ�� ���
        MI                   ���� ����� ��� ������ '-'��ȣ ���
        PR                   ���� ����� ��� '-'��� '< >' �ȿ� ���
    ,(�޸�), .(�Ҽ���)     
   -----------------------------------------------------------------------
   ��) 2005�� 2�� ��ǰ�� ������Ȳ�� ��ȸ�Ͻÿ�.
       Alias�� ��ǰ�ڵ�, ���Լ���,���Աݾ�
       SELECT BUY_PROD AS ��ǰ�ڵ�,
              TO_CHAR(SUM(BUY_QTY), '999') AS ���Լ���,
              TO_CHAR(SUM(BUY_QTY*BUY_COST),'L99,999,999') AS ���Աݾ�
         FROM BUYPROD
        WHERE BUY_DATE BETWEEN '20050201' AND LAST_DAY('20050201')
        GROUP BY BUY_PROD
        ORDER BY 1;
   000890000
   999,999,999.99
   000,000,000.00
   --------------
       890,000.00
   000,890,000.00
   
   
   
   
   
   