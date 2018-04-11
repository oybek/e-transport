
import json
import urllib
import urllib.request

from pprint import pprint

url = 'https://m.ettu.ru/station/'
data = json.load(open('tram-stops.json', encoding='UTF-8'))

for element in data:
    req = url + element['ID']
    try: 
        urllib.request.urlopen(req)
    except urllib.error.HTTPError as e:
        print('')
    except urllib.error.URLError as e:
        print('')
    else:
        print(',', element)

