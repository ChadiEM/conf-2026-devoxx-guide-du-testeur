package io.chadi.guidedutesteur.mocks;

public class OrderService {
    private final Validator validator;
    private final PriceCalculator calculator;
    private final StockService stock;
    private final LoyaltyService loyalty;
    private final NotificationService notifier;
    private final OrderRepository repo;

    public OrderService(
            Validator validator,
            PriceCalculator calculator,
            StockService stock,
            LoyaltyService loyalty,
            NotificationService notifier,
            OrderRepository repo) {
        this.validator = validator;
        this.calculator = calculator;
        this.stock = stock;
        this.loyalty = loyalty;
        this.notifier = notifier;
        this.repo = repo;
    }

    public void process(Order order) {
        if (!validator.isValid(order)) {
            throw new IllegalArgumentException("Invalid order");
        }

        if (!stock.isAvailable(order.sku())) {
            throw new IllegalStateException("Product not available");
        }

        Double finalPrice = calculator.calculateFinalPrice(order.price());
        
        // Calculate loyalty points (10% of final price)
        int points = (int) Math.floor(finalPrice / 10);
        loyalty.addPoints(order.userId(), points);

        repo.save(order);
        notifier.sendConfirmation(order);
    }
}
