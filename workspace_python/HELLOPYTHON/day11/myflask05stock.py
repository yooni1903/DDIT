from flask import Flask, render_template
from flask import request
import pymysql

def mystock(s_name):
    conn = pymysql.connect(host='localhost', user='root', password='python',
                       db='python', charset='utf8')
 
    curs = conn.cursor()   # 자바에서 statement와 같은 역할이다
     
    sql = """
            SELECT s_code,
                     s_name,
                     s_price,
                     in_date
            FROM stock
            WHERE s_name = %s
        """
    curs.execute(sql, s_name)
    
    
    rows = curs.fetchall()
    
    # list =[]
    # for obj in rows:
        # line ={
            # 's_code': obj[0],
            # 's_name': obj[1],
            # 's_price': obj[2],
            # 'in_date': obj[3]
            # }
        # list.append(line)
    # conn.close() 
    # return list
    
    conn.close() 
    print("메서드")
    print(rows)
    return rows



app = Flask(__name__)
@app.route("/stock")
def mylist():         
    s_name = request.args.get('s_name', "상장폐지")  
    
    list = mystock(s_name)  
    print(list)                
    
    return render_template('stock.html', list=list)

if __name__ == "__main__":              
    app.run(host="127.0.0.1", port="80")
