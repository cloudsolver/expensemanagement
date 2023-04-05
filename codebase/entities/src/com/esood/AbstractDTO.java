/*
 * Created on Aug 21, 2005
 */
package com.esood;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractDTO implements Serializable, Comparable
{
	protected ContextDTO userContextDTO;

	protected Log log = LogFactory.getLog(this.getClass());

	protected Integer id;

	/**
	 * @return Returns the userContextDTO.
	 */
	public ContextDTO getUserContextDTO()
	{
		return userContextDTO;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @param userContextDTO
	 *            The userContextDTO to set.
	 */
	public void setUserContextDTO(ContextDTO userContextDTO)
	{
		this.userContextDTO = userContextDTO;
	}

	public int compareTo(Object obj)
	{
		if (obj == null)
		{
			return -1;
		}
		
		if (obj instanceof AbstractDTO)
		{
			AbstractDTO that = (AbstractDTO) obj;
			if (this.getId() != null && null != that.getId())
			{
				return (this.getId().compareTo(that.getId()));
			}
		}
		
			return 0;
		

	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nUserContextDTO:" + userContextDTO + "\n");
		return buffer.toString();
	}
}
