package com.oybek.bridgevk.Entities;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.lang.Double.parseDouble;

/**
 * Сообщение
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private long id;
    private long date;
    private long uid;
    private long readState;
    private String text;
    private Geo geo;

    public Message(JsonObject jObj) {
        if (jObj == null) {
            throw new JsonParseException("Null json object");
        } else if (!jObj.has("mid")) {
            throw new JsonParseException("There is no 'mid' field");
        } else if (!jObj.has("uid")) {
            throw new JsonParseException("There is no 'uid' field");
        } else if (!jObj.has("read_state")) {
            throw new JsonParseException("There is no 'read_state' field");
        }

        this.id = jObj.get("mid").getAsLong();
        this.uid = jObj.get("uid").getAsLong();
        this.date = jObj.get("date").getAsLong();
        this.readState = jObj.get("read_state").getAsLong();

        if (jObj.has("body")) {
            this.text = jObj.get("body").getAsString();
        } else {
            this.text = null;
        }

        if (jObj.has("geo")) {
            String coord = jObj.get("geo").getAsJsonObject().get("coordinates").getAsString();
            String[] splitted = coord.split("\\s+");
            this.geo = new Geo(parseDouble(splitted[0]), parseDouble(splitted[1]));
        } else {
            this.geo = null;
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", date=" + date +
                ", uid=" + uid +
                ", readState=" + readState +
                ", text='" + text + '\'' +
                ", geo=" + ( geo == null ? geo : geo.toString() ) +
                '}';
    }
}
