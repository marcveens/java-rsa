/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import nl.marcveens.encrypt.Encrypt;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.Test;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * @author MarcVe
 */
public class EncryptJUnitTest {
    private final BigInteger P = BigInteger.valueOf(197);
    private final BigInteger Q = BigInteger.valueOf(151);
    private final BigInteger N = BigInteger.valueOf(29747);
    private final BigInteger E = BigInteger.valueOf(11);
    private final String MESSAGE = "Trust because you are willing to accept the risk, not because it's safe or certain. ~Anonymous";
    private final String ENCRYPTED_MESSAGE = "25039,10674,20519,715,21302,12500,27532,19133,21127,646,20519,715,19133,12500,29128,29365,20519,12500,646,10674,19133,12500,939,4719,12738,12738,4719,7373,20826,12500,21302,29365,12500,646,21127,21127,19133,8765,21302,12500,21302,10014,19133,12500,10674,4719,715,21814,23939,12500,7373,29365,21302,12500,27532,19133,21127,646,20519,715,19133,12500,4719,21302,20529,715,12500,715,646,6750,19133,12500,29365,10674,12500,21127,19133,10674,21302,646,4719,7373,12646,12500,8356,17576,7373,29365,7373,29128,11153,29365,20519,715";

    @Test
    public void findPandQ() {
        List<BigInteger> factors = Encrypt.getPrimeFactors(N.intValue());
        
        assertThat(factors, containsInAnyOrder(P, Q));
    }
    
    @Test
    public void findE() {
        BigInteger phi = Encrypt.getPhi(P, Q);
        BigInteger e = Encrypt.getE(phi, N.intValue());
        
        assertThat(e, is(E));
    }
    
    @Test
    public void rsaEncryption() {
        List<Integer> asciiValues = Encrypt.stringToAscii(MESSAGE);
        List<BigInteger> rsaEncrypted = Encrypt.asciiToRsa(asciiValues, N.intValue(), E);
        
        assertThat(rsaEncrypted.toString(), is(Arrays.toString(ENCRYPTED_MESSAGE.split(","))));
    }
}
