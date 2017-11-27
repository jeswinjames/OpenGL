package trial.opengl.util;

import android.util.Log;

/**
 * Created by jprince on 08-Aug-17.
 */

public class LoggerConfig {
    public static boolean ON = true;

    public static void Log(String tag, String message) {
        if (ON) {
            Log.w(tag, message);
        }
    }
}
