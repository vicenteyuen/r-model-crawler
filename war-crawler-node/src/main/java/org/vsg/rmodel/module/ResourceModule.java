package org.vsg.rmodel.module;

import javax.inject.Singleton;

import org.vsg.crawler.service.TaskManager;
import org.vsg.crawler.service.TaskService;
import org.vsg.crawler.service.impl.TaskManagerImpl;
import org.vsg.crawler.service.impl.SingleTaskServiceImpl;
import org.vsg.rmodel.tsketchfab.resource.TaskResource;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class ResourceModule extends AbstractModule {

	@Override
	protected void configure() {
		
		
		// --- set binding service ---
		this.binder().bind( TaskManager.class ).to( TaskManagerImpl.class ).in( Singleton.class );
		this.binder().bind( TaskService.class ).annotatedWith(Names.named(SingleTaskServiceImpl.class.getSimpleName())).to( SingleTaskServiceImpl.class ).in( Singleton.class );		// --- bind service ---

		this.binder().bind( TaskResource.class );

	}

}
