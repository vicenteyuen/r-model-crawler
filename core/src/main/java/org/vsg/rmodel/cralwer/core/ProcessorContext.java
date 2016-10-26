package org.vsg.rmodel.cralwer.core;

import java.util.concurrent.locks.Lock;


/**
 * Mark The Processor handle 
 * @author Vicente Yuen
 *
 */
public interface ProcessorContext {
	
	
	LinkState getState();
	
	
	DataRepo getDataRepo();
	
	
	TraceAttr  getTraceAttr();
	
	Lock getLock();

}
