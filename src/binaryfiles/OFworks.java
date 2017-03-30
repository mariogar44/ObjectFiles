package binaryfiles;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DM3-1-20
 */
public class OFworks {

    public static void writeUser() {
        int repeat = 0;

        do {
            User user = new User();//Creates a new user for saving it in a file

            try (MiObjectOutputStream oos = new MiObjectOutputStream(new FileOutputStream("C:\\program\\Users.ser", true))) {
                oos.writeObject(user);
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file");
            } catch (IOException ex) {
                Logger.getLogger(OFworks.class.getName()).log(Level.SEVERE, null, ex);
            }
            do { //A menu to go back or repeat the operation
                System.out.println("1- Save another user");
                System.out.println("2- Go back");
                repeat = Read.Int();

            } while (repeat < 1 || repeat > 2);

        } while (repeat == 1);
    }

    public static void writeFlight() {//Same method as writeUser()
        int repeat = 0;

        do {
            Flight flight = new Flight();

            try (MiObjectOutputStream oos = new MiObjectOutputStream(new FileOutputStream("C:\\program\\Flights.ser", true))) {
                oos.writeObject(flight);
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file");
            } catch (IOException ex) {
                Logger.getLogger(OFworks.class.getName()).log(Level.SEVERE, null, ex);
            }
            do {
                System.out.println("1- Save another flight");
                System.out.println("2- Go back");
                repeat = Read.Int();

            } while (repeat < 1 || repeat > 2);

        } while (repeat == 1);
    }

