package isp.lab12;

@FunctionalInterface
interface DiscountCalculator {
    double calculateDiscount(double price);
}

public class ProductApp {
    public static void main(String[] args) {
        // Lambda expression implementing the functional interface
        DiscountCalculator holidayDiscount = price -> price * 0.15;

        double originalPrice = 200.0;
        double discountAmount = holidayDiscount.calculateDiscount(originalPrice);

        System.out.println("Original Price: $" + originalPrice);
        System.out.println("Discount Amount: $" + discountAmount);
        System.out.println("Final Price: $" + (originalPrice - discountAmount));
    }
}