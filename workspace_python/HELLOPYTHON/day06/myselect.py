import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="python",
  database='python'
)

cursor = mydb.cursor()
query = ("select * from sample")

cursor.execute(query)

for ddd in cursor:
    print(ddd)