package binaryfiles;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Flight implements Serializable {

    //Members
    private int flightid;
    private int duration;
    private String from;
    private String to;
    private String pilot1;
    private String pilot2;
    private int tickets;
    private int tickets_sold;
    private LocalDate date;
    private float price;

    //Constructors
    public Flight() {
        setFlightid();
        setDuration();
        setFrom();
        setTo();
        setPilot1();
        setPilot2();
        setTickets();
        setTickets_sold();
        setDate();
        setPrice();
    }

    public Flight(int id, int dur, String from, String to, String p1, String p2, int tick, int t_sold, LocalDate date, float price) {
        flightid = id;
        duration = dur;
        this.from = from;
        this.to = to;
        pilot1 = p1;
        pilot2 = p2;
        tickets = tick;
        tickets_sold = t_sold;
        this.date = date;
        this.price = price;
    }

    Flight(String empty) {
        
    }

    //Getters
    public int getFlightid() {
        return flightid;
    }

    public int getDuration() {
        return duration;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getPilot1() {
        return pilot1;
    }

    public String getPilot2() {
        return pilot2;
    }

    public int getTickets() {
        return tickets;
    }

    public int getTickets_sold() {
        return tickets_sold;
    }

    public LocalDate getDate() {
        return date;
    }

    public float getPrice() {
        return price;
    }

    //Setters
    public void setFlightid(int NewFlightid) {
        flightid = NewFlightid;
    }

    public void setFlightid() {
        boolean ok = false;
        while (!ok) {
            System.out.println("Enter the flight ID: ");
            String s = Read.String();
            if (s.length() == 4) {
                try {
                    flightid = Integer.parseInt(s);
                    ok = true;
                } catch (Exception ex) {
                    System.out.println("You've typed something wrong");
                    ok = false;
                }
            }else{
                System.out.println("The ID must contain 4 digits");
            }
        }
    }

    public void setDuration(int NewDuration) {
        duration = NewDuration;
    }

    public void setDuration() {
        System.out.println("Enter the duration of the flight in min: ");
        duration = Read.Int();

    }

    public void setFrom(String NewFrom) {
        from = NewFrom;
    }

    public void setFrom() {
        String s = "";
        while (s.isEmpty()) {
            System.out.println("Enter the place from which the plane takes off: ");
            s = Read.String();
            if (s.isEmpty()) {
                System.out.println("You must type something");
            }

            for (int i = 0; i < 10 && !s.isEmpty(); i++) {
                if (s.contains(String.valueOf(i))) {
                    System.out.println("The name can't contain numbers");
                    s = "";
                }
            }
        }

        from = s;
    }

    public void setTo(String NewTo) {
        to = NewTo;
    }

    public void setTo() {
        String s = "";
        while (s.isEmpty()) {
            System.out.println("Enter the place of arrival: ");
            s = Read.String();
            if (s.isEmpty()) {
                System.out.println("You must type something");
            }

            for (int i = 0; i < 10 && !s.isEmpty(); i++) {
                if (s.contains(String.valueOf(i))) {
                    System.out.println("The name can't contain numbers");
                    s = "";
                }
            }
        }

        to = s;
    }

    public void setPilot1(String NewPilot1) {
        pilot1 = NewPilot1;
    }

    public void setPilot1() {
        String s = "";
        while (s.isEmpty()) {
            System.out.println("Enter the name of the first pilot: ");
            s = Read.String();
            if (s.isEmpty()) {
                System.out.println("You must type something");
            }

            for (int i = 0; i < 10 && !s.isEmpty(); i++) {
                if (s.contains(String.valueOf(i))) {
                    System.out.println("The name can't contain numbers");
                    s = "";
                }
            }
        }

        pilot1 = s;
    }

    public void setPilot2(String NewPilot2) {
        pilot2 = NewPilot2;
    }

    public void setPilot2() {
        String s = "";
        while (s.isEmpty()) {
            System.out.println("Enter the name of the second pilot: ");
            s = Read.String();
            if (s.isEmpty()) {
                System.out.println("You must type something");
            }

            for (int i = 0; i < 10 && !s.isEmpty(); i++) {
                if (s.contains(String.valueOf(i))) {
                    System.out.println("The name can't contain numbers");
                    s = "";
                }
            }
        }

        pilot2 = s;
    }

    public void setTickets(int NewTickets) {
        tickets = NewTickets;
    }

    public void setTickets() {
        int i = 0;
        System.out.println("Enter how much tickets are going to be (max 300): ");
        while (i == 0) {
            i = Read.Int();
            if (i > 0 && i <= 300) {
                tickets = i;
            } else {
                System.out.println("You must type a number between 0 and 300 (inclusive)");
                i = 0;
            }
        }

    }

    public void setTickets_sold(int NewTickets_sold) {
        tickets_sold = NewTickets_sold;
    }

    public void setTickets_sold() {
        if (getTickets() > 0 && getTickets() <= 300) {
            int i = 0;
            System.out.println("Enter how many tickets are already sold: ");
            while (i == 0) {
                i = Read.Int();
                if (i > 0 && i <= getTickets()) {
                    tickets = i;
                } else {
                    System.out.println("You must type a number between 0 and " + getTickets() + " (inclusive)");
                    i = 0;
                }
            }
        } else {
            System.out.println("There are no total tickets especified");
        }

    }

    public void setDate(LocalDate NewDate) {
        date = NewDate;
    }

    public void setDate() {
        String s;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y M d", Locale.ENGLISH);

        date = null;
        while (date == null) {

            System.out.println("Enter the flight date (YYYY MM DD): ");
            try {
                s = reader.readLine();
                date = LocalDate.parse(s, formatter);
                if (date.getYear() > LocalDate.now().getYear() + 2) {
                    date = null;
                    System.out.println("You can't reserve a flight after " + LocalDate.now().getYear() + "\\" + LocalDate.now().getMonthValue() + "\\" + LocalDate.now().getYear() + 2);
                } else if (date.isBefore(LocalDate.now())) {
                    System.out.println("You can't reserve anything for yesterday.");
                    date = null;
                }

            } catch (Exception ex) {
                System.out.println("Wrong date format");
                date = null;
            }

        }
        
    }
    public void setDate(String dt) {
        String s = dt.replace("-", " ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y M d", Locale.ENGLISH);

        date = LocalDate.parse(s, formatter);
    }
    public void setPrice(float NewPrice) {
        price = NewPrice;
    }

    public void setPrice() {
        System.out.println("Enter the price of the tickets (€): ");
        price = Read.Int();
    }
}
