package engine;

import discount.DiscountRule;

public class DiscountEngine {

    public void applyRule(double[] prices, DiscountRule rule) {

        System.out.println("Prices after discount:");

        for (double price : prices) {
            double finalPrice = rule.applyDiscount(price);

            System.out.println(finalPrice);
        }
    }
}