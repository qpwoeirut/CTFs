from python:3.8-slim
EXPOSE 5001/tcp

WORKDIR /code
copy * /code/

RUN pip install -r requirements.txt

CMD ["python", "server.py"]