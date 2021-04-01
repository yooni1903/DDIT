import requests
APP_KEY = '28e0b013532f6ef71e68b0ff2fcd5979'
IMAGE_URL = 'https://www.canceranswer.co.kr/news/photo/202007/980_1425_5550.jpg'
session = requests.Session()
session.headers.update({'Authorization': 'KakaoAK ' + APP_KEY})

response = session.post('https://cv-api.kakaobrain.com/pose', data={'image_url': IMAGE_URL})
print(response.status_code, response.json())