/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binaryfiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DM3-1-20
 */
public class Read {
//    public static Object object(){
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String s = null;
//        Object a;
//        try {
//            s = reader.readLine();
//            a = String.toObject();
//        } catch (IOException ex) {
//            Logger.getLogger(read.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public static int Int() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        int a = 0;
        boolean x;
        do {
            x = false;
            try {
                s = reader.readLine();
                a = Integer.parseInt(s);
            } catch (Exception Integer) {
                System.out.println("You typed something wrong, try again");
                System.out.println("");
                x = true;
            }
        } while (x == true);
        return a;
    }

    public static float Float() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        float a = 0;
        boolean x;
        do {
            x = false;
            try {
                s = reader.readLine();
                a = Float.parseFloat(s);
            } catch (Exception Float) {
                System.out.println("You typed something wrong, try again");
                System.out.println("");
                x = true;
            }
        } while (x == true);
        return a;
    }

    public static double Double() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        double a = 0;
        boolean x;
        do {
            x=false;
            try {
                s = reader.readLine();
                a = Double.parseDouble(s);
            } catch (Exception Double) {
                System.out.println("You typed something wrong, try again");
                System.out.println("");
                x = true;
            }
        } while (x == true);
        return a;
    }

    public static String String() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String a = null;
        try {
            a = reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
}
