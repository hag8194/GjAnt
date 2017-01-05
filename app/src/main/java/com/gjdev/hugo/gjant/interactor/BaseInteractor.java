package com.gjdev.hugo.gjant.interactor;

public interface BaseInteractor {
    /*Bus Events*/
    int FAILURE_EVENT = 0;
    int SUCCESS_EVENT = 1;
    int ERROR_EVENT = 2;

    void postEvent(int kindOfEvent, Object object);
}
