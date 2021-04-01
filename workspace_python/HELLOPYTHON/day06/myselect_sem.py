import pymysql
 
# MySQL Connection 연결
conn = pymysql.connect(host='localhost', user='root', password='python',
                       db='python', charset='utf8')
 
curs = conn.cursor()   # 자바에서 statement와 같은 역할이다
 
sql = "select col01, col02, col03 from sample"
curs.execute(sql)


rows = curs.fetchall()
print(rows)    

conn.close()