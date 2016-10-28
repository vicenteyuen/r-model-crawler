/**
 * 
 */
package org.vsg.rmodel.crawler.schema;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.vsg.cralwer.Task;

import io.protostuff.Input;
import io.protostuff.Output;
import io.protostuff.Schema;
import io.protostuff.UninitializedMessageException;

/**
 * @author ruanweibiao
 *
 */
public class TaskSchema implements Schema<Task> {

	@Override
	public String getFieldName(int number) {
	     switch(number)
	        {
	            case 1:
	                return "name";
	            case 2:
	                return "state";
	            case 3:
	                return "startTime";
	            default:
	                return null;
	        }
	}

	@Override
	public int getFieldNumber(String name) {
        Integer number = fieldMap.get(name);
        return number == null ? 0 : number.intValue();
	}

	@Override
	public boolean isInitialized(Task task) {
		return task.getName() != null;
	}

	@Override
	public void mergeFrom(Input input, Task task) throws IOException {
		// TODO Auto-generated method stub
		while (true) {
			int number = input.readFieldNumber(this);
			switch(number) {
				case 0:
					return;
				case 1:
					task.setName( input.readString() );
					break;
				case 2:
					task.setState( input.readBytes().byteAt(0) );
					break;
				case 3:
					task.setStartTime( parseToDate(input.readUInt64()) );
					break;
				default:
					input.handleUnknownField(number, this);
			}
		}
	}
	
	private Date parseToDate(long inputTime) {
		Calendar cale = Calendar.getInstance();
		cale.setTimeInMillis(inputTime);
		
		return cale.getTime();
	}

	@Override
	public String messageFullName() {
		// TODO Auto-generated method stub
		return Task.class.getName();
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return Task.class.getSimpleName();
	}

	@Override
	public Task newMessage() {
		// TODO Auto-generated method stub
		return new Task();
	}

	@Override
	public Class<? super Task> typeClass() {
		// TODO Auto-generated method stub
		return Task.class;
	}

	@Override
	public void writeTo(Output output, Task task) throws IOException {
        if(task.getName() == null)
            throw new UninitializedMessageException(task, this);
        
        output.writeString(1, task.getName(), false);
        
        byte[] stateBytes = new byte[1];
        stateBytes[0] = task.getState();
        output.writeBytes(2, ByteBuffer.wrap(stateBytes), false);
        
        output.writeUInt64(3, task.getStartTime().getTime(), false);

		
	}

	
    private static final HashMap<String,Integer> fieldMap = new HashMap<String,Integer>();    
    static
    {
        fieldMap.put("name", 1);
        fieldMap.put("state", 2);
        fieldMap.put("startTime", 3);
    }	
}
