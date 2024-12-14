
/**
 * Write a description of class CommondDP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CommondDP extends DeliveryPerson implements PopularEnRedes
{
    private int popularEnRedes;
    public CommondDP (DeliveryCompany company, Location location, String name){
        super(company, location, name, 4);
        popularEnRedes = 6;
    }
    
    public void setPopularEnRedes(){
        if(getFirstOrder().getUrgency().getValue() == 3 )
        popularEnRedes = popularEnRedes + 4;
        else{
            popularEnRedes = popularEnRedes + 1;
        }
    }
    
    @Override
    public void deliverOrder()
    {
        setPopularEnRedes();
        super.deliverOrder();
    }

}
