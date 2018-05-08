package com.oybek.bridgevk;

import com.oybek.bridgevk.Entities.Message;
import com.oybek.bridgevk.Entities.StopInfo;

import java.util.List;
import java.util.stream.Collectors;

public class Bot {
    private enum State {
          HELP
        , TRAM
        , TROLL
        , TROLL_CONFIRM
    }

    private Ettu ettu;
    private State state = State.TRAM;
    private Message lastMessage = null;

    //
    Bot(Ettu ettu) {
        this.ettu = ettu;
    }

    //
    public Message getReaction(Message msg) {
        switch (state) {
            default: {
                break;
            }

            case HELP: {
                state = State.TRAM;
                return new Message()
                        .setUid(msg.getUid())
                        .setText("Для того чтобы я мог найти ближайшую остановку, отправьте мне свои координаты, вот как это делается:")
                        .setAttachment("doc-163915852_464149858");
            }

            case TROLL_CONFIRM: {
                // if user agree to see info about trolls...
                if (msg.hasText() && msg.getText().toLowerCase().matches("да|lf|\\+")) {
                    state = State.TROLL;
                    return getReaction(lastMessage);
                } else {
                    // ... react to message as new request
                    state = State.TRAM;
                    return getReaction(msg);
                }
            }

            case TROLL: {
                List<StopInfo> stopInfos = lastMessage.hasGeo()
                                                ? ettu.getNearestTrolls(lastMessage.getGeo(), 2)
                                                : lastMessage.hasText()
                                                    ? ettu.getTrolls(lastMessage.getText())
                                                    : null;

                // if no trolleybus found ...
                if (stopInfos == null || stopInfos.size() == 0) {
                    state = State.HELP;
                    return getReaction(msg);
                } else {
                    state = State.TRAM;
                    return new Message()
                            .setUid(msg.getUid())
                            .setText(
                                    stopInfos
                                            .stream()
                                            .map( stopInfo -> "🚎 Остановка: " + stopInfo.getTextInfo() )
                                            .collect(Collectors.joining("\n")));
                }
            }

            case TRAM: {
                if (msg.hasGeo()) {
                    List<StopInfo> stopInfos = ettu.getNearestTrams(msg.getGeo(), 2);

                    if (stopInfos == null || stopInfos.size() == 0) {
                        state = State.HELP;
                        return getReaction(msg);
                    } else {
                        state = State.TROLL_CONFIRM;
                        lastMessage = msg.clone();
                        return new Message()
                                .setUid(msg.getUid())
                                .setText(
                                    stopInfos
                                        .stream()
                                        .map( stopInfo -> "🚋 Остановка: " + stopInfo.getTextInfo() )
                                        .collect(Collectors.joining("\n")))
                                .appendText("\nПоказать троллейбусы?");
                    }
                }
                else if (msg.hasText()) {
                    List<StopInfo> stopInfos = ettu.getTrams(msg.getText());

                    boolean ask = true;
                    {
                        List<StopInfo> trollStopInfos = ettu.getTrolls(msg.getText());
                        if (trollStopInfos == null || trollStopInfos.size() == 0)
                            ask = false;
                    }

                    if (stopInfos == null || stopInfos.size() == 0) {
                        state = State.TROLL;
                        lastMessage = msg.clone();
                        return getReaction(msg);
                    } else {
                        state = State.TROLL_CONFIRM;
                        lastMessage = msg.clone();
                        return new Message()
                                .setUid(msg.getUid())
                                .setText(
                                        stopInfos
                                                .stream()
                                                .map( stopInfo -> "🚋 Остановка: " + stopInfo.getTextInfo() )
                                                .collect(Collectors.joining("\n")))
                                .appendText( ask ? "\nПоказать троллейбусы?" : "" );
                    }
                }
                else {
                    state = State.HELP;
                    return getReaction(msg);
                }
            }
        }

        return null;
    }
}
