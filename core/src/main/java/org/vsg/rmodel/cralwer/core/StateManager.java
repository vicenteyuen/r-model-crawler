/**
 * 
 */
package org.vsg.rmodel.cralwer.core;

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
