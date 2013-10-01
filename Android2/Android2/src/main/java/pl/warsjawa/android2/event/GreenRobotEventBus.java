package pl.warsjawa.android2.event;

public class GreenRobotEventBus implements EventBus {

    private de.greenrobot.event.EventBus bus = de.greenrobot.event.EventBus.getDefault();

    @Override
    public void register(Object subscriber) {
        bus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        bus.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        bus.post(event);
    }
}
