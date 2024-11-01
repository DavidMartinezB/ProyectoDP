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
    /**
     * Constructor for objects of class DeliveryCompany
     */
    public DeliveryCompany(String name)
    {
        this.name = name;
        deliveryPersons = new ArrayList<>();
        wareHouse = new WareHouse();
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
        wareHouse.getOrders().add(order);
    }

    /**
     * Find a the most closed free delivery person to the whare house's location, if any.
     * @return A free delivery person, or null if there is none.
     */
    private DeliveryPerson getDeliveryPerson()
    {
        boolean repLibre = false;
        int dis1 = 0;
        int dis2 = 0;
        int index = 0;
        
        for (int i=0;i < deliveryPersons.size();i++)   {
            repLibre = deliveryPersons.get(i).isFree();
            if (repLibre != false)   {
                dis1 = deliveryPersons.get(i).distanceToTheTargetLocation();
                if (dis2 < dis1)    {
                    index = i;
                }
            }
        }
        
        return null;
    }

    /**
     * Request a pickup for the given order.
     * @param order The order to be delivered.
     * @return Whether a free delivery person is available.
     */
    public boolean requestPickup(Order order)
    {
        //TODO implementar el método 
        return true;
    }

    /**
     * A delivery person has arrived at a pickup point.
     * @param dp The delivery person at the pickup point.
     */
    public void arrivedAtPickup(DeliveryPerson dp)
    {
        //TODO implementar el método
        //TODO Descomentar siguiente línea cuando esté el método completamente implementado
        //System.out.println("<<<< "+dp + " picks up order to " + order.getDestinationName());
        //TODO el order debe guardar el nombre de la persona de reparto que le ha recogido
        //TODO la persona de reparto debe recoger el pedido
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