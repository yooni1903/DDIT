import pymongo

connection = pymongo.MongoClient("mongodb://localhost:27017/")
db = connection.python
sample = db.sample

cnt = sample.delete_many({"col01": "12"})
print(cnt)