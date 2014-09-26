package comm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Invoker {

	private static final Logger log = LoggerFactory.getLogger(Invoker.class);
	
	public HashMap<String, Object> registry = new HashMap<String, Object>();
	
	//public ProtocolPluginServer server;
	
	public Invoker(ProtocolPluginServer[] servers) throws ProtocolException {
		for (ProtocolPluginServer server : servers) {
			server.configure(this);
			server.openServer();
		}
	}

	public void register(String id, Object object) {
		registry.put(id, object);
	}
	
	public byte[] handleRequest(byte[] input)  {
		try {
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(input));
			
			String objectId = (String) ois.readObject();
			//log.debug("ObjectID:" +  objectId);
			String method = (String) ois.readObject();
			//log.debug("Method: " + method);
			Object[] params = (Object[]) ois.readObject();
			//log.debug("Params:" + params.toString());
			//String clazzName = aor.getClazzName();
			Object object = registry.get(objectId);			
			Class[] paramsClass = new Class[params.length];
			
			for (int i = 0; i < params.length; i++) {
				paramsClass[i] = params[i].getClass();
			}
			Method m = object.getClass().getMethod(method, paramsClass);
			Object result = m.invoke(object, params);
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(result);
			
			return bos.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new byte[0];
	}
}
