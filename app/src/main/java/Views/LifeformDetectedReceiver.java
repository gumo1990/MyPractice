package Views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;

/**
 * Created by whq on 17/6/26 0026.
 */

public class LifeformDetectedReceiver extends BroadcastReceiver {

    public final static String EXTRA_LIFEFORM_NAME = "EXTRA_LIFEFORM_NAME";
    public final static String EXTRA_LATITUDE = "EXTRA_LATITUDE";
    public final static String EXTRE_LONGTITUDE = "EXTRA_LONGITUDE";

    public  static final String  ACTION_BURN = "com.paad.alien.action.BURN_IT_WITH_FIRE";
    public static final String NEW_LIFEFORM = "com.paad.alien.action.NEW_LIFEFORM";

    @Override
    public void onReceive(Context context, Intent intent) {
        //从Intent中获得lifeform的细节
        Uri data = intent.getData();
        String type = intent.getStringExtra(EXTRA_LIFEFORM_NAME);

        double lat = intent.getDoubleExtra(EXTRA_LATITUDE, 0);
        double lng = intent.getDoubleExtra(EXTRE_LONGTITUDE,0);

        Location loc = new Location("gps");
        loc.setLatitude(lat);
        loc.setLongitude(lng);

        if(type.equals("facehugger")){
            Intent startIntent = new Intent(ACTION_BURN,data);
            startIntent.putExtra(EXTRA_LATITUDE,lat);
            startIntent.putExtra(EXTRE_LONGTITUDE,lng);
            context.startService(startIntent);
        }
    }
}
