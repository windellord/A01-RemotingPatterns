package comm.soap;

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
public class SOAPPlugin implements ProtocolPlugin {

	public int port;
	
	public SOAPPlugin(int port) {
		this.port = port;
	}
	
	@Override
	public ProtocolPluginClient getClient() {
		return new SOAPPluginClient();
	}
	@Override
	public ProtocolPluginServer getServer() {
		return new SOAPPluginServer(port);
	}

}
