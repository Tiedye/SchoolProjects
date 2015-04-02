/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.guessinggame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import net.dtw.command.Command;
import net.dtw.command.IllegalArgumentCountException;

/**
 *
 * @author Daniel
 */
public class LoadWordCommand extends Command {

    private final StringBuilder word;

    public LoadWordCommand(StringBuilder word) {
        this.word = word;
    }

    @Override
    protected void doCommand(String[] args, InputStream in, PrintStream out) throws IllegalArgumentException, IllegalArgumentCountException {

        // check arg length
        if (args.length != 1) {
            throw new IllegalArgumentCountException();
        }

        // check validity of filename
        Path inputFile;
        try {
            // is it a valid windows filename
            inputFile = Paths.get(args[0]);
        } catch (InvalidPathException ex) {
            out.println("'" + args[0] + "' is not a valid path.");
            return;
        }
        FileInputStream inputStream;
        try {
            // is it readable
            inputStream = new FileInputStream(inputFile.toString());
        } catch (FileNotFoundException ex) {
            out.println("The file '" + inputFile.toString() + "' could not be read.");
            return;
        }

        // reads the whole file, splits it into terms by the commas, and selects a random word from that list
        Scanner input = new Scanner(inputStream);
        if (input.hasNext()) {
            String[] words = input.useDelimiter("\\Z").next().split(",");
            word.delete(0, word.length());
            word.append(words[new Random(System.currentTimeMillis()).nextInt(words.length)]);
            out.println("Word selected.");
            
        } else {
            out.println("File is empty.");
        }

    }

}
