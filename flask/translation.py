from googletrans import Translator

translator = Translator()


def translate(text, destination):
    result = translator.translate(text, dest=destination).text
    
    return str(result)
