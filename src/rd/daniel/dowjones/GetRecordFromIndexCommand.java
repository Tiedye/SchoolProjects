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
import net.dtw.command.Command;
import net.dtw.command.IllegalArgumentCountException;

/**
 *
 * @author Daniel
 */
public class GetRecordFromIndexCommand extends Command {

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
        // get the record and print it, catchin user errors
        StockRecord record;
        try {
            record = nav.getRecord(Integer.parseInt(args[1]));
        } catch (IndexOutOfBoundsException ex) {
            out.println("Index out of bounds.");
            return;
        } catch (NumberFormatException ex) {
            out.println("'" + args[1] + "' is not a valid index.");
            return;
        }
        
        out.println(record);
        
    }
    
}
