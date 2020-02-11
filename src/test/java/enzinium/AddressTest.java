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
        assertNotNull(address.isSKpresent());
    }


    @Test
    public void transferEZI_test() {

        Address rick = new Address();
        rick.generateKeyPair();

        rick.transferEZI(20d);
        rick.transferEZI(20d);

        assertEquals(40d, rick.getBalance(), 0d);
    }
}