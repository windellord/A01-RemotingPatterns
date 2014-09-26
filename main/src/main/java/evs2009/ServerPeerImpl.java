package evs2009;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import comm.socket.SocketPluginClient;

public class ServerPeerImpl implements ServerPeer {
	
	private static final Logger log = LoggerFactory
	.getLogger(SocketPluginClient.class);

	private final HashMap<String, Resource> resources = new HashMap<String, Resource>();
	private final HashMap<String, TransferRequest> transferRequests = new HashMap<String, TransferRequest>();

	// TODO: set self string
	private String self;
	private String other;
	
	private ITransferRequestManager trm;
	
	public ServerPeerImpl(String selfName, ITransferRequestManager trm) {
		this.self = selfName;
		this.trm = trm;
	}
	
	/* (non-Javadoc)
	 * @see evs2009.ServerPeerImpl#check(java.lang.String)
	 */
	@Override
	public MetaData check(String name) {
		Resource resource = resources.get(name);
		if (resource == null) {
			throw new EppErrorException(EppErrorCode.RESOURCE_NOT_FOUND, "");
		}
		return resource.getMetaData();
	}

	/* (non-Javadoc)
	 * @see evs2009.ServerPeerImpl#create(java.lang.String, byte[])
	 */
	@Override
	public void create(String name, byte[] data) {
		if (resources.containsKey(name)) {
			throw new EppErrorException(EppErrorCode.RESOURCE_EXISTS, "");
		}
		Resource resource = new Resource();
		resource.setData(data);
		resource.setId(name);
		resource.setMetaData(new MetaData(name, self, data.length));
		resources.put(name, resource);
	}

	/* (non-Javadoc)
	 * @see evs2009.ServerPeerImpl#delete(java.lang.String)
	 */
	@Override
	public void delete(String name) {

		if (!resources.containsKey(name)) {
			throw new EppErrorException(EppErrorCode.RESOURCE_NOT_FOUND, "");
		}
		// TODO: check permission
		resources.remove(name);
	}

	/* (non-Javadoc)
	 * @see evs2009.ServerPeerImpl#login(java.lang.String, java.lang.String)
	 */
	@Override
	public void login(String username, String pw) {
		// accepting everyone
	}

	/* (non-Javadoc)
	 * @see evs2009.ServerPeerImpl#logout()
	 */
	@Override
	public void logout() {
		// ignore
	}

	/* (non-Javadoc)
	 * @see evs2009.ServerPeerImpl#read(java.lang.String)
	 */
	@Override
	public byte[] read(String name) {
		Resource resource = resources.get(name);
		if (resource == null) {
			throw new EppErrorException(EppErrorCode.RESOURCE_NOT_FOUND, "");
		}
		return resource.getData();
	}

	/* (non-Javadoc)
	 * @see evs2009.ServerPeerImpl#transferCancel(java.lang.String)
	 */
	@Override
	public void transferCancel(String token) {
		//To nothing
	}

	/* (non-Javadoc)
	 * @see evs2009.ServerPeerImpl#transferExecute(java.lang.String, evs2009.MetaData, byte[])
	 */
	@Override
	public void transferExecute(String token, MetaData info, byte[] data) {
		//TODO check token!
		String name = trm.getOutgoing(token);
		if (resources.containsKey(name)) {
			throw new EppErrorException(EppErrorCode.RESOURCE_EXISTS, "");
		}
		log.debug("CREATE A NEW INSTANCE OF "+name+" ON "+self);
		Resource resource = new Resource();
		resource.setData(data);
		resource.setId(name);
		resource.setMetaData(info);
		resources.put(name, resource);
		
	}

	/* (non-Javadoc)
	 * @see evs2009.ServerPeerImpl#transferRequest(java.lang.String, java.lang.String)
	 */
	@Override
	public void transferRequest(String name, String token) {
		// to nothing
	}

	/* (non-Javadoc)
	 * @see evs2009.ServerPeerImpl#update(java.lang.String, byte[])
	 */
	@Override
	public void update(String name, byte[] data) {
		Resource resource = resources.get(name);
		if (resource == null) {
			throw new EppErrorException(EppErrorCode.RESOURCE_NOT_FOUND, "");
		}
		resource.setData(data);
		MetaData metaData = resource.getMetaData();
		metaData.setLastModifcation(new Date());
		metaData.setSize(data.length);
	}

	/* (non-Javadoc)
	 * @see evs2009.ServerPeerImpl#getTransferRequest(java.lang.String)
	 */
	public TransferRequest getTransferRequest(String identifier) {
		return this.transferRequests.get(identifier);
	}

	/* (non-Javadoc)
	 * @see evs2009.ServerPeerImpl#getResource(java.lang.String)
	 */
	public Resource getResource(String name) {
		return resources.get(name);
	}
	/* (non-Javadoc)
	 * @see evs2009.ServerPeerImpl#updateResource(java.lang.String, evs2009.Resource)
	 */
	public void updateResource(String name, Resource resource) {
		resources.put(name, resource);
	} 

}
