/**
 * Model a location in a city.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2024.10.07 DP classes 
 */
public class Location
{
    private int x; 
    private int y;  

    /**
     * Model a location in the city.
     * @param x The x coordinate. Must be positive.
     * @param y The y coordinate. Must be positive.
     * @throws IllegalArgumentException If a coordinate is negative.
     */
    public Location(int x, int y)
    {
        if(x < 0) {
            throw new IllegalArgumentException(
                "Negative x-coordinate: " + x);
        }
        if(y < 0) {
            throw new IllegalArgumentException(
                "Negative y-coordinate: " + y);
        }        
        this.x = x;
        this.y = y;
    }

    /**
     * @return The x coordinate.
     */
    public int getX()
    {
        return x;
    }

    /**
     * @return The y coordinate.
     */
    public int getY()
    {    
        return y;
    }

    /**
     * Generate the next location to visit in order to
     * reach the destination.
     * @param destination Where we want to get to.
     * @return A location in a direct line from this to
     *         destination.
     */
    public Location nextLocation(Location destination)
    {
        int x2 = destination.getX();
        int y2 = destination.getY();
        int x1 = x;
        int y1 = y;
        if(x2 == x1 && y2 != y1){
            if(y1 < y2){
                y1++;
            }
            else{
                y1--;
            }
        }
        else{
            if(x2 != x1 && y2 == y1){
                if(x1 < x2){
                    x1++;
                }
                else{
                    x1--;
                }
            }
            else{
                if(x2 != x1 && y2 != y1){
                    if (x1 < x2) {
                        x1++;
                    }
                    else{
                        x1--;
                    }
                    if(y1 < y2){
                        y1++;
                    }
                    else{
                        y1--;
                    }
                }
            }
        }
    Location siguiente = new Location (x1, y1);
    return siguiente;
}

    /**
     * Determine the number of movements required to get
     * from here to the destination.
     * @param destination The required destination.
     * @return the number of movement steps.
     */
    public int distance(Location destination)
    {
        int x2 = destination.getX();
        int y2 = destination.getY();
        int disX = y2 - y;
        int disY = x2 - x;
        return Math.max(Math.abs(disX), Math.abs(disY));
    }

    /**
     * @return A representation of the location.
     */
    public String toString()
    {
        return "location " + x + "," + y;
    }

    /**
     * Implement content equality for locations.
     * @return true if this location matches the other, false otherwise.
     */
    public boolean equals(Object other)
    {
        if(other instanceof Location) {
            Location otherLocation = (Location) other;
            return x == otherLocation.getX() &&
            y == otherLocation.getY();
        }
        else {
            return false;
        }
    }

    /**
     * Use the top 16 bits for the y value and the bottom for the x.
     * Except for very big grids, this should give a unique hash code
     * for each (x, y) pair.
     * @return A hashcode for the location.
     */
    public int hashCode()
    {
        return (y << 16) + x;
    }
}
