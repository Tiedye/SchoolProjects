/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel;

import java.io.*;
import java.util.Scanner;
import net.dtw.command.*;

/**
 *
 * @author Daniel
 */
public class ConstructFileCommand extends Command {

    @Override
    protected void doCommand(String[] args, InputStream in, PrintStream out) throws IllegalArgumentException, IllegalArgumentCountException {
        
        // check arg length
        
        if (args.length != 1) {
            throw new IllegalArgumentCountException();
        }
        
        // check validity of filename
        
        File outputFile = new File(args[0]);
        try {
            // is it a valid windows filename
            outputFile.getCanonicalPath();
        } catch (IOException ex) {
            out.println("'" + args[0] + "' is not a valid filename.");
            return;
        }
        PrintWriter outputWriter;
        try {
            // is it writeable
            outputWriter = new PrintWriter(outputFile);
        } catch (FileNotFoundException ex) {
            out.println("The file '" + outputFile.getPath() + "' could not be created/written to.");
            return;
        }
        
        // construct string of comma separated values from user input
        
        Scanner input = new Scanner(in);
        StringBuilder newCSV = new StringBuilder();
        while(true) {
            String nextLine = input.nextLine();
            if (nextLine.equals("stop")) break;
            // hash coupons before adding to string
            newCSV.append(Hasher.hash(nextLine)).append(",");
        }
        // write the csv to the file and close the file
        outputWriter.print(newCSV);
        outputWriter.close();
        out.println("Coupon list '" + outputFile.getPath() + "' created.");
    }
    
}
