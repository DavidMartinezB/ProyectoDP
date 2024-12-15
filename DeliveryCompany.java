import java.util.*;

/**
 * Model the operation of a taxi company, operating different
 * delivery persons. This version operates a single type of delivery person.
 * 
 * @author David J. Barnes
 * @version 2024.10.07 DP classes
 */
public class DeliveryCompany {
    private String name;  //nombre de la compañía
    private List<DeliveryPerson> deliveryPersons;
    private WareHouse wareHouse;

    /**
     * Constructor for objects of class DeliveryCompany
     */
    public DeliveryCompany(String name) {
        this.name = name;
        deliveryPersons = new ArrayList<>();
        wareHouse = new WareHouse();
    }

    /**
     * @return The name of the company.
     */
    public String getName() {
        return name;
    }
    public WareHouse getWareHouse() {
        return wareHouse;
    }

    /**
     * @return The list of delivery persons.
     */
    public List<DeliveryPerson> getDeliveryPersons() {
        return deliveryPersons;
    }

    /**
     * @return The list of orders.
     */
    public Set<Order> getOrders() {
        return wareHouse.getOrders();
    }

    /**
     * Add a new delivery person.
     * @param dp The new delivery person.
     */
    public void addDeliveryPerson(DeliveryPerson dp) {
        deliveryPersons.add(dp);
    }

    /**
     * Add a new order in the warehouse of the company.
     * @param order The new order.
     */
    public void addOrder(Order order) {
        wareHouse.addOrder(order);
    }

    /**
     * Find the most closed free delivery person to the warehouse's location, if any.
     * @return A free delivery person, or null if there is none.
     */
    public DeliveryPerson getDeliveryPerson(Order order) {
        int i = 0;
        boolean enc = false;
        for (int j=0;j<deliveryPersons.size();j++)  {
            deliveryPersons.get(j).setTargetLocation(wareHouse.getLocation());
        }
        deliveryPersons.sort(new ComparadorDeliveryPerson());
        int urgencia = order.getUrgency().getValue();
        DeliveryPerson dpLibre = null;
        while(i < deliveryPersons.size() && !enc){
            DeliveryPerson dp = deliveryPersons.get(i);
            if(dp.isFree()){
                if(urgencia == 5 && dp.getMaxLoad() == 1){
                    dpLibre = dp;
                    enc = true;
                }
                if(urgencia == 3 && dp.getMaxLoad() == 2){
                    dpLibre = dp;
                    enc = true;
                }
                if((urgencia == 1 || urgencia == 3) && dp.getMaxLoad() == 4){
                    dpLibre = dp;
                    enc = true;
                }
            }
            i++;
        }
        for (int j=0;j<deliveryPersons.size();j++)  {
            deliveryPersons.get(j).clearTargetLocation();
        }
    return dpLibre;
    }

    /**
     * Request a pickup for the given order.
     * @param order The order to be delivered.
     * @return Whether a free delivery person is available.
     */
    public boolean requestPickup(Order order) {
        boolean solicita = false;

        DeliveryPerson dp = getDeliveryPerson(order);
        
        if (dp != null) {
            wareHouse.addOrder(order);
            dp.setPickupLocation(order.getLocation());
            order.setDeliveryPersonName(dp.getName());
            solicita = true;
            System.out.println("<<<< " + dp + " go to pick up order from " + 
            order.getSendingName() + " at " + order.getLocation());
        }

        return solicita;
    }

    /**
     * A delivery person has arrived at a pickup point.
     * @param dp The delivery person at the pickup point.
     */
    public void arrivedAtPickup(DeliveryPerson dp) {
        Order order = null;
        boolean enc = false;
        if (dp.distanceToTheTargetLocation() == 0)   {
            Iterator<Order> iterator = this.getWareHouse().getOrders().iterator();
            while (iterator.hasNext() && !enc) {
                Order currentOrder = iterator.next();
                if(currentOrder.getLocation().equals(dp.getLocation())){
                    dp.pickup(currentOrder);
                    order = currentOrder;
                    enc = true;
                    System.out.println("<<<< " + dp + " picks up Order from: " + order.getSendingName() + " to: " + order.getDestination());
                }
            }
        }
    }

    /**
     * A delivery person has arrived at an order's destination.
     * @param dp The delivery person at the destination.
     * @param order The order being dropped off.
     */
    public void arrivedAtDestination(DeliveryPerson dp, Order order) {
        wareHouse.addDeliveredOrder(order, dp);
        System.out.println("<<<< " + dp + " delivers " + order);
    }
}