package enzinium;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;

class GenSig {

    /**
     * Genera el par de clave publica PK
     * y clave privada SK
     * La clave pública PK es la dirección pública de la Wallet
     * La clave privada SK es necesaria para firmar los mensajes 
     */
    static KeyPair generateKeyPair() {

        try {

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);
            KeyPair pair = keyGen.generateKeyPair();
            return pair;
        
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Firma el mensaje que acompaña la transaccion
     * mediante la clave privada de la wallet que 
     * envia los EnZinIums.
     */
    static byte[] sign(PrivateKey sKey, String message) {
        
        try {

            // Indicate the message digest algorithm: SHA-1
            Signature signDsa = Signature.getInstance("SHA1withDSA", "SUN");
            signDsa.initSign(sKey);
            signDsa.update(message.getBytes());
            // firma de los datos
            byte[] realSig = signDsa.sign();
            return realSig;

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Verifica que la firma del mensaje que acompaña la transaccion
     * es autentica.
     * Recibe el mensaje, el mensaje firmado, y la clave publica
     * que corresponde a la clave privada con la que se firmo 
     * el mensaje.
     */
    static boolean verify(PublicKey pubKey, String message, byte[] signedMessage) {
        
        try {
            // importar la clave publica
            Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
            sig.initVerify(pubKey);
            
            // importar el mensaje
            sig.update(message.getBytes());

            // importar la firma
            boolean verifies = sig.verify(signedMessage);
            return verifies;

        } catch (Exception e) {
            return false;
        }
    }
}