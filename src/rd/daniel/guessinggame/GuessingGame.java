/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.guessinggame;

import net.dtw.command.CommandManager;

/**
 *
 * @author Daniel
 */
public class GuessingGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // initialise variable to store the selected word to guess
        StringBuilder word = new StringBuilder();
        // output instructions
        System.out.println("There are four commands available:\n"
                + " ∟ createwordlist [file]\n"
                + "    | Allows the user to input words\n"
                + "    ∟ that are saved into a file.\n"
                + " ∟ loadword [file]\n"
                + "    | Loads a random word from an"
                + "    ∟ file of csv.\n"
                + " ∟ guessword\n"
                + "    ∟ Starts the guessing game.\n"
                + " ∟ exit\n"
                + "    ∟ Exits the program.");
        // initialize the command manager
        CommandManager commandManager = new CommandManager(System.in, System.out);
        commandManager.registerCommand("createwordlist", new ConstructFileCommand());
        commandManager.registerCommand("loadword", new LoadWordCommand(word));
        commandManager.registerCommand("guessword", new GuessWordCommand(word));
        commandManager.registerCommand("exit", new ExitCommand(commandManager));
        commandManager.startInterpreting();
    }
    
}
