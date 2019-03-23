
var id = 163915852;
var token = "5e6b1299b1739c0bdff6ff636b341b6dc6c512215b26cb8c42ffe5d5dcf7dbb2ad7a5f7faf617d687182a";
var version = "5.80";

function getLongPollServer() {
    var str =
        http.post("https://api.vk.com/method/groups.getLongPollServer",
            {
                group_id : id,
                access_token : token,
                v : version
            }
        );

    var json = JSON.parse(str);

    if ('response' in json) {
        return {
            server : json.response.server,
            key : json.response.key,
            ts : json.response.ts,
            wait : 25
        };
    } else {
        return getLongPollServer();
    }
}

function on_start() {
    var server = getLongPollServer();

    while (true) {
        var str =
            http.post(server.server,
                {
                    act : "a_check",
                    key : server.key,
                    ts : server.ts,
                    wait : server.wait
                }
            );

        var response = JSON.parse(str);

        if ('failed' in response) {
            if (response.failed === 1) {
                server.ts = response.ts;
            } else {
                server = getLongPollServer();
            }
        } else
        if ('updates' in response) {
            for (var i = 0; i < response.updates.length; ++i) {
                if (response.updates[i].type === "message_new") {
                    var update = response.updates[i]["object"];

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
            server.ts = response.ts;
        } else {
            getLongPollServer();
        }
    }
}

function getKeyboard(message) {
    var keyboard = {
        "one_time" : true,
        "buttons" : []
    };

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

function on_message(message_str) {
    var message = JSON.parse(message_str);
    http.post("https://api.vk.com/method/messages.send",
        {
            user_id : message.user.id,
            message : message.text,
            keyboard : getKeyboard(message),
            v : version,
            access_token : token
        }
    );
}
