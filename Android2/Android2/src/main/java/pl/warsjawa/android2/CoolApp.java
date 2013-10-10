package pl.warsjawa.android2;

import android.app.Application;

import dagger.ObjectGraph;

public class CoolApp extends Application {

    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();
        graph = ObjectGraph.create(new CoolModule(this));
        graph.injectStatics();
    }

    public void inject(Object obj) {
        graph.inject(obj);
    }
}
