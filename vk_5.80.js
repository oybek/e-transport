
function getLongPollServer() {
	str = http.post("https://api.vk.com/method/groups.getLongPollServer",
		{
			group_id : 165649310,
			access_token : "5e2e0de2aadfc335caad66cc730cce6aba66a1cf519d7c1c491e51bdfc745ae51f9c60e844ffcd31248ad",
			v : "5.8"
		});

	response = JSON.parse(str);

	if ('response' in response) {
		return { server : response.response.server,
			        key : response.response.key,
			         ts : response.response.ts,
		           wait : 16 };
	} else {
		return updateLongPollServer();
	}
}

on_start = function() {
	var longPoll = getLongPollServer();

	while (true) {
		var str =
		http.post(longPoll.server,
			{
				act : "a_check",
				key : longPoll.key,
				ts : longPoll.ts,
				wait : longPoll.wait
			}
		);

		var response = JSON.parse(str);

		if ('failed' in response) {
			if (response.failed == 1) {
				longPoll.ts = response.ts;
			} else {
				longPoll = getLongPollServer();
			}
		} else
		if ('updates' in response) {
			for (var i = 0; i < response.updates.length; ++i) {
				if (response.updates[i].type == "message_new") {
					var update = response.updates[i].object;

					var message = {
						user : {
							id : update.from_id,
							app : "vk"
						},
						text : update.text
					};

					if ('geo' in update) {
						message.geo = {
							lat : update.geo.coordinates.latitude,
							lon : update.geo.coordinates.longitude
						}
					}

					io.send(message);
				}
			}
			longPoll.ts = response.ts;
		} else {
			longPoll = getLongPollServer();
		}
	}
}

function formKeyboard(message) {
	var keyboard = {
		"one_time" : false,
		"buttons" : []
	}

	if (message.keyboard != null) {
		for (var i = 0; i < message.keyboard.length; ++i) {
			var row = [];
			for (var j = 0; j < message.keyboard[i].length; ++j) {
				row.push(
					{
						action : {
							type : "text",
							label : message.keyboard[i][j]
						},
						color : "default"
					}
				);
			}
			keyboard.buttons.push(row);
		}
	}

	return JSON.stringify(keyboard);
}

on_message = function(message_json) {
	var message = JSON.parse(message_json);
	http.post("https://api.vk.com/method/messages.send",
		{
			user_id : message.user.id,
			message : message.text,
			keyboard : formKeyboard(message),
			lat : (message.geo != null ? message.geo.lat : null),
			long: (message.geo != null ? message.geo.lon : null),
			v : "5.8",
			access_token : "5e2e0de2aadfc335caad66cc730cce6aba66a1cf519d7c1c491e51bdfc745ae51f9c60e844ffcd31248ad"
		}
	);
}

