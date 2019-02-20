package enzinium;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenContract {

    private PublicKey owner = null;
    private Address manager = null;
    private String name = null;
    private String symbol = null;
    private double totalSupply = 0d;
    private Double totalTokensSold = 0d;

    private Map<PublicKey, Double> balances = new HashMap<>(); 

    /**
     * constructor
     */

    public TokenContract(Address owner) {
        this.manager = owner;
        this.owner = owner.getPK();
    }

    /**
     * getters y setters
     */

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

    /**
     * Override 
     */
    
    @Override
    public String toString() {
        return "\n" + "name = " + name() + "\n" + 
                      "symbol = " + symbol() + "\n" +
                      "totalSupply = " + totalSupply() + "\n" +
                      "owner = " + this.owner.hashCode() + "\n";
    }

    /**
     * logica
     */

    public void addOwner(PublicKey PK, Double units) {
        getBalances().putIfAbsent(PK, units);
    }

    public int numOwners() {
        return this.getBalances().size();
    }

    public Double balanceOf(PublicKey owner) {
        return this.getBalances().getOrDefault(owner, 0d);
    }

    public void transfer(PublicKey recipient, Double units) {
        try {
            require(balanceOf(owner) >= units);
            this.getBalances().compute(owner, (pk, tokens) -> tokens - units);
            this.getBalances().put(recipient, balanceOf(recipient) + units);
        } catch (Exception e) {
            // fails silently
        }      
    };

    public void transfer(PublicKey sender, PublicKey recipient, Double units) {
        try {
            require(balanceOf(sender) >= units);
            this.getBalances().put(sender, balanceOf(sender) - units);
            this.getBalances().put(recipient, balanceOf(recipient) + units);
        } catch (Exception e) {
            // fails silently
        }   
    }

    private void require(Boolean holds) throws Exception {
        if (! holds) {
            throw new Exception();
        }
    }

    public void owners() {
        for (PublicKey pk : this.getBalances().keySet()) {
            if (!pk.equals(this.owner)) {
                System.out.println("Owner: " + pk.hashCode() + " " 
                                             + getBalances().get(pk) + " "
                                             + this.symbol());
            }
        }
    }

    public int totalTokensSold() {
        this.getBalances().forEach((pk, units) -> this.totalTokensSold += units);
        this.totalTokensSold -= balanceOf(owner);
        return this.totalTokensSold.intValue();
    }

    public void payable(PublicKey recipient, Double enziniums) {
        Double tokenPrice = 5d; 
        try {
            require(enziniums >= tokenPrice);
            Double units = enziniums / tokenPrice;
            transfer(recipient, units);
            this.manager.transfer(enziniums);
        } catch (Exception e) {
            // fail silently
        }
    }
}