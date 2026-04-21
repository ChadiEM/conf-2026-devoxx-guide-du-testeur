package io.chadi.guidedutesteur.mocks;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class OrderServiceTest {

    // 6 Collaborateurs mockés : Le test devient un miroir de l'implémentation
    private final Validator validator = mock(Validator.class);
    private final PriceCalculator calculator = mock(PriceCalculator.class);
    private final StockService stock = mock(StockService.class);
    private final LoyaltyService loyalty = mock(LoyaltyService.class);
    private final NotificationService notifier = mock(NotificationService.class);
    private final OrderRepository repo = mock(OrderRepository.class);

    private final OrderService service = new OrderService(
        validator, calculator, stock, loyalty, notifier, repo);

    @Test
    void testProcessOrder_TheMockingNightmare() {
        // GIVEN: On doit tout scripter, même les détails insignifiants
        Order order = new Order("SKU-123", 100.0, "user-42");
        when(validator.isValid(order)).thenReturn(true);
        when(calculator.calculateFinalPrice(100.0)).thenReturn(90.0);
        when(stock.isAvailable("SKU-123")).thenReturn(true);

        // WHEN
        service.process(order);
        
        // THEN: On vérifie chaque interaction technique
        // Si on change l'ordre des appels ou un détail interne, tout explose.
        verify(validator).isValid(order);
        verify(stock).isAvailable("SKU-123");
        verify(calculator).calculateFinalPrice(100.0);
        verify(loyalty).addPoints("user-42", 9);
        verify(repo).save(order);
        verify(notifier).sendConfirmation(order);
    }
}
