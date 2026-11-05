
import java.util.*;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator {
    public static void main(String arg[]) {
        Scanner sc = new Scanner(System.in);

        boolean success = false;
        int attempt = 0;

        // Repeat until a valid calculation succeeds
        while (!success) {
            attempt++;

            try {
                System.out.println("Enter First Number:");
                double num1 = Double.parseDouble(sc.nextLine());

                System.out.println("Enter Operator:");
                String op = sc.nextLine().trim();

                System.out.println("Enter Second Number:");
                double num2 = Double.parseDouble(sc.nextLine());

                if (num2 == 0 && op.equals("/")) {
                    throw new DivideByZeroException("Cannot divide by zero!");
                }

                double result;

                // Perform calculation according to the operator
                switch (op) {
                    case "+":
                        result = num1 + num2;
                        break;

                    case "-":
                        result = num1 - num2;
                        break;

                    case "*":
                        result = num1 * num2;
                        break;

                    case "/":
                        result = num1 / num2;
                        break;

                    default:
                        System.out.println("Invalid operator! Please try again.");
                        continue;
                }

                success = true;

                System.out.println(num1 + " " + op + " " + num2);
                System.out.println("Result: " + result);
            }

            catch (DivideByZeroException d) {
                System.out.println(d.getMessage());
            }

            catch (NumberFormatException e) {
                System.out.println("Invalid number! Please enter a valid number.");
            }

            finally {
                // Log every calculation attempt
                System.out.println("[LOG] Attempt " + attempt);
            }
        }

        sc.close();
    }
}
