/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binaryfiles;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
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
public class BFworks {

    public static void writeUser() {
        int repeat = 0;

        do {
            User user = new User();

            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("C:\\program\\Users.dat", true))) {
                dos.writeInt(user.getDni());
                dos.writeUTF(user.getName());
                dos.writeUTF(user.getSurname());
                dos.writeUTF(user.getBirth().toString());
                dos.writeUTF(user.getAddress());
                dos.writeInt(user.getPhone());
                dos.writeUTF(user.getUsername());
                dos.writeUTF(user.getPassword());
                dos.writeUTF(user.getEmail());
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file");
            } catch (IOException ex) {
                System.out.println("You've typed something wrong");
            }
            do {
                System.out.println("1- Save another user");
                System.out.println("2- Go back");
                repeat = Read.Int();

            } while (repeat < 1 || repeat > 2);

        } while (repeat == 1);

    }

    public static void writeFlight() {
        int repeat = 0;

        do {
            Flight flight = new Flight();

            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("C:\\program\\Flights.dat", true))) {
                dos.writeInt(flight.getFlightid());
                dos.writeInt(flight.getDuration());
                dos.writeUTF(flight.getFrom());
                dos.writeUTF(flight.getTo());

                dos.writeUTF(flight.getPilot1());
                dos.writeUTF(flight.getPilot2());
                dos.writeInt(flight.getTickets());
                dos.writeInt(flight.getTickets_sold());
                dos.writeUTF(flight.getDate().toString());
                dos.writeFloat(flight.getPrice());
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file");
            } catch (IOException ex) {
                System.out.println("You've typed something wrong");
            }
            do {
                System.out.println("1- Add another flight");
                System.out.println("2- Go back");
                repeat = Read.Int();

            } while (repeat < 1 || repeat > 2);

        } while (repeat == 1);

    }

    public static void readUser() {
        boolean eof = false;
        boolean exist = true;

        try (DataInputStream dis = new DataInputStream(new FileInputStream("C:\\program\\Users.dat"));) { //This checks if there is a user in the file
            dis.readInt();
        } catch (EOFException ex) {
            exist = false;
        } catch (FileNotFoundException ex) {
            System.out.println("There are no users saved");
            exist = false;
        } catch (IOException ex) {
            Logger.getLogger(BFworks.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (exist) {

            try {

                DataInputStream dis = new DataInputStream(new FileInputStream("C:\\program\\Users.dat"));
                while (!eof) {
                    System.out.println("Dni: " + dis.readInt());
                    System.out.println("Name: " + dis.readUTF());
                    System.out.println("Surname: " + dis.readUTF());
                    System.out.println("Birth: " + dis.readUTF());
                    System.out.println("Address: " + dis.readUTF());
                    System.out.println("Phone: " + dis.readInt());
                    System.out.println("Username: " + dis.readUTF());
                    System.out.println("Password: " + dis.readUTF());
                    System.out.println("Email: " + dis.readUTF());
                    System.out.println("----------");

                }
            } catch (EOFException ex) {
                eof = true;
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file (Probably there are no users saved)");
            } catch (IOException ex) {
                System.out.println("You've typed something wrong");
            }
        }
    }

    public static void readFlight() {
        boolean eof = false, exist = true;

        try (DataInputStream dis = new DataInputStream(new FileInputStream("C:\\program\\Flights.dat"));) { //This checks if there is a flight in the file
            dis.readInt();
        } catch (EOFException ex) {
            exist = false;
        } catch (FileNotFoundException ex) {
            System.out.println("There are no flight saved");
            exist = false;
        } catch (IOException ex) {
            Logger.getLogger(BFworks.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (exist) {

            try {

                DataInputStream dis = new DataInputStream(new FileInputStream("C:\\program\\Flights.dat"));
                while (!eof) {
                    System.out.println("Flight ID: " + dis.readInt());
                    System.out.println("Duratioin: " + dis.readInt());
                    System.out.println("From: " + dis.readUTF());
                    System.out.println("To: " + dis.readUTF());
                    System.out.println("Pilot 1: " + dis.readUTF());
                    System.out.println("Pilot 2: " + dis.readUTF());
                    System.out.println("Tickets: " + dis.readInt());
                    System.out.println("Tickets sold: " + dis.readInt());
                    System.out.println("Date: " + dis.readUTF());
                    System.out.println("Price: " + dis.readFloat());
                    System.out.println("-------------");

                }
            } catch (EOFException ex) {
                eof = true;
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file");
            } catch (IOException ex) {
                System.out.println("You've typed something wrong");
            }
        }
    }

    public static void searchUser() {
        String s;
        int ask = -1;
        boolean eof;
        while (ask == -1) {
            System.out.println("Enter the DNI you want to search for: ");
            s = Read.String();
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

        try {
            FileInputStream fis = new FileInputStream("C:\\program\\Users.dat");
            DataInputStream dis = new DataInputStream(fis);
            boolean aurkitua = false;
            eof = false;
            while (!eof || aurkitua == false) {
                int dni = dis.readInt();
                String name = dis.readUTF();
                String surname = dis.readUTF();
                String birth = dis.readUTF();
                String address = dis.readUTF();
                int phone = dis.readInt();
                String username = dis.readUTF();
                String password = dis.readUTF();
                String email = dis.readUTF();
                if (ask == dni) {

                    //if both string are the same it returns 0
                    System.out.println("Dni: " + dni);
                    System.out.println("Name: " + name);
                    System.out.println("Surname: " + surname);
                    System.out.println("Birth: " + birth);
                    System.out.println("Address: " + address);
                    System.out.println("Phone: " + phone);
                    System.out.println("Username: " + username);
                    System.out.println("Password: " + password);
                    System.out.println("Email: " + email);
                    aurkitua = true;
                }

            }
            if (aurkitua == false) {
                System.out.println("There is no customer with that nan");
            }

        } catch (EOFException end) {
            eof = true;
        } catch (FileNotFoundException gaizki) {
            System.out.println("File not Found.");
        } catch (IOException gaizki) {
            System.out.println("Error: " + gaizki.toString());
        }

    }

    public static void searchFlight() {
        String s;
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

        try {
            FileInputStream fis = new FileInputStream("C:\\program\\Flights.dat");
            DataInputStream dis = new DataInputStream(fis);
            boolean aurkitua = false;
            eof = false;
            while (!eof || aurkitua == false) {
                int id = dis.readInt();
                int duration = dis.readInt();
                String from = dis.readUTF();
                String to = dis.readUTF();
                String pilot1 = dis.readUTF();
                String pilot2 = dis.readUTF();
                int tickets = dis.readInt();
                int tickets_s = dis.readInt();
                String date = dis.readUTF();
                int price = dis.readInt();
                if (ask == id) {

                    System.out.println("Flight ID: " + id);
                    System.out.println("Duration: " + duration);
                    System.out.println("From: " + from);
                    System.out.println("To: " + to);
                    System.out.println("Pilot 1: " + pilot1);
                    System.out.println("Pilot 2: " + pilot2);
                    System.out.println("Tickets: " + tickets);
                    System.out.println("Tickets sold: " + tickets_s);
                    System.out.println("Date: " + date);
                    System.out.println("Price: " + price);
                    aurkitua = true;
                }

            }
            if (aurkitua == false) {
                System.out.println("There is no flight with that id");
            }

        } catch (EOFException end) {
            eof = true;
        } catch (FileNotFoundException gaizki) {
            System.out.println("File not Found.");
        } catch (IOException gaizki) {
            System.out.println("Error: " + gaizki.toString());
        }

    }

    public static void deleteUser() {
        boolean eof = false, exist = true;
        ArrayList<User> users = new ArrayList<>();

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
        try (DataInputStream dis = new DataInputStream(new FileInputStream("C:\\program\\Users.dat"))) {

            while (!eof) { //Reads users from file and saves them in users arraylist
                User usr = new User("empty");
                usr.setDni(dis.readInt());
                usr.setName(dis.readUTF());
                usr.setSurname(dis.readUTF());
                usr.setBirth(dis.readUTF());
                usr.setAddress(dis.readUTF());
                usr.setPhone(dis.readInt());
                usr.setUsername(dis.readUTF());
                usr.setPassword(dis.readUTF());
                usr.setEmail(dis.readUTF());
                if (usr.getDni() != ask) {
                    users.add(usr);
                }else{
                    System.out.println("User found, deleting...");
                }

            }

        } catch (EOFException ex) {
            eof = true;
        } catch (FileNotFoundException ex) {
            System.out.println("Can't find file");
            exist = false;
        } catch (IOException ex) {
            System.out.println("You've typed something wrong");
        }

        if (exist) {

            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("C:\\program\\Users.dat"))) {

                for (int i = 0; i < users.size(); i++) {

                    dos.writeInt(users.get(i).getDni());
                    dos.writeUTF(users.get(i).getName());
                    dos.writeUTF(users.get(i).getSurname());
                    dos.writeUTF(users.get(i).getBirth().toString());
                    dos.writeUTF(users.get(i).getAddress());
                    dos.writeInt(users.get(i).getPhone());
                    dos.writeUTF(users.get(i).getUsername());
                    dos.writeUTF(users.get(i).getPassword());
                    dos.writeUTF(users.get(i).getEmail());

                }
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file");

            } catch (IOException ex) {
                System.out.println("You've typed something wrong");

            }
        }
    }

    public static void deleteFlight() {
        boolean eof = false, exist = true;
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
        try (DataInputStream dis = new DataInputStream(new FileInputStream("C:\\program\\Flights.dat"))) {

            while (!eof) { //Reads users from file and saves them in users arraylist
                Flight flg = new Flight("empty");
                flg.setFlightid(dis.readInt());
                flg.setDuration(dis.readInt());
                flg.setFrom(dis.readUTF());
                flg.setTo(dis.readUTF());
                flg.setPilot1(dis.readUTF());
                flg.setPilot2(dis.readUTF());
                flg.setTickets(dis.readInt());
                flg.setTickets_sold(dis.readInt());
                flg.setDate(dis.readUTF());
                flg.setPrice(dis.readFloat());
                if (flg.getFlightid() != ask) {
                    flights.add(flg);
                }else{
                    System.out.println("Flight found, deleting...");
                }

            }

        } catch (EOFException ex) {
            eof = true;
        } catch (FileNotFoundException ex) {
            System.out.println("Can't find file");
            exist = false;
        } catch (IOException ex) {
            System.out.println("You've typed something wrong");
        }

        if (exist) {
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("C:\\program\\Flights.dat"))) {

                for (int i = 0; i < flights.size(); i++) {

                    dos.writeInt(flights.get(i).getFlightid());
                    dos.writeInt(flights.get(i).getDuration());
                    dos.writeUTF(flights.get(i).getFrom());
                    dos.writeUTF(flights.get(i).getTo());

                    dos.writeUTF(flights.get(i).getPilot1());
                    dos.writeUTF(flights.get(i).getPilot2());
                    dos.writeInt(flights.get(i).getTickets());
                    dos.writeInt(flights.get(i).getTickets_sold());
                    dos.writeUTF(flights.get(i).getDate().toString());
                    dos.writeFloat(flights.get(i).getPrice());

                }
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file");

            } catch (IOException ex) {
                System.out.println("You've typed something wrong");

            }

        }
    }

    public static void backup() {
        int i;
        System.out.println("");
        System.out.println("This is going to delete previous backup, do you want to continue?: ");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        i = Read.Int();
        if (i == 1) {
            System.out.println("************");
            Path uPath = Paths.get("C:\\program\\Users.dat");
            Path uBPath = Paths.get("C:\\backup\\Users.dat");
            Path fPath = Paths.get("C:\\program\\Flights.dat");
            Path fBPath = Paths.get("C:\\backup\\Flights.dat");
            try {
                Files.copy(uPath, uBPath, REPLACE_EXISTING);
                System.out.println("Users successfully backuped to: "+uBPath);
            } catch (IOException ex) {
                System.out.println("Error with the backup of users");
            }
            try {
                Files.copy(fPath, fBPath, REPLACE_EXISTING);
                System.out.println("Flights successfully backuped to: "+fBPath);
            } catch (IOException ex) {
                System.out.println("Error with the backup of flights");
            }
            System.out.println("************");
        }
    }

    public static void restore() {
        int i;
        System.out.println("");
        System.out.println("This is going to delete current data, do you want to continue?: ");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        i = Read.Int();
        if (i == 1) {
            System.out.println("************");
            Path uPath = Paths.get("C:\\program\\Users.dat");
            Path uBPath = Paths.get("C:\\backup\\Users.dat");
            Path fPath = Paths.get("C:\\program\\Flights.dat");
            Path fBPath = Paths.get("C:\\backup\\Flights.dat");
            try {
                Files.copy(uBPath, uPath, REPLACE_EXISTING);
                System.out.println("Users successfully restored");
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
}
