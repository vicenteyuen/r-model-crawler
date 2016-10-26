/**
 * 
 */
package org.vsg.rmodel.cralwer.core;

import java.util.concurrent.locks.Lock;

/**
 * @author Vicente Yuen
 *
 */
class ProcessorContextImpl implements ProcessorContext {

	/* (non-Javadoc)
	 * @see com.ma.bi.webcralwer.ProcessorContext#getState()
	 */
	@Override
	public LinkState getState() {
		// TODO Auto-generated method stub
		return state;
	}
	
	private LinkState state;
	
	void setState(LinkState state) {
		this.state = state;
	}
	
	
	private DataRepo dataRepo;

	@Override
	public DataRepo getDataRepo() {
		// TODO Auto-generated method stub
		return dataRepo;
	}
	
	void setDataRepo(DataRepo dataRepo) {
		this.dataRepo = dataRepo;
	}
	
	
	private TraceAttr ta;

	@Override
	public TraceAttr getTraceAttr() {
		// TODO Auto-generated method stub
		return ta;
	}
	
	void setTraceAttr(TraceAttr ta) {
		this.ta = ta;
	}
	
	private Lock lock;

	@Override
	public Lock getLock() {
		// TODO Auto-generated method stub
		return lock;
	}
	
	void setLock(Lock lock) {
		this.lock = lock;
	}
	

}
