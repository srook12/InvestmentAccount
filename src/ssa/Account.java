package ssa;

/* Basic account class. All classes at our bank have these common basic traits. Specialized
 * accounts can be added simply by extending this class. Made abstract because it makes no sense
 * to create just an Account.
 */
public abstract class Account {
	private int id;
	private String description;
		
	// Allows unique account numbers to be assigned to any account
	// nextAccountId is static so only one copy is present for ALL types of accounts
	public static final int STARTING_ACCOUNT_ID = 100;
	public static final int ACCOUNT_ID_INCREMENT = 100;
	private static int nextAccountId = STARTING_ACCOUNT_ID;
		
	public static final String DEFAULT_DESCRIPTION = "My basic account";
		
	public Account() {
		this(nextAccountId, DEFAULT_DESCRIPTION);
		nextAccountId+=ACCOUNT_ID_INCREMENT;
	}
	
	public Account(String description) {
		this(nextAccountId, description);
		nextAccountId+=ACCOUNT_ID_INCREMENT;
	}
	
	public Account(int id, String description) {
		this.id = id;
		this.description = description;
	}
		
	// Should be able to view the account number, but never to set it
	public int getId() {
		return id;
	}
	
	// Methods to get and set the account number
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
		
	public String print() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Account ").append(getId()).append(" ");
		
		return sb.toString();
	}
			
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Account Statement for ").append(description);
		sb.append("\n==================================================================\n");
		sb.append("Account id: " + getId());
		return sb.toString();
	}
}
