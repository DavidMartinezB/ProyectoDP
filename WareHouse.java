import java.util.*;

public class WareHouse {
    private Location location;
    private Set<Order> orders;
    private Map<Order, DeliveryPerson> deliveredOrders;

    /**
     * Constructor for objects of class WareHouse
     */
    public WareHouse() {
        this.location = new Location(5, 5);
        this.orders = new TreeSet<>(new ComparadorUrgenciaOrder());
        this.deliveredOrders = new TreeMap<>(new ComparadorDeliveredOrders());
    }

    /**
     * @return The location of the warehouse.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @return The set of orders in the warehouse.
     */
    public Set<Order> getOrders() {
        return orders;
    }

    /**
     * Add an order to the warehouse.
     * The orders are automatically sorted by urgency, delivery time, and destination name.
     * @param order The order to be added.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Retrieve and remove the first order in the warehouse.
     * @return The first order if it exists, null otherwise.
     */
    public Order retrieveFirstOrder() {
        Iterator<Order> iterator = orders.iterator();
        if (iterator.hasNext()) {
            Order firstOrder = iterator.next();
            orders.remove(firstOrder);
            return firstOrder;
        }
        return null;
    }

    /**
     * Add a delivered order and its delivery person to the delivered orders collection.
     * @param order The delivered order.
     * @param deliveryPerson The delivery person who delivered the order.
     */
    public void addDeliveredOrder(Order order, DeliveryPerson deliveryPerson) {
        deliveredOrders.put(order, deliveryPerson);
    }

    /**
     * @return A map of delivered orders and their associated delivery persons.
     */
    public Map<Order, DeliveryPerson> getDeliveredOrders() {
        return deliveredOrders;
    }

}
