import helper.*;
import java.util.*;


// TicketBooker Start

class TicketBooker
{
    static LinkedHashMap<String, Passenger> passengers = new LinkedHashMap<>();
    static int lowerSeats = 1;
    static int middleSeats = 1;
    static int upperSeats = 1;
    static int racSeats = 2;
    static int waitingSeats = 2;

    static int bookedlowerSeats = 0;
    static int bookedmiddleSeats = 0;
    static int bookedupperSeats = 0;
    static int bookedracSeats = 0;
    static int bookedwaitingSeats = 0;

    static int passengerId = 0;


    //start handling cancellations

    public static boolean shiftWaitingToRac(String s)
    {
        String a = "";
        Passenger p = null;
        for(String seat: passengers.keySet())
        {
            if(seat.charAt(seat.length() - 1) == 'W')
            {
                p = passengers.get(seat);
                a = seat;
                break;
            }
        }
        // if there are any passenger in waiting list
        if(p != null)
        {
            TicketBooker.passengers.remove(s);
            TicketBooker.passengers.remove(a);
            bookedracSeats--;
            racSeats++;
            String seatNo = ""+(bookedracSeats)+"R";
            bookedracSeats++;
            racSeats--;
            passengers.put(seatNo, p);
            return true;
        }
        return false;
    }

    public static String shiftRacToConfirmed(String s)
    {
        String ret = "";
        Passenger p = null;
        char class_ = s.charAt(s.length() - 1);
        for(String seat: passengers.keySet())
        {
            if(seat.charAt(seat.length() - 1) == 'R')
            {
                p = passengers.get(seat);
                ret = seat;
                break;
            }
        }
        if(p != null)
        {
            TicketBooker.passengers.remove(s);
            TicketBooker.passengers.remove(ret);
            switch(class_)
            {
            case 'L':
                {
                    bookedlowerSeats--;
                    lowerSeats++;
                    String seatNo = ""+(bookedlowerSeats)+"L";
                    bookedlowerSeats++;
                    lowerSeats--;
                    passengers.put(seatNo, p);
                    break;
                }
            case 'M':
                {
                    bookedmiddleSeats--;
                    middleSeats++;
                    String seatNo = ""+(bookedmiddleSeats)+"M";
                    bookedmiddleSeats++;
                    middleSeats--;
                    passengers.put(seatNo, p);
                    break;
                }
            case 'U':
                {
                    bookedupperSeats--;
                    upperSeats++;
                    String seatNo = ""+(bookedupperSeats)+"U";
                    bookedupperSeats++;
                    upperSeats--;
                    passengers.put(seatNo, p);
                    break;
                }
            }
        }
        return ret;
    }

    //end handling cancellations


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
                    if(!TicketBooker.passengers.containsKey(seat))
                    {
                        System.out.println("No such ticket booked!");
                    }
                    else
                    {
                        if(seat.charAt(seat.length() - 1) == 'W')
                        {
                            TicketBooker.waitingSeats++;
                            TicketBooker.bookedwaitingSeats--;
                            TicketBooker.passengers.remove(seat);
                        }

                        else if(seat.charAt(seat.length() - 1) == 'R')
                        {
                            boolean update = TicketBooker.shiftWaitingToRac(seat);
                            if(!update)
                            {
                                TicketBooker.racSeats++;
                                TicketBooker.bookedracSeats--;
                            }
                            else
                            {
                                TicketBooker.waitingSeats++;
                                TicketBooker.bookedwaitingSeats--;
                            }

                        }

                        else if(seat.charAt(seat.length() - 1) == 'U')
                        {
                            String racToConfirmed = TicketBooker.shiftRacToConfirmed(seat);
                            if(racToConfirmed != "")
                            {
                                boolean update = TicketBooker.shiftWaitingToRac(racToConfirmed);
                                if(!update)
                                {
                                    TicketBooker.upperSeats++;
                                    TicketBooker.bookedupperSeats--;
                                }
                                else
                                {
                                   TicketBooker.waitingSeats++;
                                   TicketBooker.bookedwaitingSeats--;
                                }
                            }
                        }


                        else if(seat.charAt(seat.length() - 1) == 'L')
                        {
                            String racToConfirmed = TicketBooker.shiftRacToConfirmed(seat);
                            if(racToConfirmed != "")
                            {
                                boolean update = TicketBooker.shiftWaitingToRac(racToConfirmed);
                                if(!update)
                                {
                                    TicketBooker.lowerSeats++;
                                    TicketBooker.bookedlowerSeats--;
                                }
                                else
                                {
                                    TicketBooker.waitingSeats++;
                                    TicketBooker.bookedwaitingSeats--;
                                }
                            }
                        }

                        else if(seat.charAt(seat.length() - 1) == 'M')
                        {
                            String racToConfirmed = TicketBooker.shiftRacToConfirmed(seat);
                            if(racToConfirmed != "")
                            {
                                boolean update = TicketBooker.shiftWaitingToRac(racToConfirmed);
                                if(!update)
                                {
                                    TicketBooker.middleSeats++;
                                    TicketBooker.bookedmiddleSeats--;
                                }
                                else
                                {
                                    TicketBooker.waitingSeats++;
                                    TicketBooker.bookedwaitingSeats--;
                                }
                            }
                        }

                        System.out.println("Cancelled Ticket!");
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






































/*
. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
1
Enter name, age, berth preference:
a 1 L
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
1
Enter name, age, berth preference:
b 2 U
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
1
Enter name, age, berth preference:
c 3 M
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
1
Enter name, age, berth preference:
d 4 U
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
1
Enter name, age, berth preference:
e 5 L
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
4
Upper: 0
Lower: 0
Middle: 0
RAC: 0
Waiting: 2
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
1
Enter name, age, berth preference:
f 8 L
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
1
Enter name, age, berth preference:
g 8 M
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
1
Enter name, age, berth preference:
j 9 L
Tickets full!
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
3
{0L=a male 1 L, 0U=b male 2 U, 0M=c male 3 M, 0R=d male 4 U, 1R=e male 5 L, 0W=f male 8 L, 1W=g male 8 M}
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
2
Enter Seat Number:
0W
Cancelled Ticket!
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
3
{0L=a male 1 L, 0U=b male 2 U, 0M=c male 3 M, 0R=d male 4 U, 1R=e male 5 L, 1W=g male 8 M}
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
2
Enter Seat Number:
0W
No such ticket booked!
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
2
Enter Seat Number:
1W
Cancelled Ticket!
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
3
{0L=a male 1 L, 0U=b male 2 U, 0M=c male 3 M, 0R=d male 4 U, 1R=e male 5 L}
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
1
Enter name, age, berth preference:
g 8 M
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
3
{0L=a male 1 L, 0U=b male 2 U, 0M=c male 3 M, 0R=d male 4 U, 1R=e male 5 L, 0W=g male 8 M}
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
2
Enter Seat Number:
0M
Cancelled Ticket!
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
3
{0L=a male 1 L, 0U=b male 2 U, 1R=g male 8 M, 0M=d male 4 U}
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
4
Upper: 0
Lower: 0
Middle: 0
RAC: 0
Waiting: 2
1. Book
2. Cancel
3. Print booked tickets
4. Print available tickets
5. Exit
*/
