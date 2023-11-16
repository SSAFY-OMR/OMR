from dotenv import load_dotenv
from translation import translate
from deep_translator import GoogleTranslator
import google.generativeai as palm
import os

load_dotenv()
API_KEY = str(os.environ.get('API_KEY'))
DEEP_KEY = str(os.environ.get('DEEP_KEY'))
palm.configure(api_key=API_KEY)

replace_dict = {'봄': 'Spring', '콩':'Bean', '지도': 'Map', '투어': '탐색', '어린이': '자식', '링크 된 목록': 'LinkedList', '기본 키': 'PRIMARY KEY', '외국 키': 'FOREIGN KEY', '관리자': '매니지먼트', '저명하지 않은 읽기': '오손 읽기', '공개 클래스': 'public class', '교차 절단 문제': 'cross-cutting concerns', '공개': 'public', '보호된': 'protected', '개인': 'private'}
models = [m for m in palm.list_models() if 'generateText' in m.supported_generation_methods]
model = models[0].name


def replace_job(text):
    for key in replace_dict.keys():
        text = text.replace(key, replace_dict.get(key))

    return text

def get_answer(category, question):
    try:
        question = '개발 분야에서' + category + '에 대해서 물어볼게' + question
        question = GoogleTranslator(source='auto', target='en').translate(text=question)
        response = palm.generate_text(
            model=model,
            prompt=question,
            temperature=0,
            max_output_tokens=500
        )

        result = response.result
        result = GoogleTranslator(source='auto', target='ko').translate(text=result)

        return replace_job(result)

    except:
        return '시스템 점검중입니다'