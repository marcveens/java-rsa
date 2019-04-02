/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsaencrypt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MarcVe
 */
public class RSAEncrypt {
    private static final int P = 197;
    private static final int Q = 151;
    private static final int E = 11;
    private static final int N = P * Q;
    private static final String Message = "Trust because you are willing to accept the risk, not because it's safe or certain. ~Anonymous";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(String.format("Encrypting message: \n%s\n", Message));
        
        List<Integer> asciiValues = stringToAscii(Message);
        List<BigInteger> rsaEncrypted = asciiToRsa(asciiValues, N, E);
        
        System.out.println(String.format("Encrypted message: \n%s\n", rsaEncrypted.toString()));
    }
    
    private static List<Integer> stringToAscii(String message) {
        List<Integer> asciiValues = new ArrayList<>();
        
        for (int i = 0; i < message.length(); i++){
            char c = message.charAt(i);   
            
            asciiValues.add((int)c);
        }
        
        return asciiValues;
    }
    
    private static List<BigInteger> asciiToRsa(List<Integer> values, int pq, int e) {
        List<BigInteger> rsaValues = new ArrayList<>();
        
        for (int i = 0; i < values.size(); i++){
            BigInteger bi = new BigInteger(values.get(i).toString());
            BigInteger bigPq = BigInteger.valueOf(pq);
            BigInteger powered = bi.pow(e);
            BigInteger restValue = powered.mod(bigPq);
            
            
            rsaValues.add(restValue);
        }
        
        return rsaValues;
    }
}
