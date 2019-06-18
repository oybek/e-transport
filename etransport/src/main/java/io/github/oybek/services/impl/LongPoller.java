package io.github.oybek.services.impl;

import com.mashape.unirest.http.Unirest;
import io.github.oybek.lib.vk.Event;
import io.github.oybek.lib.vk.Message;
import io.github.oybek.lib.vk.groups.*;
import io.github.oybek.services.Logic;
import io.github.oybek.services.Poller;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
@Component
public class LongPoller implements Poller {

    Logger logger = LoggerFactory.getLogger(LongPoller.class);

    @Value("${vk.version}")
    private String version;

    @Value("${vk.group-id}")
    private int groupId;

    @Value("${vk.access-token}")
    private String accessToken;

    @Value("${vk.wait}")
    private long wait;

    private Logic logic;

    public LongPoller(Logic logic) {
        this.logic = logic;
    }

    public Logic getLogic() {
        return this.logic;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    @PostConstruct
    public void init() {
        MyObjectMapper myObjectMapper = new MyObjectMapper();
        Unirest.setObjectMapper(myObjectMapper);

        start();
    }

    public void start() {
        new Thread(() -> {
            try {
                GroupsApi groupsApi = new GroupsApiUnirestImpl("https://api.vk.com/method/groups.");

                GetLongPollServerReq getLongPollServerReq
                        = new GetLongPollServerReq()
                            .setGroupId(groupId)
                            .setAccessToken(accessToken)
                            .setV(version);

                GetLongPollServerRes longPollServer
                        = groupsApi.getLongPollServer(getLongPollServerReq);

                logger.info(longPollServer.toString());

                AtomicInteger ts = new AtomicInteger(longPollServer.getResponse().getTs());

                while (true) {
                    GetUpdatesRes updatesRes
                            = groupsApi.getUpdates(
                            longPollServer.getResponse().getServer(),
                            new GetUpdatesReq()
                                    .setAct("a_check")
                                    .setKey(longPollServer.getResponse().getKey())
                                    .setTs(ts.get())
                                    .setWait(10));

                    if (updatesRes.getFailed().isPresent()) {
                        longPollServer = groupsApi.getLongPollServer(getLongPollServerReq);
                        continue;
                    } else {
                        ts.set(updatesRes.getTs());
                    }

                    logger.info(updatesRes.toString());
                    for (Event event : updatesRes.getUpdates()) {
                        switch (event.what()) {
                            default:
                                logger.info("Unknown event came");
                                break;

                            case EVENT_MESSAGE_NEW:
                                Message message = event.asEventMessageNew().getObject();
                                new Thread(() -> logic.onMessage(message)).start();
                                break;
                        }
                    }
                }
            } catch (Exception e) {
                logger.info(e.toString());
            }
        }).start();
    }
}
