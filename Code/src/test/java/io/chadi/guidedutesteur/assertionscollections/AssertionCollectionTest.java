package io.chadi.guidedutesteur.assertionscollections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AssertionCollectionTest {
    public static final Service service = new Service();

    @Test
    void checkUserListProperties_usingVanillaJUnit() {
        List<User> users = service.findAll();

        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(u ->
                u.name().equals("Alice") && u.role().equals("ADMIN")));
        assertTrue(users.stream().anyMatch(u ->
                u.name().equals("Bob") && u.role().equals("VIEWER")));
    }

    @Test
    void checkUserListProperties_usingVanillaJUnit_withExplicitErrorMessages() {
        List<User> users = service.findAll();

        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(u ->
                        u.name().equals("Alice") && u.role().equals("ADMIN")),
                "Expecting Alice/ADMIN, not found");
        assertTrue(users.stream().anyMatch(u ->
                        u.name().equals("Bob") && u.role().equals("VIEWER")),
                "Expecting Bob/VIEWER, not found");
    }

    @Test
    void checkUserListProperties_usingAssertJ() {
        List<User> allUsers = service.findAll();

        assertThat(allUsers)
                .hasSize(2)
                .extracting(User::name, User::role)
                .containsExactlyInAnyOrder(
                        tuple("Alice", "ADMIN"),
                        tuple("Bob", "VIEWER")
                );
    }
}