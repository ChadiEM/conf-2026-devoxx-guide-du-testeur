package io.chadi.guidedutesteur.statics;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Random.class)
@Execution(ExecutionMode.CONCURRENT)
class UserInterfaceTest {
    @AfterEach
    void reset() {
        FeatureFlags.reset();
    }

    @RepeatedTest(100)
    void old_ui_should_display_old_user() {
        var ui = new UserInterface();

        var result = ui.display("Aldrik");

        assertEquals("Aldrik", result);
    }

    @RepeatedTest(100)
    void new_ui_should_display_new_user() {
        FeatureFlags.enable("new-ui");
        var ui = new UserInterface();

        var result = ui.display("Aldrik");

        assertEquals("ALDRIK", result);
    }

}