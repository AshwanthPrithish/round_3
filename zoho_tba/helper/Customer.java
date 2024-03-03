package helper;

public class Customer
{
    public static int cId = 0;

    public char pickUpPoint, dropPoint;
    public int pickUpTime, id;

    public Customer(char pickUpPoint, char dropPoint, int pickUpTime)
    {
        this.pickUpPoint = pickUpPoint;
        this.dropPoint = dropPoint;
        this.pickUpTime = pickUpTime;
        this.id = cId++;
    }
    public String toString()
    {
        return this.id + " " + this.pickUpPoint + " " + this.dropPoint + " " + this.pickUpTime;
    }
}
