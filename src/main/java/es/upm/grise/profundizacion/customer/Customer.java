package es.upm.grise.profundizacion.customer;

public class Customer {

	// Customer id
	protected int id;

	// Shipping address
	private String shippingAddress;

	// Invoicing address
	private String invoicingAddress;

	// When the customer is created, the id and addresses are loaded from the database
	Customer(int id, DBDriver dbDriver) throws CustomerException, DatabaseException {
		this.id = id;
		this.shippingAddress = dbDriver.getShippingAddress(id);
		this.invoicingAddress = dbDriver.getInvoicingAddress(id);
	}

	// Checks if the shipping address is the same than the invoicing address
	public boolean checkAddresses() throws NoAddressException, EmptyAddressException {
		if(shippingAddress == null || invoicingAddress == null)
			throw new NoAddressException();

		if(shippingAddress.length() == 0 || invoicingAddress.length() == 0)
			throw new EmptyAddressException();

		if(shippingAddress.length() != invoicingAddress.length())
			return false;

		int stringLength = shippingAddress.length();
		int index = 0;

		while(index < stringLength &&
				shippingAddress.charAt(index) == invoicingAddress.charAt(index))
			index++;

		if(index == stringLength)
			return true;
		else
			return false;
	}
}
