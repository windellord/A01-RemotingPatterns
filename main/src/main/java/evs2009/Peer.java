package evs2009;

/**
 *
 * @author Michael Borko<michael@borko.at>,
 *         Florian Motlik<flomotlik@gmail.com>,
 *         Michael Greifeneder <mikegr@gmx.net>
 *
 */
public interface Peer {

	public void login(String username, String pw);

	public void logout();

	public void create(String name, byte[] data);

	public byte[] read(String name);

	public void update(String name, byte[] data);

	public void delete(String name);

	public void transferRequest(String name, String token);

	public void transferExecute(String token, MetaData info, byte[] data);

	public void transferCancel(String token);

	public MetaData check(String name);

}