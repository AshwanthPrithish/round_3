import helper.*;
import java.util.*;

class Taxi
{

    public static int tId = 1;
    public char currentPos = 'A';
    public float earnings = 0;
    public int freeAfter = 6;
    public int id;
    public ArrayList<String> trips;
    public static ArrayList<Taxi> taxiList = new ArrayList<Taxi>();

    public Taxi(){ this.id = tId++; trips = new ArrayList<String>(); }

    public void updateDetails(char currentPos, float earnings, int freeAfter, String trip)
    {
        this.currentPos = currentPos;
        this.earnings += earnings;
        this.freeAfter = freeAfter;
        this.trips.add(trip);
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
            int taxiDistFromCustomer = Math.abs((t.currentPos-'0')-(c.pickupPoint-'0'));
            if(taxiDistFromCustomer+t.freeAfter <= c.pickupTime)
            {
                freeTaxis.add(t);
            }
        }
        Collections.sort(freeTaxis, (a, b) -> Float.compare(a.earnings, b.earnings));
        return freeTaxis;
    }


    public static Taxi chooseMinTaxi(ArrayList<Taxi> freeTaxis, Customer c)
    {
        int min = 999;
        Taxi n = null;;
        for(Taxi t : freeTaxis)
        {
            int distToCustomer = Math.abs((t.currentPos-'0')-(c.pickupPoint-'0'));
            if(min > distToCustomer)
            {
                min = distToCustomer;
                n = t;
            }
        }
        return n;
    }
    public static void bookCustomer(Customer c)
    {
        ArrayList<Taxi> freeTaxis = getFreeTaxis(c);
        if(freeTaxis.size() == 0)
        {
            System.out.println("No Taxi available!");
            return;
        }
        Taxi chosenTaxi = chooseMinTaxi(freeTaxis, c);

        int taxiTravelDist = Math.abs((c.dropPoint-'A')-(c.pickupPoint-'A'));

        int kms = taxiTravelDist * 15;
        float earnings_ = 100;
        kms -= 5;
        float extra = (Math.abs(kms) * 10);
        earnings_ += extra;

        int freeAfter_ = c.pickupTime + taxiTravelDist;
        System.out.println("Taxi "+ chosenTaxi.id + " booked!");

        String trip = chosenTaxi.id + " " + c.pickupPoint + " " + c.dropPoint + " " +c.pickupTime +" " + freeAfter_ + " " + earnings_;
        chosenTaxi.updateDetails(c.dropPoint, earnings_, freeAfter_, trip);
    }

    public static void printTaxiDetails()
    {
        for(Taxi t: taxiList)
        {
            System.out.print("Taxi "+ t.id+" Details: ");
            System.out.println(t);
            System.out.println("-----------------------------------------");
        }
    }

    public String toString()
    {
        return "(" + id + "," + currentPos + "," + earnings + "," + freeAfter + ")\nTrips of Taxi " + id + ":\n" + trips;
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
