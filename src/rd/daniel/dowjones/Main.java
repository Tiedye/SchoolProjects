/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.dowjones;

import java.util.ArrayList;
import net.dtw.command.CommandManager;

/**
 *
 * @author Daniel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // initialize csv storage
        // note that the convertcsvfile and create db functions could (should) 
        //  be combined into one so the whole file need not be laoded into ram
        //  simtaneously, however the assignment requested the two be separate
        ArrayList<StockRecord> loadedCSV = new ArrayList<>();
        // output instructions
        System.out.println("There are five commands available:\n"
                + " ∟ convertcsvfile [file]\n"
                + "    | Loads a csv database into\n"
                + "    ∟ memory.\n"
                + " ∟ createbinarydb [file]\n"
                + "    | Outputs the database in memory\n"
                + "    ∟ to a binary database file.\n"
                + " ∟ getindexfromfile [file] [index]\n"
                + "    | Gets the record at a certain\n"
                + "    | index in a binary database\n"
                + "    ∟ file.\n"
                + " ∟ getdatefromfile [file] [index]\n"
                + "    | Gets the record at a certain\n"
                + "    | date in a binary database\n"
                + "    ∟ file.\n"
                + " ∟ exit\n"
                + "    ∟ Exits the program.");
        CommandManager commandManager = new CommandManager(System.in, System.out);
        commandManager.registerCommand("convertcsvfile", new LoadCSVCommand(loadedCSV));
        commandManager.registerCommand("createbinarydb", new ConstuctDBCommand(loadedCSV));
        commandManager.registerCommand("getindexfromfile", new GetRecordFromIndexCommand());
        commandManager.registerCommand("getdatefromfile", new GetRecordFromDateCommand());
        commandManager.registerCommand("exit", new ExitCommand(commandManager));
        commandManager.startInterpreting();
    }
    
}
