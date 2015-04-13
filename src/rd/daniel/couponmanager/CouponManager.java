/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.couponmanager;

import java.io.IOException;
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
        // Create the command manager and add the commands
        CommandManager commandManager = new CommandManager(System.in, System.out);
        commandManager.registerCommand("createcouponlist", new ConstructFileCommand());
        commandManager.registerCommand("checkcoupon", new CheckCouponCommand());
        commandManager.registerCommand("exit", new ExitCommand(commandManager));
        commandManager.startInterpreting();
    }

}
