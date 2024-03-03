import java.util.*;

import helper.*;

class Taxi
{
    public static int tId = 1;
    public float earnings;
    public char currentPos;
    public int freeAfter;
    public int id;
    public ArrayList<String> trips;

    public static ArrayList<Taxi> taxis = new ArrayList<Taxi>();

    public Taxi()
    {
        this.earnings = 0;
        this.currentPos = 'A';
        this.freeAfter = 6;
        this.id = tId++;
        this.trips = new ArrayList<>();
    }

    public void updateDetails(float earnings, char currentPos, int freeAfter, String trip)
    {
        this.earnings += earnings;
        this.currentPos = currentPos;
        this.freeAfter = freeAfter;
        this.trips.add(trip);
    }

    public static void createTaxis(int x)
    {
        for(int i = 0; i < x; i++)
        {
            Taxi t = new Taxi();
            taxis.add(t);
        }
    }

    public static ArrayList<Taxi> getFreeTaxis(Customer c)
    {
        ArrayList<Taxi> freeTaxis = new ArrayList<>();

        for(Taxi t: taxis)
        {
            int taxiDistToCustomer = Math.abs((t.currentPos-'0') - (c.pickUpPoint-'0'));
            if((t.freeAfter + taxiDistToCustomer) <= c.pickUpTime)
            {
                freeTaxis.add(t);
            }
        }
        Collections.sort(freeTaxis, (a, b)->Float.compare(a.earnings, b.earnings));
        return freeTaxis;
    }

    public static Taxi getMinFromFreeTaxis(ArrayList<Taxi> freeTaxis, Customer c)
    {
        int mini = 999;
        Taxi n = null;
        for(Taxi t: freeTaxis)
        {
            int taxiDistToCustomer = Math.abs((t.currentPos-'0') - (c.pickUpPoint-'0'));
            if(mini > taxiDistToCustomer)
            {
                mini = taxiDistToCustomer;
                n = t;
            }
        }
        return n;
    }

    public static void bookTaxi(Customer c)
    {
        ArrayList<Taxi> freeTaxis = getFreeTaxis(c);
        if(freeTaxis.size() == 0)
        {
            System.out.println("No Taxi Available");
            return;
        }
        Taxi chosenTaxi = getMinFromFreeTaxis(freeTaxis, c);

        int rideDistanceInUnits = Math.abs((c.pickUpPoint - '0')-(c.dropPoint - '0'));
        int rideDistanceInKms = rideDistanceInUnits * 15;
        float earnings_ = 100 + ((rideDistanceInKms-5)*10);

        int taxiNewPosition = c.dropPoint;
        int taxiDistToCustomer = Math.abs((chosenTaxi.currentPos-'0') - (c.pickUpPoint-'0'));
        int newFreeAfter = rideDistanceInUnits + c.pickUpTime;

        System.out.println("Taxi " + chosenTaxi.id + " is alloted");
        String trip = "(" + chosenTaxi.id + " " + c.pickUpPoint + " " + c.dropPoint + " "  + c.pickUpTime + " " + newFreeAfter  + " " + earnings_+ " )";

        chosenTaxi.updateDetails(earnings_, c.dropPoint, newFreeAfter, trip);
    }

    public static void printDetails()
    {
        for(Taxi t : taxis)
        {
            System.out.println(t);
            System.out.println("-----------------------------------------------------");
        }
    }

    public String toString()
    {
        return this.id + " " + this.currentPos + " " + this.earnings + " " + this.freeAfter + " " + "\nTrips:\n" + this.trips;
    }
}

class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Taxi.createTaxis(5);
        while(true)
        {
            System.out.println("1. Book Taxi");
            System.out.println("2. View Taxi Details");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            switch(choice)
            {
            case 1:
                {
                    System.out.println("Enter Customer pickUpPoint, dropPoint, pickUpTime: ");
                    char pickUpPoint = sc.next().charAt(0), dropPoint = sc.next().charAt(0);
                    int pickUpTime = sc.nextInt();
                    Customer c = new Customer(pickUpPoint, dropPoint, pickUpTime);
                    Taxi.bookTaxi(c);
                    break;
                }
            case 2:
                {
                    Taxi.printDetails();
                    break;
                }
            default:
                {
                    break;
                }
            }
        }
    }
}