    public static void readUser() {

        try (MiObjectInputStream ois = new MiObjectInputStream(new FileInputStream("C:\\program\\Users.ser"))) {
            while (true) {//Reads users from file and shows their members
                User user = (User) ois.readObject();

                System.out.println("DNI number: " + user.getDni());
                System.out.println("Name: " + user.getName() + " | Surname: " + user.getSurname() + " | Birthdate: " + user.getBirth());
                System.out.println("Address: " + user.getAddress() + " | Phone number: " + user.getPhone());
                System.out.println("Username: " + user.getUsername() + " | Password: " + user.getPassword());
                System.out.println("--------");

            }

        } catch (FileNotFoundException ex) {
            System.out.println("Can't find file (Probably there are no users saved)");
        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OFworks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void readFlight() {//same as readUser()

        try (MiObjectInputStream ois = new MiObjectInputStream(new FileInputStream("C:\\program\\Flights.ser"))) {
            while (true) {
                Flight flight = (Flight) ois.readObject();

                System.out.println("FlightID: " + flight.getFlightid() + " | Duration: " + flight.getDuration() + "min Date: " + flight.getDate());
                System.out.println("Goes from: " + flight.getFrom() + " To: " + flight.getTo());
                System.out.println("The pilots are " + flight.getPilot1() + " and " + flight.getPilot2());
                System.out.println(flight.getTickets_sold() + " tickets have been sold out of " + flight.getTickets() + " with a price of " + flight.getPrice() + "€");
                System.out.println("----------");

            }

        } catch (FileNotFoundException ex) {
            System.out.println("Can't find file (Probably there are no flights saved)");
        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OFworks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void searchUser() {
        String s;
        int ask = -1;
        boolean aurkitua = false;
        while (ask == -1) { //It asks for the DNI to delete
            System.out.println("Enter the DNI you want to search for: ");
            s = Read.String();
            if (s.length() == 8) {//Checks if the typed DNI has 8 characters
                try {
                    ask = Integer.parseInt(s);
                } catch (NumberFormatException ex) {
                    System.out.println("You've typed something wrong");
                    ask = -1;
                }
            } else {
                System.out.println("The DNI number must contain 8 digits");
            }
        }

        try (MiObjectInputStream ois = new MiObjectInputStream(new FileInputStream("C:\\program\\Users.ser"))) {

            while (!aurkitua) {//Reads users until finds the one we want to show

                User user = (User) ois.readObject();
                if (user.getDni() == ask) {//If it is the user to be shown enters here

                    System.out.println("DNI number: " + user.getDni());
                    System.out.println("Name: " + user.getName() + " | Surname: " + user.getSurname() + " | Birthdate: " + user.getBirth());
                    System.out.println("Address: " + user.getAddress() + " | Phone number: " + user.getPhone());
                    System.out.println("Username: " + user.getUsername() + " | Password: " + user.getPassword());
                    System.out.println("--------");
                    aurkitua = true;
                }
            }

        } catch (EOFException end) {
            if (!aurkitua) {
                System.out.println("Can't find user with that DNI");
            }
        } catch (FileNotFoundException gaizki) {
            System.out.println("File not Found.");
        } catch (IOException gaizki) {
            System.out.println("Error: " + gaizki.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OFworks.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void searchFlight() {//same as searchUser()
        String s;
        boolean aurkitua = false;
        int ask = -1;
        boolean eof;
        while (ask == -1) {
            System.out.println("Enter the ID you want to search for: ");
            s = Read.String();
            if (s.length() == 4) {
                try {
                    ask = Integer.parseInt(s);
                } catch (Exception ex) {
                    System.out.println("You've typed something wrong");
                    ask = -1;
                }
            } else {
                System.out.println("The ID number must contain 4 digits");
            }
        }

        try (MiObjectInputStream ois = new MiObjectInputStream(new FileInputStream("C:\\program\\Flights.ser"))) {

            while (!aurkitua) {
                Flight flg = (Flight) ois.readObject();
                if (ask == flg.getFlightid()) {

                    System.out.println("FlightID: " + flg.getFlightid() + " | Duration: " + flg.getDuration() + "min Date: " + flg.getDate());
                    System.out.println("Goes from: " + flg.getFrom() + " To: " + flg.getTo());
                    System.out.println("The pilots are " + flg.getPilot1() + " and " + flg.getPilot2());
                    System.out.println(flg.getTickets_sold() + " tickets have been sold out of " + flg.getTickets() + " with a price of " + flg.getPrice() + "€");
                    System.out.println("----------");
                    aurkitua = true;
                }

            }

        } catch (EOFException end) {
            if (!aurkitua) {
                System.out.println("There is no flight with that id");
            }
        } catch (FileNotFoundException gaizki) {
            System.out.println("File not Found.");
        } catch (IOException gaizki) {
            System.out.println("Error: " + gaizki.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OFworks.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void deleteUser() {
        boolean aurkitua = false, exist = true;
        ArrayList<User> users = new ArrayList<>();//Arraylist for saving the users

        int ask = -1;
        while (ask == -1) {//Asks for the DNI
            System.out.println("Enter the DNI of the user you want to delete: ");
            String s = Read.String();
            if (s.length() == 8) {
                try {
                    ask = Integer.parseInt(s);
                } catch (NumberFormatException ex) {
                    System.out.println("You've typed something wrong");
                    ask = -1;
                }
            } else {
                System.out.println("The DNI number must contain 8 digits");
            }
        }
        try (MiObjectInputStream ois = new MiObjectInputStream(new FileInputStream("C:\\program\\Users.ser"))) {

            while (true) { //Reads users from file and saves them in "users" arraylist
                User usr = (User) ois.readObject();
                if (usr.getDni() != ask) {//If it is not the user to be deleted saves it in the arraylist
                    users.add(usr);
                } else {
                    System.out.println("User found, deleting...");
                }

            }

        } catch (EOFException ex) {
            if (!aurkitua) {
                System.out.println("There is no user with that DNI");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Can't find file");
            exist = false;
        } catch (IOException ex) {
            System.out.println("You've typed something wrong");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OFworks.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (exist) {//If the file exist

            try (MiObjectOutputStream oos = new MiObjectOutputStream(new FileOutputStream("C:\\program\\Users.ser"))) {

                for (int i = 0; i < users.size(); i++) {//Writes all the users

                    oos.writeObject(users.get(i));

                }
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file");

            } catch (IOException ex) {
                System.out.println("You've typed something wrong");

            }
        }
    }

    public static void deleteFlight() {//sames as deleteUser()
        boolean aurkitua = false, exist = true;
        ArrayList<Flight> flights = new ArrayList<>();

        int ask = -1;
        while (ask == -1) {//Asks for the DNI
            System.out.println("Enter the ID of the flight you want to delete: ");
            String s = Read.String();
            if (s.length() == 4) {
                try {
                    ask = Integer.parseInt(s);
                } catch (NumberFormatException ex) {
                    System.out.println("You've typed something wrong");
                    ask = -1;
                }
            } else {
                System.out.println("The ID number must contain 4 digits");
            }
        }
        try (MiObjectInputStream ois = new MiObjectInputStream(new FileInputStream("C:\\program\\Flights.ser"))) {

            while (true) { //Reads users from file and saves them in users arraylist
                Flight flg = (Flight) ois.readObject();
                if (flg.getFlightid() != ask) {
                    flights.add(flg);
                } else {
                    System.out.println("User found, deleting...");
                }

            }

        } catch (EOFException ex) {
            if (!aurkitua) {
                System.out.println("There is no flight with that id");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Can't find file");
            exist = false;
        } catch (IOException ex) {
            System.out.println("You've typed something wrong");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OFworks.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (exist) {
            try (MiObjectOutputStream oos = new MiObjectOutputStream(new FileOutputStream("C:\\program\\Flights.ser"))) {

                for (int i = 0; i < flights.size(); i++) {

                    oos.writeObject(flights.get(i));

                }
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file");

            } catch (IOException ex) {
                System.out.println("You've typed something wrong");

            }

        }
    }

    public static void backup() {//This backups both two files
        int i;
        System.out.println("");
        System.out.println("This is going to delete previous backup, do you want to continue?: ");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        i = Read.Int();
        if (i == 1) {
            System.out.println("************");
            //These path objects save all the paths for both backup and normal files
            Path uPath = Paths.get("C:\\program\\Users.ser");//Users path
            Path uBPath = Paths.get("C:\\backup\\Users.ser");//Users backup path
            Path fPath = Paths.get("C:\\program\\Flights.ser");//Flights path
            Path fBPath = Paths.get("C:\\backup\\Flights.ser");//Flights backup path
            try {
                Files.copy(uPath, uBPath, REPLACE_EXISTING);//Copies the file from uPath to uBPath and replaces if it exists in uBPath
                System.out.println("Users successfully backuped to: " + uBPath);
            } catch (IOException ex) {
                System.out.println("Error with the backup of users");
            }
            try {
                Files.copy(fPath, fBPath, REPLACE_EXISTING);
                System.out.println("Flights successfully backuped to: " + fBPath);
            } catch (IOException ex) {
                System.out.println("Error with the backup of flights");
            }
            System.out.println("************");
        }
    }

    public static void restore() {//It's more or lesslike backup, changing path orders
        int i;
        System.out.println("");
        System.out.println("This is going to delete current data, do you want to continue?: ");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        i = Read.Int();
        if (i == 1) {
            System.out.println("************");
            Path uPath = Paths.get("C:\\program\\Users.ser");
            Path uBPath = Paths.get("C:\\backup\\Users.ser");
            Path fPath = Paths.get("C:\\program\\Flights.ser");
            Path fBPath = Paths.get("C:\\backup\\Flights.ser");
            try {
                Files.copy(uBPath, uPath, REPLACE_EXISTING);
                System.out.println("Users successfulllllllllllllllllly restored");
            } catch (IOException ex) {
                System.out.println("Error restoring users");
            }
            try {
                Files.copy(fBPath, fPath, REPLACE_EXISTING);
                System.out.println("Flights successfully restored");
            } catch (IOException ex) {
                System.out.println("Error restoring flights");
            }
            System.out.println("************");
        }
    }

    public static class MiObjectInputStream extends ObjectInputStream {

        public MiObjectInputStream(ObjectInputStream out) throws IOException {
            super(out);
        }

        protected MiObjectInputStream() throws IOException, SecurityException {
            super();
        }

        public MiObjectInputStream(InputStream inputStream) throws IOException {
            super(inputStream);
        }

        protected void readStreamHeader() throws IOException {
        }

    }

    public static class MiObjectOutputStream extends ObjectOutputStream {

        /**
         * Constructor que recibe OutputStream
         */
        public MiObjectOutputStream(OutputStream out) throws IOException {

            super(out);

        }

        /**
         * Constructor sin parÃ¡metros
         */
        protected MiObjectOutputStream() throws IOException, SecurityException {

            super();

        }

        /**
         * RedefiniciÃ3n del mÃ©todo de escribir la cabecera para que no haga
         * nada.
         *
         */
        protected void writeStreamHeader() throws IOException {

        }

    }
}
