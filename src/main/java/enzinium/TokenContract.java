package enzinium;

public class TokenContract {

    private String name = null;
    private String symbol = null;
    private int totalSupply = 0;

    public TokenContract() {
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
                      "TotalSupply = " + totalSupply() + "\n";
    }
}