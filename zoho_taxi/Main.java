import helper.*;
import java.util.*;

class Taxi
{

    public static int tId = 1;
    public char currentPos = 'A';
    public float earnings = 0;
    public int freeAfter = 0;
    public int id;
    public static ArrayList<Taxi> taxiList = new ArrayList<Taxi>();

    public Taxi(){ this.id = tId++; }

    public void updateDetails(char currentPos, float earnings, int freeAfter)
    {
        this.currentPos = currentPos;
        this.earnings += earnings;
        this.freeAfter = freeAfter;
    }

    public static void createTaxis(int x)
    {
        for(int i = 0; i < x; i++)
        {
            Taxi t = new Taxi();
            taxiList.add(t);
        }
    }

    public static ArrayList<Taxi> getFreeTaxis(Customer c)
    {
        ArrayList<Taxi> freeTaxis = new ArrayList<>();
        for(Taxi t : taxiList)
        {
            int taxiDistFromCustomer = Math.abs((t.currentPos-'A')-(c.pickupPoint-'A'));
            if((taxiDistFromCustomer+t.freeAfter) <= c.pickupTime)
            {
                freeTaxis.add(t);
            }
        }
        Collections.sort(freeTaxis, (a, b) -> Float.compare(a.earnings, b.earnings));
        return freeTaxis;
    }

    public static void bookCustomer(Customer c)
    {
        ArrayList<Taxi> freeTaxis = getFreeTaxis(c);
        if(freeTaxis.size() == 0)
        {
            System.out.println("No Taxi available!");
            return;
        }
        Taxi chosenTaxi = freeTaxis.get(0);

        int taxiDistFromCustomer = Math.abs((chosenTaxi.currentPos-'A')-(c.pickupPoint-'A'));

        int kms = taxiDistFromCustomer * 15;
        float earnings_ = 100;
        kms -= 5;
        float extra = (Math.abs(kms) * 10);
        earnings_ += extra;

        int freeAfter_ = c.pickupTime + taxiDistFromCustomer;

        chosenTaxi.updateDetails(c.dropPoint, earnings_, 0);
    }

    public static void printTaxiDetails()
    {
        for(Taxi t: taxiList)
        {
            System.out.println("Taxi ID: " + t.id);
            System.out.println("Current Position: " + t.currentPos);
            System.out.println("Total Earnings: " + t.earnings);
            System.out.println("Free After: " + t.freeAfter);
            System.out.println("-----------------------------------------");
        }
    }
}

class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Taxi.createTaxis(5);
        while(true)
        {
            int choice;
            System.out.println("1. Book Taxi");
            System.out.println("2. Display Bookings");
            choice = sc.nextInt();
            switch(choice)
            {
            case 1:{
                System.out.println("Enter Customer pickUp point, Drop point, Pickup Time:");
                char pickupPoint = sc.next().charAt(0), dropPoint = sc.next().charAt(0);
                int pickUpTime = sc.nextInt();
                Customer c = new Customer(pickupPoint, dropPoint, pickUpTime);
                Taxi.bookCustomer(c);
                break;
            }
            case 2:{
                Taxi.printTaxiDetails();
                break;
            }
            default:
                return;
            }
        }
    }
}
