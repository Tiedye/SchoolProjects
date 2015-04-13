/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.couponmanager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import net.dtw.command.CommandManager;

/**
 *
 * @author Daniel
 */
public class CouponManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*Scanner in = new Scanner(System.in);
        while (true) { 
            String line = in.nextLine();
            System.out.println(new File(line).getCanonicalPath().toString());
        }*/
        CommandManager commandManager = new CommandManager(System.in, System.out);
        commandManager.registerCommand("createcouponlist", new ConstructFileCommand());
        commandManager.registerCommand("checkcoupon", new CheckCouponCommand());
        commandManager.registerCommand("exit", new ExitCommand(commandManager));
        commandManager.startInterpreting();
    }

}
