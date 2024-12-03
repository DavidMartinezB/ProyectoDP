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
        this.orders = new TreeSet<>(new OrderComparator());
        this.deliveredOrders = new TreeMap<>(new DeliveredOrderComparator());
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
    /**
     * Comparator for sorting orders by urgency, delivery time, and destination name.
     */
    private static class OrderComparator implements Comparator<Order> {
        @Override
        public int compare(Order o1, Order o2) {
            int urgencyComparison = Integer.compare(o2.getUrgency().getValue(), o1.getUrgency().getValue());
            if (urgencyComparison != 0) {
                return urgencyComparison;
            }
            int timeComparison = Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
            if (timeComparison != 0) {
                return timeComparison;
            }
            return o1.getDestinationName().compareTo(o2.getDestinationName());
        }
    }

    /**
     * Comparator for sorting delivered orders by sending name and delivery time.
     */
    private static class DeliveredOrderComparator implements Comparator<Order> {
        @Override
        public int compare(Order o1, Order o2) {
            int senderComparison = o1.getSendingName().compareTo(o2.getSendingName());
            if (senderComparison != 0) {
                return senderComparison;
            }
            return Integer.compare(o1.getDeliveryTime(), o2.getDeliveryTime());
        }
    }
}
