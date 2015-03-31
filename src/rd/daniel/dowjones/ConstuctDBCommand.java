/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.dowjones;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import net.dtw.command.Command;
import net.dtw.command.IllegalArgumentCountException;

/**
 *
 * @author Daniel
 */
public class ConstuctDBCommand extends Command {

    private final StringBuilder buffer;

    public ConstuctDBCommand(StringBuilder buffer) {
        this.buffer = buffer;
    }

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
        DataOutputStream output;
        try {
            // is it writeable
            output = new DataOutputStream(new FileOutputStream(outputFile));
        } catch (FileNotFoundException ex) {
            out.println("The file '" + outputFile.getPath() + "' could not be created/written to.");
            return;
        }

        String[] records = buffer.toString().split("\n");

        for (String record : records) {
            String[] feilds = record.split(",");
            if (feilds[0].equals("Date")) {
                continue;
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d-M-u");
            LocalDate date = LocalDate.parse(feilds[0], dateFormatter);
            if(date.getYear() > 2025){
                date.minusYears(100);
            }
            
        }

    }

}
