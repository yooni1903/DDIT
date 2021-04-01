import matplotlib as mpl
from mpl_toolkits.mplot3d import Axes3D
import numpy as np                
import matplotlib.pyplot as plt   
import pymongo

def getPrices(s_name):
    # MySQL Connection 연결
    connection = pymongo.MongoClient("mongodb://localhost")
    db = connection.python
    mystock = db.mystock
    arr = []
    
    rows = mystock.find({"s_name":s_name})
    #
    # for i in rows:
        # print(i)
    
    print(rows[0])
    # print(rows[0][0])    
    stand = rows[0]['s_price']
    print("stand", stand)
    for i in rows:
        a = int(i['s_price']) / int(stand)
        arr.append(a)
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