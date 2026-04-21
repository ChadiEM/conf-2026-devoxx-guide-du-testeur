package io.chadi.guidedutesteur.statics;

public class UserInterfaceEnvVar {
    public String display(String username) {
        if (System.getenv("NEW_UI") != null && System.getenv("NEW_UI").equals("true")) {
            return username.toUpperCase();
        } else {
            return username;
        }
    }
}
