from pymongo import MongoClient

client = MongoClient('localhost', 27017)

db = client['local']

collection = db['sample']

# x = collection.insert_one({
    # "col01": '1',
   # "col02":'1',
    # "col03":'1'
    # })

data = [{"col01":'2', "col02" : '2', "col03" :'2'},
        {"col01":'1', "col02" : '2', "col03" :'3'},
        {"col01":'3', "col02" : '3', "col03" :'3'},
        {"col01":'28', "col02" : '8', "col03" :'6'},]

x = collection.insert_many(data)


