2020-11-25-02
4. MOD(n,c), REMAINDER(n,c)
 - n�� c�� ���� ������
 - ���ο��� ���� �ٸ� �Լ��� ����Ͽ� �������� ����
 MOD : n - c * FLOOR(n/c)
 REMAINDER : n - c * ROUND(n/c)
    **FLOOR(n) : n�� ���ų� ���� �ʿ��� ���� ū ����(n�� �ʰ����� �ʴ� �ִ� ����)
    
SELECT MOD(10,3), REMAINDER(10,3) FROM DUAL;    
MOD(10,3) : 10 - 3*FLOOR(10/3)
            10 - 3*FLOOR(3.33333)
            10 - 3*3 -> 10 - 9 -> 1
REMAINDER(10,3) : 10 - 3*ROUND(10/3)
                  10 - 3*ROUND(3.3333)
                  10 - 3*3 -> 10 - 9 -> 1

SELECT MOD(11,3), REMAINDER(11,3) FROM DUAL;                  
MOD(11,3) : 11 - 3*FLOOR(11/3)
            11 - 3*FLOOR(3.66666)
            11 - 3*3 -> 11 - 9 -> 2
REMAINDER(11,3) : 11 - 3*ROUND(11/3)
                  11 - 3*ROUND(3.66666)
                  11 - 3*4 -> 11 - 12 -> -1
                  
5. FLOOR(n), CEIL(n)
 - FLOOR : n�� �ʰ����� �ʴ� �ִ� ����
 - CEIL  : n�� ���ų� n���� ū, ���� ���� ����
          . �Ҽ����� ������ �ʴ� �÷��� ���������Ͱ� �Էµ� ��� �Ҽ����� ������ �ݿø��Ͽ� ���� �ڷḸ �����ϴ� ��� �ַ� ���(�޿�, ���� ��)
��)
SELECT FLOOR(-10), FLOOR(-10.234), CEIL(-10), CEIL(-10.001) FROM DUAL; -- ������ ��쿡�� ���� �޶����Ƿ� �� ������ ��! ������ ���ڰ� ū ���� ���� ���̴�!
SELECT FLOOR(10), FLOOR(10.234), CEIL(10), CEIL(10.001) FROM DUAL;

6. WIDTH_BUCKET(n,min,max,b)
 - �־��� �� n�� min�� max ���̸� b���� �������� �������� ���, �־��� �� n�� ��� ������ ���ϴ����� ��ȯ
 
 ��)ȸ�����̺��� ȸ������ ������ ���ϸ����� 1~10000 ���̸� 10���� �������� ������, �� ȸ���� ���� ���� ���� ��ȸ�Ͻÿ�.
 SELECT MEM_ID AS ȸ����ȣ,
        MEM_NAME AS ȸ����,
        MEM_MILEAGE AS ���ϸ���,
        WIDTH_BUCKET(MEM_MILEAGE, 1,10000,10) AS ������,
        WIDTH_BUCKET(MEM_MILEAGE, 10000,1,10) AS ���
    FROM MEMBER;
    
SELECT MAX(SALARY),MIN(SALARY) FROM EMPLOYEES;

��) ������̺��� �޿��� 2000~30000 ������ ���� 3���� �������� ���� �� �� ����� �޿��� ���� �������� ���ϰ�, 
    �� �������� 1�� ��� '���� �޿�', 2�� ��� '�߰� �޿�',3�� ��� '�� �ӱ�'�̶�� �޽����� ������ ����Ͻÿ�.
    Alias�� �����ȣ, �����, �μ��ڵ�, �޿�, ������, ���
SELECT EMPLOYEE_ID AS �����ȣ, 
       EMP_NAME AS �����, 
       DEPARTMENT_ID AS �μ��ڵ�, 
       SALARY AS �޿�, 
       WIDTH_BUCKET(SALARY,2000,30000,3) AS ������, 
       CASE WHEN WIDTH_BUCKET(SALARY,2000,30000,3) = 1 THEN '���� �޿�' 
            WHEN WIDTH_BUCKET(SALARY,2000,30000,3) = 2 THEN '�߰� �޿�'
            ELSE '�� �ӱ�' END ���
  FROM EMPLOYEES