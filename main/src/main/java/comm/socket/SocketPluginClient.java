package comm.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import comm.AbsoluteObjectReference;
import comm.ProtocolException;
import comm.ProtocolPluginClient;
import evs2009.mapping.Check;

/**
 * 
 * @author Michael Borko<michael@borko.at>, Florian Motlik<flomotlik@gmail.com>,
 *         Michael Greifeneder <mikegr@gmx.net>
 * 
 */
public class SocketPluginClient implements ProtocolPluginClient {

	private static final Logger log = LoggerFactory
			.getLogger(SocketPluginClient.class);

	private String peer;
	int port;
	private Socket socket;
	private InputStream is;
	private OutputStream os;

	@Override
	public void configure(String location) throws ProtocolException {
		String[] splitted = location.split(":");
		peer = splitted[0];
		port = Integer.parseInt(splitted[1]);
		log.info("SPC :: Configuring clientpeer plugin socket to host: "
						+ peer + " " + "port: " + port);
	}

	@Override
	public byte[] sendData(AbsoluteObjectReference aor, byte[] data)
			throws ProtocolException {
		openSocket();
		try {
			
			os.write(String.valueOf(data.length).getBytes());
			os.write("\n".getBytes());
			// log.debug("CLNT :: Size: " + data.length + " Data: " + data);

			os.write(data);
			os.flush();

			long size = SocketPlugin.readSize(is);
			log.info("Client receives bytes:" + size);
			byte[] output = new byte[4096];

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			int i = 0;
			long read = 0;

			while ((i = is.read(output)) != -1) {
				log.debug("Got bytes" + i);
				read += i;
				log.debug("Read is " + read);
				bos.write(output, 0, i);
				if (read >= size) {
					break;
				}
			}
			return bos.toByteArray();

		} catch (Exception e) {
			throw new ProtocolException(e);
		}
		finally {
			try {
				os.close();
				is.close();
				socket.close();
				
			} catch (Exception e2) {
				log.info("",e2);
			}
		}
	}
	
	private void openSocket() {
		try {
			socket = new Socket(peer, port);
			is = socket.getInputStream();
			os = socket.getOutputStream();
		} catch (Exception e) {
			throw new ProtocolException(e);
		}
	}
}
