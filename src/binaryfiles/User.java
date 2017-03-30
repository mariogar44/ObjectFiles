package binaryfiles;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

public class User extends Person implements Serializable {

    private String username;
    private String password;
    private String email;

    public User() {
        super();
        setUsername();
        setPassword();
        setEmail();

    }

    public User(int d, String n, String s, String a, int p, String UserName, String Password, String Email) {
        super(d, n, s, a, p);
        username = UserName;
        password = Password;
        email = Email;
    }

    public User(String s) {
        super(s);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String NewUsername) {
        username = NewUsername;
    }

    public void setUsername() {
        String s = "";
        while (s.isEmpty()) {
            System.out.println("Enter the username: ");
            s = Read.String();
            if (s.contains(" ")) {
                System.out.println("Your username can't contain spaces");
                s = "";
            }
        }
        username = s;
    }

    public void setPassword(String NewPassword) {
        password = NewPassword;
    }

    public void setPassword() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the password: ");
        password = Read.String();
    }

    public void setEmail(String NewEmail) {
        email = NewEmail;
    }

    public void setEmail() {
        String s = "";
        while (s.isEmpty()) {
            System.out.println("Enter your email: ");
            s = Read.String();
            if (s.contains("@")) {
                if (s.contains(".com")||s.contains(".es")) {
                    email = s;
                }else{
                    System.out.println("Your email must contain .com or .es");
                    s = "";
                }
            }else{
                System.out.println("Your email must contain @domain.com or @domain.es");
                s = "";
            }
        }
    }
}
