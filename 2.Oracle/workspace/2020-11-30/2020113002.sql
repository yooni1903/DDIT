2020-11-30-02 ROLLUP�� CUBE
 - SUM �Լ��� �׷캰 ���踸 ��ȯ�ϸ� ��ü �հ踦 ��ȯ���� ����
 - GROUP BY ���� ����� �÷��� �������θ� ���� ��ȯ
 ex)
   SELECT PERIOD, GUBUN,
          SUM(LOAN_JAN_AMT)
    FROM KOR_LOAN_STATUS
    GROUP BY PERIOD, GUBUN
    ORDER BY 1,2;
    
1.ROLLUP
 - GROUP BY ���� ���
 - ROLLUP ������ ����� �÷����� �������� ������ ���谡 �ʿ��� ��� ���
 (�������)
 GROUP BY ROLLUP(�÷���1[,�÷���2,...])
    . ROLLUP ���� ����� �÷��� ���� n���� ��, n+1 ������ ���� ��ȯ
    . �÷���1�� �÷���2�� ������ ����(GROUP BY�� ����) -- �����ʿ� �����ִ� �÷��ϼ��� ������ ���ٰ� ���Ѵ�.
      �÷���1�� �������� ������ ����
      ��ü����
      
 SELECT PERIOD, GUBUN,
          SUM(LOAN_JAN_AMT)
    FROM KOR_LOAN_STATUS
    GROUP BY ROLLUP(PERIOD, GUBUN)
    ORDER BY 1,2;
    
 SELECT SUM(LOAN_JAN_AMT)
    FROM KOR_LOAN_STATUS
   
��) �������̺��� �Ⱓ��, ������, ���к� �����ܾ��� �հ踦 ��ȸ�Ͻÿ�
SELECT PERIOD, REGION, GUBUN,
       SUM(LOAN_JAN_AMT)
  FROM KOR_LOAN_STATUS
  GROUP BY PERIOD, REGION, GUBUN
  ORDER BY 1,2;
  
SELECT PERIOD, REGION, GUBUN,
       SUM(LOAN_JAN_AMT)
  FROM KOR_LOAN_STATUS
  GROUP BY ROLLUP(PERIOD, REGION, GUBUN)
  ORDER BY 1,2;
  
SELECT PERIOD, REGION, GUBUN,
       SUM(LOAN_JAN_AMT)
  FROM KOR_LOAN_STATUS
  GROUP BY CUBE(PERIOD, REGION, GUBUN)
  ORDER BY 1,2;
  