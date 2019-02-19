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
         * El balance de enZinium de su direccion es cero 
         */
        Address rick = new Address();
        rick.generateKeyPair();

        System.out.println("\n Direccion de Rick: \n" +  rick.getPK().hashCode());

         /**
         * Visualiza la direccion de Rick
         */
        
        System.out.println("\n" + "Ver Address de Rick" + "\n" + 
                                  "================="        );
        System.out.println("Wallet_1: \n" + rick.toString());


    }
}
