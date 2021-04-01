import requests
from bs4 import BeautifulSoup
from urllib.request import urlopen
import pymongo
import datetime
import time

# 크롤링한 데이터 몽고db에 넣고 삼성, LG, SK select하여 그래프 그리기

connection = pymongo.MongoClient("mongodb://localhost")
db = connection.python
mystock = db.mystock

count = 0
for i in range(10):
    url = urlopen('https://www.sedaily.com/Stock/Quote/?mobile')
    soup = BeautifulSoup(url, 'html.parser')
    body = soup.body
    
    target = body.find_all(class_="tbody")
    yyyymmdd_hhmm = datetime.datetime.now().strftime('%Y%m%d.%H%M')
    
    for info in target:
        s_name = info.find('dt').text
        s_price = info.find('dd').find('span').text.replace(",", "")
        a = info.find('dd')['id']
        s_code = a[len(a)-6:len(a)]
        doc = {"s_name" : s_name, "s_price":s_price, "s_code":s_code,"in_date": yyyymmdd_hhmm}
        mystock.insert_one(doc)
    
    time.sleep(60)     

# for i in range(10):
    # print("i", i)
    # response = requests.get('https://www.sedaily.com/Stock/Quote/?mobile')
    # txt = response.text
    # soup = BeautifulSoup(txt, 'html.parser')
    # yyyymmdd_hhmm = datetime.datetime.now().strftime('%Y%m%d.%H%M') 
    #
    # for info in soup.select('.tbody'):
    #
        # s_name = info.dt.text
        # s_price = info.dd.span.text.replace(",","")
        # s_code_txt = info.dd['id']
        # s_code = s_code_txt[len(s_code_txt)-6:len(s_code_txt)]
        #
        # cnt = curs.execute(sql, (s_code, s_name, s_price, yyyymmdd_hhmm))
    # conn.commit()
    # time.sleep(60)
    #
    #
    #
# conn.close()