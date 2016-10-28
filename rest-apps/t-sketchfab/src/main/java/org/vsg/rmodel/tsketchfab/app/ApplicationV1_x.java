/**
 * 
 */
package org.vsg.rmodel.tsketchfab.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

import org.vsg.rest.plugins.ext.AbstractGuiceInjectApplication;
import org.vsg.rmodel.tsketchfab.resource.TaskResource;

import com.google.inject.Injector;

/**
 * @author ruanweibiao
 *
 */
@ApplicationPath("v1")
public class ApplicationV1_x extends AbstractGuiceInjectApplication {

	public ApplicationV1_x(Injector injector) {
		super(injector);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<Object> getSingletons() {
		Set<Object> objects = new HashSet<Object>();
		
		Injector injector = this.getInjector();
		
		
		
		objects.add( injector.getInstance( TaskResource.class ) );



		return objects;
	}	

}
