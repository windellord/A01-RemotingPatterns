package evs2009;

public interface ITransferRequestManager {

	public abstract void run();

	public abstract void start();

	public abstract void putOutgoing(String name, String token);

	public abstract String getOutgoing(String token);

	public abstract void putIncoming(String token,
			TransferRequest transferRequest);

	public abstract void removeIncoming(String token);

	/**
	 * @return the lookup
	 */
	public abstract Lookup getLookup();

	/**
	 * @param lookup the lookup to set
	 */
	public abstract void setLookup(Lookup lookup);

	/**
	 * @return the serverPeer
	 */
	public abstract ServerPeerImpl getServerPeer();

	/**
	 * @param serverPeer the serverPeer to set
	 */
	public abstract void setServerPeer(ServerPeerImpl serverPeer);

}