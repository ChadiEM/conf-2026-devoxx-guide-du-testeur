package io.chadi.guidedutesteur.statics;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class UserInterfaceEnvVarTest {
    @DisabledIfEnvironmentVariable(named = "NEW_UI", matches = "true")
    @Test
    void old_ui_should_display_old_user() {
        var ui = new UserInterfaceEnvVar();

        var result = ui.display("Aldrik");

        assertEquals("Aldrik", result);
    }

    @EnabledIfEnvironmentVariable(named = "NEW_UI", matches = "true")
    @Test
    void new_ui_should_display_new_user() {
        var ui = new UserInterfaceEnvVar();

        var result = ui.display("Aldrik");

        assertEquals("ALDRIK", result);
    }
}