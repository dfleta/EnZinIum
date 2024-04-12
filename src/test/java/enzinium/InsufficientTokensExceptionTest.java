package enzinium;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InsufficientTokensExceptionTest {

    private Address rick = null;
    private TokenContract contract = null;

    @Before
    public void setupContract() {
        rick = new Address();
        rick.generateKeyPair();
        contract = new TokenContract(rick);
    }
    
    @Test(expected = InsufficientTokensException.class)
    public void InsufficientTokensExceptionThrowTest() throws InsufficientTokensException {
        contract.require(false);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void InsufficientTokensExceptionMessageTest() throws InsufficientTokensException {
        exceptionRule.expect(InsufficientTokensException.class);
        exceptionRule.expectMessage("No dispones de tokens suficientes para completar la transaccion.");
        contract.require(false);
    }
}
