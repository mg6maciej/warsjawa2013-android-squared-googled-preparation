package pl.warsjawa.android2;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseUser;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ParseInitializer {

    private static final String APPLICATION_ID = "Ja3hqz8TUm5aqMg9RpqrmWCUmnYYLTRPrWiNGFnh";
    private static final String CLIENT_KEY = "GVLRaccpVWT9FIvVW2yXDnrGx9hnuCnAXdcclzTE";

    @Inject
    static ParseInitializer instance;

    @Inject
    ParseInitializer(Context context) {
        Parse.initialize(context, APPLICATION_ID, CLIENT_KEY);
        ParseUser.enableAutomaticUser();
    }
}
