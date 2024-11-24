
/**
 * Enumeration class Urgency - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Urgency
{
    EMERGENCY(5),       //Nivel de urgencia alto
    IMPORTANT(3),       //Nivel de urgencia medio
    NONESSENTIAL(1);    //Nivel de urgencia bajo
    
    private final int value;        //Entero que indica el tipo de urgencia
    
    Urgency (int value)     {
        this.value = value;
    }
    
    public int getValue()  {
        return value;
    }
}
