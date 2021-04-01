import matplotlib as mpl
from mpl_toolkits.mplot3d import Axes3D
import numpy as np                
import matplotlib.pyplot as plt   
import pymysql


def getAllPrices():
    # MySQL Connection 연결
    zs = []
    conn = pymysql.connect(host='localhost', user='root', password='python',
                           db='_stock_old', charset='utf8')
     
    curs = conn.cursor()   # 자바에서 statement와 같은 역할이다
     
    sql = """
            SELECT *
            FROM stock_sync_0121


        """
    curs.execute(sql)
    rows = curs.fetchall()
    
    cnt10 = len(rows)
    cnt3 = len(rows[0])

    for i3 in range(cnt3-1):
        line = []
        first_price = rows[0][i3]
        for j10 in range(cnt10):
            if first_price == 0:
                line.append(0.8)
            else:
                line.append(rows[j10][i3]/first_price)
        zs.append(line)
    
    conn.close()
    return zs

mpl.rcParams['legend.fontsize'] = 10      
fig = plt.figure()                            
ax = fig.gca(projection='3d')   # 3d 그래프 그리는 명령  
# MySQL Connection 연결
    
zs = getAllPrices()
x   = np.zeros(len(zs[0]))
y   = range(len(zs[0]))


for i in range(len(zs)):
    ax.plot(x+i, y, zs[i])


plt.show()


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