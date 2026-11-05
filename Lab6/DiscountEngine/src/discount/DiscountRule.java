package discount;

@FunctionalInterface
public interface DiscountRule {
    double applyDiscount(double price);
}
