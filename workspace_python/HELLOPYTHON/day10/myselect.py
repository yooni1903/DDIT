import pymysql

def getAllPrices():
    # MySQL Connection 연결
    zs = []
    conn = pymysql.connect(host='localhost', user='root', password='python',
                           db='_stock_old', charset='utf8')
     
    curs = conn.cursor()   # 자바에서 statement와 같은 역할이다
     
    sql = """
            SELECT s000020, s000040, s000050
            FROM stock_sync_0121
            LIMIT 10

        """
    curs.execute(sql)
    rows = curs.fetchall()
    
    cnt10 = len(rows)
    cnt3 = len(rows[0])

    for i3 in range(cnt3):
        line = []
        first_price = rows[0][i3]
        for j10 in range(cnt10):
            line.append(rows[j10][i3]/first_price)
        zs.append(line)
    
    conn.close()
    return zs
    
if __name__ == '__main__':
    arr = getAllPrices()
    print(arr)
    