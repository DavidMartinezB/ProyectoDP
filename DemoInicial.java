import java.util.*;

/**
 * Provide a simple demonstration of running a stage-one
 * scenario. Two orders and three delivery persons are created. 
 * Two pickups are requested. As the simulation is run, the orders
 * should be picked up and then delivered to their destination.
 * 
 * @version 2024.10.07 DP classes
 */
public class DemoInicial {
    DeliveryCompany company;
    private List<DeliveryPerson> actors; //simulation's actors, they are the delivery persons
                                         //of the company
    private List<Order> ordersTemp; // Temporary list of orders

    /**
     * Constructor for objects of class DemoInicial
     */
    public DemoInicial() {
        company = new DeliveryCompany("Compañía DP Delivery Cáceres");
        actors = new ArrayList<>();
        ordersTemp = new ArrayList<>();
        reset();
    }

    /**
     * Run the demo for a fixed number of steps.
     */
    public void run() {        
        // Ejecutamos un número de pasos la simulación.
        // En cada paso, cada persona de reparto realiza su acción
        for (int step = 0; step < 100; step++) {
            step();
        }
        showFinalInfo();
    }

    /**
     * Run the demo for one step by requesting
     * all actors to act.
     */
    public void step() {
        for (DeliveryPerson actor : actors) {
            actor.act();
        }
    }

    /**
     * Reset the demo to a starting point.
     * A single delivery person and order are created, and a pickup is
     * requested for a single order.
     * @throws IllegalStateException If a pickup cannot be found
     */
    public void reset() {
        actors.clear();
        ordersTemp.clear();

        createDeliveryPersons();
        createOrders(); 
        showInicialInfo();
        runSimulation();
    }

    /**
     * DeliveryPersons are created and added to the company
     */
    private void createDeliveryPersons() {
        DeliveryPerson dp1 = new DeliveryPerson(company, new Location(10, 10), "DP1");
        DeliveryPerson dp2 = new DeliveryPerson(company, new Location(3, 3), "DP2");
        DeliveryPerson dp3 = new DeliveryPerson(company, new Location(12, 14), "DP3");

        actors.add(dp1);
        actors.add(dp2);
        actors.add(dp3);

        company.addDeliveryPerson(dp1);
        company.addDeliveryPerson(dp2);
        company.addDeliveryPerson(dp3);
    }

    /**
     * Orders are created and added to the company
     */
    private void createOrders() {
        Location whLocation = company.getWareHouse().getLocation();
        Order order1 = new Order("Gru", whLocation, new Location(5, 2), 10, 1.5, "Pintores");
        Order order2 = new Order("Kevin", whLocation, new Location(14, 2), 11, 2.2, "Ruta de la Plata");
        Order order3 = new Order("Lucy", whLocation, new Location(2, 6), 10, 1.2, "Decathon Cáceres");

        ordersTemp.add(order1);
        ordersTemp.add(order2);
        ordersTemp.add(order3);

        company.addOrder(order1);
        company.addOrder(order2);
        company.addOrder(order3);
    }

    /**
     * Show initial information about the simulation.
     */
    private void showInicialInfo() {
        System.out.println("--->> Simulation of the company: " + company.getName() + " <<---");
        System.out.println("-->> Delivery persons of the company <<--");
        System.out.println("-->> ------------------------------- <<--");
        actors.sort(Comparator.comparing(DeliveryPerson::getName));
        for (DeliveryPerson dp : actors) {
            System.out.println(dp);
        }
        System.out.println(" ");
        System.out.println("-->> Orders to be picked up <<--");
        System.out.println("-->> ---------------------- <<--");
        ordersTemp.sort(Comparator.comparing(Order::getSendingName).thenComparing(Order::getDeliveryTime));
        for (Order order : ordersTemp) {
            System.out.println(order.showFirstInfo());
        }
        System.out.println(" ");
        System.out.println("-->> Simulation start <<--");
        System.out.println("-->> ---------------- <<--");
        System.out.println(" ");
    }

    /**
     * Run the simulation by requesting pickups for all orders.
     * @throws IllegalStateException If a pickup cannot be found
     */
    private void runSimulation() {
        ordersTemp.sort(Comparator.comparing(Order::getDeliveryTime).thenComparing(Order::getDestinationName));
        for (Order order : ordersTemp) {
            if (!company.requestPickup(order)) {
                throw new IllegalStateException("Failed to find a pickup.");
            }
        }
    }

    /**
     * Show final information about the simulation.
     */
    private void showFinalInfo() {
        System.out.println(" ");
        System.out.println("-->> ----------------- <<--");
        System.out.println("-->> End of simulation <<--");
        System.out.println("-->> ----------------- <<--");
        System.out.println(" ");
        System.out.println("-->> Delivery persons final information <<--");
        System.out.println("-->> ---------------------------------- <<--");
        actors.sort(Comparator.comparing(DeliveryPerson::ordersDelivered).thenComparing(DeliveryPerson::getName));
        for (DeliveryPerson dp : actors) {
            System.out.println(dp.showFinalInfo());
        }
        System.out.println(" ");
        System.out.println("-->> Orders final information <<--");
        System.out.println("-->> ------------------------ <<--");
        ordersTemp.sort(Comparator.comparing(Order::getDeliveryTime).thenComparing(Order::getDestinationName));
        for (Order order : ordersTemp) {
            System.out.println(order.showFinalInfo());
        }
        System.out.println(" ");
    }
}