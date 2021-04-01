import requests
from bs4 import BeautifulSoup
 
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

# 선생님 코드
for info in soup.select('.tbody'):
    s_name = info.dt.text
    s_price = info.dd.span.text     # span은 여러개지만 for을 안돌리면 첫번째꺼만 가져온다
    s_code_txt = info.dd['id']      # 속성 id의 값을 가져온다
    s_code =  s_code_txt[len(s_code_txt)-6:len(s_code_txt)]     # 문자열 자르는 방법은 그냥 []에 인덱스만 잘 정해주면 된다!
    
    print(s_name, end="\t")
    print(s_price, end="\t")
    print(s_code)
    print()
