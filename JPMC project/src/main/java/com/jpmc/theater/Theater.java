package com.jpmc.theater;

import netscape.javascript.JSObject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class Theater {

    LocalDateProvider provider;
    private List<Showing> schedule;
    // List of shows put in a schedule

    private List<Reservation> reservationList;
    // List of reservations

    private Integer seats;
    // Seats availabe in the Theatre

    public Theater(LocalDateProvider provider) {
        this.provider = provider;

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        seats=100;
        schedule = List.of(
            new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0)),seats),
            new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0)),seats),
            new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50)),seats),
            new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30)),seats),
            new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10)),seats),
            new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50)),seats),
            new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30)),seats),
            new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10)),seats),
            new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)),seats)
        );
        reservationList = new ArrayList<>();
    }


    public List<Reservation> getTotalReservations()
    {
        return reservationList;
    }
    public void reserve(Customer customer, int sequence, int boughtTickets) // Code to reserve at the Theatre
    {

            Showing currentShowing = schedule.stream().filter(showing -> sequence==showing.getSequenceOfTheDay()).findAny().orElse(null);

            try {
                if(currentShowing==null)
                    throw new NullPointerException();
                if(boughtTickets<1)
                {
                    System.out.println("Invalid tickets count");        //  Deal with exceptions by handling via try catch block
                    return;                                             // also elimate cases such as 0 and negative tickets bought at al

                }
            }
            catch(NullPointerException n)
            {
                System.out.println("Show does not exist");
                return;
            }
            if(currentShowing.seatsAvailableToReserve(boughtTickets))
            {
                currentShowing.deductSeats(boughtTickets);
            }
            else
            {
                System.out.println("Not enough available seats, currently "+currentShowing.getAvailableSeats()+" available");
                return;
            }

            Reservation reservation = new Reservation(customer,currentShowing,boughtTickets);
            System.out.println("Made "+ boughtTickets
                +" reservation" +handlePlural(boughtTickets) +" for "
                +currentShowing.getMovie().getTitle() + " at "+currentShowing.getStartTime());
            System.out.println("Total fee for reservation: +"+reservation.totalFee());

            reservationList.add(reservation);
    }
    public void printSchedule() {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay()
                        + ": " + s.getStartTime()
                        + " " + s.getMovie().getTitle()
                        + " " + humanReadableFormat(s.getMovie().getRunningTime())
                        + " $" + s.calculateShowingPrice())
        );
        System.out.println("===================================================");
    }

    public void printJsonSchedule()
    {
       System.out.println(JsonSchedule());
    }

    public JSONObject JsonSchedule(){ // Convert the string to Json format
        JSONObject jsonObject = new JSONObject();

        for(Showing s: schedule)
        {
            jsonObject.put(s.getSequenceOfTheDay()+"",s.getStartTime()
                    + " " + s.getMovie().getTitle()
                    + " " + humanReadableFormat(s.getMovie().getRunningTime())
                    + " $" + s.calculateShowingPrice());
        }
        return jsonObject;
    }
    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }



    public static void main(String[] args) {
      //  Scanner scanner = new Scanner(System.in);
        String selection;
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer customer = new Customer("John Doe",1);
        theater.printSchedule();
        theater.reserve(customer,1,60);

        theater.printJsonSchedule();
        //Graphical UI to just test out the application nicely and make reservations without modifying the above code
        // Uncomment line 148 & line 187 to test out the application
        /*
         String entryScreen= "1. Print Schedule" +"\n"+
                "2. Print Schedule in Json Format" +"\n"+
                "3. Make a reservation"+"\n"+
                "4. Exit";
        do{
            System.out.println("Welcome to the Theatre "+ customer.getName()+
                    " pick from the following options");
            System.out.println(entryScreen);
            selection = scanner.nextLine();
            if(selection.equals("1"))
            {
                theater.printSchedule();
            }
            else if(selection.equals("2"))
            {
                theater.printJsonSchedule();
            }
            else if(selection.equals("3"))
            {
                int movieChoice;
                int numTickets;
                System.out.println("Enter the movie you wish to reserve or 0 to return to the main menu");
                try{
                    movieChoice=Integer.parseInt(scanner.nextLine());
                    if(movieChoice==0)
                        continue;
                    System.out.println("Enter the number of tickets you wish to buy");
                    numTickets= Integer.parseInt(scanner.nextLine());
                }
                catch(NumberFormatException e)
                {
                 System.out.println("Invalid format");
                 continue;
                }
                theater.reserve(customer,movieChoice,numTickets);
            }

        }while(!(selection.toLowerCase().equals("exit")) && !selection.equals("4"));
*/

    }



}
