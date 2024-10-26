import java.util.*;

public class WareHouse {
    private Location location;
    private List<Order> orders;

    /**
     * Constructor for objects of class WareHouse
     */
    public WareHouse() {
        this.location = new Location(5, 5);
        this.orders = new ArrayList<>();
    }

    /**
     * @return The location of the warehouse.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @return The list of orders in the warehouse.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Add an order to the warehouse.
     * The orders are sorted by delivery time and sending name.
     * @param order The order to be added.
     */
    public void addOrder(Order order) {
        orders.add(order);
        orders.sort((o1, o2) -> {
            int timeComparison = Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
            if (timeComparison != 0) {
                return timeComparison;
            }
            return o1.getSendingName().compareTo(o2.getSendingName());
        });
    }
    /**
     * Retrieve and remove an order from the warehouse.
     * @param order The order to be retrieved.
     * @return The order if it exists, null otherwise.
     */
    public Order retrieveOrder(Order order) {
        if (orders.remove(order)) {
            return order;
        }
        return null;
    }
}