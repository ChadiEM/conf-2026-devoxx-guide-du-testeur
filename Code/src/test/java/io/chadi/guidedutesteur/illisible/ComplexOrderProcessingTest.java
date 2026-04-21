package io.chadi.guidedutesteur.illisible;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexOrderProcessingTest {

    @Test
    void complexOrderProcessing_scenarioTest_IsImpossibleToFollow() {
        CustomerService service = new CustomerService();
        int customerId = 5001;
        double initialAmount = 1200.0;
        String result = service.processOrder(customerId, initialAmount, true, "FREE_SHIP");
        assertEquals("PENDING_APPROVAL", result);
        assertEquals(5, service.getAuditLog().size());
        assertEquals("Start processing for 5001", service.getAuditLog().get(0));
        assertTrue(service.getAuditLog().get(1).contains("VIP discount"));
        assertEquals("Shipping waived", service.getAuditLog().get(2));
        String secondResult = service.processOrder(10, 50.0, false, "");
        assertEquals("REJECTED_INVALID_INPUT", secondResult);
        assertEquals(6, service.getAuditLog().size());
        assertFalse(service.getAuditLog().contains("Order complete"));
        CustomerService newService = new CustomerService();
        String thirdResult = newService.processOrder(200, 400.0, false, "NOCode");
        assertEquals("COMPLETED", thirdResult);
        assertEquals(2, newService.getAuditLog().size());
    }

    @Test
    void highValueVipOrderWithFreeShipping_shouldRequireApproval() {
        // GIVEN - Un client VIP avec une commande de grande valeur et livraison gratuite
        CustomerService service = new CustomerService();
        int customerId = 5001;
        double initialAmount = 1200.0;

        // WHEN - La commande est traitée
        String result = service.processOrder(customerId, initialAmount, true, "FREE_SHIP");

        // THEN - La commande nécessite une approbation
        assertEquals("PENDING_APPROVAL", result);
        assertEquals(5, service.getAuditLog().size());
        assertEquals("Start processing for 5001", service.getAuditLog().get(0));
        assertTrue(service.getAuditLog().get(1).contains("VIP discount"));
        assertEquals("Shipping waived", service.getAuditLog().get(2));
    }
}

