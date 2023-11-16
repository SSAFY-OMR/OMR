from flask import Flask, request, jsonify
from answer import get_answer

app = Flask(__name__)


@app.route('/')
def hello():
    return 'success'


@app.route('/chat', methods=['POST'])
def chat():
    params = request.get_json()
    category = params['category']
    question = params['question']
    
    answer = get_answer(category, question)
    return jsonify({"code": '200', "message": 'success', "data": answer})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
