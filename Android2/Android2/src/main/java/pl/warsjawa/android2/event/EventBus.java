package pl.warsjawa.android2.event;

public interface EventBus {

    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);
}
