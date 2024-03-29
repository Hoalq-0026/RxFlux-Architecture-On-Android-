package com.techdb.app.rxfluxarchitecture.utils;

import android.util.Log;

/**
 * Created by HoaLQ on 11/24/15.
 */
public class LogUtils {
    private static final String LOG_PREFIX = "GitHubApp";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    public static boolean LOGGING_ENABLE = true;

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }
        return LOG_PREFIX + str;
    }

    /**
     * Don't use this when obfuscating class names!
     */
    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static void LOGD(final String tag, String message) {
        if (LOGGING_ENABLE) {
            if (Log.isLoggable(tag, Log.DEBUG)) {
                Log.d(tag, message);
            }
        }
    }

    public static void LOGD(final String tag, String message, Throwable cause) {
        if (LOGGING_ENABLE) {
            if (Log.isLoggable(tag, Log.DEBUG)) {
                Log.d(tag, message, cause);
            }
        }
    }

    public static void LOGV(final String tag, String message) {
        if (LOGGING_ENABLE) {
            if (Log.isLoggable(tag, Log.VERBOSE)) {
                Log.v(tag, message);
            }
        }
    }

    public static void LOGV(final String tag, String message, Throwable cause) {
        if (LOGGING_ENABLE) {
            if (Log.isLoggable(tag, Log.VERBOSE)) {
                Log.v(tag, message, cause);
            }
        }
    }

    public static void LOGI(final String tag, String message) {
        if (LOGGING_ENABLE) {
            Log.i(tag, message);
        }
    }

    public static void LOGI(final String tag, String message, Throwable cause) {
        if (LOGGING_ENABLE) {
            Log.i(tag, message, cause);
        }
    }

    public static void LOGW(final String tag, String message) {
        if (LOGGING_ENABLE) {
            Log.w(tag, message);
        }
    }

    public static void LOGW(final String tag, String message, Throwable cause) {
        if (LOGGING_ENABLE) {
            Log.w(tag, message, cause);
        }
    }

    public static void LOGE(final String tag, String message) {
        if (LOGGING_ENABLE) {
            Log.e(tag, message);
        }
    }

    public static void LOGE(final String tag, String message, Throwable cause) {
        if (LOGGING_ENABLE) {
            Log.e(tag, message, cause);
        }
    }

    private LogUtils() {

    }
}
