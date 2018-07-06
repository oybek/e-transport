
var longPoll = {
	server : "",
	key : "",
	ts : 0,
	wait : 25
};

function updateLongPollServer() {
	str =
	hippo.post("https://api.vk.com/method/groups.getLongPollServer",
		{
			group_id : 163915852,
			access_token : "",
			v : "5.8"
		}
	);

	response = JSON.parse(str);

	if ('response' in response) {
		longPoll.server = response.response.server;
		longPoll.key = response.response.key;
		longPoll.ts = response.response.ts;
	} else {
	}
}
 
function main(message) {
	updateLongPollServer();

	while (true) {
		var str =
		hippo.post(longPoll.server,
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
				updateLongPollServer();
			}
		} else {
			for (var i = 0; i < response.updates.length; ++i) {
				if (response.updates[i].type == "message_new") {
					var update = response.updates[i].object;

					var message = {
						user : {
							id : update.from_id,
							messenger : "vk"
						},
						text : update.text
					};

					if ('geo' in update) {
						message.geo = {
							lat : update.geo.coordinates.latitude,
							lon : update.geo.coordinates.longitude
						}
					}

					hippo.send(message);
				}
			}
			longPoll.ts = response.ts;
		}
	}
}

