from flask import Flask, render_template
from flask import request
import pymysql
def getStocks(s_name):
    ret = []
    conn = pymysql.connect(host='localhost', user='root', password='python',
                           db='python', charset='utf8')
     
    curs = conn.cursor()   # 자바에서 statement와 같은 역할이다
     
    sql = """
    SELECT s_code,
             s_name,
             s_price,
             in_date
    FROM stock
    WHERE s_name = '"""+s_name+"""'"""
    curs.execute(sql)
    rows = curs.fetchall()
    for s in rows:
        temp = {'s_code':s[0], 's_name':s[1], 's_price':s[2], 'in_date':s[3]} 
        ret.append(temp)
    
    conn.close()
    return ret

app = Flask(__name__)
@app.route("/stockSem")
def mylist():         
    s_name = request.args.get('s_name', '삼성전자')  
    list = getStocks(s_name)                
    return render_template('stock_sem.html', a="으하하", list=list)

if __name__ == "__main__":              
    app.run(host="127.0.0.1", port="80")
