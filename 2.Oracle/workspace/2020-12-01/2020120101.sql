2020-12-01-01
2. CUBE(col1, col2, ...)
 - �پ��� ����ó��(2^���� �÷���)
 - ROLLUP�� ������� ������ ������ ������� �ʴ´�.
 ��) �����ܾ����̺�(KOR_LOAN_STATUS)���� �Ⱓ, ����, ���ⱸ�� �׸��� �̿��Ͽ� ���� ������ ��� ���踦 ��ȸ
 SELECT PERIOD, REGION, GUBUN, SUM(LOAN_JAN_AMT)
   FROM KOR_LOAN_STATUS
 GROUP BY CUBE(PERIOD, REGION, GUBUN)
 ORDER BY 1,2;