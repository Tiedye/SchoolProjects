/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.guessinggame;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import net.dtw.command.Command;
import net.dtw.command.IllegalArgumentCountException;

/**
 *
 * @author Daniel
 */
public class GuessWordCommand extends Command {

    private final StringBuilder word;

    public GuessWordCommand(StringBuilder word) {
        this.word = word;
    }

    @Override
    protected void doCommand(String[] args, InputStream in, PrintStream out) throws IllegalArgumentException, IllegalArgumentCountException {

        // check arg length
        if (args.length != 0) {
            throw new IllegalArgumentCountException();
        }
        
        // first checks is there is a word to be guessed at
        if (word.length() == 0) {
            out.println("No word loaded.");
            return;
        }

        // allows the user to guess at the word and gives hints based on their guess
        Scanner input = new Scanner(in);
        // tracks attempts and hints
        int attempts = 0;
        int hintsUsed = 0;
        Random randomGenerator = new Random(System.currentTimeMillis());
        out.println("Type !hint for a hint (counts as a attempt).  Everything else is considered a guess.");
        while (true) {
            String nextLine = input.nextLine();
            attempts++;
            if (nextLine.equalsIgnoreCase("!hint")) {
                switch (hintsUsed) {
                    // gives a different hint based on how many hints they've gotten when they ask for a hint
                    case 0:
                        out.println("The first letter is '" + word.charAt(0) + "'.");
                        break;
                    case 1:
                        out.println("The last letter is '" + word.charAt(word.length()-1) + "'.");
                        break;
                    case 2:
                        out.println("The word is " + word.length() + " letters long.");
                        break;
                    default:
                        int index = randomGenerator.nextInt(word.length());
                        out.println("The letter at position " + (index + 1) + " is '" + word.charAt(index) + "'.");
                        break;
                }
                hintsUsed++;
            } else if (nextLine.equalsIgnoreCase(word.toString())) {
                out.println("Success!  You found the word in " + attempts + (attempts == 1 ? " attempt." : " attempts."));
                return;
            } else {
                // gives how many corrent letters there were in the attempt
                int equalLetters = 0;
                StringBuilder guess = new StringBuilder(nextLine);
                for (char letter : word.toString().toCharArray()) {
                    int index = guess.toString().indexOf(letter);
                    if (index != -1) {
                        guess.delete(index, index + 1);
                        equalLetters++;
                    }
                }
                out.println("Incorrect.  But " + equalLetters + " of those letters " + (equalLetters == 1 ? "is" : "are") + " in the word.");
            }
        }
    }

}
