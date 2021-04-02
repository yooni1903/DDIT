2020-12-02-02 JOIN
3.SELF JOIN
 - �ϳ��� ���̺��� ���̺� ��Ī�� �̿��Ͽ� 2���� ���̺�ó�� �ڽ��� ���̺��� �ڽ��� ���̺�� JOIN�ϴ� ���
 
��)ȸ�����̺��� ȸ����ȣ 'r001' ȸ���� ������ ���ϸ������� ���� ���ϸ����� ������ ȸ���� ��ȸ�Ͻÿ�.
    Alias ȸ����ȣ, ȸ����, ���ϸ���
SELECT B.MEM_ID AS ȸ����ȣ, 
       B.MEM_NAME AS ȸ����, 
       B.MEM_MILEAGE AS ���ϸ���
  FROM MEMBER A, MEMBER B -- A : 'r001' ȸ��, B : ��üȸ��
 WHERE UPPER(A.MEM_ID) = 'T001'
   AND A.MEM_MILEAGE < B.MEM_MILEAGE
   ORDER BY 3 DESC;
   
(���������� ������� ��)
SELECT A.MEM_ID AS ȸ����ȣ, 
       A.MEM_NAME AS ȸ����, 
       A.MEM_MILEAGE AS ���ϸ���,
       B.MILE AS T001ȸ��
  FROM MEMBER A, (SELECT MEM_MILEAGE AS MILE
                    FROM MEMBER
                   WHERE UPPER(MEM_ID) = 'T001') B
   WHERE A.MEM_MILEAGE > B.MILE
   ORDER BY 3 DESC;
   
SELECT MEM_ID AS ȸ����ȣ, 
       MEM_NAME AS ȸ����, 
       MEM_MILEAGE AS ���ϸ���
  FROM MEMBER 
  WHERE MEM_MILEAGE > (SELECT MEM_MILEAGE
                         FROM MEMBER
                        WHERE UPPER(MEM_ID) = 'T001')
   ORDER BY 3 DESC;
   
   
��)'������'�ŷ�ó�� ���� ������ �ּ����� �ΰ� �ִ� �ŷ�ó�� ��ȸ�Ͻÿ�.
    Alias�� �ŷ�ó�ڵ�, �ŷ�ó��, �ּ�, �����(BUYER_CHARGER)

SELECT B.BUYER_ID AS �ŷ�ó�ڵ�, 
       B.BUYER_NAME AS �ŷ�ó��, 
       B.BUYER_ADD1||' '||B.BUYER_ADD2 AS �ּ�, 
       B.BUYER_CHARGER AS �����
  FROM BUYER A, BUYER B -- A�� ������, B�� ���̺� ��ü
 WHERE A.BUYER_NAME = '������'
   AND SUBSTR(A.BUYER_ADD1,1,2) = SUBSTR(B.BUYER_ADD1,1,2);
   

��)��ٱ������̺��� 't001'ȸ������ �Ǹ��� 2005�⵵ ���� �Ǹ���Ȳ�� ��ȸ�Ͻÿ�.
    Alias�� ȸ����, �ֹι�ȣ, ��, ���ž��հ�
SELECT ȸ����, �ֹι�ȣ, ��, ���ž��հ�
  FROM 