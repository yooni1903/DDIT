from mpl_toolkits.mplot3d import Axes3D
import pymongo
import pymysql
import matplotlib as mpl
import matplotlib.pyplot as plt  
import numpy as np               
def getPrices(s_name):
    arr = []
    conn = pymongo.MongoClient("mongodb://localhost")
    db = conn.python
    stock = db.stock
    rows = stock.find({"s_name" : s_name}).sort("in_date",  1)
    first_price = int(rows[0]['s_price'])
    print(first_price)
    for row in rows:
        print(row)
        arr.append(int(row['s_price'])/first_price)
    conn.close()
    return arr

mpl.rcParams['legend.fontsize'] = 10           
fig = plt.figure()                               
ax = fig.gca(projection='3d')
zs = []
x = np.zeros(10) 
y = range(10)
zs.append(getPrices('삼성전자'))
zs.append(getPrices('LG'))
zs.append(getPrices('SK'))
ax.plot(x+0, y, zs[0], label='SAMSUNG')       
ax.plot(x+1, y, zs[1], label='LG')       
ax.plot(x+2, y, zs[2], label='SK')       
ax.legend()                                        
plt.show()