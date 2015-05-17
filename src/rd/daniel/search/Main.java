/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;

/**
 *
 * @author Tiedye <tiedye1@hotmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("File path?");
        String fileName = in.nextLine();
        String[] dat = new String[]{};
        // read the lines of a file into an array of strings
        try {
            dat = new Scanner(new File(fileName)).useDelimiter("\\Z").next().trim().split("\r\n");
        } catch (FileNotFoundException ex) {
            System.err.println("File not found.");
        }
        while (true) {
            // searches for the specified string 10000 times using each algorithim and times the total execution time of each algorithim.
            System.out.println("String?");
            String searchString = in.nextLine();
            int pos;
            long t1 = System.nanoTime();
            for (int i = 0; i < 10000; i++) {
                pos = Search.BinarySearch(dat, searchString);
            }
            long t2 = System.nanoTime();
            for (int i = 0; i < 10000; i++) {
                pos = Search.LinearSearch(dat, searchString);
            }
            long t3 = System.nanoTime();
            System.out.println((t2 - t1) / 1000000000.0);
            System.out.println((t3 - t2) / 1000000000.0);
        }
    }

}
