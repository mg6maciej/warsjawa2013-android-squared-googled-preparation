package pl.warsjawa.android2.rest;

import com.google.android.gms.maps.model.LatLng;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ParseClient {

    @Inject
    public ParseClient() {
    }

    public void addLocation(LatLng location) {
        ParseObject object = new ParseObject("Location");
        object.put("geo", new ParseGeoPoint(location.latitude, location.longitude));
        object.saveInBackground();
    }

    public void retrieveLocations(final Callback<List<LatLng>> locationsCallback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Location");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    ArrayList<LatLng> locations = new ArrayList<LatLng>();
                    for (ParseObject object : parseObjects) {
                        ParseGeoPoint geo = object.getParseGeoPoint("geo");
                        locations.add(new LatLng(geo.getLatitude(), geo.getLongitude()));
                    }
                    locationsCallback.got(locations);
                }
            }
        });
    }

    public interface Callback<T> {

        void got(T object);
    }
}
