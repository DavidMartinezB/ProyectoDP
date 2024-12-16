/**
 * Provide a simple demonstration of running a stage-one
 * scenario. Three simulaions are create: 

 * DemoOneOrder: A single order and delivery person are created, and a pickup
 * requested. As the simulation is run, the order
 * should be picked up and then delivered to their destination.
 * 
 * DemoInicial: Two orders and three delivery persons are created, two pickups
 * requested. As the simulation is run, the orders
 * should be picked up and then delivered to their destination.
 *
 * DemoAvanzada: Some orders and delivery persons are created, so some pickup
 * are requested. As the simulation is run, all the orders
 * should be picked up and then delivered to to their destination.
 * 
 * @author DP Clasess
 * @version 2024
 */
public class DeliveryDemo
{
    public static final int MAXX = 20; //Valor máximo de número de filas 
    public static final int MAXY = 20; //Valor máximo de número de columnas
    
    public static void main() {
        //Comentar o descomentar cada demo para ser ejecutada

        //Demo con 1 paquete y 1 persona de reparto
        //DemoOneOrderFinal demoOne = new DemoOneOrderFinal();
        //demoOne.run();

        //Demo con 2 paquetes y 3 personas de reparto
        DemoInicialFinal demoIni = new DemoInicialFinal();
        demoIni.run();

        //Demo con varios paquetes y personas de reparto
        //DemoAvanzadaFinal demoAvan = new DemoAvanzadaFinal();
        //demoAvan.run();
    }
}