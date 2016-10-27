/**
 * 
 */
package org.vsg.cralwer.core;


/**
 * @author ruanweibiao
 *
 */
public interface LinkState {
	
	public static byte PENDING = 0;
	
	public static byte ERROR = -1;
	
	public static byte FININED = 2;
	
	/**
	 * check the url state 
	 * @param urlStr
	 * @return
	 */
	byte getLinkState(String urlStr);
	
	
	boolean updateLinkState(String urlStr , byte state);
	
	/**
	 * update all link string
	 * @param urlStr
	 * @return
	 */
	boolean addLink(String urlStr);

}
