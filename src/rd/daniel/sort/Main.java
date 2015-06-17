/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;
import net.dtw.command.CommandManager;

/**
 *
 * @author Tiedye <tiedye1@hotmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] pargs) {
        CommandManager cm = new CommandManager(System.in, System.out);
        cm.registerCommand("srtRawFile", (String[] args, InputStream in, PrintStream out) -> {
            String[] dat = new String[]{};
            try {
                dat = new Scanner(new File(args[0])).useDelimiter("\\Z").next().trim().split("\n");
            } catch (FileNotFoundException ex) {
                out.println("File not found.");
            }
            
            Sort.InsertionSort(dat);
            //Sort.HeapSort(dat);
            
            try (PrintWriter file = new PrintWriter(new File(args[0].replaceAll("^(.+)(\\.[^\\.]*)$", "$1_sorted$2")));) {
                for (String string : dat) {
                    file.println(string);
                }
                file.flush();
                out.println("File sorted.  Saved in '" + args[0].replaceAll("^(.+)(\\.[^\\.]*)$", "$1_sorted$2") + "'.");
                file.close();
            } catch (FileNotFoundException ex) {
                out.println("Could not create the file.");
            }
        });
        cm.registerCommand("srtStockFile", (String[] args, InputStream in, PrintStream out) -> {
            String[] dat = new String[]{};
            try {
                dat = new Scanner(new File(args[0])).useDelimiter("\\Z").next().trim().split("\n");
            } catch (FileNotFoundException ex) {
                out.println("File not found.");
            }
            
            StockInfo[] stocks = new StockInfo[dat.length];
            int i;
            try {
            for (i = 0; i < dat.length; i++){   
                String[] tokens = dat[i].replaceAll(",,", ",NaN,").replaceAll(",,", ",NaN,").replaceAll("NA", "NaN").split(",");
                stocks[i] = new StockInfo((long)(Double.parseDouble(tokens[0])*100), (long)(Double.parseDouble(tokens[1])*100), (long)(Double.parseDouble(tokens[2])*100), 
                        (long)(Double.parseDouble(tokens[3])*100), (long)(Double.parseDouble(tokens[4])*100), (long)(Double.parseDouble(tokens[5])*100), 
                        (long)(Double.parseDouble(tokens[6])*100));
            }
            } catch (IllegalArgumentException e) {
                out.println("File error.");
            }
            
            Sort.HeapSort(stocks);
            
            try (PrintWriter file = new PrintWriter(new File(args[0].replaceAll("^(.+)(\\.[^\\.]*)$", "$1_sorted$2")));) {
                for (StockInfo stock : stocks) {
                    file.println(stock.toString().replaceAll("NaN", "NA"));
                }
                file.flush();
                out.println("Stacks sorted.  Saved in '" + args[0].replaceAll("^(.+)(\\.[^\\.]*)$", "$1_sorted$2") + "'");
                file.close();
            } catch (FileNotFoundException ex) {
                out.println("Could not create the file.");
            }
        });
        cm.registerCommand("quit", (String[] strings, InputStream in, PrintStream stream) -> {
            System.exit(0);
        });
        System.out.println("There are three commands available:\n"
                + " ∟ srtRawFile [file]\n"
                + "    | Sorts the lines of a file as\n"
                + "    ∟ strings.\n"
                + " ∟ srtStockFile [file]\n"
                + "    | Sorts the lines of a file as \n"
                + "    ∟ stocks.\n"
                + " ∟ quit\n"
                + "    ∟ Exits the program.");
        cm.startInterpreting();
    }

}
