package enzinium;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenContract {

    private PublicKey owner = null;
    private String name = null;
    private String symbol = null;
    private double totalSupply = 0d;

    private Map<PublicKey, Double> balances = new HashMap<>(); 

    public TokenContract(Address owner) {
        this.owner = owner.getPK();
    }

	public void setName(String name) {
        this.name = name;
	}

	public void setSymbol(String symbol) {
        this.symbol = symbol;
	}

	public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    };

    public String name() {
        return this.name;
    }

    public String symbol() {
        return this.symbol;
    }

    public double totalSupply() {
        return this.totalSupply;
    }

    public Map<PublicKey, Double> getBalances() {
        return this.balances;
    }

    public void addOwner(PublicKey PK, Double units) {
        getBalances().putIfAbsent(PK, units);
    }

    public int numOwners() {
        return this.getBalances().size();
    }

    public Double balanceOf(Address owner) {
        return this.getBalances().get(owner.getPK());
    }

    public void transfer(Address recipient, Double units) {
       // require(balanceOf(owner) >= units);
    };
    
    @Override
    public String toString() {
        return "\n" + "name = " + name() + "\n" + 
                      "symbol = " + symbol() + "\n" +
                      "totalSupply = " + totalSupply() + "\n" +
                      "owner = " + this.owner.hashCode() + "\n";
    }


}