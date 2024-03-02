package helper;

public class Customer
{
    public static int cId = 0;
    public char pickupPoint, dropPoint;
    public int pickupTime, id;
    public Customer(char pickup, char drop, int time)
    {
        this.id = cId++;
        this.pickupPoint = pickup;
        this.dropPoint = drop;
        this.pickupTime = time;
    }
    public String toString()
    {
        return this.id + " " + this.pickupPoint + " " + this.dropPoint + " " + this.pickupTime;
    }
}
