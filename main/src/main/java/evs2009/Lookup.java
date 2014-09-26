package evs2009;

/**
 *
 * @author Michael Borko <michael@borko.at> Florian Motlik <flomotlik@gmail.com>
 *         Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class Lookup {

	private comm.Lookup commLookup;
	private comm.RequestHandler rh;
	private ITransferRequestManager trm;
	private String ownName;
	
	public Lookup(String ownName, ITransferRequestManager trm, comm.Lookup clookup, comm.RequestHandler rh) {
		this.ownName = ownName;
		this.trm = trm;
		this.commLookup = clookup;
		this.rh = rh;
	}
	
	public Peer lookup( String name ) {
		comm.AbsoluteObjectReference aor = commLookup.lookup(name);
		comm.Communication remote = null;
		try {
			remote = (comm.Communication) rh.getObject(aor);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ClientPeer(ownName, remote, trm);
	}

}