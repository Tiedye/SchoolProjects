/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;
import net.dtw.command.Command;
import net.dtw.command.IllegalArgumentCountException;

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
        out.println("Type up to 10 words to be added to the file or type stop to finish writing to the file.");
        StringBuilder newCSV = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            // add up to ten words
            String nextLine = input.nextLine();
            if (nextLine.equals("stop")) {
                break;
            }
            newCSV.append(nextLine).append(",");
        }
        // write the csv to the file and close the file
        outputWriter.print(newCSV);
        outputWriter.close();
        out.println("Word list '" + outputFile.getPath() + "' created.");

    }

}
