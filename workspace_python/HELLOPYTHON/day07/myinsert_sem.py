import pymysql
 
# MySQL Connection 연결
conn = pymysql.connect(host='localhost', user='root', password='python',
                       db='python', charset='utf8')
 
curs = conn.cursor()   # 자바에서 statement와 같은 역할이다
 
sql = """
    INSERT INTO stock
    (s_code, s_name, s_price, in_date)
    VALUES
    (%s, %s, %s, %s)
    """

cnt = curs.execute(sql, ('4', '4', '4', '4'))
print("cnt", cnt)

conn.commit()   

conn.close()