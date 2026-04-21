package io.chadi.guidedutesteur.beantesting.after;

public class Displayer {
    public static String display(User user) {
        return user.username() + "/" + user.id();
    }
}
