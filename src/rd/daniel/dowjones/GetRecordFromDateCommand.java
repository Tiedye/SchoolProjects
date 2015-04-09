/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.dowjones;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import net.dtw.command.Command;
import net.dtw.command.IllegalArgumentCountException;

/**
 *
 * @author Daniel
 */
public class GetRecordFromDateCommand extends Command {

    @Override
    protected void doCommand(String[] args, InputStream in, PrintStream out) throws IllegalArgumentException, IllegalArgumentCountException {

        // check arg length
        if (args.length != 2) {
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
        DBNavigator nav;
        try {
            // is it readable
            nav = new DBNavigator(inputFile.getAbsolutePath());
        } catch (IOException ex) {
            out.println("The file '" + inputFile.getPath() + "' could not be read.");
            return;
        }

        // try a whole bunch of date formats that can be interpreted
        LocalDate date;
        try {
            date = LocalDate.parse(args[1], DateTimeFormatter.ofPattern("d-MM-uu"));
            // correct the date if only two number given for the date
            if (date.getYear() > 2025) {
                date = date.minusYears(100);
            }
        } catch (DateTimeParseException ex) {
            try {
                date = LocalDate.parse(args[1], DateTimeFormatter.ofPattern("d-MM-uuuu"));
            } catch (DateTimeParseException ex1) {
                try {
                    date = LocalDate.parse(args[1], DateTimeFormatter.ofPattern("d-MMM-uu"));
                    // correct the date if only two number given for the date
                    if (date.getYear() > 2025) {
                        date = date.minusYears(100);
                    }
                } catch (DateTimeParseException ex2) {
                    try {
                        date = LocalDate.parse(args[1], DateTimeFormatter.ofPattern("d-MMM-uuuu"));
                    } catch (DateTimeParseException ex3) {
                        try {
                            date = LocalDate.parse(args[1], DateTimeFormatter.ofPattern("d-MMMM-uu"));
                            // correct the date if only two number given for the date
                            if (date.getYear() > 2025) {
                                date = date.minusYears(100);
                            }
                        } catch (DateTimeParseException ex4) {
                            try {
                                date = LocalDate.parse(args[1], DateTimeFormatter.ofPattern("d-MMMM-uuuu"));
                            } catch (DateTimeParseException ex5) {
                                try {
                                    date = LocalDate.parse(args[1], DateTimeFormatter.ISO_LOCAL_DATE);
                                } catch (DateTimeParseException ex6) {
                                    out.println("'" + args[1] + "' is not a date in the correct format (d-m-y or yyyy-mm-dd).");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // find the index of the given date
        int index = Collections.binarySearch(nav, date.getYear() * 10000 + date.getMonthValue() * 100 + date.getDayOfMonth());

        if (index < 0) {
            out.println("'" + args[1] + "' was not found in the file.");
            return;
        }
        
        StockRecord record = nav.getRecord(index);

        // print that record
        out.println(record);

    }

}
