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

        System.out.println("\n" + "Direccion de Rick: \n" +  rick.getPK().hashCode());

         /**
         * Visualiza la direccion de Rick
         */
        
        System.out.println("\n" + "Ver Address de Rick" + "\n" + 
                                  "==================="        );
        System.out.println(rick.toString());

        /**
         * Crea una contrato inteligente de tipo TokenContract en nuestro 
         * sistema para que Rick pueda vender 100 entradas para 
         * el concierto de "los Ricknillos". 
         *  
         * El nombre del token es Ricknillos
         * Su simbolo es RNiLL
         * 
         * El propietario del contrato es Rick. Asigna su Public Key a la 
         * propiedad owner del contrato mediante eñ contructor de TokenContract.
         * 
         * En la clase TokenContract programa las funciones a utilizar en 
         * el metodo toString():
         * 
         * name() 
         * @return devuelve el nombre del token de forma human-readable (p.e., “US Dollars”).
         * 
         * symbol()
         * @return el nombre del símbolo del token de forma human-readable (p.e., “USD”).
         * 
         * totalSupply()
         * @return el total de unidades de este token que actualmente existen.
         */

        TokenContract ricknillos = new TokenContract(rick);
        ricknillos.setName("Ricknillos");
        ricknillos.setSymbol("RNiLL");
        ricknillos.setTotalSupply(100);

        System.out.println("\n" + "Ver contrato de los Rickillos" + "\n" + 
                                  "============================="        );
        System.out.println(ricknillos.toString());



        /**
         * El contrato ToenContract it contains two data structures, one to track balances and one to track allowances. I
         * The first data mapping implements an internal table of token balances, by owner. This allows the token contract to keep track of who owns the tokens. Each transfer is a deduction from one balance and an addition to another balance:
         */



    }
}
