import pymongo

connection = pymongo.MongoClient("mongodb://localhost:27017/")
db = connection.python
sample = db.sample

cnt = sample.update_many({"col01": "12"},{'$set':{"col02":"4","col03":"4" }})
print(cnt)