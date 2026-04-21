package io.chadi.guidedutesteur.statics;

public class UserInterface {
    public String display(String username) {
        if (FeatureFlags.isEnabled("new-ui")) {
            return username.toUpperCase();
        } else {
            return username;
        }
    }
}
