import helper.*;

import java.util.*;

class TicketBooker
{
    static int lowerSeats = 1;
    static int middleSeats = 1;
    static int upperSeats = 1;
    static int racSeats = 1;
    static int waitingListSeats = 1;

    static int bookedLowerSeats = 0;
    static int bookedMiddleSeats = 0;
    static int bookedUpperSeats = 0;
    static int bookedRacSeats = 0;
    static int bookedWaitingListSeats = 0;

    static LinkedHashMap<String, Passenger> lowerBooked = new LinkedHashMap<String, Passenger>();
    static LinkedHashMap<String, Passenger> middleBooked = new LinkedHashMap<String, Passenger>();
    static LinkedHashMap<String, Passenger> upperBooked = new LinkedHashMap<String, Passenger>();

    static LinkedHashMap<String, Passenger> racList = new LinkedHashMap<String, Passenger>();
    static LinkedHashMap<String, Passenger> waitingList = new LinkedHashMap<String, Passenger>();


    public static void bookTicket(Passenger p)
    {
        if(waitingListSeats == 0)
        {
            System.out.println("Tickets full!");
            return;
        }
        String seatNo = "";
        int pref = p.berthPref;
        switch (pref)
        {
        case 'L':
            {
                if(lowerSeats == 0)
                {
                    if(middleSeats == 0)
                    {
                        if(upperSeats == 0)
                        {
                            if(racSeats == 0)
                            {
                                waitingListSeats--;
                                seatNo = bookedWaitingListSeats + "W";
                                bookedWaitingListSeats++;
                                p.alloted = seatNo;
                                waitingList.put(seatNo, p);
                                System.out.println("Waiting List booked!");
                                return;
                            }
                            racSeats--;
                            seatNo = bookedRacSeats + "R";
                            bookedRacSeats++;
                            p.alloted = seatNo;
                            racList.put(seatNo, p);
                            System.out.println("RAC booked!");
                            return;
                        }
                        upperSeats--;
                        seatNo = bookedUpperSeats + "U";
                        bookedUpperSeats++;
                        p.alloted = seatNo;
                        upperBooked.put(seatNo, p);
                        System.out.println("Upper booked!");
                        return;
                    }
                    middleSeats--;
                    seatNo = bookedMiddleSeats + "M";
                    bookedMiddleSeats++;
                    p.alloted = seatNo;
                    middleBooked.put(seatNo, p);
                    System.out.println("Middle booked!");
                    return;
                }
                lowerSeats--;
                seatNo = bookedLowerSeats + "L";
                bookedLowerSeats++;
                p.alloted = seatNo;
                lowerBooked.put(seatNo, p);
                System.out.println("Lower booked!");
                return;
            }
        case 'M':
            {

                if(middleSeats == 0)
                {
                    if(lowerSeats == 0)
                    {
                        if(upperSeats == 0)
                        {
                            if(racSeats == 0)
                            {
                                waitingListSeats--;
                                seatNo = bookedWaitingListSeats + "W";
                                bookedWaitingListSeats++;
                                p.alloted = seatNo;
                                waitingList.put(seatNo, p);
                                System.out.println("Waiting List booked!");
                                return;
                            }
                            racSeats--;
                            seatNo = bookedRacSeats + "R";
                            bookedRacSeats++;
                            p.alloted = seatNo;
                            racList.put(seatNo, p);
                            System.out.println("RAC booked!");
                            return;
                        }
                        upperSeats--;
                        seatNo = bookedUpperSeats + "U";
                        bookedUpperSeats++;
                        p.alloted = seatNo;
                        upperBooked.put(seatNo, p);
                        System.out.println("Upper booked!");
                        return;
                    }

                    lowerSeats--;
                    seatNo = bookedLowerSeats + "L";
                    bookedLowerSeats++;
                    p.alloted = seatNo;
                    lowerBooked.put(seatNo, p);
                    System.out.println("Lower booked!");
                    return;
                }

                middleSeats--;
                seatNo = bookedMiddleSeats + "M";
                bookedMiddleSeats++;
                p.alloted = seatNo;
                middleBooked.put(seatNo, p);
                System.out.println("Middle booked!");
                return;
            }
        case 'U':
            {

                if(upperSeats == 0)
                {
                    if(lowerSeats == 0)
                    {
                        if(middleSeats == 0)
                        {
                            if(racSeats == 0)
                            {
                                waitingListSeats--;
                                seatNo = bookedWaitingListSeats + "W";
                                bookedWaitingListSeats++;
                                p.alloted = seatNo;
                                waitingList.put(seatNo, p);
                                System.out.println("Waiting List booked!");
                                return;
                            }
                            racSeats--;
                            seatNo = bookedRacSeats + "R";
                            bookedRacSeats++;
                            p.alloted = seatNo;
                            racList.put(seatNo, p);
                            System.out.println("RAC booked!");
                            return;
                        }

                        middleSeats--;
                        seatNo = bookedMiddleSeats + "M";
                        bookedMiddleSeats++;
                        p.alloted = seatNo;
                        middleBooked.put(seatNo, p);
                        System.out.println("Middle booked!");
                        return;

                    }

                    lowerSeats--;
                    seatNo = bookedLowerSeats + "L";
                    bookedLowerSeats++;
                    p.alloted = seatNo;
                    lowerBooked.put(seatNo, p);
                    System.out.println("Lower booked!");
                    return;
                }

                upperSeats--;
                seatNo = bookedUpperSeats + "U";
                bookedUpperSeats++;
                p.alloted = seatNo;
                upperBooked.put(seatNo, p);
                System.out.println("Upper booked!");
                return;
            }
        default:
            {
                System.out.println("Invalid seat preference!");
                return;
            }
        }
    }

