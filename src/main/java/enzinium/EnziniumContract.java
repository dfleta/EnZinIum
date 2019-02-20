/*
package enzinium;

public class EnziniumContract {

	private Address owner = null;

	// Contract constructor: set owner
	public EnziniumContract(PublicKey owner) {
		this.owner = msg.sender;
	}

	// Contract destructor
	function destroy() public {
		require(msg.sender == owner);
		selfdestruct(owner);
	}

    // Give out ether to anyone who asks
    function withdraw(uint withdraw_amount) public {

        // Limit withdrawal amount
		require(withdraw_amount <= 0.1 ether);

        // Send the amount to the address that requested it
        msg.sender.transfer(withdraw_amount);
    }

    // Accept any incoming amount
    function () public payable {}

}*/