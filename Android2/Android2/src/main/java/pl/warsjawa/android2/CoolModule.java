package pl.warsjawa.android2;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(injects = {LauncherActivity.class, LoginActivity.class, MainActivity.class})
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
}
