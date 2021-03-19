import pymysql
import matplotlib.pyplot as plt
# MySQL Connection 연결
conn = pymysql.connect(host='localhost', user='root', password='python',
                       db='python', charset='utf8')
 
curs = conn.cursor()   # 자바에서 statement와 같은 역할이다
 
sql =  """
    SELECT * FROM stock
    WHERE s_name = %s or s_name = %s

    """
a = ['삼성전자', 'LG']
curs.execute(sql, ('삼성전자', 'LG'))
plt.figure(figsize=(10,4))
rows = curs.fetchall()
for i in range(2):
    plt.plot(rows[3], rows[2])


   
plt.xlabel('date')
plt.ylabel('price')
plt.tick_params(
    axis='x',          # changes apply to the x-axis
    which='both',      # both major and minor ticks are affected
    bottom=False,      # ticks along the bottom edge are off
    top=False,         # ticks along the top edge are off
    labelbottom=False) # labels along the bottom edge are off
plt.show()
     

conn.close()