/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class Hasher {
    
    private Hasher() {}
    
    private static MessageDigest digester;
    
    static {
        try {
            digester = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hasher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String hash(String string) {
        digester.update(string.getBytes());
        byte[] hash = digester.digest();
        StringBuilder output = new StringBuilder();
        for (byte currentByte:hash) {
            output.append(String.format("%02X", currentByte));
        }
        return output.toString();
    }
    
}
