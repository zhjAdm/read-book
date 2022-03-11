package com.zhjAdm.util;


import com.intellij.ide.util.PropertiesComponent;

public class SimpleConfigUtil {

    private static final PropertiesComponent PROPERTIES_COMPONENT = PropertiesComponent.getInstance();

    public static String getString(String key) {
        // key
        // key defaultValue
        return PROPERTIES_COMPONENT.getValue(key);
    }

    public static int getIntValue(String key,int defaultValue){
        String valueStr = getString(key);
        try {
            return Integer.parseInt(valueStr);
        }catch (Exception e){
            return defaultValue;
        }
    }

    public static void save(String key, Object value) {
        if (value instanceof String) {
            save(key, (String) value);
        } else if (value instanceof Integer) {
            save(key, (int) value);
        } else if (value instanceof Boolean) {
            save(key, (boolean) value);
        } else if (value instanceof Float) {
            save(key, (float) value);
        } else {
            NotificationUtil.error("type error : " + value);
        }
    }

    private static void save(String key, String value) {
        // key value
        PROPERTIES_COMPONENT.setValue(key, value);
    }

    private static void save(String key, int value) {
        // key value defaultValue
        PROPERTIES_COMPONENT.setValue(key, value, 0);
    }

    private static void save(String key, boolean value) {
        // key value
        // key value defaultValue
        PROPERTIES_COMPONENT.setValue(key, value);
    }

    private static void save(String key, float value) {
        // key value defaultValue
        PROPERTIES_COMPONENT.setValue(key, value, 0.0f);
    }

}