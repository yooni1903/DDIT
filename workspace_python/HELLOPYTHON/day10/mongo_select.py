from pymongo import MongoClient

my_client = MongoClient("mongodb://localhost:27017/")

db = my_client['python']
collection = db['sample']

x = collection.find_one()
print("한개",x)

list = collection.find()

for x in list:
    print("여러개",x)