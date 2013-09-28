package pl.warsjawa.android2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://secure.meetup.com/oauth2/authorize"
                + "?client_id=a7r6qufa5utub7l5m1eva4hsaa"
                + "&response_type=token"
                + "&redirect_uri=warsjawa://android2"
                + "&set_mobile=on"));
        startActivity(intent);
        finish();
    }
}
