/**
 * 
 */
package org.vsg.rmodel.cralwer.core;

import us.codecraft.webmagic.Page;

/**
 * @author ruanweibiao
 *
 */
public interface PageHandler{
	
	
	public void handle(Page page);
	
	
	void setProcessorContext(ProcessorContext  procContext);
	
	
	void setLastRequestListener(LastRequestListener listener);


}
