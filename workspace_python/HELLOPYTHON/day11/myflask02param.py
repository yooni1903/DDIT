from flask import Flask
from flask import request

app = Flask(__name__)

@app.route("/get" )
def hello():
    # temp = request.args.get('name', "하하하")  뒤에 붙는 값은 null일 경우 붙는 default값이다.
    temp = request.args.get('a', "와아아")                           
    return "Go Flask! "+temp

@app.route("/post", methods=['POST'])
def hellopost():
    a = request.form.get('a')              
    return "Go Flask! "+a

if __name__ == "__main__":              
    app.run(host="127.0.0.1", port="80")
