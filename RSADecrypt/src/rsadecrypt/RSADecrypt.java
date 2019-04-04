/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsadecrypt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MarcVe
 */
public class RSADecrypt {
    private static final String encryptedString = "1818,4599,23536,1354,22073,4599,20331,23536,13351,4932,4599,593,20331,6872,14720,16661,20331,4932,21578,14720,4932,20331,12684,23536,4932,9462,5585,14720,4932,9462,23536,593,20331,20962,23536,4599,6872,593,4620,4932,20331,22073,14720,6872,4932,12731,20331,3526,4599,22073,22073,24933,20331,593,4599,9462,4932,21578,4599,13422,20331,20962,23536,4599,6872,20331,24185,14720,4932,21578,9462,593,9893,20331,25022,20331,4932,21578,14720,4932,4620,6872,20331,25340,21578,16661,20331,25340,4599,20331,13422,4599,7009,23536,12684,12684,4599,593,20962,20331,9462,4932,20331,20962,14720,9462,22073,16661,12731,20331,22093,14134,9462,9893,20331,14134,9462,9893,22073,14720,13422";
    private static int E = 5;
    private static int N = 26219;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(String.format("Decrypting message: \n%s\n", encryptedString));
        
        List<BigInteger> rsaDecrypted = rsaToAscii(encryptedString.split(","), N, E);
//        List<Integer> asciiValues = stringToAscii(asciiValues, N, E);
        
System.out.println(rsaDecrypted.toString());
        
        
//        System.out.println(String.format("Decrypted message: \n%s\n", rsaEncrypted.toString()));
    }
    
        private static List<Integer> stringToAscii(String message) {
        List<Integer> asciiValues = new ArrayList<>();
        
        for (int i = 0; i < message.length(); i++){
            char c = message.charAt(i);   
            
            asciiValues.add((int)c);
        }
        
        return asciiValues;
    }
    
    private static List<BigInteger> rsaToAscii(String[] values, int pq, int e) {
        List<BigInteger> asciiValues = new ArrayList<>();
        
        for (int i = 0; i < values.length; i++){
            BigInteger bi = new BigInteger(values[i]);
            BigInteger bigPq = BigInteger.valueOf(pq);
            BigInteger powered = bi.pow(e);
            BigInteger restValue = powered.mod(bigPq);
            
            asciiValues.add(restValue);
        }
        
        return asciiValues;
    }
}
