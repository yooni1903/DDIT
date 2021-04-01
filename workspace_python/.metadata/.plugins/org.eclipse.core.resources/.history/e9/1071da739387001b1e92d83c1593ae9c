import requests
from bs4 import BeautifulSoup
 
response = requests.get('http://localhost/MYSERVER/secret.html')
 
text = response.text
# print(text)
 
soup = BeautifulSoup(text, 'html.parser')   # html.parser : html로 파싱
 
for info in soup.select('td'):
    print(info.text)

