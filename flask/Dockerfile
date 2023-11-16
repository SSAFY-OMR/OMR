FROM python:3.9-slim

COPY . /app

WORKDIR /app

RUN apt-get update && apt-get install git -y
RUN pip3 install --upgrade pip
RUN pip3 install -r requirements.txt

EXPOSE 5000

CMD python ./app.py