import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="python",
  database='python'
)

cursor = mydb.cursor()
query = ("insert into sample(col01, col02, col03) values(%s, %s, %s)")
value =("5","5","5")

cursor.execute(query, value)

mydb.commit()

print(cursor.rowcount)