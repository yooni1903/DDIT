#app.py
from flask import Flask, render_template, request, flash, redirect, url_for
from flask_recaptcha import ReCaptcha
 
app = Flask(__name__)
recaptcha = ReCaptcha(app=app)
 
app.config.update(dict(
    RECAPTCHA_ENABLED = True,
    RECAPTCHA_SITE_KEY = "6LdeI48aAAAAAFexL57NpMIWDKaQP-p5LIqvNnY7",
    RECAPTCHA_SECRET_KEY = "6LdeI48aAAAAAO_UUHoOTLKgSY_5GVz9qfrkgGTH",
))
 
recaptcha = ReCaptcha()
recaptcha.init_app(app)
 
app.config['SECRET_KEY'] = 'cairocoders-ednalan'
 
 
@app.route('/register')
def register():
    return render_template("form.html")    
 
@app.route('/submit', methods=['POST'])
def submit():
    if recaptcha.verify():
        flash('New Device Added successfully')
        return redirect(url_for('register'))
    else:
        flash('Error ReCaptcha')
        return redirect(url_for('register'))
   
if __name__ == '__main__':
 app.run(debug=True)