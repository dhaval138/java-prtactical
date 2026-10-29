import java.util.Scanner;

public class Warehouse {
    static class InvalidQuantityException extends Exception {
        public InvalidQuantityException(String msg) { super(msg); }
    }
    
    static class OutOfStockException extends Exception {
        public OutOfStockException(String msg) { super(msg); }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int appleStock = 10; 

        System.out.println("Welcome to the Warehouse!");
        System.out.println("Starting Apple Stock: " + appleStock);
        while (true) {
            System.out.println("\n--- Current Apple Stock: " + appleStock + " ---");
            System.out.print("Enter amount of apples to buy (or 999 to exit): ");
            int qty = scanner.nextInt();
            if (qty == 999) {
                break;
            }
            try {
               
                if (qty <= 0) {
                    throw new InvalidQuantityException("Quantity must be greater than 0.");
                }
                if (qty > appleStock) {
                    int shortfall = qty - appleStock;
                    throw new OutOfStockException("Not enough apples! Shortfall: " + shortfall);
                }           
                appleStock = appleStock - qty;
                System.out.println("SUCCESS! You got your apples.");
            } catch (InvalidQuantityException e) {
                System.out.println("FAILED -> Error: " + e.getMessage());
            } catch (OutOfStockException e) {
                System.out.println("FAILED -> Error: " + e.getMessage());
            }
        }
        System.out.println("Warehouse closed. Goodbye!");
        scanner.close();
    }
}