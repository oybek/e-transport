package com.oybek.bridgevk;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oybek.bridgevk.Entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Component
public class Courier {
    private static final String SEND_MESSAGE_URL = "https://api.vk.com/method/messages.send?v=3.0&user_id=%d&message=%s&access_token=%s";
    private static final String GET_MESSAGE_URL = "https://api.vk.com/method/messages.get?last_message_id=%d&access_token=%s&v=3";
    @Value("${artificialPing:1000}")
    private long artificialPing;
    @Value("${vk.token}")
    private String accessToken;
    private QueueController queueController;

    private AtomicLong lastMessageId = new AtomicLong(0);

    @Autowired
    public Courier(QueueController controller) {
        this.queueController = controller;
    }

    /**
     * Обработка GET-запроса на указанный адрес
     *
     * @param urlStr - адрес
     * @return ответ в текстовом виде
     * @throws IOException - имеется вероятность неудачи при обращении к API
     */
    public static String get(String urlStr) throws IOException {

        URL obj = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    /**
     * Планирование выполнения периодического обновления после загрузки контекста приложения
     */
    @EventListener(classes = {ContextRefreshedEvent.class})
    public void scheduleUpdate() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(this::update, 1000L, artificialPing, TimeUnit.MILLISECONDS);
    }

    /**
     * Штатное обновление перечня сообщений
     */
    private void update() {
        try {
            JsonParser parser = new JsonParser();
            JsonObject messageContent = parser.parse(get(String.format(GET_MESSAGE_URL, lastMessageId.get(), accessToken))).getAsJsonObject();

            if (messageContent.has("response")) {
                JsonArray arr = messageContent.get("response").getAsJsonArray();
                for (JsonElement element : arr) {
                    if (element.isJsonObject()) {
                        JsonObject jObj = element.getAsJsonObject();

                        Message msg = new Message(jObj);

                        lastMessageId.set(Math.max(lastMessageId.get(), msg.getId()));

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
                get(String.format(SEND_MESSAGE_URL, message.getUid(), message.getText(), accessToken));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
