package pl.warsjawa.android2;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.warsjawa.android2.event.EventBus;
import pl.warsjawa.android2.event.OttoEventBus;
import pl.warsjawa.android2.ui.LauncherActivity;
import pl.warsjawa.android2.ui.LoginActivity;
import pl.warsjawa.android2.ui.MainActivity;
import pl.warsjawa.android2.ui.MeetupsMapFragment;

@Module(injects = {LauncherActivity.class, LoginActivity.class, MainActivity.class, MeetupsMapFragment.class})
public class CoolModule {

    private Context context;

    public CoolModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return context;
    }

    @Provides
    @Singleton
    EventBus providesBus() {
        return new OttoEventBus();
    }
}
