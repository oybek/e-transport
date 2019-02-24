
var url = "http://127.0.0.1:8080";

function transportIcon(type) {
	if (type == "bus") return "🚌";
	if (type == "tram") return "🚋";
	if (type == "troll") return "🚎";
	return "транспорт";
}

function transportType(type) {
	if (type == "bus") return "автобус";
	if (type == "tram") return "трамвай";
	if (type == "troll") return "троллейбус";
	return "транспорт";
}

function transportTypeGenetive(type) {
	if (type == "bus") return "автобусов";
	if (type == "tram") return "трамваев";
	if (type == "troll") return "троллейбусов";
	return "транспорт";
}

function stopInfo(stop) {
	return transportIcon(stop.type)
		+ stop.names[0] + " на " + stop.direction + "\n"
		+ (stop.reaches.length == 0
			? "В ближайшее время " + transportTypeGenetive(stop.type) + " не будет"
			: stop.reaches.map(function(x) {
				return (x.route.length == 2 ? " " : "") + x.route + "-й " + transportType(stop.type) + (x.time == 0 ? " уже подъезжает" : " через "+x.time +" мин.")
			}).join("\n"));
}

function makeText(stopInfos) { return stopInfos.map(stopInfo).join("\n\n"); }

function getBus(name)   { return JSON.parse(http.get(  url+"/bus/get", { name : name })); }
function getTram(name)  { return JSON.parse(http.get( url+"/tram/get", { name : name })); }
function getTroll(name) { return JSON.parse(http.get(url+"/troll/get", { name : name })); }

function Hippo() {

	this.last = null;

	this.on_message = function(message) {
		if (message.text != null && message.text.length > 0) {

			message.text = message.text.substr(0, 40);

			if (this.last != null) {
				switch (message.text) {
					case '🚌': message.text = this.last + "автобусы"; break;
					case '🚃': message.text = this.last + "трамваи"; break;
					case '🚎': message.text = this.last + "троллейбусы"; break;
				}
			}

			var stopName = message.text.toLowerCase()
				.replace(/автобусы?/, '')
				.replace(/трамва(и|й)/, '')
				.replace(/тролл?ейбусы?/, '')
				.trim();

			this.last = stopName;

			if (/.*автобусы?.*/.test(message.text)) {
				var stops = getBus(stopName);
				message.text = stops.length == 0
					? "На остановке '" + stopName + "' автобусы не ходят"
					: makeText(stops);
			} else
			if (/.*трамва(и|й).*/.test(message.text)) {
				var stops = getTram(stopName);
				message.text = stops.length == 0
					? "На остановке '" + stopName + "' трамваи не ходят"
					: makeText(stops);
			} else
			if (/.*тролл?ейбусы?.*/.test(message.text)) {
				var stops = getTroll(stopName);
				message.text = stops.length == 0
					? "На остановке '" + stopName + "' троллейбусы не ходят"
					: makeText(stops);
			} else {
				var tramStops = getTram(stopName);
				if (tramStops.length == 0) {
					var trollStops = getTroll(stopName);
					if (trollStops.length == 0) {
						var busStops = getBus(stopName);
						if (busStops.length == 0) {
							this.last = null;
							message.text = "Напишите название остановки, например\n'Дом кино'";
						} else {
							message.text = makeText(busStops);
						}
					} else {
						message.text = makeText(trollStops);
					}
				} else {
					message.text = makeText(tramStops);
				}
			}
			message.keyboard = [
				["🚌", "🚃", "🚎"]
			];
		}
		else if(message.geo != null) {
			message.text = "Скоро добавим функцию поиска по геолокации, пишите название остановки";
		} else {
			this.last = null;
			message.text = "Напишите название остановки, например 'Дом кино'";
		}

		io.send(message);

		// cache buses for faster response
		getBus(stopName);
	}
}

