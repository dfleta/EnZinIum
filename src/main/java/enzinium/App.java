package enzinium;

/**
 * Contratos inteligentes con enZinium.
 * 
 * Rick va a organizar un concieto de Los Ricknillos.
 * Quiere poner a la venta 100 entradas.
 * Va a utilizar un contrato inteligente construido 
 * sobre nuestra plataforma enZinium.
 * Este contrato recibira instrucciones de a quien vender
 * las entradas. El contrato almacenara que usuarios/as 
 * de la plataforma poseen las entradas.
 * Cada usuario/a dipone de una Address /direccion en la
 * plataforma, desde la que gestionar sus enZiniums 
 * y las entradas.
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

        /**
         * Visualiza la direccion de Rick y su balance
         */
        
        System.out.println("\n" + "Address de Rick" + "\n" + 
                                  "==============="        );
        System.out.println(rick.toString());

        /**
         * Creamos una Address en nuestro sistema para Morty
         * y otra para Jen y visualizamos su direccion y balance
         */
        
        Address morty = new Address();
        morty.generateKeyPair();

        Address jen = new Address();
        jen.generateKeyPair();

        System.out.println("\n" + "Address de Morty" + "\n" + 
                                  "==============="        );
        System.out.println(morty.toString());  
        System.out.println("\n" + "Address de Jen" + "\n" + 
                                  "==============="        );
        System.out.println(jen.toString());         

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

        System.out.println("\n" + "Contrato de los Rickillos" + "\n" + 
                                  "========================="        );
        System.out.println(ricknillos.toString());

        /**
         * El contrato TokenContract contiene una tabla de balances 
         * de token por propietario:
         * mapping(propietario => numero de unidades que posee)
         * Permite al contrato llevar el seguimiento de quien
         * posee los tokens. 
         * Cada transferencia es una deducción en un balance 
         * y una adicion en el otro.
         * 
         * Crea una tabla "balances" que mapee cada propietario 
         * (su Public Key) al numero de tokens que posee.
         * Añade a Rick con sus 100 entradas.
         * Asegurate de que si el propietario (su Public Key) 
         * ya existe en la tabla, sus unidades no se actualicen.
         * 
         * addOwner()
         * añade el propietario inicial de todos los tokens de este contrato
         * @param PublicKey del propietario
         * @param cantidad de tokens que posee
         */

        ricknillos.addOwner(rick.getPK(), ricknillos.totalSupply());
        // chequea que Rick no se actualiza a 500 unidades
        ricknillos.addOwner(rick.getPK(), 500d);
        
        /**
         * Consulta los balances
         * 
         * numOwners()
         * @return numero de propietarios registrados en la tabla balances
         * 
         * balanceOf()
         * @param PublicKey del propietario
         * @return cantidad de tokens que posee
         * Dada una direccion, devuelve su balance de tokens. Si no existe 
         * el propietario, devuelve cero.
         */
        System.out.println("\n" + "Consulta de balances" + "\n" + 
                                  "===================="        );

        System.out.println("\n" + "Numero de propietarios: " + ricknillos.numOwners());

        System.out.println("Entradas de Rick: " + ricknillos.balanceOf(rick.getPK()) 
                                                + " "
                                                + ricknillos.symbol());

        System.out.println("Entradas de Morty: " + ricknillos.balanceOf(morty.getPK())
                                                 + " "
                                                 + ricknillos.symbol());

        /**
         * Morty quiere comprarle 2 entradas a Rick
         * 
         * transfer()
         * @param PublicKey del destinatario
         * @param cantidad de tokens
         * Dada una direccion y una cantidad, transfiere esa cantidad
         * de tokens a esa direccion, desde el balance de la direccion
         * propietaria del contrato.
         * LLama a la funcion require() para comprobar si el propietario 
         * del contrato tiene suficientes tokens. Si no hay suficientes,
         * falla silenciosamente (no hace nada).
         * 
         * require(condicion)
         * @param una condicion que ha de verificarse (ser cierta)
         * lanza una excepcion si no se cumple la condicion
         */

        System.out.println("\n" + "Transferencia de entradas" + "\n" + 
                                  "========================="        );

        ricknillos.transfer(morty.getPK(), 2d);

        System.out.println("Entradas de Rick: " + ricknillos.balanceOf(rick.getPK()) 
                                                + " "
                                                + ricknillos.symbol());

        System.out.println("Entradas de Morty: " + ricknillos.balanceOf(morty.getPK())
                                                 + " "
                                                 + ricknillos.symbol());

        // chequea que require falla si no hay tokens suficientes en el balance de Rick
        ricknillos.transfer(morty.getPK(), 300d);

        System.out.println("Entradas de Morty: " + ricknillos.balanceOf(morty.getPK())
                                                 + " "
                                                 + ricknillos.symbol());
        
        // Morty vuelve a comprar un par de entradas mas
        ricknillos.transfer(morty.getPK(), 2d);
        System.out.println("Entradas de Morty: " + ricknillos.balanceOf(morty.getPK())
                                                 + " "
                                                 + ricknillos.symbol());

        /**
         * A veces, hay reventa ;)
         * 
         * Dado un remitente, un destinatario, y una cantidad, 
         * transfiere tokens de una cuenta a la otra.
         * Morty le vende 1 entrada a Jen.
         *  
         * transfer()
         * @param sender PK
         * @param recipient PK
         * @param cantidad de tokens
         */

        System.out.println("\n" + "Reventa de entradas" + "\n" + 
                                  "==================="        );
        
        ricknillos.transfer(morty.getPK(), jen.getPK(), 1d);

        System.out.println("Entradas de Morty: " + ricknillos.balanceOf(morty.getPK())
                                                 + " "
                                                 + ricknillos.symbol());
        System.out.println("Entradas de Jen: " + ricknillos.balanceOf(jen.getPK())
                                                 + " "
                                                 + ricknillos.symbol());

        /**
         * Llega el dia del concierto y Rick quiere
         * controlar el acceso a la sala de conciertos.
         * Muestra una lista de compradores con el número de entradas
         * que han adquirido (excluyendo a Rick).
         * 
         * owners()
         * @return muestra en consola la PublicKey de los compradores
         *         y el numero de tokens que han adquirido
         */

        System.out.println("\n" + "Lista de compradores" + "\n" + 
                                  "===================="        );
        ricknillos.owners();
        
        /**
         * Calcula el numero de asistentes al concierto (excluyendo a Rick),
         * es decir, el numero de entradas vendidas.
         * 
         * totalTokensSold()
         * @return numero de tokens (entradas) vendidos
         */

        System.out.println("\n" + "Total de asistentes: " + ricknillos.totalTokensSold() + "\n");

        /**
         * Vamos a comprar entradas con nuestra moneda enZinium.
         * 
         * Añade 20 enZiniums a la direccion de Morty:
         * 
         * addEZI()
         * @param cantidad de enziniums
         */

        morty.addEZI(20d);

        System.out.println("\n" + "Address de Morty" + "\n" + 
                                  "==============="        );
        System.out.println(morty.toString());

        /** 
         * Morty Compra 2 entradas a 5 enZiniums cada una:
         * envia una transferencia al contrato de ricknillo 
         * por valor de 10 enZiniums:
         * 
         * send()
         * @param contrato
         * @param enziniums
         * Esta funcion envia esa cantidad de enziniums
         * al contrato (¡si la direccion dispone de suficientes enziniums!)
         * y actualiza el balance de Morty
         * 
         * Esta funcion llama a la funcion payable del contrato:
         * 
         * payable()
         * @param direccion del receptor de los tokens /entradas
         * @param cantidad de enziniums
         * Esta funcion calcula cuantas entradas se pueden comprar 
         * con esa cantidad de enziniums y llama a la funcion
         * transfer() del contrato para actualizar los balances 
         * de Morty (el receptor) y Rick (el vendedor, propietario
         * del contrato).
         * 
         * Finalmente, la funcion payable() del contrato le envia
         * los enZinium a Rick mediante la funcion transfer() de Address:
         * 
         * transfer()
         * @param address del receptor
         * @param cantidad de enZiniums
         * Actualiza el balance de la dirección del receptor de esos
         * enZiniums
         */

        morty.send(ricknillos, 10d);
        ricknillos.owners();

        System.out.println("\n" + "Address de Rick" + "\n" + 
                                  "==============="        );
        System.out.println(rick.toString());
        System.out.println("\n" + "Address de Morty" + "\n" + 
                                  "==============="        );
        System.out.println(morty.toString());


    }
}
