package app;

import java.util.Scanner;
import discount.DiscountRule;
import engine.DiscountEngine;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double[] prices = {1000, 2000, 3000, 4000};

        System.out.println("Discount Options:");
        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");
        System.out.println("3. Flat Rs. 100 Discount");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        DiscountRule rule;

        // Select discount rule using lambda
        switch (choice) {

            case 1:
                rule = price -> price - (price * 0.10);
                break;

            case 2:
                rule = price -> price - (price * 0.20);
                break;

            case 3:
                rule = price -> price - 100;
                break;

            default:
                System.out.println("Invalid choice!");
                sc.close();
                return;
        }

        DiscountEngine engine = new DiscountEngine();

        engine.applyRule(prices, rule);

        sc.close();
    }
}