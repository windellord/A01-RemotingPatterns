package comm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import comm.socket.SocketPlugin;

public class Requestor {

	public ProtocolPluginClient client;
	
	public Requestor(ProtocolPluginClient client) {
		super();
		this.client = client;
	}

	/** 
	 *  
	 * @param interfac Interface of Remote object
	 * @param id Identification of object
	 * @return
	 */
	public Object getObject(final AbsoluteObjectReference aor) throws ClassNotFoundException {
		client = aor.getPluginClient();
		Class clz = Class.forName(aor.getClazzName());
		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				oos.writeObject(aor.getObjectId());
				oos.writeObject(method.getName());
				oos.writeObject(args);
				oos.flush();
				
				byte[] response = client.sendData(aor, bos.toByteArray());
				
				ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(response));
				return ois.readObject();
			}
		};
		return Proxy.newProxyInstance(clz.getClassLoader(), new Class[]{clz}, handler); 
	}
	
}
