package comm.soap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import comm.AbsoluteObjectReference;
import comm.ProtocolException;
import comm.ProtocolPluginClient;
import comm.soap.client.CommunicationChannelService;

/**
 * 
 * @author Michael Borko<michael@borko.at>, Florian Motlik<flomotlik@gmail.com>,
 *         Michael Greifeneder <mikegr@gmx.net>
 * 
 */
public class SOAPPluginClient implements ProtocolPluginClient {

	private static final Logger log = LoggerFactory
			.getLogger(SOAPPluginClient.class);

	
	comm.soap.client.CommunicationChannel cc;
	@Override
	public void configure(String location) throws ProtocolException {
		try {
			URL url = new URL(location + "?wsdl");
			CommunicationChannelService service = new CommunicationChannelService(url);
			cc = service.getCommunicationChannelPort();
		} catch (Exception e) {
			throw new ProtocolException(e);
		}
	}

	@Override
	public byte[] sendData(AbsoluteObjectReference aor, byte[] data) throws ProtocolException {
		try {
			return cc.invoke(data);
		} catch (Exception e) {
			throw new ProtocolException(e);
		}
	}
}
