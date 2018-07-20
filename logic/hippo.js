
////////////////////////////////////////////////////////////////////////////////

function formatStopInfo(stop) {
	return (stop.stopType == "tram" ? "🚋" : "🚎")
		+ stop.stopName + "\n"
		+ stop.transportInfoList
			.map(function(x) {
				return x.route
					+ "-й " + (stop.stopType == "tram" ? "трамвай" : "троллейбус")
					+ (x.timeReach == 0 ? " уже подъезжает" : " через "+x.timeReach+" мин.")
			})
			.join("\n");
}

function makeText(stopInfos) { return stopInfos.map(formatStopInfo).join("\n\n"); }

function getTramByGeo(geo) { return JSON.parse(hippo.get(url+"/tram_stops/get_nearest", { latitude : geo.lat, longitude: geo.lon, n : 2 })); }

function getTrollByGeo(geo) { return JSON.parse(hippo.get(url+"/troll_stops/get_nearest", { latitude : geo.lat, longitude: geo.lon, n : 2 })); }

function getTramByName(name) { return JSON.parse(hippo.get(url+"/tram_stops/get", { name : name })); }

function getTrollByName(name) { return JSON.parse(hippo.get(url+"/troll_stops/get", { name : name })); }

////////////////////////////////////////////////////////////////////////////////

var url = "";
var tramStops = [];
var trollStops = [];
var lastStopname = null;

////////////////////////////////////////////////////////////////////////////////

function help(message)
{
	message.text = "Отправьте геолокацию или напишите название остановки";
	hippo.send(message);
}

function main(message) {
	if (message.geo != null)
	{
		tramStops = getTramByGeo(message.geo);
		trollStops = getTrollByGeo(message.geo);

		message.text = makeText(tramStops) + "\n\n" + makeText(trollStops);

		hippo.send(message);
	} else
	if (message.text != null && message.text.length > 0) {
		message.text = message.text.substr(0, 40);
		tramStops = getTramByName(message.text);
		trollStops = getTrollByName(message.text);

		if (trollStops.length == 0 && tramStops.length == 0) {
			help(message);
		} else {
			message.keyboard = {
				buttons : [[ message.text ]]
			};
			message.text = makeText(tramStops) + "\n\n" + makeText(trollStops);
			hippo.send(message);
		}
	} else {
		help(message);
	}
}

