/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.marcveens.encrypt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MarcVe
 */
public class Encrypt {
    private static final int N = 29747;
    private static final String MESSAGE = "Trust because you are willing to accept the risk, not because it's safe or certain. ~Anonymous";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        String message = args[1];
        long startTime = System.nanoTime();
        List<BigInteger> primeFactors = getPrimeFactors(n);
        BigInteger phi = getPhi(primeFactors.get(0), primeFactors.get(1));
        BigInteger e = getE(phi, n);

        List<Integer> asciiValues = stringToAscii(message);
        List<BigInteger> rsaEncrypted = asciiToRsa(asciiValues, n, e);
        long endTime = System.nanoTime();
        double totalSeconds = (endTime - startTime) / 1000000000.0;

        System.out.println(String.format("p is: %s", primeFactors.get(0)));
        System.out.println(String.format("q is: %s", primeFactors.get(1)));
        System.out.println(String.format("e is: %s", e));
        System.out.println(String.format("Message after encryption: %s", rsaEncrypted));
        System.out.println(String.format("Amount of seconds busy encrypting: %s", totalSeconds));
    }

    public static List<Integer> stringToAscii(String message) {
        List<Integer> asciiValues = new ArrayList<>();

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);

            asciiValues.add((int) c);
        }

        return asciiValues;
    }

    public static List<BigInteger> asciiToRsa(List<Integer> values, int pq, BigInteger e) {
        List<BigInteger> rsaValues = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            BigInteger bi = new BigInteger(values.get(i).toString());
            BigInteger bigPq = BigInteger.valueOf(pq);
            BigInteger powered = bi.pow(e.intValue());
            BigInteger restValue = powered.mod(bigPq);

            rsaValues.add(restValue);
        }

        return rsaValues;
    }

    public static List<BigInteger> getPrimeFactors(int number) {
        List<BigInteger> factors = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            while (number % i == 0) {
                factors.add(BigInteger.valueOf(i));
                number = number / i;
            }
        }
        return factors;
    }

    public static BigInteger getE(BigInteger phi, int n) {
        for (BigInteger i = BigInteger.TWO;
                i.compareTo(phi) < 0;
                i = i.add(BigInteger.ONE)) {

            if (gcd(i, phi).equals(BigInteger.ONE)
                    && gcd(i, BigInteger.valueOf(n)).equals(BigInteger.ONE)) {
                return i;
            }
        }

        return BigInteger.ONE;
    }

    public static BigInteger getPhi(BigInteger p, BigInteger q) {
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        return phi;
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return a;
        } else {
            return gcd(b, a.mod(b));
        }
    }
}