    public static void printAvailable()
    {
        System.out.println("Avaliable Lower Berths: " + lowerSeats);
        System.out.println("Avaliable Middle Berths: " + middleSeats);
        System.out.println("Avaliable Upper Berths: " + upperSeats);
        System.out.println("Avaliable RAC: " + racSeats);
        System.out.println("Avaliable Waiting List Seats: " + waitingListSeats);
        System.out.println("--------------------------------------------------");
    }

    public static void printBooked()
    {
        System.out.print("Lower Berth: ");
        for(String key : lowerBooked.keySet())
        {
            System.out.print(lowerBooked.get(key) + " ; ");
        }
        System.out.println();

        System.out.print("Middle Berth: ");
        for(String key : middleBooked.keySet())
        {
            System.out.print(middleBooked.get(key) + " ; ");
        }
        System.out.println();

        System.out.print("Upper Berth: ");
        for(String key : upperBooked.keySet())
        {
            System.out.print(upperBooked.get(key) + " ; ");
        }
        System.out.println();

        System.out.print("RAC: ");
        for(String key : racList.keySet())
        {
            System.out.print(racList.get(key) + " ; ");
        }
        System.out.println();

        System.out.print("Waiting List: ");
        for(String key : waitingList.keySet())
        {
            System.out.print(waitingList.get(key) + " ; ");
        }
        System.out.println();
        System.out.println("-------------------------------------------------");
    }
    public static void cancelTicket(String ticketNo)
    {
        char pref = ticketNo.charAt(ticketNo.length()-1);
        switch(pref)
        {
        case 'W':
            {
                waitingListSeats++;
                bookedWaitingListSeats--;
                waitingList.remove(ticketNo);
                return;
            }
        case 'R':
            {
                String seatNo = "";
                if(bookedWaitingListSeats > 0)
                {
                    Passenger pn = null;
                    for(Map.Entry<String, Passenger> e: waitingList.entrySet())
                    {
                        pn = e.getValue();
                        waitingList.remove(e.getKey());
                        break;
                    }
                    racSeats++;
                    bookedRacSeats--;
                    racList.remove(ticketNo);

                    racSeats--;
                    seatNo = bookedRacSeats + "R";
                    bookedRacSeats++;
                    pn.alloted = seatNo;
                    racList.put(seatNo, pn);
                    System.out.println("RAC booked!");

                    waitingListSeats++;
                    bookedWaitingListSeats--;
                    return;
                }
                else
                {
                    racSeats++;
                    bookedRacSeats--;
                    racList.remove(ticketNo);
                }
            }
        case 'L':
            {
                String seatNo = "";
                if(bookedRacSeats > 0)
                {
                    Passenger pn = null;
                    for(Map.Entry<String, Passenger> e: racList.entrySet())
                    {
                        pn = e.getValue();
                        racList.remove(e.getKey());
                        break;
                    }
                    lowerSeats++;
                    bookedLowerSeats--;
                    lowerBooked.remove(ticketNo);

                    lowerSeats--;
                    seatNo = bookedLowerSeats + "L";
                    bookedLowerSeats++;
                    pn.alloted = seatNo;
                    lowerBooked.put(seatNo, pn);
                    System.out.println("Lower booked!");

                    racSeats++;
                    bookedRacSeats--;
                }
                else
                {
                    lowerSeats++;
                    bookedLowerSeats--;
                    lowerBooked.remove(ticketNo);
                    return;
                }
                if(bookedWaitingListSeats > 0)
                {
                    Passenger pn = null;
                    for(Map.Entry<String, Passenger> e: waitingList.entrySet())
                    {
                        pn = e.getValue();
                        waitingList.remove(e.getKey());
                        break;
                    }
                    waitingListSeats++;
                    bookedWaitingListSeats--;

                    racSeats--;
                    seatNo = bookedRacSeats + "R";
                    bookedRacSeats++;
                    pn.alloted = seatNo;
                    racList.put(seatNo, pn);
                    System.out.println("RAC booked!");
                    return;
                }
            }
        case 'M':
            {
                String seatNo = "";
                if(bookedRacSeats > 0)
                {
                    Passenger pn = null;
                    for(Map.Entry<String, Passenger> e: racList.entrySet())
                    {
                        pn = e.getValue();
                        racList.remove(e.getKey());
                        break;
                    }
                    middleSeats++;
                    bookedMiddleSeats--;
                    middleBooked.remove(ticketNo);

                    middleSeats--;
                    seatNo = bookedMiddleSeats + "M";
                    bookedMiddleSeats++;
                    pn.alloted = seatNo;
                    middleBooked.put(seatNo, pn);
                    System.out.println("Middle booked!");

                    racSeats++;
                    bookedRacSeats--;
                }
                else
                {
                    middleSeats++;
                    bookedMiddleSeats--;
                    middleBooked.remove(ticketNo);
                    return;
                }
                if(bookedWaitingListSeats > 0)
                {
                    Passenger pn = null;
                    for(Map.Entry<String, Passenger> e: waitingList.entrySet())
                    {
                        pn = e.getValue();
                        waitingList.remove(e.getKey());
                        break;
                    }
                    waitingListSeats++;
                    bookedWaitingListSeats--;

                    racSeats--;
                    seatNo = bookedRacSeats + "R";
                    bookedRacSeats++;
                    pn.alloted = seatNo;
                    racList.put(seatNo, pn);
                    System.out.println("RAC booked!");
                    return;
                }
            }
        case 'U':
            {
                String seatNo = "";
                if(bookedRacSeats > 0)
                {
                    Passenger pn = null;
                    for(Map.Entry<String, Passenger> e: racList.entrySet())
                    {
                        pn = e.getValue();
                        racList.remove(e.getKey());
                        break;
                    }
                    upperSeats++;
                    bookedUpperSeats--;
                    upperBooked.remove(ticketNo);

                    upperSeats--;
                    seatNo = bookedUpperSeats + "U";
                    bookedUpperSeats++;
                    pn.alloted = seatNo;
                    upperBooked.put(seatNo, pn);
                    System.out.println("Upper booked!");

                    racSeats++;
                    bookedRacSeats--;
                }
                else
                {
                    upperSeats++;
                    bookedUpperSeats--;
                    upperBooked.remove(ticketNo);
                    return;
                }
                if(bookedWaitingListSeats > 0)
                {
                    Passenger pn = null;
                    for(Map.Entry<String, Passenger> e: waitingList.entrySet())
                    {
                        pn = e.getValue();
                        waitingList.remove(e.getKey());
                        break;
                    }
                    waitingListSeats++;
                    bookedWaitingListSeats--;

                    racSeats--;
                    seatNo = bookedRacSeats + "R";
                    bookedRacSeats++;
                    pn.alloted = seatNo;
                    racList.put(seatNo, pn);
                    System.out.println("RAC booked!");
                    return;
                }
            }

        }
    }
}

class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Booked Tickets");
            System.out.println("4. View Available tickets");
            System.out.println("5. Exit");
            int choice = sc.nextInt();
            switch (choice)
            {
            case 1:
                {
                    System.out.println("Enter name, age, berthpref(L/M/U): ");
                    String name = sc.next();
                    int age = sc.nextInt();
                    char berthPref = sc.next().charAt(0);
                    Passenger p = new Passenger(name, age, berthPref);
                    TicketBooker.bookTicket(p);
                    break;
                }
            case 2:
                {
                    System.out.println("Enter the Ticket Number: ");
                    String ticketNumber = sc.next();
                    TicketBooker.cancelTicket(ticketNumber);
                    break;
                }
            case 3:
                {
                    TicketBooker.printBooked();
                    break;
                }
            case 4:
                {
                    TicketBooker.printAvailable();
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
