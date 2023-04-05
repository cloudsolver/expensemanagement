
package com.esood.ui;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class RandomizerUtil {
	static Log log=LogFactory.getLog(RandomizerUtil.class);
	/**
	 * Creates an array with unique random numbers. 
	 * @param n
	 * @return
	 */
	public static int[] randomizeArray(int n)
	{
		int[] returnValue=new int[n];
		
		//populate
		for(int a=0; a<n; a++)
		{
			returnValue[a]=a;
		}
		//randomly swap
		for(int a=0; a<n*2; a++)
		{
			int r1=(int)(Math.round(Math.random()*(n-1)));
			int r2=(int)(Math.round(Math.random()*(n-1)));
			log.debug("r1="+r1+"r2="+r2);
			int temp=returnValue[r1];
			returnValue[r1]=returnValue[r2];
			returnValue[r2]=temp;
			//swap whats in position r1 & r2.
		}
		
		//Lets see
		if(log.isDebugEnabled())
		{
			for(int a=0; a<n; a++) log.debug(a+"="+returnValue[a]);
		}
		return returnValue;
	}
	
	public static String[] getPortalTypes(Integer id)
	{
		String[] returnValue=null;
		int customerId=id.intValue();
		String[][] data={
				{"A","B","C"},
				{"A","C","B"},
				{"C","B","A"},
				{"C","A","B"},
				{"B","C","A"},
				{"B","A","C"}
		};
		int selection=customerId%6;
		returnValue=data[selection];
		return returnValue;
	}
	
}
