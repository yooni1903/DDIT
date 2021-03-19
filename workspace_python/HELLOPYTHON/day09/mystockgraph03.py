import matplotlib as mpl
from mpl_toolkits.mplot3d import Axes3D
import numpy as np                
import matplotlib.pyplot as plt   
import pymysql

mpl.rcParams['legend.fontsize'] = 10      
fig = plt.figure()                            
ax = fig.gca(projection='3d')   # 3d 그래프 그리는 명령  
# MySQL Connection 연결
conn = pymysql.connect(host='localhost', user='root', password='python',
                       db='_stock_old', charset='utf8')
 
curs = conn.cursor()   # 자바에서 statement와 같은 역할이다
 
sql = """
        SELECT * 
    FROM stock_sync_0121

    """
curs.execute(sql)


rows = curs.fetchall()
# print(rows[0][0])    
# print(rows)
zs = []
x   = np.zeros(len(rows), dtype=int)
y   = range(len(rows))
# print("여기",len(rows[0]))

for i in range(len(rows[0])):
    zs.append([])

for i in rows:
    arr = []
    cnt = 0
    for j in range(len(i)):
        if not str(i[j]).isdigit(): continue
        b = rows[0][j]
        a = 0
        if(b == 0):
            b = 0 - rows[0][j]
            a = 0
        else:
            a = i[j] / b
        zs[cnt].append(a)
        # zs[cnt].append(arr)
        cnt += 1
        
for i in range(800):
    ax.plot(x+i, y, zs[i], label = 'hi')
  
        # print(j)
ax.legend()                                     
#
plt.show()
conn.close()
# return arr


# mpl.rcParams['legend.fontsize'] = 10           
#
# fig = plt.figure()                            
# ax = fig.gca(projection='3d')   # 3d 그래프 그리는 명령
#
#
# zs = []
# x   = np.zeros(10, dtype=int)
# y   = range(10)
# zs.append(getPrices('삼성전자'))
# zs.append(getPrices('LG'))
# zs.append(getPrices('SK'))
#
#
#
# ax.plot(x+0, y, zs[0], label='Samsung')       
# ax.plot(x+1, y, zs[1], label='LG')       
# ax.plot(x+2, y, zs[2], label='SK')       
                        #
# ax.legend()                                     
#
# plt.show()