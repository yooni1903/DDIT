import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="python",
  database='python'
)

cursor = mydb.cursor()
query = ("delete from sample where col01 = '1'")

cursor.execute(query)

mydb.commit()

print(cursor.rowcount)