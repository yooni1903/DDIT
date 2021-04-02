--private String mem_id;

select 'private '||
    decode(data_type, 'NUMBER', 'Integer ', 'String ')||
    lower(column_name)||';'
from cols
where table_name = 'MEMBER';

--member.setMem_id(rs.getString("MEM_ID"));

select '.set'||substr(column_name,0,1)||lower(substr(column_name,2))||'(rs.get'||
    decode(data_type, 'NUMBER', 'Int', 'String')||'("'||column_name||'"));'
from cols
where table_name = 'MEMBER';


SELECT LOWER(TABLE_NAME)||'.setMem_'||
        LOWER(SUBSTR(COLUMN_NAME, INSTR(COLUMN_NAME, '_')+1))||
        '(rs.get'||
        DECODE(DATA_TYPE, 'NUMBER', 'Int', 'String')||
        '("'||COLUMN_NAME||'"));'
FROM COLS
WHERE TABLE_NAME = 'MEMBER';


SELECT
	    MEM_ID, MEM_PASS, MEM_NAME,
	    MEM_REGNO1, MEM_REGNO2, TO_CHAR(MEM_BIR, 'YYYY-MM-DD'),
	    MEM_ZIP,    MEM_ADD1,    MEM_ADD2,
	    MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,
	    MEM_MAIL,    MEM_JOB,    MEM_LIKE,
	    MEM_MEMORIAL,    TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD'), MEM_MILEAGE,
	    MEM_DELETE
	    FROM MEMBER
	    WHERE MEM_ID = 'a001';

--<tr>
--    <th>���̵�</th>
--    <td><%=member.getMem_id() %></td>
-- </tr>

SELECT '<tr><th>' || COMMENTS || '</th><td><%=' || LOWER(TABLE_NAME) || '.get' ||
        SUBSTR(COLUMN_NAME, 1,1) ||
        LOWER(SUBSTR(COLUMN_NAME, 2)) ||
        '() %></td></tr>'
FROM USER_COL_COMMENTS
WHERE TABLE_NAME = 'MEMBER';

-- COMMENT ON COLUMN MEMBER.MEM_ID IS

SELECT 'COMMENT ON COLUMN '||
    TABLE_NAME || '.' || COLUMN_NAME || ' IS '''';'
FROM COLS
WHERE TABLE_NAME = 'MEMBER';

SELECT *
FROM USER_COL_COMMENTS;

COMMENT ON COLUMN MEMBER.MEM_ID IS 'ȸ�� ���̵�';
COMMENT ON COLUMN MEMBER.MEM_PASS IS '��й�ȣ';
COMMENT ON COLUMN MEMBER.MEM_NAME IS '�̸�';
COMMENT ON COLUMN MEMBER.MEM_REGNO1 IS '�ֹι�ȣ1';
COMMENT ON COLUMN MEMBER.MEM_REGNO2 IS '�ֹι�ȣ2';
COMMENT ON COLUMN MEMBER.MEM_BIR IS '����';
COMMENT ON COLUMN MEMBER.MEM_ZIP IS '�����ȣ';
COMMENT ON COLUMN MEMBER.MEM_ADD1 IS '�ּ�1';
COMMENT ON COLUMN MEMBER.MEM_ADD2 IS '�ּ�2';
COMMENT ON COLUMN MEMBER.MEM_HOMETEL IS '�� ��ȭ��ȣ';
COMMENT ON COLUMN MEMBER.MEM_COMTEL IS 'ȸ�� ��ȭ��ȣ';
COMMENT ON COLUMN MEMBER.MEM_HP IS '�޴���';
COMMENT ON COLUMN MEMBER.MEM_MAIL IS '�̸���';
COMMENT ON COLUMN MEMBER.MEM_JOB IS '����';
COMMENT ON COLUMN MEMBER.MEM_LIKE IS '���';
COMMENT ON COLUMN MEMBER.MEM_MEMORIAL IS '�����';
COMMENT ON COLUMN MEMBER.MEM_MEMORIALDAY IS '�������';
COMMENT ON COLUMN MEMBER.MEM_MILEAGE IS '���ϸ���';
COMMENT ON COLUMN MEMBER.MEM_DELETE IS 'Ż�𿩺�';
