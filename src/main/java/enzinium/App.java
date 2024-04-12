package enzinium;

/**
 * Contratos inteligentes con enZinium.
 * 
 * Rick va a organizar un concieto de Los Ricknillos
 * y quiere poner a la venta 100 entradas en nuestra
 * plataforma enZinium.
 * Para ello va a utilizar un contrato inteligente 
 * construido sobre nuestra plataforma.
 * Este contrato recibira instrucciones sobre a quien vender
 * las entradas. El contrato almacenara que usuarios/as 
 * de la plataforma poseen las entradas.
 * Cada usuario/a dipone de una Address /direccion en la
 * plataforma, desde la que gestionar sus enZiniums 
 * y las entradas.
 */

public class App {
     
    public static void main( String[] args )
    {
        /**
         * Crea una Address en nuestro sistema para Rick
         * Genera las claves privada y publica de la direccion
         * El balance de enZinium de su direccion es cero
         * El simbolo del enZinium es EZI
         */

        Address rick = new Address();
        rick.generateKeyPair();

        /**
         * Visualiza la direccion publica de Rick y su balance
         * 
         * Utiliza el metodo hashCode() de PublicKey para 
         * mostrar la PublicKey en consola 
         */
        
        System.out.println("\n" + "Address de Rick" + "\n" + 
                                  "==============="        );
        System.out.println(rick.toString());

        /**
         * Creamos una Address en nuestro sistema para Morty
         * y otra para Jen y visualizamos su direccion puvlica
         * y balance
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
         * Crea una contrato inteligente de tipo TokenContract 
         * en nuestro sistema para que Rick pueda vender 100 
         * entradas para el concierto de "los Ricknillos". 
         *  
         * El nombre del token (las entradas) es Ricknillos.
         * Su simbolo es RNiLL.
         * 
         * El propietario del contrato es Rick. Asigna su Public Key
         * a la propiedad owner del contrato mediante el contructor
         * de TokenContract.
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
        ricknillos.setTokenPrice(5d);

        System.out.println("\n" + "Contrato de los Ricknillos" + "\n" + 
                                  "=========================="        );
        System.out.println(ricknillos.toString());

        /**
         * El contrato TokenContract contiene una tabla de balances 
         * de token por propietario:
         * mapping(propietario => numero de unidades que posee)
         * Permite al contrato llevar el seguimiento de quien
         * posee las entradas. 
         * Cada transferencia de entradas entre propietarios es 
         * una deducción en un balance y una adicion en el otro.
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
        // verifica que Rick no se actualiza una vez que ya existe en el balance
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

        System.out.println("Entradas de Rick: " 
                                + ricknillos.balanceOf(rick.getPK()) 
                                + " "
                                + ricknillos.symbol());

        System.out.println("Entradas de Morty: " 
                                + ricknillos.balanceOf(morty.getPK())
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
         * propietaria del contrato (la de Rick en este caso).
         * 
         * LLama a la funcion require() para comprobar si el propietario 
         * del contrato dispone de suficientes tokens. Si no hay suficientes,
         * falla silenciosamente (no hace nada) y no modifica los balances.
         * 
         * require()
         * @param una condicion que ha de verificarse (ser cierta)
         * Lanza una EXCEPCION si no se cumple la condicion
         */

        System.out.println("\n" + "Transferencia de entradas" + "\n" + 
                                  "========================="        );

        ricknillos.transfer(morty.getPK(), 2d);

        System.out.println("Entradas de Rick: " 
                                + ricknillos.balanceOf(rick.getPK()) 
                                + " "
                                + ricknillos.symbol());

        System.out.println("Entradas de Morty: " 
                                + ricknillos.balanceOf(morty.getPK())
                                + " "
                                + ricknillos.symbol());

        // verifica que require falla si no hay tokens suficientes en el balance de Rick
        ricknillos.transfer(morty.getPK(), 300d);

        System.out.println("Rick no tiene 300 entradas => entradas de Morty: " 
                                + ricknillos.balanceOf(morty.getPK())
                                + " "
                                + ricknillos.symbol());
        
        // Morty vuelve a comprar un par de entradas mas
        ricknillos.transfer(morty.getPK(), 2d);

        System.out.println("2 entradas mas para Morty: " 
                                + ricknillos.balanceOf(morty.getPK())
                                + " "
                                + ricknillos.symbol());

        /**
         * A veces, hay reventa ;)
         * 
         * Morty le vende 1 entrada a Jen.
         * 
         * Dado un remitente, un destinatario, y una cantidad, 
         * se transfieren tokens de una direccion a la otra.
         *   
         * transfer()
         * @param sender PK
         * @param recipient PK
         * @param cantidad de tokens
         */

        System.out.println("\n" + "Reventa de entradas" + "\n" + 
                                  "==================="        );
        
        ricknillos.transfer(morty.getPK(), jen.getPK(), 1d);

        System.out.println("Entradas de Morty: " 
                                + ricknillos.balanceOf(morty.getPK())
                                + " "
                                + ricknillos.symbol());
        System.out.println("Entradas de Jen: " 
                                + ricknillos.balanceOf(jen.getPK())
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
        System.out.println(ricknillos.owners());
        
        /**
         * Calcula el numero de asistentes al concierto (excluyendo a Rick),
         * es decir, el numero de entradas vendidas.
         * 
         * totalTokensSold()
         * @return numero de tokens (entradas) vendidos
         */

        System.out.println("\n" + "Total de asistentes: " 
                                    + ricknillos.totalTokensSold() + "\n");

        
        /**
         * AHORA A POR EL EXCEL·LENT !!
         * CAN DO !!
         */

        /**
         * Vamos a comprar entradas con nuestra moneda enZinium.
         * 
         * Añade 20 enZiniums a la direccion de Morty:
         * 
         * transferEZI()
         * @param address del receptor
         * @param cantidad de enZiniums
         * Añade la cantidad de EZi al balance de EZI en
         * la dirección del receptor.
         */

        morty.transferEZI(20d);

        System.out.println("\n" + "Recarga eZis de Morty" + "\n" + 
                                  "====================="        );
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
         * La funcion send() llama a la funcion payable() del contrato:
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
         * los enZinium al propietario del contrato (Rick) mediante
         * la funcion transfer() de Address:
         * 
         * transferEZI()
         * @param address del receptor
         * @param cantidad de enZiniums
         * Actualiza el balance de EZI en la dirección del receptor
         * de esos enZiniums
         */

        morty.send(ricknillos, 10d);

        System.out.println(ricknillos.owners());
        System.out.println("Owner: " + rick.getPK().hashCode() + " " 
                                     + ricknillos.balanceOf(rick.getPK())
                                     + " "
                                     + ricknillos.symbol());

        System.out.println("\n" + "Address de Rick" + "\n" + 
                                  "==============="        );
        System.out.println(rick.toString());
        System.out.println("\n" + "Address de Morty" + "\n" + 
                                  "==============="        );
        System.out.println(morty.toString());

        // verifica que Morty no tiene dinero para comprar una entrada
        morty.send(ricknillos, 3d);

        System.out.println("Morty no paga suficientes EZI por una entrada" +
                            "\n => sigue teniendo 5 entradas: " 
                                + ricknillos.balanceOf(morty.getPK())
                                + " "
                                + ricknillos.symbol());

        System.out.println("\n" + "y Rick no recibe EZI => sigue con 10: " 
                                        + rick.getBalance()
                                        + "\n");

        System.out.println("y Rick sigue con sus 94 entradas: " 
                                            + ricknillos.balanceOf(rick.getPK())
                                            + " "
                                            + ricknillos.symbol());
    }
}
