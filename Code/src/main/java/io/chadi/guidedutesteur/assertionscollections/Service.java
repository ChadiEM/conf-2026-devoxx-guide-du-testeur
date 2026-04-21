package io.chadi.guidedutesteur.assertionscollections;

import java.util.List;

public class Service {

    public List<User> findAll() {
        return List.of(
                new User(1, "Alice", "ADMIN"),
                new User(2, "Bob", "VIEWER")
//                new User(2, "Charlie", "ADMIN")
        );
    }
}
