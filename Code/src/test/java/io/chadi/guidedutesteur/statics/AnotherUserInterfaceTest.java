package io.chadi.guidedutesteur.statics;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnotherUserInterfaceTest {
    @AfterEach
    void reset() {
        FeatureFlags.reset();
    }

    @Test
    void old_ui_should_display_old_user() {
        var ui = new UserInterface();

        var result = ui.display("Aldrik");

        assertEquals("Aldrik", result);
    }

    @Test
    void new_ui_should_display_new_user() {
        FeatureFlags.enable("new-ui");
        var ui = new UserInterface();

        var result = ui.display("Aldrik");

        assertEquals("ALDRIK", result);
    }
}