from bs4 import BeautifulSoup
from gevent.pywsgi import WSGIServer
from flask import Flask, request, send_from_directory
from flask_limiter import Limiter
from flask_limiter.util import get_remote_address

import requests

flag = 'ctf{00000000000000000000000000000000}'

app = Flask(__name__)

limiter = Limiter(
	app,
	key_func=get_remote_address,
)

# please test this locally so this challenge doesn't get cloudflare'd
@app.route('/whoami', methods=['POST'])
@limiter.limit('5 per minute') 
def whoami():
	token = request.form.get('token', None)
	if token == None or not 1 < len(token) < 60:
		print("bad", token)
		return 'invalid token'

	token = request.form['token']
	res = requests.get('https://dmoj.ca/user', headers={
		'Authorization': 'Bearer ' + token
	})

	if not res.ok:
		print("wrong", token)
		return 'invalid token'

	dom = BeautifulSoup(res.text, features='html.parser')
	user = dom.select_one('#user-links b')

	if user == None:
		print("no user", token)
		return 'invalid token'

	if user.text == 'flag': # https://dmoj.ca/user/flag
		return flag

	return user.text


@app.route('/')
def index():
	return send_from_directory('', 'index.html')

WSGIServer(('0.0.0.0', 5001), app).serve_forever()