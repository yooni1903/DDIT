from selenium import webdriver
import time

driver = webdriver.Chrome('chromedriver.exe')

url = 'http://localhost/MYSERVER/login.html'
driver.get(url)

driver.find_element_by_id('u_name').send_keys('abe')
driver.find_element_by_id('pwd').send_keys('1')
driver.find_element_by_id('mysubmit').click()

driver.implicitly_wait(5)

url = 'http://localhost/MYSERVER/secret'
driver.get(url)



all_tds = driver.find_elements_by_tag_name("td")

for td in all_tds:
    print(td.text)


# print(driver.page_source)
