
/**
 * Write a description of class ExpressDP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExpressDP extends DeliveryPerson
{
    public ExpressDP (DeliveryCompany company, Location location, String name){
        super(company, location, name, 2);
    }

    public int getUrgency(Order order){
        return 3;
    }
}