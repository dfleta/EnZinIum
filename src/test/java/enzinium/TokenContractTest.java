package enzinium;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TokenContractTest {

    private Address rick = null;
    private Address morty = null;
    private TokenContract ricknillos = null;

    @Before
    public void setup_address_y_contract() {

        rick = new Address();
        rick.generateKeyPair();
        ricknillos = new TokenContract(rick);
        ricknillos.addOwner(rick.getPK(), 100d);
        assertEquals(1, ricknillos.getBalances().size());

        ricknillos.setTokenPrice(5d);

        morty = new Address();
        morty.generateKeyPair(); 
    }

    @Test
    public void addOwner_test() {

        ricknillos.addOwner(morty.getPK(), 0d);
        assertEquals(2, ricknillos.getBalances().size());

        assertEquals(100, ricknillos.getBalances().get(rick.getPK()), 0d);
        ricknillos.addOwner(rick.getPK(), 500d);
        assertEquals(100, ricknillos.getBalances().get(rick.getPK()), 0d);
    }

    @Test
    public void balanceOf_test() {

        assertEquals(100d, ricknillos.balanceOf(rick.getPK()), 0d);
        // chequeo getOrDefault(PK, 0d) para direcciones que no existen
        assertEquals(0d, ricknillos.balanceOf(morty.getPK()), 0d);
    }

    @Test
    public void transfer_test() {

        ricknillos.transfer(morty.getPK(), 2d);
        assertEquals(2d, ricknillos.balanceOf(morty.getPK()), 0d);
        assertEquals(98d, ricknillos.balanceOf(rick.getPK()), 0d);

        // require falla silenciosamente
        ricknillos.transfer(morty.getPK(), 500d);
        assertEquals(2d, ricknillos.balanceOf(morty.getPK()), 0d);   
    }

    @Test
    public void payable_test() {

        morty.transferEZI(20d);

        // verifico la transferencia de entradas
        ricknillos.payable(morty.getPK(), morty.getBalance());
        assertEquals(4d, ricknillos.balanceOf(morty.getPK()), 0d);   
        // verifico la trasnferencia de EZI
        assertEquals(20d, ricknillos.owner().getBalance(), 0d);

        // sin EZI suficiente
        ricknillos.payable(morty.getPK(), 4d);
        assertEquals(4d, ricknillos.balanceOf(morty.getPK()), 0d);
        assertEquals(20d, ricknillos.owner().getBalance(), 0d);

        // intento de compra de media entrada
        ricknillos.payable(morty.getPK(), 8d);
        assertEquals(5d, ricknillos.balanceOf(morty.getPK()), 0d);
        assertEquals(28d, ricknillos.owner().getBalance(), 0d);
    }
}