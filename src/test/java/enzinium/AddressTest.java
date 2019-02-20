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

    @Test
    public void addOwner_test() {

        Address rick = new Address();
        rick.generateKeyPair();
        TokenContract ricknillos = new TokenContract(rick);
        ricknillos.addOwner(rick.getPK(), 100d);
        assertEquals(1, ricknillos.getBalances().size());

        Address morty = new Address();
        morty.generateKeyPair(); 
        ricknillos.addOwner(morty.getPK(), 0d);
        assertEquals(2, ricknillos.getBalances().size());

        assertEquals(100, ricknillos.getBalances().get(rick.getPK()), 0d);
        ricknillos.addOwner(rick.getPK(), 500d);
        assertEquals(100, ricknillos.getBalances().get(rick.getPK()), 0d);
    }

}