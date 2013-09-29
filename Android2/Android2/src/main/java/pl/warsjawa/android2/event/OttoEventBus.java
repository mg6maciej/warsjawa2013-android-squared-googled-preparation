package pl.warsjawa.android2.event;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

@Singleton
public class OttoEventBus implements EventBus {

    private Bus bus = new Bus();

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
