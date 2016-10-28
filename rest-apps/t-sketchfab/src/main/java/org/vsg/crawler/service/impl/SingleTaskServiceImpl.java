/**
 * 
 */
package org.vsg.crawler.service.impl;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import org.iq80.leveldb.CompressionType;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBException;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteBatch;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.vsg.common.async.AsyncResult;
import org.vsg.common.async.Callback;
import org.vsg.common.async.DefaultAsyncResult;
import org.vsg.cralwer.Task;
import org.vsg.crawler.service.TaskService;
import org.vsg.rmodel.crawler.schema.TaskSchema;

import io.protostuff.ProtobufIOUtil;

/**
 * @author ruanweibiao
 *
 */
public class SingleTaskServiceImpl implements TaskService {
	
	@Inject
	@Named("dir.data")
	private String localDataPath;
	


	/* (non-Javadoc)
	 * @see org.vsg.rmodel.crawler0.service.TaskService#checkTaskStatus(org.vsg.common.async.Callback)
	 */
	@Override
	public void checkTaskStatus(Callback<AsyncResult<Byte>> callback , String taskName) {
		
		// --- check folder exist ---
		File dataDir = new File(localDataPath);
		
		File taskDataDir = new File(dataDir , taskName);
		
		if (!taskDataDir.exists()) {
			taskDataDir.mkdirs();
		}
		
		LevelDBTaskState taskState = new LevelDBTaskState(dataDir);
		
		byte state = taskState.getTaskStateByTaskName(taskName);
		
		
		// --- check and return call back handle ---
		DefaultAsyncResult<Byte> asyncResult = new DefaultAsyncResult<Byte>();
		asyncResult.setResult( state );
		asyncResult.setSucceeded(true);
		
		if (callback != null) {
			try {
				callback.invoke( asyncResult );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}
	
	

	

	/* (non-Javadoc)
	 * @see org.vsg.rmodel.crawler0.service.TaskService#crateTask(org.vsg.common.async.Callback)
	 */
	@Override
	public void createTask(Callback<AsyncResult<Task>> callback , String taskName) {
		
		// --- check folder exist ---
		File dataDir = new File(localDataPath);
		
		LevelDBTaskState taskState = new LevelDBTaskState(dataDir);
		

	}
	
	private class LevelDBTaskState {
		
		private File taskStateFolder;
		
		private String __taskstate = "__taskstate";		
		
		public LevelDBTaskState(File dataFolder) {
			this.taskStateFolder = new File(dataFolder , __taskstate);
			
			
			// --- check the folder ---
			if (!this.taskStateFolder.exists()) {
				this.taskStateFolder.mkdirs();
			}
			
			options = new Options();
			options.compressionType( CompressionType.SNAPPY );
			
		}
		
		
		
		public byte getTaskStateByTaskName(String taskName) {
			
			byte state = Task.STATE_NOTEXISTED;
			DB db = null;
			try {
				db = Iq80DBFactory.factory.open(taskStateFolder, options);
				
				byte[] value = db.get(taskName.getBytes());
				
				// --- parse status ---
				if (null == value) {
					return state;
				}
				
				// --- parse value to json ---
				Task task = new Task();
				TaskSchema taskSchema = new TaskSchema();
				ProtobufIOUtil.mergeFrom(value, task, taskSchema);
				
				state = task.getState();
				
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(db);
			}		

			
			return state;
		}
		
		
		public Task getTaskByTaskName(String taskName) {
			return null;
		}
		
		private Options options = new Options();
		
		private DB db;
		
		private WriteBatch batch = null;		
		
		public void open() {
			// TODO Auto-generated method stub
			
			// --- create tmp example ---
			try {
				this.db = Iq80DBFactory.factory.open(taskStateFolder, options);
				
				
				// --- create write batch ---
				batch = this.db.createWriteBatch();
				
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

			}		

			
		}		
		
		public void close(DB db) {
			// TODO Auto-generated method stub
			try {
				db.close();
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (db != null) {
					db = null;
				}
			}				
		}		
	}
	

}
