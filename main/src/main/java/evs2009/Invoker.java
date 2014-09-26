package evs2009;

/**
 * 
 * @author Michael Borko<michael@borko.at>, Florian Motlik<flomotlik@gmail.com>,
 *         Michael Greifeneder <mikegr@gmx.net>
 * 
 */
public interface Invoker {

	public byte[] handleRequest(byte[] data);

}
