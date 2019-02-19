package enzinium;

import java.security.PublicKey;

public class TokenContract {

    private PublicKey owner = null;
    private String name = null;
    private String symbol = null;
    private int totalSupply = 0;

    public TokenContract(Address owner) {
        this.owner = owner.getPK();
    }

	public void setName(String name) {
        this.name = name;
	}

	public void setSymbol(String symbol) {
        this.symbol = symbol;
	}

	public void setTotalSupply(int totalSupply) {
        this.totalSupply = totalSupply;
    };

    public String name() {
        return this.name;
    }

    public String symbol() {
        return this.symbol;
    }

    public int totalSupply() {
        return this.totalSupply;
    }
    
    @Override
    public String toString() {
        return "\n" + "name = " + name() + "\n" + 
                      "symbol = " + symbol() + "\n" +
                      "totalSupply = " + totalSupply() + "\n" +
                      "owner = " + this.owner.hashCode() + "\n";
    }


}