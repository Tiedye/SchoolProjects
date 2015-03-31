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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import net.dtw.command.Command;
import net.dtw.command.IllegalArgumentCountException;

/**
 *
 * @author Daniel
 */
public class ConstuctDBCommand extends Command {

    private final ArrayList<StockRecord> records;

    public ConstuctDBCommand(ArrayList<StockRecord> records) {
        this.records = records;
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

        try {
            for (StockRecord record : records) {
                record.write(output);
            }
        } catch (IOException ex) {
            out.println("Could not write to the file '" + outputFile.getAbsolutePath() + "'.");
        }

    }

}
