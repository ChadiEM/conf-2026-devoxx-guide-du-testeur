package io.chadi.guidedutesteur.async;

import io.chadi.guidedutesteur.assertionscollections.User;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

class AsyncProcessorTest {
    @Test
    void shouldProcessAsynchronously_Flaky() throws Exception {
        // GIVEN
        var processor = new AsyncProcessor();

        // WHEN
        processor.processAsync();

        // THEN: On espère que 150ms suffisent…
        Thread.sleep(150);
        assertTrue(processor.hasSucceeded(),
                "Processing should have succeeded.");
    }

    @Test
    void shouldProcessAsynchronously_Flaky_Slow() throws Exception {
        // GIVEN
        var processor = new AsyncProcessor();

        // WHEN
        processor.processAsync();

        // THEN: 1s suffit largement...
        Thread.sleep(1000);
        assertTrue(processor.hasSucceeded(),
                "Processing should have succeeded.");
    }

    @Test
    void shouldProcessAsynchronously_Sleep_Correctly() throws Exception {
        // GIVEN
        var processor = new AsyncProcessor();

        // WHEN
        processor.processAsync();

        // THEN
        var start = System.currentTimeMillis();
        while (!processor.hasSucceeded()
                && (System.currentTimeMillis() - start) < (Duration.ofSeconds(10).toMillis())) {
            Thread.sleep(10);
        }

        assertTrue(processor.hasSucceeded(),
                "Processing should have succeeded.");
    }

    @Test
    void shouldProcessAsynchronously_Robust() {
        // GIVEN
        var processor = new AsyncProcessor();

        // WHEN
        processor.processAsync();

        // THEN: Awaitility pour attendre
        // que la condition soit satisfaite (jusqu’à 10 s)
        await()
                .atMost(Duration.ofSeconds(10)) // Max wait
                .pollInterval(Duration.ofMillis(10)) // Poll
                .until(processor::hasSucceeded); // Condition

        assertTrue(processor.hasSucceeded(), "Processing should have succeeded.");
    }

    @Test
    void shouldProcessAsynchronously_Robust_With_AssertJ() {
        // GIVEN
        var processor = new AsyncProcessor();

        // WHEN
        processor.processAsync();

        // THEN
        await()
                .atMost(Duration.ofSeconds(10)) // Max wait
                .pollInterval(Duration.ofMillis(10)) // Poll
                .untilAsserted(() -> {
                    assertTrue(processor.hasSucceeded());
                    assertThat(processor.getResult())
                            .hasSize(2)
                            .extracting(User::name)
                            .containsExactlyInAnyOrder("Alice", "Bob");
                });
    }
}