package binaryfiles;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Person implements Serializable {

    private int dni;
    private String name;
    private String surname;
    private LocalDate birth;
    private String address;
    private int phone;

    public Person() {
        setDni();
        setName();
        setSurname();
        setBirth();
        setAddress();
        setPhone();
    }

    public Person(int d, String n, String s, String a, int p) {
        dni = d;
        name = n;
        surname = s;
        setBirth();
        address = a;
        phone = p;
    }

    public Person(String s) {

    }

    public int getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public void setDni(int a) {
        dni = a;
    }

    public void setDni() {
        boolean ok = false;
        while (!ok) {
            System.out.println("Enter your DNI: ");
            String s = Read.String();
            if (s.length() == 8) {
                try {
                    dni = Integer.parseInt(s);
                    ok = true;
                } catch (Exception ex) {
                    System.out.println("You've typed something wrong");

                    ok = false;
                }
            } else {
                System.out.println("Your DNI must have 8 digits");
                ok = false;
            }
        }
    }

    public void setName(String NewName) {
        name = NewName;
    }

    public void setName() {
        String s = "";
        while (s.isEmpty()) {
            System.out.println("Enter your name: ");
            s = Read.String();
            if (s.isEmpty()) {
                System.out.println("You must type something");
            }
            

            for (int i = 0; i < 10 && !s.isEmpty(); i++) {
                if (s.contains(String.valueOf(i))) {
                    System.out.println("Your name can't contain numbers");
                    s = "";
                }
            }
        }

        name = s;
    }

    public void setSurname(String NewSurname) {
        surname = NewSurname;
    }

    public void setSurname() {

        String s = "";
        while (s.isEmpty()) {
            System.out.println("Enter your surname: ");
            s = Read.String();
            if (s.isEmpty()) {
                System.out.println("You must type something");
            }

            for (int i = 0; i < 10 && !s.isEmpty(); i++) {
                if (s.contains(String.valueOf(i))) {
                    System.out.println("Your surname can't contain numbers");
                    s = "";
                }
            }
        }

        surname = s;
    }

    public void setBirth(LocalDate NewBirth) {
        birth = NewBirth;
    }

    public void setBirth() {
        String s;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y M d", Locale.ENGLISH);

        birth = null;
        while (birth == null) {

            System.out.println("Enter your birth date (YYYY MM DD): ");
            try {
                s = reader.readLine();
                birth = LocalDate.parse(s, formatter);
                if (birth.getYear() < LocalDate.now().getYear() - 100) {
                    birth = null;
                    System.out.println("You are too old");
                } else if (birth.isAfter(LocalDate.now())) {
                    System.out.println("Are you a time traveler?");
                    birth = null;
                }

            } catch (Exception ex) {
                System.out.println("Wrong date format");
                birth = null;
            }

        }
    }

    public void setBirth(String brth) { //This is used for setting the birth from a file
        String s = brth.replace("-", " ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y M d", Locale.ENGLISH);

        birth = LocalDate.parse(s, formatter);

    }

    public void setAddress(String NewAddress) {
        address = NewAddress;
    }

    public void setAddress() {

        System.out.println("Enter the address: ");
        address = Read.String();
    }

    public void setPhone(int NewPhone) {
        phone = NewPhone;
    }

    public void setPhone() {
        boolean ok = false;
        while (!ok) {
            System.out.println("Enter your phone number: ");
            String s = Read.String();
            if (s.length() == 9) {
                try {
                    phone = Integer.parseInt(s);
                    ok = true;
                } catch (Exception ex) {
                    System.out.println("You've typed something wrong");
                    ok = false;
                }
            } else {
                System.out.println("Your number must have 9 digits");
            }
        }
    }

}
