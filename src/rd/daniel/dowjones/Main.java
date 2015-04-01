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
        ArrayList<StockRecord> loadedCSV = new ArrayList<>();
        CommandManager commandManager = new CommandManager(System.in, System.out);
        commandManager.registerCommand("convertcsvfile", new LoadCSVCommand(loadedCSV));
        commandManager.registerCommand("createbinarydb", new ConstuctDBCommand(loadedCSV));
        commandManager.registerCommand("getindexfromfile", new GetRecordFromIndexCommand());
        commandManager.registerCommand("getdatefromfile", new GetRecordFromDateCommand());
        commandManager.startInterpreting();
    }
    
}
