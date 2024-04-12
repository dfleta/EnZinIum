package enzinium;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Address {

    private PublicKey PK = null;
    private PrivateKey SK = null;
    private double balance = 0d;
    private final String symbol = "EZI";

    public Address() {
    }

    private void setSK(PrivateKey sKey) {
        this.SK = sKey;
    }

    private PrivateKey getSK() {
        return this.SK;
    }

    boolean isSKpresent() {
        return this.getSK() != null;
    }

    private void setPK(PublicKey pKey) {
        this.PK = pKey;
    }

    public PublicKey getPK() {
        return this.PK;
    }

    double getBalance() {
        return this.balance;
    }

    public void generateKeyPair() {
        KeyPair pair = GenSig.generateKeyPair();
        this.setSK(pair.getPrivate());
        this.setPK(pair.getPublic());
    }

    void transferEZI(double enziniums) {
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
        return new StringBuilder()
                .append("\nPK = ")
                .append(this.getPK().hashCode())
                .append("\nBalance = ")
                .append(this.getBalance())
                .append("\s")
                .append(this.symbol).toString();
    }
}