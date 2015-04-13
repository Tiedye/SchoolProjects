/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.couponmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import net.dtw.command.Command;
import net.dtw.command.IllegalArgumentCountException;

/**
 *
 * @author Daniel
 */
public class CheckCouponCommand extends Command {

    @Override
    protected void doCommand(String[] args, InputStream in, PrintStream out) throws IllegalArgumentException, IllegalArgumentCountException {
        
        // check arg length
        if (args.length != 2) {
            throw new IllegalArgumentCountException();
        }
        
        // check validity of filename
        File inputFile = new File(args[1]);
        try {
            // is it a valid windows filename
            inputFile.getCanonicalPath();
        } catch (IOException ex) {
            out.println("'" + args[1] + "' is not a valid filename.");
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
        
        // hash coupon and check for a match in the file
        String hashedCoupon = Hasher.hash(args[0]);
        for (String hash : new Scanner(inputStream).useDelimiter("\\Z").next().split(",")) {
            if (hashedCoupon.equals(hash)) {
                out.println("Coupon found.");
                return;
            }
        }
        out.println("Coupon not found.");
    }

}
