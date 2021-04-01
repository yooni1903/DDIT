import matplotlib as mpl
from mpl_toolkits.mplot3d import Axes3D
import numpy as np                
import matplotlib.pyplot as plt   
import numpy as np
import pymysql


conn = pymysql.connect(host='localhost', user='root', password='python', db='_stock_old', charset='utf8')
curs = conn.cursor()

sql = """
SELECT COLUMN_NAME
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA='_stock_old'  
    AND TABLE_NAME='stock_sync_0121'
    and column_name != 'in_time'
"""
curs.execute(sql)
rows = curs.fetchall()
names = []
for i in rows:
    names.append(i)
sql = """
    SELECT 
       *
    FROM stock_sync_0121
    ORDER BY in_time
"""
curs.execute(sql)

rows = curs.fetchall()
x = np.zeros(len(rows))
y = range(len(rows))

zs = []

for i in range(30):
    arr = []
    first_price = rows[0][i]
    for row in rows:
        print(row[i])
        arr.append(row[i]/first_price)
    zs.append(arr)
# Connection 닫기
conn.close()


mpl.rcParams['legend.fontsize'] = 10           

fig = plt.figure()                                
ax = fig.gca(projection='3d')

# x = np.zeros(len(rows[0])-1,dtype=int)
for i in range(30):
    name = str(names[i])
    ax.plot(x+i, y, zs[i], label=name[2:9])      
# ax.plot(x+2, y, zs[2], label='SK')      
ax.legend()                                     

plt.show()


