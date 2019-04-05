/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigInteger;
import java.util.List;
import nl.marcveens.rsa.Decrypt;
import nl.marcveens.rsa.RsaHelper;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.Test;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * @author MarcVe
 */
public class DecryptJUnitTest {
    private final BigInteger P = BigInteger.valueOf(167);
    private final BigInteger Q = BigInteger.valueOf(157);
    private final BigInteger N = BigInteger.valueOf(26219);
    private final BigInteger D = BigInteger.valueOf(20717);
    private final BigInteger E = BigInteger.valueOf(5);
    private final String MESSAGE = "People often say that motivation doesn't last. Well, neither does bathing - that's why we recommend it daily. ~Zig Ziglar";
    private final String ENCRYPTED_MESSAGE = "1818,4599,23536,1354,22073,4599,20331,23536,13351,4932,4599,593,20331,6872,14720,16661,20331,4932,21578,14720,4932,20331,12684,23536,4932,9462,5585,14720,4932,9462,23536,593,20331,20962,23536,4599,6872,593,4620,4932,20331,22073,14720,6872,4932,12731,20331,3526,4599,22073,22073,24933,20331,593,4599,9462,4932,21578,4599,13422,20331,20962,23536,4599,6872,20331,24185,14720,4932,21578,9462,593,9893,20331,25022,20331,4932,21578,14720,4932,4620,6872,20331,25340,21578,16661,20331,25340,4599,20331,13422,4599,7009,23536,12684,12684,4599,593,20962,20331,9462,4932,20331,20962,14720,9462,22073,16661,12731,20331,22093,14134,9462,9893,20331,14134,9462,9893,22073,14720,13422";

    @Test
    public void findPandQ() {
        List<BigInteger> factors = RsaHelper.getPrimeFactors(N.intValue());
        
        assertThat(factors, containsInAnyOrder(P, Q));
    }
    
    @Test
    public void findE() {
        BigInteger phi = RsaHelper.getPhi(P, Q);
        BigInteger e = RsaHelper.getE(phi, N.intValue());
        
        assertThat(e, is(E));
    }    
    
    @Test
    public void findD() {
        BigInteger phi = RsaHelper.getPhi(P, Q);
        BigInteger e = RsaHelper.getE(phi, N.intValue());
        BigInteger d = RsaHelper.getD(phi, e);
        
        assertThat(d, is(D));
    }  
    
    @Test
    public void rsaDecryption() {
        List<BigInteger> rsaDecrypted = Decrypt.rsaToAscii(ENCRYPTED_MESSAGE.split(","), N.intValue(), D);
        String decryptedMessage = Decrypt.asciiToString(rsaDecrypted);
        
        assertThat(decryptedMessage, is(MESSAGE));
    }
}
