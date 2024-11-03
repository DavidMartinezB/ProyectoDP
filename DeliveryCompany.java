import java.util.*;

/**
 * Model the operation of a taxi company, operating different
 * delivery persons. This version operates a single type of delivery person.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2024.10.07 DP classes
 */
public class DeliveryCompany  
{
    private String name;  //nombre de la compañía
    private List <DeliveryPerson> deliveryPersons;
    private WareHouse wareHouse;
    private int posDeliveryPersonLibre;
    /**
     * Constructor for objects of class DeliveryCompany
     */
    public DeliveryCompany(String name)
    {
        this.name = name;
        deliveryPersons = new ArrayList<>();
        wareHouse = new WareHouse();
        posDeliveryPersonLibre = 0;
    }

    /**
     * @return The name of the company.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return The list of delivery persons.
     */
    public List<DeliveryPerson> getDeliveryPersons()
    {       
        return deliveryPersons;
    }

    /**
     * @return The list of orders.
     */
    public List<Order> getOrders()
    {
        return wareHouse.getOrders();
    }

    /**
     * @param Add a new delivery person.
     */
    public void addDeliveryPerson(DeliveryPerson dp)
    { 
        deliveryPersons.add(dp);
    }

    /**
     * Add a new order in the whare house of the company.
     * @param order The new order.
     */
    public void addOrder(Order order)
    {
        wareHouse.addOrder(order);
    }

    /**
     * Find a the most closed free delivery person to the whare house's location, if any.
     * @return A free delivery person, or null if there is none.
     */
    private DeliveryPerson getDeliveryPerson() {
        boolean repLibre1 = false;
        boolean repLibre2 = false;
        int dis1 = 0;
        int dis2 = 0;
        boolean salir = false;
        int i = 0;
        ComparadorNombreDeliveryPerson comparador = new ComparadorNombreDeliveryPerson();

        posDeliveryPersonLibre = -1; // Reset the position
    
        while (i < deliveryPersons.size() && !salir) {
            repLibre1 = deliveryPersons.get(i).isFree();
            if (repLibre1) {
                salir = true;
                posDeliveryPersonLibre = i;
                if (i + 1 < deliveryPersons.size()) {
                    repLibre2 = deliveryPersons.get(i + 1).isFree();
                    if (repLibre2) {
                        deliveryPersons.get(i).setTargetLocation(wareHouse.getLocation());
                        deliveryPersons.get(i + 1).setTargetLocation(wareHouse.getLocation());
                        dis1 = deliveryPersons.get(i).distanceToTheTargetLocation();
                        dis2 = deliveryPersons.get(i + 1).distanceToTheTargetLocation();
                        if (dis1 == dis2) {
                            if (comparador.compare(deliveryPersons.get(i), deliveryPersons.get(i + 1)) > 0) {
                                posDeliveryPersonLibre = i + 1;
                            }
                        }
                    }
                }
            }
            i++;
        }
    
        if (posDeliveryPersonLibre == -1) {
            return null;
        }
    
        return deliveryPersons.get(posDeliveryPersonLibre);
    }

    /**
     * Request a pickup for the given order.
     * @param order The order to be delivered.
     * @return Whether a free delivery person is available.
     */
    public boolean requestPickup(Order order) {
        boolean solicita = false;
        
        DeliveryPerson dp = getDeliveryPerson();
        if (dp != null) {
            wareHouse.addOrder(order);
            dp.setPickupLocation(wareHouse.getLocation());
            solicita = true;
        }
        
        return solicita;
    }

    /**
     * A delivery person has arrived at a pickup point.
     * @param dp The delivery person at the pickup point.
     */
    public void arrivedAtPickup(DeliveryPerson dp) {
        List<Order> orders = wareHouse.getOrders();
        if (orders.isEmpty()) {
            throw new IllegalStateException("No orders available for pickup.");
        }
    
        int lastIndex = orders.size() - 1;
        Order orderToPickup = orders.get(lastIndex);
        dp.pickup(orderToPickup);
        orderToPickup.setDeliveryPersonName(dp.getName());
    
        System.out.println(dp + " picks up Order from " + orderToPickup.getSendingName() + 
            " to " + orderToPickup.getDestinationName());
    }

    /**
     * A delivery person has arrived at a orders's destination.
     * @param dp The delivery person at the destination.
     * @param order The order being dropped off.
     */
    public void arrivedAtDestination(DeliveryPerson dp, Order order) {
        System.out.println(dp + " delivers " + order);
    }
}