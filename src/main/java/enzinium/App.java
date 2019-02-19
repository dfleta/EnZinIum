package enzinium;

/**
 * enZinium
 *
 */
public class App 
{
     
    public static void main( String[] args )
    {
        /**
         * Crea una Address en nuestro sistema para Rick
         * Genera las claves privada y publica de la direccion
         */
        Address rick = new Address();
        rick.generateKeyPair();

        System.out.println("\n Direccion de Rick: \n" +  rick.getPK().hashCode());
    }
}
