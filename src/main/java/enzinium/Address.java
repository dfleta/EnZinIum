package enzinium;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Address {

    private PublicKey PK = null;
    private PrivateKey SK = null;

    public Address() {
    }

    public void setSK(PrivateKey sKey) {
        this.SK = sKey;
    }

    public PrivateKey getSK() {
        return this.SK;
    }

    public void setPK(PublicKey pKey) {
        this.PK = pKey;
    }

    public PublicKey getPK() {
        return this.PK;
    }

    public void generateKeyPair() {
        KeyPair pair = GenSig.generateKeyPair();
        this.setSK(pair.getPrivate());
        this.setPK(pair.getPublic());
    }


}