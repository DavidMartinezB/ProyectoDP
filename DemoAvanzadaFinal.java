import java.util.*;

/**
 * Provide a simple demonstration of running a stage-one
 * scenario. Several orders and delivery persons are created. 
 * Pickups are requested. As the simulation is run, the orders
 * should be picked up and then delivered to their destination.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.11.13 DP classes
 */
public class DemoAvanzadaFinal
{
    DeliveryCompany company;
    private List<DeliveryPerson> actors; //simulation's actors, they are the delivery persons
    //of the company

    /**
     * Constructor for objects of class DemoOneOrder
     */
    public DemoAvanzadaFinal()
    {
        company = new DeliveryCompany("Compañía DP Delivery Cáceres");
        actors = new ArrayList<>();

        reset();
    }

    /**
     * Run the demo for a fixed number of steps.
     */
    public void run()
    {        
        //Ejecutamos un número de pasos la simulación.
        //En cada paso, cada persona de reparto realiza su acción
        for(int step = 0; step < 100; step++) {
            step();
        }
        showFinalInfo();
    }

    /**
     * Run the demo for one step by requesting
     * all actors to act.
     */
    public void step()
    {
        for(DeliveryPerson actor : actors) {
            actor.act();
        }
    }

    /**
     * Reset the demo to a starting point.
     * A single delivery person and order are created, and a pickup is
     * requested for a single order.
     * @throws IllegalStateException If a pickup cannot be found
     */
    public void reset()
    {
        actors.clear();

        createDeliveryPersons();
        createOrders(); 
        showInicialInfo();
        runSimulation();
    }

    /**
     * DeliveryPersons are created and added to the company
     */
   private void createDeliveryPersons() {
        DeliveryPerson dp1 = new SpecialDP(company, new Location(3, 3),"DP2");
        DeliveryPerson dp2 = new ExpressDP(company, new Location(10, 10),"DP1");
        DeliveryPerson dp3 = new CommonDP(company, new Location(12, 14),"DP3");

        company.addDeliveryPerson(dp1);
        company.addDeliveryPerson(dp2);
        company.addDeliveryPerson(dp3);
        actors.addAll(company.getDeliveryPersons());
    }

    /**
     * Orders are created and added to the company
     */
    private void createOrders() {
        //all the orders are created in the warehouse location
        //Parameters: sendingName, location, destination, deliveryTime, 
        //weight, destinationName, urgency, surchage (only for Urgent and NonUrgent orders)
        Location whLocation = company.getWareHouse().getLocation();
        Order order1 = new UrgentOrder("Agnes", whLocation,
                new Location(19,19),12, 1.2, "Pintores 19", Urgency.IMPORTANT, Charge.MEDIUM);
        Order order2 = new NonUrgentOrder("Lucy", whLocation,
                new Location(2, 6),10, 1.2, "Cruz de los caídos", Urgency.NONESSENTIAL, Charge.MEDIUM);
        Order order3 = new UrgentOrder("Gru", whLocation,
                new Location(5,2),10, 1.5, "Pintores", Urgency.IMPORTANT, Charge.LOW);
        Order order4 = new MedicalOrder("Kevin", whLocation,
                new Location(14,2),11, 2.2, "Ruta de la Plata",Urgency.EMERGENCY);
        Order order5 = new UrgentOrder("Edith", whLocation,
                new Location(19,19),11, 1.2, "Pintores 19 B",Urgency.IMPORTANT, Charge.LOW);
        Order order6 = new NonUrgentOrder("Stuart", whLocation,
                new Location(14,3),10, 1.2, "Ruta de la Plata",Urgency.NONESSENTIAL,Charge.LOW);
        Order order7 = new NonUrgentOrder("Margo", whLocation,
                new Location(7,1),11, 1.5, "Cruz de los caídos, 3",Urgency.IMPORTANT,Charge.MEDIUM);
        company.addOrder(order1);
        company.addOrder(order2);
        company.addOrder(order3);
        company.addOrder(order4);
        company.addOrder(order5);
        company.addOrder(order6);
        company.addOrder(order7);
    }

    /**
     * A pickup is requested for a single order.
     * @throws IllegalStateException If a pickup cannot be found
     */
        private void runSimulation() {
        //Obtener los orders desde wareHouse
        //ya vienen ordenados por su tipo de urgency, hora de llegada y destinationName
        //TODO colección   orders = company.getOrders();
        Iterator<Order> it = orders.iterator();
        while(it.hasNext()) {
            Order order = it.next();
            if(!company.requestPickup(order)) {
                throw new IllegalStateException("Failed to find a pickup.");        
            }
        }
    }

    /**
     * Initial info is showed with the information about delivery persons and orders
     */
    private void showInicialInfo() {
        //Obtenemos los objetos DeliveryPerson de la compañía
        List<DeliveryPerson> deliveryPersons = company.getDeliveryPersons();
        //Obtenemos los objetos Orders del almacén (vienen ya ordenados)
        //TODO colección  orders = company.getOrders();

        System.out.println("--->> Simulation of the company: "+company.getName()+" <<---");
        System.out.println("-->> Delivery persons of the company <<--");
        System.out.println("-->> ------------------------------- <<--");
        //TODO ordenar (por su nombre) y mostrar los objetos delivery persons
        for(DeliveryPerson  dp : deliveryPersons) {
            System.out.println(dp);
        }

        System.out.println("-->> Orders to be picked up <<--");
        System.out.println("-->> ---------------------- <<--");
        Iterator<Order> it = orders.iterator();
        while(it.hasNext()) {
            Order order = it.next();
            System.out.println(order.showInitialInfo());
        }
        System.out.println("");
        System.out.println("-->> Simulation start <<--");
        System.out.println("-->> ---------------- <<--");
        System.out.println("");        
    }

    /**
     * Final info is showed with the information about delivery persons and orders
     */
    private void showFinalInfo() {
        //Obtenemos los objetos DeliveryPerson de la compañía
        List<DeliveryPerson> deliveryPersons = company.getDeliveryPersons();
        //Obtenemos los orders entregados con sus objetos DeliveryPerson asociados
        //desde el almacén (vienen ya ordenados)
        //TODO declarar colección ordersDelivered = company.getWareHouse().getDeliveredOrders();

        System.out.println("");
        System.out.println("-->> ----------------- <<--");
        System.out.println("-->> End of simulation <<--");        
        System.out.println("-->> ----------------- <<--");
        System.out.println("");

        System.out.println("-->> Delivery persons final information <<--");
        System.out.println("-->> ---------------------------------- <<--");
        //TODO ordenar (por número de pedidos entregados y si empate por nombre) 
        // y mostrar los objetos delivery persons        
        for(DeliveryPerson  dp : deliveryPersons) {
            System.out.println(dp.showFinalInfo());
        }

        System.out.println("");
        System.out.println("-->> Orders final information <<--");
        System.out.println("-->> ------------------------ <<--");
        //TODO los pedidos entregados vienen en orden creciente por sendingName y  
        // en caso de empate por la hora de entrega y mostrar los pedidos y quién 
        // lo entregó
        //Mostrar los orders obtenidos
        

    }
}
