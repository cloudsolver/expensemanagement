/*
 * Created on May 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.esood;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The abstract entity for all entities.
 * @author Rohit Sood
 */
public abstract class AbstractEntity implements Entity {
	
	protected Log log= LogFactory.getLog(this.getClass());
	private Integer id;
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	
	public String toString()
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append("\nid="+id);
		return buffer.toString();
	}
}
