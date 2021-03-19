import pymysql
import requests
from bs4 import BeautifulSoup

from time import strftime
from datetime import datetime
 
response = requests.get('https://www.sedaily.com/Stock/Quote/?mobile')
 
text = response.text
# print(text)
 
soup = BeautifulSoup(text, 'html.parser')   # html.parser : html로 파싱
 
# print(soup.select('.tbody'))
# for info in soup.dl:
    # print(info.text)
    
# for info in soup.select('.tbody'):
    # print("기업이름", info.dt.text, end="  /  ")
    # print("기업코드", info.dd.get('id')[8:], end="  /  ")
    # print("주가", info.dd.span.text)
    # print()
conn = pymysql.connect(host='localhost', user='root', password='python',
                       db='python', charset='utf8')
curs = conn.cursor()

sql = "INSERT INTO stock (s_code, s_name, s_price, in_date) VALUES (%s, %s, %s, %s)"
now = datetime.now()
a = strftime('%Y%m%d:%H%M')
# 선생님 코드
count = 0
for info in soup.select('.tbody'):
    s_name = info.dt.text
    s_price = info.dd.span.text     # span은 여러개지만 for을 안돌리면 첫번째꺼만 가져온다
    s_code_txt = info.dd['id']      # 속성 id의 값을 가져온다
    s_code =  s_code_txt[len(s_code_txt)-6:len(s_code_txt)]     # 문자열 자르는 방법은 그냥 []에 인덱스만 잘 정해주면 된다!
    s_price = s_price.replace(",", "")
    
    cnt = curs.execute(sql, (s_code, s_name, s_price, a))
    if cnt > 0:
        count += 1
    
print(count)
conn.commit()   
    
conn.close()
