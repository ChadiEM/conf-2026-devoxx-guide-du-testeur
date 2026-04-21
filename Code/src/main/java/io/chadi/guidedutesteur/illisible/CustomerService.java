package io.chadi.guidedutesteur.illisible;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private final List<String> auditLog = new ArrayList<>();

    public String processOrder(int customerId, double amount, boolean isVip, String promoCode) {
        auditLog.add("Start processing for " + customerId);

        if (customerId < 100 || amount < 100.0) {
            return "REJECTED_INVALID_INPUT";
        }

        if (isVip && amount > 1000.0) {
            auditLog.add("VIP discount applied");
            amount *= 0.9;
        }

        if ("FREE_SHIP".equals(promoCode)) {
            auditLog.add("Shipping waived");
        }

        if (amount > 1000.0) {
            auditLog.add("High value order");
            auditLog.add("Requires approval");
            return "PENDING_APPROVAL";
        }

        auditLog.add("Order complete");
        return "COMPLETED";
    }

    public List<String> getAuditLog() {
        return auditLog;
    }
}
