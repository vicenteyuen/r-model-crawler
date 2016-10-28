/**
 * 
 */
package org.vsg.crawler.service;

import org.vsg.common.async.AsyncResult;
import org.vsg.common.async.Callback;
import org.vsg.cralwer.Task;

/**
 * @author ruanweibiao
 *
 */
public interface TaskService {
	
	
	/**
	 * check the crawler status 
	 * @param callback
	 * @param taskName
	 */
	void checkTaskStatus(Callback<AsyncResult<Byte>> callback , String taskName);
	
	
	void createTask(Callback<AsyncResult<Task>> callback , String taskName);

}
