package evs2009;

public interface ServerPeer extends Peer {

	public abstract TransferRequest getTransferRequest(String identifier);

	public abstract Resource getResource(String name);

	public abstract void updateResource(String name, Resource resource);

}