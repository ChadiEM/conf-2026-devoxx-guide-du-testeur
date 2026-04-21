package io.chadi.guidedutesteur.beantesting.after;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DisplayerTest {
    @Test
    void test_display() {
        var user = new User(1, "Bob");

        var onDisplay = Displayer.display(user);

        assertEquals("Bob/1", onDisplay);
    }
}