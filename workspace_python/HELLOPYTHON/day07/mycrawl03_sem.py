import requests
from bs4 import BeautifulSoup
import pymysql
import datetime

conn = pymysql.connect(host='localhost', user='root', password='python', db='python', charset='utf8')
curs = conn.cursor()

sql = """
    INSERT INTO stock
    (
        s_code,
        s_name,
        s_price,
        in_date
    )
    VALUES
    (
        %s,
        %s,
        %s,
        %s
    )
    """

 
response = requests.get('https://www.sedaily.com/Stock/Quote/?mobile')
 
txt = response.text

soup = BeautifulSoup(txt, 'html.parser')
 
yyyymmdd_hhmm = datetime.datetime.now().strftime('%Y%m%d.%H%M') 
 
for info in soup.select('.tbody'):

    s_name = info.dt.text
    s_price = info.dd.span.text.replace(",","")
    s_code_txt = info.dd['id']
    s_code = s_code_txt[len(s_code_txt)-6:len(s_code_txt)]
    print(s_name ,end="\t")
    print(s_price,end="\t")
    print(s_code)
    
    cnt = curs.execute(sql, (s_code, s_name, s_price, yyyymmdd_hhmm))
    print("cnt",cnt)

    
    
    
conn.commit()
conn.close()