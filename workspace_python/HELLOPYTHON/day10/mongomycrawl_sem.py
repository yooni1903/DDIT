from _datetime import datetime
from datetime import datetime
import threading
import time
from bokeh.core.property.datetime import Datetime
from bs4 import BeautifulSoup
import pymongo
import pymysql
import requests
def insertStock(s_name, s_code, s_price, formatted_date):
    connection = pymongo.MongoClient("mongodb://localhost")
    db = connection.python
    stock = db.stock
    now = datetime.now()
    doc = {"s_name" : s_name, "s_code" : s_code, "s_price" : s_price, "in_date": formatted_date}
    stock.insert_one(doc)
for i in range(10):
    response = requests.get('https://www.sedaily.com/Stock/Quote/?mobile')
    text = response.text
    # print(text)
    soup = BeautifulSoup(text, 'html.parser')
    now = datetime.now()
    formatted_date = now.strftime('%Y%m%d.%H:%M')
    print("i", i)
    for info in soup.select('.tbody'):
        s_name = info.dt.text
        s_code_text = info.dd["id"]
        s_code = info.dd["id"][len(s_code_text)-6:len(s_code_text)]
        s_price = info.dd.span.text.replace(",","");
        # print(title, "/", num, "/", price)
        # print("-----------------------------------------------")
        insertStock(s_name, s_code, s_price, formatted_date)
    time.sleep(60)