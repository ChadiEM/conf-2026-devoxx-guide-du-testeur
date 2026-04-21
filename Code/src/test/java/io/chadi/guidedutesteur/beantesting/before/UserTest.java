package io.chadi.guidedutesteur.beantesting.before;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testGetAndSetId() {
        User user = new User();
        user.setId(123L);
        assertEquals(123L, user.getId());
    }

    @Test
    void testGetAndSetUsername() {
        User user = new User();
        user.setUsername("testuser");
        assertEquals("testuser", user.getUsername());
    }
}
