from selenium import webdriver
import time

driver = webdriver.Chrome('chromedriver.exe')

url = 'http://localhost/MYSERVER/login'
driver.get(url)

time.sleep(5)

url = 'http://localhost/MYSERVER/secret'
driver.get(url)


print(driver.page_source)
