
package com.esood.customer;

import com.esood.AbstractEntity;

/**
 * The user is primarily used for authentication
 * 
 * @author Rohit Sood
 */
public class User extends AbstractEntity{
		
	public Integer id;
	private Customer customer;
	private String userName;
	private String password;
	
	
	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the profile.
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param profile The profile to set.
	 */
	public void setCustomer(Customer customer) {
		this.customer= customer;
	}
	/**
	 * @return Returns the username.
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param username The username to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String toString(){
		return userName+", "+password;
	}
}
