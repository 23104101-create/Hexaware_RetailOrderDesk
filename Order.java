
public class Order {

    long orderId;
    String customerId;
    double amount;
    String createdAt;

    Order(long orderId, String customerId,
          double amount, String createdAt) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public String toString() {
        return "Order ID: " + orderId +
               ", Customer ID: " + customerId +
               ", Amount: " + amount +
               ", Created At: " + createdAt;
    }
}
