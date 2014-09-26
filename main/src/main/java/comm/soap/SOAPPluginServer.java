package comm.soap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import comm.Invoker;
import comm.ProtocolPluginServer;
import comm.socket.SocketPluginServer;

/**
 *
 *@author Michael Borko<michael@borko.at>, Florian Motlik<flomotlik@gmail.com>,
 *        Michael Greifeneder <mikegr@gmx.net>
 * 
 */
@WebService
public class SOAPPluginServer implements ProtocolPluginServer {

	private static final Logger log = LoggerFactory
			.getLogger(SOAPPluginServer.class);
	private int port;
	private Invoker invoker;
	
	public SOAPPluginServer(int port) {
		this.port = port;
	}

	@Override
	public void configure(Invoker callback) {
		this.invoker = callback;
	}

	@Override
	public void openServer() {
		Endpoint.publish("http://localhost:"+port+"/peer", new CommunicationChannel(invoker));
	}
	
	public static void main(String[] args) {
		new SOAPPluginServer(8080).openServer();
	}
}
