
import json
import urllib
import urllib.request

from pprint import pprint

url = 'https://m.ettu.ru/station/'
data = json.load(open('troll-stops.json', encoding='UTF-8'))

for element in data:
    req = url + element['ID']
    try: 
        urllib.request.urlopen(req)
    except urllib.error.HTTPError as e:
        pass
    except urllib.error.URLError as e:
        pass
    else:
        print(',', element)

