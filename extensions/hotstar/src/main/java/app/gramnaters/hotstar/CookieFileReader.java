package app.gramnaters.hotstar;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CookieFileReader {
    private static final String TAG = "HotstarPatch";

    public static String readAsset(Context context, String filename) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = context.getAssets().open(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        } catch (Exception e) {
            Log.e(TAG, "Failed to read asset: " + filename);
        }
        return sb.toString();
    }
}
