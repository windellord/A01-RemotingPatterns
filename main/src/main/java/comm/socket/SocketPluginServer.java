package comm.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import comm.Invoker;
import comm.ProtocolPluginServer;

/**
 * 
 *@author Michael Borko<michael@borko.at>, Florian Motlik<flomotlik@gmail.com>,
 *         Michael Greifeneder <mikegr@gmx.net>
 * 
 */
public class SocketPluginServer implements Runnable, ProtocolPluginServer {

	private static final Logger log = LoggerFactory
			.getLogger(SocketPluginServer.class);
	private int port;
	private Invoker invoker;

	public SocketPluginServer(int port) {
		this.port = port;
	}

	@Override
	public void configure(Invoker callback) {
		this.invoker = callback;
	}

	private final ExecutorService pool = Executors.newCachedThreadPool();

	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(port);
			while(true) {
				final Socket socket = ss.accept();

				pool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							log.debug("SRV :: Running listener on port " + port);
							 
							InputStream is = socket.getInputStream();
							OutputStream os = socket.getOutputStream();
							
							long size = SocketPlugin.readSize(is);
							byte[] output = new byte[(int) size];

							is.read(output, 0, output.length);
							
							byte[] handleRequest = invoker.handleRequest(output);
							log.info("Server sends bytes:" + handleRequest.length);
							
							os.write(String.valueOf(handleRequest.length).getBytes());
							os.write("\n".getBytes());
							//log.debug("Sends size");
							os.flush();
							//log.debug("Send flush");
							
							os.write(handleRequest);
							//log.debug("send response");
							os.flush();
							//log.debug("Send flush");
							is.close();
							os.close();
							socket.close();

						} catch (Exception e) {
							log.warn("SRV :: Client socket problem!", e);
						}
					}
				});
			}

		} catch (Exception e) {
			log.warn("ServerSocket problem", e);
		}
	}

	@Override
	public void openServer() {
		new Thread(this).start();
	}
}
