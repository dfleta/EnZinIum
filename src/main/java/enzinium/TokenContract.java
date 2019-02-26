package enzinium;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenContract {

    private PublicKey ownerPK = null;
    private Address owner = null;
    private String name = null;
    private String symbol = null;
    private double totalSupply = 0d;
    private Double totalTokensSold = 0d;
    private Double tokenPrice = 5d; 

    private Map<PublicKey, Double> balances = new HashMap<>(); 

    /**
     * constructor
     */

    public TokenContract(Address owner) {
        this.owner = owner;
        this.ownerPK = owner.getPK();
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

    public Address owner() {
        return this.owner;
    }

    public String name() {
        return this.name;
    }

    public String symbol() {
        return this.symbol;
    }

    public double totalSupply() {
        return this.totalSupply;
    }

    public void setTokenPrice(Double tokenPrice) {
        this.tokenPrice = tokenPrice;
    }

    public Double getTokenPrice() {
        return this.tokenPrice;
    }

    public void setBalances(Map<PublicKey, Double> balances) {
        this.balances = balances;
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
                      "owner PK = " + this.ownerPK.hashCode() + "\n";
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
            require(balanceOf(ownerPK) >= units);
            this.getBalances().compute(ownerPK, (pk, tokens) -> tokens - units);
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
            if (!pk.equals(this.ownerPK)) {
                System.out.println("Owner: " + pk.hashCode() + " " 
                                             + getBalances().get(pk) + " "
                                             + this.symbol());
            }
        }
    }

    public int totalTokensSold() {
        this.getBalances().forEach((pk, units) -> this.totalTokensSold += units);
        this.totalTokensSold -= balanceOf(ownerPK);
        return this.totalTokensSold.intValue();
    }

    public void payable(PublicKey recipient, Double enziniums) {
        try {
            require(enziniums >= this.getTokenPrice());
            Double units = Math.ceil(enziniums / tokenPrice);
            transfer(recipient, units);
            this.owner.transferEZI(enziniums);
        } catch (Exception e) {
            // fail silently
        }
    }
}