package io.github.oybek.lib.vk.groups;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Optional;

public class GroupsApiUnirestImpl implements GroupsApi {

  String baseUrl;

  public GroupsApiUnirestImpl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  MyObjectMapper myObjectMapper = new MyObjectMapper();

  public GetLongPollServerRes getLongPollServer(GetLongPollServerReq x) throws UnirestException {
    return Unirest.post(baseUrl + "getLongPollServer")
        .header("accept", "application/json")
        .field("access_token", x.getAccessToken())
        .field("v", x.getV())
        .field("group_id", x.getGroupId())
        .asObject(GetLongPollServerRes.class)
        .getBody();
  }

  public GetUpdatesRes getUpdates(String url, GetUpdatesReq x) throws UnirestException {
    return Unirest.post(Optional.ofNullable(url).orElse(baseUrl + "getUpdates"))
        .header("accept", "application/json")
        .field("act", x.getAct())
        .field("key", x.getKey())
        .field("ts", x.getTs())
        .field("wait", x.getWait())
        .asObject(GetUpdatesRes.class)
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
