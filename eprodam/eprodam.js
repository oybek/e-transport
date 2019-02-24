
function Hippo() {

	function search(name) {
		return [
			{
				name: "утюг",
				description: "Продам #Утюг новый, philips, пользовался 3 раза, 1500 рублей",
				price: 1500,
				owner: 213461412,
			}
		];
	}

	function help(message) {
		message.text
			= "Если хотите что-то продать пишите:\n"
			+ "Продам #<товар> <описание> <цена> рублей\n\n"
			+ "Например: \n"
			+ "Продам #Утюг новый, philips, пользовался 3 раза, 1500 рублей\n\n"
			+ "Если хотите что-то купить пишите:\n"
			+ "Ищу #<товар> от <цена> до <цена>:\n\n"
			+ "Например ('от', 'до' не обязательны):\n"
			+ "Ищу #утюг до 2000"

		message.keyboard = [["Что я продаю?"]];
		io.send(message);
	}

	function sell(message) {
		function help(pretext, message) {
			message.text
				= pretext
				+ "Например: \n"
				+ "Продам #Утюг новый, philips, пользовался 3 раза, 1500 рублей\n\n"
			io.send(message);
		}

		function hasSharp(message) {
			var sharpCount = (message.text.match(/#/g)||[]).length;
			if (sharpCount == 1) {
				return true;
			} else {
				help("Надо написать символ решётки перед названием товара\n", message);
				return false;
			}
		}

		function correctName(message) {
			var words = message.text.split(" ");
		}

		if (!hasSharp(message)) return;
	}

	function buy(message) {
	}

	//
	this.on_message = function(message) {

		var words = message.text.split(" ");

		if (words[0].toLowerCase() === "продам") {
			sell(message);
		} else
		if (words[0].toLowerCase() === "ищу") {
			buy(message);
		} else {
			help(message);
		}
	}
}

