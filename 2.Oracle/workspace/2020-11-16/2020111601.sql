2020-11-16-01) ����� ����
 - CREATE ������� ����� ����
 - GRANT ������� ���� �ο�
 - Developer�� ���

**��ȣ
 '[ ]' : ���� ����
 '|' : ���� ���
 ',...' : �ݺ��� �� ����
 
1.CREATE USER ���
 - ����Ŭ ��ü(TABLE, USER, INDEX, etc) �� ����ڸ� ������Ŵ
 (�������)
CREATE USER ������ IDENTIFIED BY ��ȣ;

��)
CREATE USER KDY11 IDENTIFIED BY java;

2. ���� �ο�
GRANT ���Ѹ�,... TO ������;
��)
GRANT CONNECT,RESOURCE,DBA TO KDY11;