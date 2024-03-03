package helper;

public class Passenger
{
    public String name;
    public int age;
    public char berthPref;
    public String alloted;
    public Passenger(String name, int age, char berthPref)
    {
        this.name = name;
        this.age = age;
        this.berthPref = berthPref;
    }
    public String toString()
    {
        return this.name + " " + this.alloted + " " + this.berthPref + " " + this.age;
    }
}
