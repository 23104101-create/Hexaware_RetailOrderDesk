
import java.io.*;
import java.util.*;

public class RetailOrderSearch {

    // Load orders from CSV file
    static ArrayList<Order> loadOrders(String file) throws Exception {

        ArrayList<Order> orders = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        br.readLine(); // Skip header

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            long id = Long.parseLong(data[0]);
            String customer = data[1];
            double amount = Double.parseDouble(data[2]);
            String date = data[3];

            orders.add(new Order(id, customer, amount, date));
        }

        br.close();

        return orders;
    }

    // Linear Search - O(n)
    static Order linearSearch(ArrayList<Order> orders, long id) {

        for (Order o : orders) {

            if (o.orderId == id) {
                return o;
            }
        }

        return null;
    }

    // Binary Search - O(log n)
    static Order binarySearch(ArrayList<Order> orders, long id) {

        int low = 0;
        int high = orders.size() - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (orders.get(mid).orderId == id) {
                return orders.get(mid);
            }

            else if (orders.get(mid).orderId < id) {
                low = mid + 1;
            }

            else {
                high = mid - 1;
            }
        }

        return null;
    }

    public static void main(String[] args) throws Exception {

       ArrayList<Order> orders = loadOrders("RetailOrderDesk/orders.csv");

        System.out.println("Total Orders: " + orders.size());

        long id = 10025;

        // Linear Search
        long start = System.nanoTime();

        Order result1 = linearSearch(orders, id);

        long end = System.nanoTime();

        System.out.println("\nLinear Search:");
        System.out.println(result1);
        System.out.println("Time: " + (end - start) + " ns");

        // Sort orders before Binary Search
        ArrayList<Order> sorted = new ArrayList<>(orders);

        sorted.sort(Comparator.comparingLong(o -> o.orderId));

        // Binary Search
        start = System.nanoTime();

        Order result2 = binarySearch(sorted, id);

        end = System.nanoTime();

        System.out.println("\nBinary Search:");
        System.out.println(result2);
        System.out.println("Time: " + (end - start) + " ns");
    }
}
