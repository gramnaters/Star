package app.gramnaters.hotstar;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * JioHotstar Auth Injection v3.0
 *
 * Architecture:
 * - App uses HTTP headers: X-Hs-UserToken, x-hs-mediatoken
 * - Auth interceptor LHe/c reads tokens from identityLibrary
 * - identityLibrary = LHf/a interface, implemented by LFf/d (IdentityRepository)
 *   - field 'a' = user token (memory cache)
 *   - field 'g' = media token (memory cache)
 * - LFf/d.c() checks field 'a' first, falls back to DataStore
 * - LFf/d.i() checks field 'g' first, falls back to DataStore
 *
 * Strategy:
 * 1. CookieSeeder reads tokens from assets at startup
 * 2. Stores them in STATIC fields on this class
 * 3. Patched LFf/d methods check our static fields first
 * 4. If found -> store in memory cache (field a/g) -> return token
 */
public class CookieSeeder {
    private static final String TAG = "HotstarPatch";
    private static final String PREFS_NAME = "hotstar_patch_prefs";

    private static String injectedUserToken;
    private static String injectedMediaToken;

    public static String getInjectedUserToken() {
        return injectedUserToken;
    }

    public static String getInjectedMediaToken() {
        return injectedMediaToken;
    }

    public static void seedIfNeeded(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (prefs.getBoolean("is_seeded", false)) {
            injectedUserToken = prefs.getString("cached_user_token", null);
            injectedMediaToken = prefs.getString("cached_media_token", null);
            Log.d(TAG, "Already seeded, restored static fields from prefs");
            return;
        }

        Log.i(TAG, "Seeding auth tokens for JioHotstar...");

        // Read and cache user token
        String userToken = CookieFileReader.readAsset(context, "cookies/sessionUserUP.txt");
        if (userToken != null && userToken.length() > 0) {
            injectedUserToken = userToken;
            prefs.edit().putString("cached_user_token", userToken).apply();
            Log.i(TAG, "Cached user token for injection");
        }

        // Read and cache media token
        String mediaToken = CookieFileReader.readAsset(context, "cookies/media_token.txt");
        if (mediaToken != null && mediaToken.length() > 0) {
            injectedMediaToken = mediaToken;
            prefs.edit().putString("cached_media_token", mediaToken).apply();
            Log.i(TAG, "Cached media token for injection");
        }

        // Write device_id to StarApp SharedPreferences
        String deviceId = CookieFileReader.readAsset(context, "cookies/deviceId.txt");
        if (deviceId != null && deviceId.length() > 0) {
            SharedPreferences starPrefs = context.getSharedPreferences("StarApp", Context.MODE_PRIVATE);
            starPrefs.edit().putString("guid", deviceId).apply();
            Log.i(TAG, "Seeded device_id into StarApp prefs");
        }

        prefs.edit().putBoolean("is_seeded", true).apply();
        Log.i(TAG, "Auth token seeding complete");
    }
}
