#!/bin/bash

while :
do
	if [[ $(tmux ls | grep etransport) == *"etransport"* ]];
	then
		echo "ok"
	else
		token="a4e7785d4e8e15bcb1ad7626da7e28808c55b158d82c78e6edcfd502e171221e0efbf793f2cf24b8c4d21"
		curl \
			-d "access_token=$token&v=5.95&random_id=$RANDOM&chat_id=1&message=Бот%10упал%10дебилы!%0AЯ его поднял%0AПосмотрите логи блин" \
			-H "Content-Type: application/x-www-form-urlencoded" \
			-X POST "https://api.vk.com/method/messages.send"
		./restart.sh
	fi
	sleep 60
done

