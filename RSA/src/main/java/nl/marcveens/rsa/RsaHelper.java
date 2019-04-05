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
public class RsaHelper {

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

    public static BigInteger getD(BigInteger phi, BigInteger e) {
        for (BigInteger i = BigInteger.ONE;
                i.compareTo(phi) < 0;
                i = i.add(BigInteger.ONE)) {

            if (e.multiply(i).mod(phi).equals(BigInteger.ONE)) {
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
