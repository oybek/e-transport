# -*- coding: utf-8 -*-
import requests
import time
import json
import io
import vk

file = open('data.json', 'a')

def getAllGeoFromHistory(vkapi, userID, m_count=200, m_offset=0):
    do_it = 'yes'
    while do_it == 'yes':
        message = vkapi.messages.getHistory(user_id=userID, count=m_count, offset=m_offset)
        for message_count in range(m_count):
            if message_count >= message['count']:
                do_it = 'no'
                break
            if message['items'][message_count].get('geo'):
                coordinates_string = message['items'][message_count]['geo']['coordinates']
                coordiates_words = coordinates_string.split()
                jsonData = { 'x' : coordiates_words[0], 'y' : coordiates_words[1] }
                file.write(str(jsonData) + ',\n')
                print('ID:', message['items'][message_count]['user_id'], jsonData)
            if message['items'][message_count]['id'] < 2:
                do_it = 'no'
                break
        m_offset = m_offset + m_count
        time.sleep(0.5) # min time should be longer 0.3 s


def openAllDialogsAndGetGeo(vkapi, max_count=200, cur_offset=0):
    do_it = 'yes'
    while do_it == 'yes':
        dialogs = vkapi.messages.getDialogs(count=max_count, offset=cur_offset)
        for dialog_count in range(max_count):
            if dialog_count  >= dialogs['count']:
                do_it = 'no'
                break
            userID = dialogs['items'][dialog_count]['message']['user_id']
            getAllGeoFromHistory(vkapi, userID)            
        cur_offset = cur_offset + max_count
        time.sleep(0.5) # min time should be longer 0.3 s

        
def main():

    # get token from hide json file
    with open('.token.json') as json_data:
        d = json.load(json_data)
        json_data.close()
    user_token = d['token']

    # autentification
    vk_session = vk.AuthSession(access_token=user_token)
    vkapi = vk.API(vk_session, v='5.35', lang='ru', timeout=10)
    
    # open all dialogs, read history and write geo positions to json file
    openAllDialogsAndGetGeo(vkapi)

if __name__ == '__main__':
    main()
    file.close()

