
class OutOfStockException extends Exception {
    private int shortfall;

    public OutOfStockException(String message, int shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {

    private String[] items = {"Laptop", "Mouse", "Keyboard"};
    private int[] stock = {10, 20, 5};

    public void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        // Check whether the requested quantity is valid
        if (qty <= 0) {
            throw new InvalidQuantityException(
                "Quantity must be greater than 0."
            );
        }

        int index = -1;

        // Find the requested item in the warehouse
        for (int i = 0; i < items.length; i++) {
            if (items[i].equalsIgnoreCase(item)) {
                index = i;
                break;
            }
        }

        // Throw exception if the item is not available
        if (index == -1) {
            throw new OutOfStockException(
                "Item " + item + " is not available.",
                qty
            );
        }

        // Calculate shortfall if available stock is insufficient
        if (stock[index] < qty) {
            int shortfall = qty - stock[index];

            throw new OutOfStockException(
                "Not enough stock for " + item +
                ". Available: " + stock[index] +
                ", Requested: " + qty,
                shortfall
            );
        }

        // Update stock after successful issue
        stock[index] = stock[index] - qty;

        System.out.println(
            "Issued " + qty + " " + item + "(s) successfully."
        );
    }
}

class Request {
    String item;
    int qty;

    Request(String item, int qty) {
        this.item = item;
        this.qty = qty;
    }
}

public class StockIssue {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        // Store multiple issue requests
        Request[] requests = {
            new Request("Laptop", 5),
            new Request("Mouse", 25),
            new Request("Keyboard", 3),
            new Request("Laptop", 0),
            new Request("Monitor", 2),
            new Request("Keyboard", 2)
        };

        // Process every request without stopping after a failure
        for (Request request : requests) {

            System.out.println(
                "\nProcessing: " + request.item +
                " - Quantity: " + request.qty
            );

            try {
                warehouse.issue(request.item, request.qty);

            } catch (OutOfStockException e) {

                System.out.println("Out of Stock: " + e.getMessage());
                System.out.println("Shortfall: " + e.getShortfall());

            } catch (InvalidQuantityException e) {

                System.out.println(
                    "Invalid Quantity: " + e.getMessage()
                );
            }
        }

        System.out.println("\nAll requests processed.");
    }
}
