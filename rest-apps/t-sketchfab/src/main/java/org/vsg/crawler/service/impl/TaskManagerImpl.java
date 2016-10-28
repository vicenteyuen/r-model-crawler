package org.vsg.crawler.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.vsg.crawler.service.TaskManager;
import org.vsg.crawler.service.TaskService;

public class TaskManagerImpl implements TaskManager {
	
	/**
	 * value is not null
	 */
	@Inject
	@Named("mode")
	private String mode;
	
	
	private static final String MODE_SINGLE = "single";
	
	private static final String MODE_DISTRIBUTED = "distributed";
	
	@Inject
	@Named("SingleTaskServiceImpl")	
	private TaskService singleTaskServiceImpl;

	@Override
	public TaskService getTaskService() {
		
		TaskService taskSericeProxy = null;
		if ( mode.equalsIgnoreCase( MODE_SINGLE ) ) {
			
			// --- load local task service ---
			taskSericeProxy = singleTaskServiceImpl;
		}
		else if(mode.equalsIgnoreCase( MODE_DISTRIBUTED )) {
			
		}
		
		return taskSericeProxy;
	}

}
