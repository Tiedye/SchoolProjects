/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel;

import java.io.*;
import net.dtw.command.*;

/**
 *
 * @author Daniel
 */
public class ExitCommand extends Command {
    
    private CommandManager manager;
    
    public ExitCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    protected void doCommand(String[] args, InputStream in, PrintStream out) throws IllegalArgumentException, IllegalArgumentCountException {
        if (args.length != 0) {
            throw new IllegalArgumentCountException();
        }
        manager.stopInterpreting();
    }
}
