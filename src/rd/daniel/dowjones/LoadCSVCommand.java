/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.dowjones;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import net.dtw.command.Command;
import net.dtw.command.IllegalArgumentCountException;

/**
 *
 * @author Daniel
 */
public class LoadCSVCommand extends Command {

    private final StringBuilder buffer;

    public LoadCSVCommand(StringBuilder buffer) {
        this.buffer = buffer;
    }

    @Override
    protected void doCommand(String[] args, InputStream in, PrintStream out) throws IllegalArgumentException, IllegalArgumentCountException {

        // check arg length
        if (args.length != 1) {
            throw new IllegalArgumentCountException();
        }

        // check validity of filename
        File inputFile = new File(args[0]);
        try {
            // is it a valid windows filename
            inputFile.getCanonicalPath();
        } catch (IOException ex) {
            out.println("'" + args[0] + "' is not a valid filename.");
            return;
        }
        FileInputStream inputStream;
        try {
            // is it readable
            inputStream = new FileInputStream(inputFile);
        } catch (FileNotFoundException ex) {
            out.println("The file '" + inputFile.getPath() + "' could not be read.");
            return;
        }

        // reads the whole file, splits it into terms by the commas, and selects a random word from that list
        Scanner input = new Scanner(inputStream);
        if (input.hasNext()) {
            buffer.delete(0, buffer.length());
            buffer.append(input.useDelimiter("\\Z").next());
            out.println("CSV value loaded.");
        } else {
            out.println("File is empty.");
        }

    }

}
