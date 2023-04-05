package com.esood.customer;

/**
 * Used primarily for authorization
 */
public class UserProfile {
	public static int ADMIN=99;
	public static int USER=1;
	
	private Customer customer;
	private int role;
	
	public UserProfile(int roleCode){
		this.role=roleCode;
	}

	/**
	 * @return Returns the customer.
	 */
	public Customer getCustomer()
	{
		return customer;
	}

	/**
	 * @param customer The customer to set.
	 */
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	/**
	 * @return Returns the role.
	 */
	public int getRole()
	{
		return role;
	}

	/**
	 * @param role The role to set.
	 */
	public void setRole(int role)
	{
		this.role = role;
	}
}
