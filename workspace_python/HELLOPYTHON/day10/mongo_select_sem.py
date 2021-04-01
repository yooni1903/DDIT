import pymongo

connection = pymongo.MongoClient("mongodb://localhost:27017/")

db = connection.python
sample = db.sample


rows = sample.find({"col01":"1"})


for x in rows:
    print("여러개",x)