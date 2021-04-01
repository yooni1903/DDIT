import pymongo

connection = pymongo.MongoClient("mongodb://localhost")
db = connection.python
sample = db.sample

doc = {'col01':'1','col02':'1','col03':'1'}

sample.insert_one(doc)


