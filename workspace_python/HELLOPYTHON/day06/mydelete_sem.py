import pymysql
 
# MySQL Connection 연결
conn = pymysql.connect(host='localhost', user='root', password='python',
                       db='python', charset='utf8')
 
curs = conn.cursor()   # 자바에서 Statement와 같은 역할이다
 
sql = """
         delete from sample
         where col01 = %s
     """

cnt = curs.execute(sql, ('4'))
print("cnt", cnt)

conn.commit()

conn.close()