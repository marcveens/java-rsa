/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.marcveens.rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MarcVe
 */
public class Decrypt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        String message = args[1];
        long startTime = System.nanoTime();
        List<BigInteger> primeFactors = RsaHelper.getPrimeFactors(n);
        BigInteger phi = RsaHelper.getPhi(primeFactors.get(0), primeFactors.get(1));
        BigInteger e = RsaHelper.getE(phi, n);
        BigInteger d = RsaHelper.getD(phi, e);

        List<BigInteger> rsaDecrypted = rsaToAscii(message.split(","), n, d);
        String decryptedMessage = asciiToString(rsaDecrypted);

        long endTime = System.nanoTime();
        double totalSeconds = (endTime - startTime) / 1000000000.0;

        System.out.println(String.format("n is: %s", n));
        System.out.println(String.format("p is: %s", primeFactors.get(0)));
        System.out.println(String.format("q is: %s", primeFactors.get(1)));
        System.out.println(String.format("e is: %s", e));
        System.out.println(String.format("d is: %s", d));
        System.out.println(String.format("Message after decryption: %s", decryptedMessage));
        System.out.println(String.format("Amount of seconds busy decrypting: %s", totalSeconds));
    }

    public static String asciiToString(List<BigInteger> asciiValues) {
        String message = "";

        for (int i = 0; i < asciiValues.size(); i++) {
            int convertableInt = asciiValues.get(i).intValue();
            String letter = Character.toString((char) convertableInt);

            message += letter;
        }

        return message;
    }

    public static List<BigInteger> rsaToAscii(String[] values, int pq, BigInteger e) {
        List<BigInteger> asciiValues = new ArrayList<>();

        for (String value : values) {
            BigInteger bi = new BigInteger(value);
            BigInteger bigPq = BigInteger.valueOf(pq);
            BigInteger powered = bi.pow(e.intValue());
            BigInteger restValue = powered.mod(bigPq);
            asciiValues.add(restValue);
        }

        return asciiValues;
    }
}
