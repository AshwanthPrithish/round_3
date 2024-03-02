import helper.*;
import java.util.*;


// TicketBooker Start

class TicketBooker
{
    static HashMap<String, Passenger> passengers = new HashMap<>();
    static int lowerSeats = 1;
    static int middleSeats = 1;
    static int upperSeats = 1;
    static int racSeats = 1;
    static int waitingSeats = 1;

    static int bookedlowerSeats = 0;
    static int bookedmiddleSeats = 0;
    static int bookedupperSeats = 0;
    static int bookedracSeats = 0;
    static int bookedwaitingSeats = 0;

    static int passengerId = 0;

    public static void addPassenger(Passenger p)
    {
        if(waitingSeats == 0)
        {
            System.out.println("Tickets full!");
            return;
        }
        String pref = p.berthPref;
        String seatNo = "";
        switch(pref.charAt(0))
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
                                seatNo = ""+(bookedwaitingSeats)+"W";
                                bookedwaitingSeats++;
                                waitingSeats--;
                                passengers.put(seatNo, p);
                                return;
                            }
                            seatNo = ""+(bookedracSeats)+"R";
                            bookedracSeats++;
                            racSeats--;
                            passengers.put(seatNo, p);
                            return;
                        }
                        seatNo = ""+(bookedupperSeats)+"U";
                        bookedupperSeats++;
                        upperSeats--;
                        passengers.put(seatNo, p);
                        return;
                    }
                    seatNo = ""+(bookedmiddleSeats)+"M";
                    bookedmiddleSeats++;
                    middleSeats--;
                    passengers.put(seatNo, p);
                    return;
                }
                seatNo = ""+(bookedlowerSeats)+"L";
                bookedlowerSeats++;
                lowerSeats--;
                passengers.put(seatNo, p);
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
                                seatNo = ""+(bookedwaitingSeats)+"W";
                                bookedwaitingSeats++;
                                waitingSeats--;
                                passengers.put(seatNo, p);
                                return;
                            }
                            seatNo = ""+(bookedracSeats)+"R";
                            bookedracSeats++;
                            racSeats--;
                            passengers.put(seatNo, p);
                            return;
                        }
                        seatNo = ""+(bookedupperSeats)+"U";
                        bookedupperSeats++;
                        upperSeats--;
                        passengers.put(seatNo, p);
                        return;
                    }
                    seatNo = ""+(bookedlowerSeats)+"L";
                    bookedlowerSeats++;
                    lowerSeats--;
                    passengers.put(seatNo, p);
                    return;

                }

                seatNo = ""+(bookedmiddleSeats)+"M";
                bookedmiddleSeats++;
                middleSeats--;
                passengers.put(seatNo, p);
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
                                seatNo = ""+(bookedwaitingSeats)+"W";
                                bookedwaitingSeats++;
                                waitingSeats--;
                                passengers.put(seatNo, p);
                                return;
                            }
                            seatNo = ""+(bookedracSeats)+"R";
                            bookedracSeats++;
                            racSeats--;
                            passengers.put(seatNo, p);
                            return;
                        }
                        seatNo = ""+(bookedmiddleSeats)+"M";
                        bookedmiddleSeats++;
                        middleSeats--;
                        passengers.put(seatNo, p);
                        return;
                    }
                    seatNo = ""+(bookedlowerSeats)+"L";
                    bookedlowerSeats++;
                    lowerSeats--;
                    passengers.put(seatNo, p);
                    return;

                }
                seatNo = ""+(bookedupperSeats)+"U";
                bookedupperSeats++;
                upperSeats--;
                passengers.put(seatNo, p);
                return;

            }
        }
    }
    public static void printAvailable()
    {
        System.out.println("Upper: " + upperSeats);
        System.out.println("Lower: " + lowerSeats);
        System.out.println("Middle: " + middleSeats);
        System.out.println("RAC: " + racSeats);
        System.out.println("Waiting: " + waitingSeats);
    }


}


// TickerBooker End



class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("1. Book");
            System.out.println("2. Cancel");
            System.out.println("3. Print booked tickets");
            System.out.println("4. Print available tickets");
            System.out.println("5. Exit");
            int choice = sc.nextInt();
            if(choice == 5)break;

            switch(choice)
            {
            case 1:
                {
                    Passenger p = new Passenger();
                    p.getDetails();
                    TicketBooker.addPassenger(p);
                    break;
                }
            case 2:
                {
                    System.out.println("Enter Seat Number: ");
                    String seat = sc.next();
                    if(TicketBooker.passengers.containsKey(seat))
                    {
                        if(seat.charAt(seat.length() - 1) == 'L')
                        {
                            TicketBooker.racSeats++;
                            TicketBooker.bookedracSeats--;
                        }
                        else if(seat.charAt(seat.length() - 1) == 'M')
                        {
                            TicketBooker.middleSeats++;
                            TicketBooker.bookedmiddleSeats--;
                        }
                        else if(seat.charAt(seat.length() - 1) == 'U')
                        {
                            TicketBooker.upperSeats++;
                            TicketBooker.bookedupperSeats--;
                        }
                        else if(seat.charAt(seat.length() - 1) == 'R')
                        {
                            TicketBooker.racSeats++;
                            TicketBooker.bookedracSeats--;
                        }
                        else if(seat.charAt(seat.length() - 1) == 'W')
                        {
                            TicketBooker.waitingSeats++;
                            TicketBooker.bookedwaitingSeats--;
                        }
                    TicketBooker.passengers.remove(seat);
                    System.out.println("Cancelled Ticket!");
                    }
                    else
                    {
                        System.out.println("No such ticket booked!");
                    }
                    break;
                }
            case 3:
                {
                    System.out.println(TicketBooker.passengers);
                    break;
                }
            case 4:
                {
                    TicketBooker.printAvailable();
                    break;
                }
            default:
                {
                    System.out.println("Invalid option");
                }
            }
        }
    }
}
