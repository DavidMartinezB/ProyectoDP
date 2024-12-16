
/**
 * Enumeration class Surcharge - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Surcharge
{
    MEDIUM("Medium", 10),     //Recargo de entrega medio
    LOW("Low", 4);         //Recargo de entrega bajo
    
    private String name;  //Cadena de caracteres que indica el tipo de recargo
    private int value;    //Entero que indica el tipo de recargo
    
    Surcharge (String name, int value){
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

