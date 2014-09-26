package comm;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public interface ProtocolPluginServer {

	public void configure(Invoker callback) throws ProtocolException;

	public void openServer() throws ProtocolException;

}
