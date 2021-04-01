import matplotlib as mpl
from mpl_toolkits.mplot3d import Axes3D
import numpy as np                
import matplotlib.pyplot as plt   
import pymysql
from sympy.physics import pring

# MySQL Connection 연결
arr = []
conn = pymysql.connect(host='localhost', user='root', password='python',
                       db='_stock_old', charset='utf8')
 
curs = conn.cursor()   # 자바에서 statement와 같은 역할이다
 
sql = """
        SELECT * 
    FROM stock_sync_0121

    """
curs.execute(sql)


rows = curs.fetchall()
# print(len(rows))
# print(rows[0][0])    
# print(rows)

for i in rows:
    # print(i[len(i)-1])
    for j in range(len(i)):
        if not str(i[j]).isdigit(): continue
        # if(j == len[i]-1):
            # break
        b = rows[0][j]
        print(i[j], b)
        if(b == 0):
            b = 0 - rows[0][j]
            print(i[j], rows[0][j], b, j)
    print()
# for i in rows[0]:
    # print(i[0], end=" ")
    # print(i[])
    # for j in rows[0]:
        # print(j)
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