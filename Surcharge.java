
/**
 * Enumeration class Surcharge - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Surcharge
{
    MEDIUM(10),     //Recargo de entrega medio
    LOW(4);         //Recargo de entrega bajo
    
    private final int value;    //Entero que indica el tipo de recargo
    
    Surcharge (int value)    {
        this.value = value;
    }
    
    public int getValue()  {
        return value;
    }
}

