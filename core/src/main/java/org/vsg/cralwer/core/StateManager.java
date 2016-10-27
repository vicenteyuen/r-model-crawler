/**
 * 
 */
package org.vsg.cralwer.core;

/**
 * @author ruanweibiao
 *
 */
public interface StateManager {
	
	/**
	 * create link schema
	 * @param scheme
	 * @return
	 */
	LinkState getLinkStateByScheme(String scheme);

}
