package com.oybek.bridgevk;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oybek.bridgevk.Entities.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Компонент взаимодействия с VK API
 */
@Component
public class Courier {
    private final static String sendMessageURL = "https://api.vk.com/method/messages.send?v=3.0&user_id=%d&message=%s&access_token=%s";
    private final static String getMessageUrl = "https://api.vk.com/method/messages.get?last_message_id=%d&access_token=%s&v=3";
    @Value("${vk.token}")
    private static volatile String accessToken;

    @Value("${artificialPing:1000}")
    private long artificialPing;

    private QueueController queueController;

    private long lastMessageId = 0;

    public Courier(QueueController queueController ) {
        this.queueController = queueController;

        new Thread(this::update).start();
    }

    /**
     * Отправка GET-запроса
     *
     * @param urlStr - адрес запроса
     * @return
     * @throws Exception
     */
    static String get(String urlStr) throws Exception {

        URL obj = new URL( urlStr );
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // return result
        return response.toString();
    }

    /**
     * Периодическое обращение к API за обновлениями
     */
    private void update() {
        while(true) {
            try {
                JsonParser parser = new JsonParser();
                JsonObject o = parser.parse(get(String.format(getMessageUrl, lastMessageId, accessToken))).getAsJsonObject();

                if (o.has("response")) {
                    JsonArray arr = o.get("response").getAsJsonArray();
                    for (JsonElement element : arr) {
                        if (element.isJsonObject()) {
                            JsonObject jObj = element.getAsJsonObject();

                            Message msg = new Message(jObj);

                            lastMessageId = Math.max(lastMessageId, msg.getId());

                            if (msg.getReadState() == 0) {
                                System.out.println(msg.toString());
                                queueController.getQueueToBot().add(msg);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Message message = queueController.getQueueFromBot().poll();
                if (message != null) {
                    get(String.format(sendMessageURL, message.getUid(), message.getText(), accessToken));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(artificialPing);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
