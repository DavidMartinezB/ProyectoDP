
/**
 * Enumeration class Urgency - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Urgency
{
    EMERGENCY("Emergency", 5),       //Nivel de urgencia alto
    IMPORTANT("Important", 3),       //Nivel de urgencia medio
    NONESSENTIAL("Non essential", 1);    //Nivel de urgencia bajo
    
    private String name;      //Cadena de caracteres que indica el tipo de urgencia
    private int value;        //Entero que indica el tipo de urgencia
    
    Urgency (String name, int value)     {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }
    
    public int getValue()  {
        return value;
    }
}
