package barqsoft.footballscores.muzei;

/**
 * Created by shimonaj on 2/16/2016.
 */
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

import com.google.android.apps.muzei.api.Artwork;
import com.google.android.apps.muzei.api.MuzeiArtSource;

import java.text.SimpleDateFormat;
import java.util.Date;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.sync.FootballSyncAdapter;

public class FootballMuzeiSource extends MuzeiArtSource {



    public FootballMuzeiSource() {
        super("FootballMuzeiSource");
    }

    protected void onHandleIntent(Intent intent) {
        super.onHandleIntent(intent);
        boolean dataUpdated = intent != null &&
                FootballSyncAdapter.ACTION_DATA_UPDATED.equals(intent.getAction());
        if (dataUpdated && isEnabled()) {
            onUpdate(0);
        }
    }

    @Override
    protected void onUpdate(int reason) {
        Uri weatherForLocationUri = DatabaseContract.scores_table.buildScoreWithDate();
        Date fragmentdate = new Date(System.currentTimeMillis()+(24*3600*1000));

        SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
        String string =mformat.format(fragmentdate);
      Cursor  cursor = getContentResolver().query(weatherForLocationUri,
              null,
              null
              , new String[]{string},
              DatabaseContract.scores_table.DATE_COL + " ASC");
        if (cursor.moveToFirst()) {


//            String imageUrl = Utility.getImageUrlForWeatherCondition(weatherId);
//            // Only publish a new wallpaper if we have a valid image
//            if (imageUrl != null) {
//                publishArtwork(new Artwork.Builder()
//                        .imageUri(Uri.parse(imageUrl))
//                        .title(desc)
//                        .byline(location)
//                        .viewIntent(new Intent(this, MainActivity.class))
//                        .build());
//            }
        }
        cursor.close();
    }
}