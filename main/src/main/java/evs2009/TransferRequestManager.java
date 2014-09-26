package evs2009;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferRequestManager implements Runnable, ITransferRequestManager {

	

	private static final Logger log = LoggerFactory
			.getLogger(TransferRequestManager.class);
	
	private Map<String, String> outgoing = new HashMap<String, String>();
	private LinkedBlockingQueue<TransferRequest> queue = new LinkedBlockingQueue<TransferRequest>();
	private Map<String, TransferRequest> incoming = new HashMap<String, TransferRequest>();

	
	private Lookup lookup;
	private ServerPeerImpl serverPeer;
	
	public TransferRequestManager() {}
	
	/* (non-Javadoc)
	 * @see evs2009.ITransferRequestManager#run()
	 */
	@Override
	public void run() {
		while(true) {
			try {
				TransferRequest tr = queue.take();
				Peer peer = lookup.lookup(tr.getPeer());
				String name = tr.getResource();
				Resource resource = serverPeer.getResource(name);
				peer.login("", "");
				peer.transferExecute(tr.getToken(), resource.getMetaData(), resource.getData());
				peer.logout();
				serverPeer.delete(name);
			} catch (InterruptedException e) {
				log.warn("TransferRequestManager interrupted");
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see evs2009.ITransferRequestManager#start()
	 */
	public void start() {
		new Thread(this).start();
	}
	
	/* (non-Javadoc)
	 * @see evs2009.ITransferRequestManager#putOutgoing(java.lang.String, java.lang.String)
	 */
	public synchronized void putOutgoing(String name, String token) {
		outgoing.put(token, name);
	}
	
	/* (non-Javadoc)
	 * @see evs2009.ITransferRequestManager#getOutgoing(java.lang.String)
	 */
	public synchronized String getOutgoing(String token) {
		return outgoing.get(token);
	}

	/* (non-Javadoc)
	 * @see evs2009.ITransferRequestManager#putIncoming(java.lang.String, evs2009.TransferRequest)
	 */
	public synchronized void putIncoming(String token, TransferRequest transferRequest) {
		incoming.put(token, transferRequest);
		queue.add(transferRequest);
	}


	/* (non-Javadoc)
	 * @see evs2009.ITransferRequestManager#removeIncoming(java.lang.String)
	 */
	public synchronized void removeIncoming(String token) {
		queue.remove(incoming.get(token));
		incoming.remove(token);
	}

	/* (non-Javadoc)
	 * @see evs2009.ITransferRequestManager#getLookup()
	 */
	public Lookup getLookup() {
		return lookup;
	}

	/* (non-Javadoc)
	 * @see evs2009.ITransferRequestManager#setLookup(evs2009.Lookup)
	 */
	public void setLookup(Lookup lookup) {
		this.lookup = lookup;
	}

	/* (non-Javadoc)
	 * @see evs2009.ITransferRequestManager#getServerPeer()
	 */
	public ServerPeerImpl getServerPeer() {
		return serverPeer;
	}

	/* (non-Javadoc)
	 * @see evs2009.ITransferRequestManager#setServerPeer(evs2009.ServerPeerImpl)
	 */
	public void setServerPeer(ServerPeerImpl serverPeer) {
		this.serverPeer = serverPeer;
	}
	
}
