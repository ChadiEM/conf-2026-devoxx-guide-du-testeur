package io.chadi.guidedutesteur.statics;

import java.util.HashMap;
import java.util.Map;

public class FeatureFlags {

    private static final Map<String, Boolean> flags
            = new HashMap<>();

    public static void enable(String feature) {
        flags.put(feature, true);
    }

    public static boolean isEnabled(String feature) {
        return flags.getOrDefault(feature, false);
    }

    public static void reset() {
        flags.clear();
    }
}