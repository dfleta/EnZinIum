package enzinium;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Address {

    private PublicKey PK = null;
    private PrivateKey SK = null;
    private double balance = 0d;
    private String symbol = "EZI";

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

    public double getBalance() {
        return this.balance;
    }

    public void generateKeyPair() {
        KeyPair pair = GenSig.generateKeyPair();
        this.setSK(pair.getPrivate());
        this.setPK(pair.getPublic());
    }

    public void transferEZI(double enziniums) {
        this.balance += enziniums;
    }

    public void send(TokenContract contract, Double enziniums) {
        if (enziniums <= this.balance) {
            contract.payable(getPK(), enziniums);
            this.balance -= enziniums;
        }
    }

    @Override
    public String toString() {
        return "\n" + "PK = " + getPK().hashCode() + "\n" + 
                      "Balance = " + getBalance() + " " 
                      + this.symbol + "\n";
    }

}