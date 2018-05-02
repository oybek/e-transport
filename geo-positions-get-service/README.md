# Service for getting of users geo-positions from messages history

## How use it?

* Add token to **.token.json**

```json
{
	'token' : 'user_token'
}
```

* Install **python3** and **vk api**

```bash
sudo apt install python3 python3-pip
pip3 install vk
```

* Run **geoget.py**

```bash
python3 geoget.py
```

* The geodata will be recorded to file **data.json**
