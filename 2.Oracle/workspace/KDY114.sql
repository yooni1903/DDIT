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
--    <th>아이디</th>
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

COMMENT ON COLUMN MEMBER.MEM_ID IS '회원 아이디';
COMMENT ON COLUMN MEMBER.MEM_PASS IS '비밀번호';
COMMENT ON COLUMN MEMBER.MEM_NAME IS '이름';
COMMENT ON COLUMN MEMBER.MEM_REGNO1 IS '주민번호1';
COMMENT ON COLUMN MEMBER.MEM_REGNO2 IS '주민번호2';
COMMENT ON COLUMN MEMBER.MEM_BIR IS '생일';
COMMENT ON COLUMN MEMBER.MEM_ZIP IS '우편번호';
COMMENT ON COLUMN MEMBER.MEM_ADD1 IS '주소1';
COMMENT ON COLUMN MEMBER.MEM_ADD2 IS '주소2';
COMMENT ON COLUMN MEMBER.MEM_HOMETEL IS '집 전화번호';
COMMENT ON COLUMN MEMBER.MEM_COMTEL IS '회사 전화번호';
COMMENT ON COLUMN MEMBER.MEM_HP IS '휴대폰';
COMMENT ON COLUMN MEMBER.MEM_MAIL IS '이메일';
COMMENT ON COLUMN MEMBER.MEM_JOB IS '직업';
COMMENT ON COLUMN MEMBER.MEM_LIKE IS '취미';
COMMENT ON COLUMN MEMBER.MEM_MEMORIAL IS '기념일';
COMMENT ON COLUMN MEMBER.MEM_MEMORIALDAY IS '기념일자';
COMMENT ON COLUMN MEMBER.MEM_MILEAGE IS '마일리지';
COMMENT ON COLUMN MEMBER.MEM_DELETE IS '탈퇴여부';
