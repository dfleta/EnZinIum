package enzinium;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TokenContractTest {

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

    @Test
    public void balanceOf_test() {
        
        Address rick = new Address();
        rick.generateKeyPair();
        TokenContract ricknillos = new TokenContract(rick);
        ricknillos.addOwner(rick.getPK(), 100d);
        assertEquals(1, ricknillos.getBalances().size());

        Address morty = new Address();
        morty.generateKeyPair(); 

        assertEquals(100d, ricknillos.balanceOf(rick.getPK()), 0d);
        // chequeo getOrDefault(PK, 0d) para direcciones que no existen
        assertEquals(0d, ricknillos.balanceOf(morty.getPK()), 0d);
    }

    @Test
    public void transfer_test() {

        Address rick = new Address();
        rick.generateKeyPair();
        TokenContract ricknillos = new TokenContract(rick);
        ricknillos.addOwner(rick.getPK(), 100d);
        assertEquals(1, ricknillos.getBalances().size());

        Address morty = new Address();
        morty.generateKeyPair();

        ricknillos.transfer(morty.getPK(), 2d);
        assertEquals(2d, ricknillos.balanceOf(morty.getPK()), 0d);
        assertEquals(98d, ricknillos.balanceOf(rick.getPK()), 0d);

        // require falla silenciosamente
        ricknillos.transfer(morty.getPK(), 500d);
        assertEquals(2d, ricknillos.balanceOf(morty.getPK()), 0d);
        
    }
}