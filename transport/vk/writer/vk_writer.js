
function getKeyboard(message) {
	var keyboard = {
		"one_time" : true,
		"buttons" : []
	}

	if (message.keyboard != null) {
		for (var i = 0; i < message.keyboard.buttons.length; ++i) {
			var row = [];
			for (var j = 0; j < message.keyboard.buttons[i].length; ++j) {
				row.push(
					{
						action : {
							type : "text",
							label : message.keyboard.buttons[i][j]
						},
						color : "primary"
					}
				);
			}
			keyboard.buttons.push(row);
		}
	}

	return JSON.stringify(keyboard);
}

function main(message) {
	hippo.post("https://api.vk.com/method/messages.send",
		{
			user_id : message.user.id,
			message : message.text,
			keyboard : getKeyboard(message),
			v : "5.8",
			access_token : ""
		}
	);
}

