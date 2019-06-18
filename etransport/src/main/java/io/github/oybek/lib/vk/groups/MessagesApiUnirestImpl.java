package io.github.oybek.lib.vk.groups;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Optional;

public class MessagesApiUnirestImpl implements MessagesApi {

  String baseUrl;

  public MessagesApiUnirestImpl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  io.github.oybek.lib.vk.groups.MyObjectMapper myObjectMapper = new MyObjectMapper();

  public SendRes send(SendReq x) throws UnirestException {
    return Unirest.post(baseUrl + "send")
        .header("accept", "application/json")
        .field("access_token", x.getAccessToken())
        .field("v", x.getV())
        .field("user_id", x.getUserId())
        .field("random_id", x.getRandomId())
        .field("peer_id", x.getPeerId())
        .field("domain", x.getDomain())
        .field("chat_id", x.getChatId())
        .field("user_ids", x.getUserIds())
        .field("message", x.getMessage())
        .field("lat", x.getLat())
        .field("long", x.getLong())
        .field("attachment", x.getAttachment())
        .field("reply_to", x.getReplyTo())
        .field("forward_messages", x.getForwardMessages())
        .field("sticker_id", x.getStickerId())
        .field("group_id", x.getGroupId())
        .field("payload", x.getPayload())
        .field("dont_parse_links", x.getDontParseLinks())
        .field("disable_mentions", x.getDisableMentions())
        .field("keyboard", myObjectMapper.writeValue(x.getKeyboard()))
        .asObject(SendRes.class)
        .getBody();
  }

  // TODO: implement Either, wrap result to it, eliminate exception throwing
  private <S, Q> S post(Optional<String> urlOpt, Q x, String method, Class<? extends S> resClass)
      throws UnirestException {
    HttpResponse<S> update =
        Unirest.post(urlOpt.orElse(baseUrl + method))
            .header("Content-Type", "application/json")
            .body(x)
            .asObject(resClass);
    return update.getBody();
  }

  private <S> S post(Optional<String> urlOpt, String method, Class<? extends S> resClass)
      throws UnirestException {
    HttpResponse<S> update =
        Unirest.post(urlOpt.orElse(baseUrl + method))
            .header("Content-Type", "application/json")
            .asObject(resClass);
    return update.getBody();
  }
}
