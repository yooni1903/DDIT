import requests
from bs4 import BeautifulSoup
import selenium
from selenium import webdriver
from selenium.webdriver import ActionChains

from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By

from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support.ui import WebDriverWait

URL = 'http://localhost/MYSERVER/secret'

driver = webdriver.Chrome(executable_path='chromedriver')
driver.get(url=URL)

driver.find_element_by_tag_name("input")
 
response = requests.get(URL)
 
text = response.text
# print(text)
 
soup = BeautifulSoup(text, 'html.parser')   # html.parser : html로 파싱
 
for info in soup.select('td'):
    print(info.text)
