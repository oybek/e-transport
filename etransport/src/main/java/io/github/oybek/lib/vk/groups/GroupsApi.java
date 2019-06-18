package io.github.oybek.lib.vk.groups;

public interface GroupsApi {
  GetLongPollServerRes getLongPollServer(GetLongPollServerReq x) throws Exception;

  GetUpdatesRes getUpdates(String url, GetUpdatesReq x) throws Exception;
}
