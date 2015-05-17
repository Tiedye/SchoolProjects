/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("File path?");
            String fileName = in.nextLine();
            String[] dat = new String[]{};
            try {
                dat = new Scanner(new File(fileName)).useDelimiter("\\Z").next().trim().split("\r\n");
            } catch (FileNotFoundException ex) {
                System.err.println("File not found.");
            }
            Sort.BubbleSort(dat);
            try (PrintWriter out = new PrintWriter(new File(fileName.replaceAll("^(.+)(\\.[^\\.]*)$", "$1_sorted$2")));) {
                for (String string : dat) {
                    out.println(string);
                }
                out.flush();
                System.out.println("File lines sorted.");
                out.close();
            } catch (FileNotFoundException ex) {
                System.err.println("Could not create the file.");
            }
        }
    }

}
