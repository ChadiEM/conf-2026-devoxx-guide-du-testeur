package io.chadi.guidedutesteur.async;

import io.chadi.guidedutesteur.assertionscollections.User;

import java.util.List;

public class AsyncProcessor {
    private boolean success;
    private List<User> result;

    public void processAsync() {
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            this.result = List.of(
                    new User(1, "Alice", "ADMIN"),
                    new User(2, "Bob", "VIEWER")
            );
            this.success = true;
        }).start();
    }

    public boolean hasSucceeded() {
        return success;
    }

    public List<User> getResult() {
        return result;
    }
}
