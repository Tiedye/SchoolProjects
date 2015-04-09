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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import net.dtw.command.Command;
import net.dtw.command.IllegalArgumentCountException;

/**
 *
 * @author Daniel
 */
public class LoadCSVCommand extends Command {

    private final ArrayList<StockRecord> records;

    public LoadCSVCommand(ArrayList<StockRecord> records) {
        this.records = records;
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
        String fileData;
        if (input.hasNext()) {
            fileData = input.useDelimiter("\\Z").next();
            input.close();

            String[] rawRecords = fileData.toString().split("\n");
            
            records.clear();
            
            try {
                for (String record : rawRecords) {
                    String[] feilds = record.split(",");
                    if (feilds[0].equals("Date")) {
                        continue;
                    }

                    LocalDate date = LocalDate.parse(feilds[0], DateTimeFormatter.ofPattern("d-MMM-uu"));
                    if (date.getYear() > 2025) {
                        date = date.minusYears(100);
                    }

                    int condensedDate = date.getYear() * 10000 + date.getMonthValue() * 100 + date.getDayOfMonth();
                    int open = (int) (Double.parseDouble(feilds[1]) * 100);
                    int high = (int) (Double.parseDouble(feilds[2]) * 100);
                    int low = (int) (Double.parseDouble(feilds[3]) * 100);
                    int close = (int) (Double.parseDouble(feilds[4]) * 100);
                    long volume = Long.parseLong(feilds[5]);
                    int adjClose = (int) (Double.parseDouble(feilds[6]) * 100);

                    records.add(new StockRecord(condensedDate, open, high, low, close, volume, adjClose));

                }
            }catch(Exception e){
                out.println("Invalid file format:\n  " + e.toString());
            }
            
            out.println("CSV file loaded.");
        } else {
            out.println("File is empty.");
        }

    }

}
