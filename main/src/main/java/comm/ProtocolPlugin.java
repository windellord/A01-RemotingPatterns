package comm;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public interface ProtocolPlugin {

	public int port = 0;
	public ProtocolPluginServer getServer();
	public ProtocolPluginClient getClient();
}
