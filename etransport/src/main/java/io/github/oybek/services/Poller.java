package io.github.oybek.services;

public interface Poller {

    Logic getLogic();
    void setLogic(Logic logic);

    void start();
}
