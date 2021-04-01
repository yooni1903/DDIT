from flask import Flask, render_template

app = Flask(__name__)
@app.route("/hello")
def hello():         
    return render_template('list.html', a="으하하")

if __name__ == "__main__":              
    app.run(host="127.0.0.1", port="80")
