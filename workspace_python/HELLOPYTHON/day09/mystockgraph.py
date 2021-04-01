import matplotlib as mpl
from mpl_toolkits.mplot3d import Axes3D
import numpy as np                
import matplotlib.pyplot as plt   
import pymysql


def getPrices(s_name):
    # MySQL Connection 연결
    arr = []
    conn = pymysql.connect(host='localhost', user='root', password='python',
                           db='python', charset='utf8')
     
    curs = conn.cursor()   # 자바에서 statement와 같은 역할이다
     
    sql = """
            SELECT s_code,
                     s_name,
                     s_price,
                     in_date
            FROM stock
            WHERE s_name = '""" + s_name +"""'
        """
    curs.execute(sql)
    
    
    rows = curs.fetchall()
    # print(rows[0][0])    
    
    for i in rows:
        arr.append(i[2])
    
    conn.close()
    return arr


mpl.rcParams['legend.fontsize'] = 10           

fig = plt.figure()                            
ax = fig.gca(projection='3d')   # 3d 그래프 그리는 명령


zs = []
x   = np.zeros(10, dtype=int)
y   = range(10)
zs.append(getPrices('삼성전자'))
zs.append(getPrices('LG'))
zs.append(getPrices('SK'))



ax.plot(x+0, y, zs[0], label='Samsung')       
ax.plot(x+1, y, zs[1], label='LG')       
ax.plot(x+2, y, zs[2], label='SK')       
                        
ax.legend()                                     

plt.show()