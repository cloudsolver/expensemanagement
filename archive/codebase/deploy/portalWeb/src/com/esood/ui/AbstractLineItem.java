/*
 * Created on Sep 8, 2005
 */
package com.esood.ui;


/**
 * This is a web form with some special characteristics to support line items.
 * 
 * @author Rohit
 */
public abstract class AbstractLineItem extends WebForm
{
	static final long serialVersionUID=12348641321L;
	private boolean selected;
	private String id;

	/**
	 * @return Returns the selected.
	 */
	public boolean isSelected()
	{
		return selected;
	}

	/**
	 * @param selected The selected to set.
	 */
	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	
	public boolean equals(AbstractLineItem that)
	{
		if(that==null || this.id==null){
			return false;
		}
		
		if(this==that){
			return true;
		} 
		
		if(this.id.equals(that.id))
		{
			return true;
		}
		log.warn("Returning false for some unknown condition");
		return false;
	}
	
	
}
