package com.skullshooter.ssloader.app.api;

public class API {
    /**
     * TODO: Paste your domain, example: https://yourdomain.com/
     * or
     * if you paste the project inside any directory, paste the domain with mention the directory
     * example: https://yourdomain.com/project_directory/
     * **/
    public static String _ROOT_URL = "http://192.168.8.101/Admin-Panel/Admin-panel-Laravel-9/";
    public static String _API = _ROOT_URL + "api/v1/";

    //API
    public static String _LOADER_DATA = _API + "loaderData";
    public static String _SAFETY_STATUS = _API + "safety-status";
    public static String _PLUGIN32_DATA = _API + "plugin32Data";
    public static String _PLUGIN64_DATA = _API + "plugin64Data";

    //Fetch data
    public static String API_LOADER_VERSION = "loaderVersion";
    public static String ANNOUNCEMENT = "announcement";
    public static String STATUS32 = "status32bit";
    public static String STATUS64 = "status64bit";
    public static String PLUGIN_VERSION = "plugin32Version";
    public static String PLUGIN_URL = "plugin32Url";
    public static String PLUGIN64_VERSION = "plugin64Version";
    public static String PLUGIN64_URL = "plugin64Url";
    public static String IS_MAINTENANCE = "ismaintenance";


    //safetySave in prefs
    public static final String is32isSafe = "is32isSafe?";
    public static final String is64isSafe = "is64isSafe?";
    public static final String SAFE = "Safe";
    public static final String UNSAFE = "Unsafe";
    public static String LOADER_VERSION = "loaderVersion";

    //App data
    public static String PLUGIN = "plugin";
    public static String PLUGIN64 = "plugin64";
    public final static String PLUG_DIR = "plugin_dir";
    public final static String PLUG_NAME = "plugin.apk";
    public final static String PLUG64_DIR = "plugin64_dir";
    public final static String PLUG64_NAME = "plugin64.apk";
    public final static String PG_32BIT_NAME = "pgName";
    public final static String PG_64BIT_NAME = "pg64Name";
    public final static String dataWasUpdatedAt = "data_when_updated";
    public final static String PG_VERSION = "pgVersion";
    public final static String PG64_VERSION = "pg64Version";
    public final static String ERROR_TEXT = "Modified or edited version may lead you get ban, this is unofficial version. Please install the latest version of loader. Goto @skullshooter_tg\nOr Click here to visit official Telegram channel";



    /**In-App-String-Notify**/
    public static String plugin32DownloadLink = "";
    public static String plugin32Version = "";




}
