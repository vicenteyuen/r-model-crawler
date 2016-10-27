/**
 * 
 */
package org.vsg.cralwer.core;

/**
 * Data repository setting 
 * @author Vicente Yuen
 *
 */
public interface DataRepo {
	
	
	void open();

	/**
	 * append defined record
	 * @param record
	 */
	void addOne(String... record);
	
	
	void commit(ResultCallback resultCallback);
	
	
	void close();
	
}
