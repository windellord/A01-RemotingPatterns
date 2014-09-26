package comm.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import comm.ProtocolPlugin;
import comm.ProtocolPluginClient;
import comm.ProtocolPluginServer;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class SocketPlugin implements ProtocolPlugin {

	public int port;
	
	public SocketPlugin(int port) {
		this.port = port;
	}
	
	@Override
	public ProtocolPluginClient getClient() {
		return new SocketPluginClient();
	}
	@Override
	public ProtocolPluginServer getServer() {
		return new SocketPluginServer(port);
	}

	public static void output(byte[] b) {
		System.out.println("ByteArray:");
		for (int i = 0; i < b.length; i++) {
			System.out.print("|" + b[i]);	
		}
		System.out.println("\\end");
	}
	
	public static long readSize(InputStream is) throws IOException{
		int i = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((i =is.read())!=-1) {
			if (i == 10) {
				break;
			}
			bos.write(i);
		}
		return Long.parseLong(new String(bos.toByteArray()));
		
	}
	
}
