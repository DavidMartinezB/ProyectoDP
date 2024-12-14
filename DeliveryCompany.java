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
        System.out.println("Added delivery person: " + dp);
    }

    /**
     * Add a new order in the warehouse of the company.
     * @param order The new order.
     */
    public void addOrder(Order order) {
        wareHouse.addOrder(order);
        System.out.println("Order added: " + order);
        System.out.println("Current orders in warehouse: " + wareHouse.getOrders().size());
    }

    /**
     * Find the most closed free delivery person to the warehouse's location, if any.
     * @return A free delivery person, or null if there is none.
     */
    private DeliveryPerson getDeliveryPerson(Order order) {
        
        for (int i=0;i<deliveryPersons.size();i++)  {
            deliveryPersons.get(i).setTargetLocation(wareHouse.getLocation());
        }
        
        Collections.sort(deliveryPersons, new ComparadorDeliveryPerson());
        DeliveryPerson DPLibre = null;
        boolean salir = false;
        int i = 0;

        while (i < deliveryPersons.size() && !salir) {
            if (deliveryPersons.get(i).isFree() != false)   {
                int cargaDP = deliveryPersons.get(i).getMaxLoad();
                switch(cargaDP)    {
                    case 1:
                        if (order.getUrgency().getValue() == 5)
                            DPLibre = deliveryPersons.get(i);
                        salir = true;
                        break;
                    case 2:
                        if (order.getUrgency().getValue() == 3)
                            DPLibre = deliveryPersons.get(i);
                        salir = true;
                        break;
                    case 4:
                        if (order.getUrgency().getValue() == 3 || order.getUrgency().getValue() == 1)
                            DPLibre = deliveryPersons.get(i);
                        salir = true;
                        break;
                }
            }
            i++;
        }
        
        for (int j=0;j<deliveryPersons.size();j++)  {
            deliveryPersons.get(j).clearTargetLocation();
        }

        return DPLibre;
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
            solicita = true;
        }

        return solicita;
    }

    /**
     * A delivery person has arrived at a pickup point.
     * @param dp The delivery person at the pickup point.
     */
    public void arrivedAtPickup(DeliveryPerson dp, Order order) {
        
        if (dp.distanceToTheTargetLocation() == 0)   {
            dp.pickup(order);
        }
    
        System.out.println(dp + " picks up Order from " + order.getSendingName() + 
            " to " + order.getDestinationName());
    }

    /**
     * A delivery person has arrived at an order's destination.
     * @param dp The delivery person at the destination.
     * @param order The order being dropped off.
     */
    public void arrivedAtDestination(DeliveryPerson dp, Order order) {
        wareHouse.addDeliveredOrder(order, dp);
        System.out.println(dp + " delivers " + order);
    }
}