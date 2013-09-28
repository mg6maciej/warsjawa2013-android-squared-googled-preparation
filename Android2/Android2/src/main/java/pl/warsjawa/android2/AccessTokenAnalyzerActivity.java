package pl.warsjawa.android2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class AccessTokenAnalyzerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String token = getToken();
        new PreferenceManager(this).saveToken(token);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private String getToken() {
        Uri data = getIntent().getData();
        for (String info : data.getEncodedFragment().split("&")) {
            if (info.startsWith("access_token=")) {
                return info.substring("access_token=".length());
            }
        }
        return null;
    }
}
