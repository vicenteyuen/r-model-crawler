/**
 * 
 */
package org.vsg.rmodel.tsketchfab.resource;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vsg.common.async.AsyncResult;
import org.vsg.common.async.Callback;
import org.vsg.common.i18n.I18nMesssageSupport;
import org.vsg.cralwer.Task;
import org.vsg.crawler.service.TaskManager;
import org.vsg.crawler.service.TaskService;
import org.vsg.rmodel.tsketchfab.MessageCodeConstant;
import org.vsg.vo.model.json.ResponseObj;



/**
 * @author ruanweibiao
 *
 */
@Path("/tasks")
@Consumes({
	MediaType.APPLICATION_JSON
})
public class TaskResource {
	
	private static Logger logger = LoggerFactory.getLogger( TaskResource.class );
	

	@Inject
	private I18nMesssageSupport i18n;
	
	@Inject
	private TaskManager taskManager;
		
	
	private int timeout = 5; // --- set the timeout time ---
	
	
	@GET
	@Produces("application/json")
	@Path("/{taskname}")
	public void keywordSearch(@Suspended AsyncResponse asyncResponse,
			@PathParam("taskname") String taskName) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		asyncResponse.setTimeoutHandler( new TimeoutHandler() {

			@Override
			public void handleTimeout(AsyncResponse asyResponse) {
				logger.info("reached timeout");
				asyResponse.resume( Response.ok().build() );
			}
			
		});

		// --- set time out ---
		asyncResponse.setTimeout(timeout, TimeUnit.SECONDS);
		
		
		/**
		 * create a new message to fetch the new page site when the task is not running.
		 */

		TaskService taskService = taskManager.getTaskService();
		
		
		/**
		 * check status handle ---
		 */
		taskService.checkTaskStatus(new Callback<AsyncResult<Byte>>() {

			@Override
			public void invoke(AsyncResult<Byte> result) throws Exception {
				
				if (result.succeeded()) {
					// --- handle result ---
					System.out.println("result is ok : " + result);
				}
				else {
					// --- handle execption ---
				}
				
			}
			
		} , taskName);
		
		// --- create task handle ---
		taskService.createTask( new Callback<AsyncResult<Task>>() {

			@Override
			public void invoke(AsyncResult<Task> result) throws Exception {
				
				if (result.succeeded()) {
					// --- handle result ---
					System.out.println("result is ok : " + result);
				}
				else {
					// --- handle execption ---
				}
				
			}
			
		} , taskName );
		
		//Observable<String> howdy = Observable.just("Howdy!");
		
		
		
		
		//howdy.subscribe(System.out::println);
		
		// --- call index build research index ---
		ResponseObj result = new ResponseObj();
		result.setMessage( i18n.getMessage(MessageCodeConstant.S100_0001, null) );
		result.setCode( MessageCodeConstant.S100_0001 );
		result.setLang( Locale.getDefault().toString() );
		
		
		
		Response jaxrs = Response.ok( result ).type( MediaType.APPLICATION_JSON ).build();

		asyncResponse.resume(jaxrs);	

	}		

	
}
