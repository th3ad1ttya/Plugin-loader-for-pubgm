package com.skullshooter.ssloader.app.preferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.skullshooter.ssloader.app.secureENC.XxTea;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Preferences {
    private static final String LENGTH = "_length";
    private static final String DEFAULT_STRING_VALUE = "";
    private static final int DEFAULT_INT_VALUE = -1;
    private static final double DEFAULT_DOUBLE_VALUE = -1d;
    private static final float DEFAULT_FLOAT_VALUE = -1f;
    private static final long DEFAULT_LONG_VALUE = -1L;
    private static final boolean DEFAULT_BOOLEAN_VALUE = false;
    private static Preferences prefsInstance;
    private final SharedPreferences sharedPreferences;

    private Preferences(Context context) {
        sharedPreferences = context.getSharedPreferences(
                context.getPackageName() + "_preferences",
                Context.MODE_PRIVATE
        );
    }

    private Preferences(Context context, String preferencesName) {
        sharedPreferences = context.getSharedPreferences(
                preferencesName,
                Context.MODE_PRIVATE
        );
    }

    public final class Editor implements SharedPreferences.Editor {
        private SharedPreferences.Editor mEditor;
        private Editor() {
            mEditor = sharedPreferences.edit();
        }

        @Override
        public SharedPreferences.Editor putString(String key, String value) {
            mEditor.putString(encrypt(key),
                    encrypt(value));
            return this;
        }
        public SharedPreferences.Editor putUnencryptedString(String key, String value) {
            mEditor.putString(encrypt(key), value);
            return this;
        }

        @Override
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public SharedPreferences.Editor putStringSet(String key, Set<String> values) {
            final Set<String> encryptedValues = new HashSet<String>(
                    values.size());
            for (String value : values) {
                encryptedValues.add(encrypt(value));
            }
            mEditor.putStringSet(encrypt(key),
                    encryptedValues);
            return this;
        }

        @Override
        public SharedPreferences.Editor putInt(String key, int value) {
            mEditor.putString(encrypt(key),
                    encrypt(Integer.toString(value)));
            return this;
        }

        @Override
        public SharedPreferences.Editor putLong(String key, long value) {
            mEditor.putString(encrypt(key),
                    encrypt(Long.toString(value)));
            return this;
        }

        @Override
        public SharedPreferences.Editor putFloat(String key, float value) {
            mEditor.putString(encrypt(key),
                    encrypt(Float.toString(value)));
            return this;
        }

        @Override
        public SharedPreferences.Editor putBoolean(String key, boolean value) {
            mEditor.putString(encrypt(key),
                    encrypt(Boolean.toString(value)));
            return this;
        }

        @Override
        public SharedPreferences.Editor remove(String key) {
            mEditor.remove(encrypt(key));
            return this;
        }

        @Override
        public SharedPreferences.Editor clear() {
            mEditor.clear();
            return this;
        }

        @Override
        public boolean commit() {
            return mEditor.commit();
        }

        @Override
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        public void apply() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                mEditor.apply();
            } else {
                commit();
            }
        }
    }

    public String getString(String key, String defaultValue) {
        final String encryptedValue = sharedPreferences.getString(
                encrypt(key), null);

        String decryptedValue = decrypt(encryptedValue);
        if (encryptedValue != null && decryptedValue != null) {
            return decryptedValue;
        } else {
            return defaultValue;
        }
    }

    public String getEncryptedString(String key, String defaultValue) {
        final String encryptedValue = sharedPreferences.getString(
                encrypt(key), null);
        return (encryptedValue != null) ? encryptedValue : defaultValue;
    }

    public Map<String, String> getAll() {
        final Map<String, ?> encryptedMap = sharedPreferences.getAll();
        final Map<String, String> decryptedMap = new HashMap<String, String>(
                encryptedMap.size());
        for (Map.Entry<String, ?> entry : encryptedMap.entrySet()) {
            try {
                Object cipherText = entry.getValue();
                decryptedMap.put(entry.getKey(),
                        decrypt(cipherText.toString()));
                //  }
            } catch (Exception e) {
                decryptedMap.put(entry.getKey(),
                        entry.getValue().toString());
            }
        }
        return decryptedMap;
    }

    public static Preferences getInstance() {
        return prefsInstance;
    }

    public static Preferences with(Context context) {
        if (prefsInstance == null) {
            prefsInstance = new Preferences(context);
        }
        return prefsInstance;
    }

    public static Preferences with(Context context, boolean forceInstantiation) {
        if (forceInstantiation) {
            prefsInstance = new Preferences(context);
        }
        return prefsInstance;
    }

    public static Preferences with(Context context, String preferencesName) {
        if (prefsInstance == null) {
            prefsInstance = new Preferences(context, preferencesName);
        }
        return prefsInstance;
    }

    public static Preferences with(Context context, String preferencesName,
                                   boolean forceInstantiation) {
        if (forceInstantiation) {
            prefsInstance = new Preferences(context, preferencesName);
        }
        return prefsInstance;
    }

    // String related methods

    public String read(String what) {
        final String encryptedValue = sharedPreferences.getString(encrypt(what), encrypt(DEFAULT_STRING_VALUE));
        String decryptValue = decrypt(encryptedValue);
        if (encryptedValue != null && decryptValue != null) {
            return decryptValue;
        } else {
            return DEFAULT_STRING_VALUE;
        }
    }

    public String read(String what, String defaultString) {
        final String encryptedValue = sharedPreferences.getString(encrypt(what), encrypt(defaultString));
        String decryptedValue = decrypt(encryptedValue);
        if (encryptedValue != null && decryptedValue != null) {
            return decryptedValue;
        } else {
            return defaultString;
        }
    }

    public void write(String where, String what) {
        sharedPreferences.edit().putString(encrypt(where), encrypt(what)).apply();
    }

    // int related methods

    public int readInt(String what) {
        final String encryptedValue = String.valueOf(sharedPreferences.getInt(encrypt(what), Integer.parseInt(encrypt(String.valueOf(DEFAULT_INT_VALUE)))));
        String decryptValue = decrypt(encryptedValue);
        if (encryptedValue !=null && decryptValue != null) {
            return Integer.parseInt(decryptValue);
        } else {
            return DEFAULT_INT_VALUE;
        }
    }

    public int readInt(String what, int defaultInt) {
        return sharedPreferences.getInt(what, defaultInt);
    }

    public void writeInt(String where, int what) {
        sharedPreferences.edit().putInt(where, what).apply();
    }

    // double related methods

    public double readDouble(String what) {
        if (!contains(what))
            return DEFAULT_DOUBLE_VALUE;
        return Double.longBitsToDouble(readLong(what));
    }

    public double readDouble(String what, double defaultDouble) {
        if (!contains(what))
            return defaultDouble;
        return Double.longBitsToDouble(readLong(what));
    }

    public void writeDouble(String where, double what) {
        writeLong(where, Double.doubleToRawLongBits(what));
    }

    // float related methods

    public float readFloat(String what) {
        return sharedPreferences.getFloat(what, DEFAULT_FLOAT_VALUE);
    }

    public float readFloat(String what, float defaultFloat) {
        return sharedPreferences.getFloat(what, defaultFloat);
    }

    public void writeFloat(String where, float what) {
        sharedPreferences.edit().putFloat(where, what).apply();
    }

    // long related methods

    public long readLong(String what) {
        return sharedPreferences.getLong(what, DEFAULT_LONG_VALUE);
    }

    public long readLong(String what, long defaultLong) {
        return sharedPreferences.getLong(what, defaultLong);
    }

    public void writeLong(String where, long what) {
        sharedPreferences.edit().putLong(where, what).apply();
    }

    // boolean related methods

    public boolean readBoolean(String what) {
        return readBoolean(what, DEFAULT_BOOLEAN_VALUE);
    }

    public boolean readBoolean(String what, boolean defaultBoolean) {
        return sharedPreferences.getBoolean(what, defaultBoolean);
    }

    public void writeBoolean(String where, boolean what) {
        sharedPreferences.edit().putBoolean(where, what).apply();
    }

    // String set methods

    public void putStringSet(final String key, final Set<String> value) {
        sharedPreferences.edit().putStringSet(key, value).apply();
    }

    public Set<String> getStringSet(final String key, final Set<String> defValue) {

        final Set<String> encryptedSet = sharedPreferences.getStringSet(
                encrypt(key), null);
        if (encryptedSet == null) {
            return defValue;
        }
        final Set<String> decryptedSet = new HashSet<String>(
                encryptedSet.size());
        for (String encryptedValue : encryptedSet) {
            decryptedSet.add(decrypt(encryptedValue));
        }
        return decryptedSet;
    }

    // end related methods

    public void remove(final String key) {
        if (contains(key + LENGTH)) {
            // Workaround for pre-HC's lack of StringSets
            int stringSetLength = readInt(encrypt(key) + LENGTH);
            if (stringSetLength >= 0) {
                sharedPreferences.edit().remove(encrypt(key) + LENGTH).apply();
                for (int i = 0; i < stringSetLength; i++) {
                    sharedPreferences.edit().remove(encrypt(key) + "[" + i + "]").apply();
                }
            }
        }
        sharedPreferences.edit().remove(encrypt(key)).apply();
    }

    public boolean contains(final String key) {
        return sharedPreferences.contains(key);
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
    private static String encrypt(String str)
    {
        try {
            return new String(XxTea.encryptToBase64String(str, "UTF-16LE"));
        }catch(Exception e){
            return null;
        }
    }

    private static String decrypt(String str)
    {
        try {
            return new String(XxTea.decryptBase64StringToString(str, "UTF-16LE"));
        } catch(Exception e) {
            return null;
        }
    }
}