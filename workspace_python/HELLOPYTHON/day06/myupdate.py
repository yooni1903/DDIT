import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="python",
  database='python'
)

cursor = mydb.cursor()
query = ("update sample set col01 = '5765' where col03 = '5'" )

cursor.execute(query)

mydb.commit()

print(cursor.rowcount)