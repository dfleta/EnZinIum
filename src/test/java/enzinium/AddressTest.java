package enzinium;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddressTest {

    /**
     * Test SK y PK
     */
    @Test
    public void constructor_test() {
        Address address = new Address();
        assertNotNull(address);
        assertEquals(0d, address.getBalance(), 0d);
    }

    @Test
    public void generate_key_pair_test() {
        Address address = new Address();
        assertNotNull(address);
        address.generateKeyPair();
        assertNotNull(address.getPK());
        assertNotNull(address.getSK());
    }
}