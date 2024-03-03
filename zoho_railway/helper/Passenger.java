package helper;

import java.util.*;
public class Passenger
{
    public String name, gender, berthPref;
    public int age;
    public void getDetails()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name, age, berth preference: ");
        name = sc.next();
        gender = "male";
        age = sc.nextInt();
        berthPref = sc.next();
    }

    public String toString()
    {
        return name + " " + gender + " " + age + " " + berthPref;
    }
}
