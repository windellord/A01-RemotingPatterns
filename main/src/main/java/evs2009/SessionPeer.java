package evs2009;

/**
 * This class is an interceptor for Peer, providing session and transaction
 * management.
 * 
 * @author Michael Borko<michael@borko.at>, Florian Motlik<flomotlik@gmail.com>,
 *         Michael Greifeneder <mikegr@gmx.net>
 * 
 */
public class SessionPeer implements Peer {

	private ServerPeer localPeer;
	private TransactionManager transactionManager;
	private String token;
	private boolean loggedIn = false;
	private String username;

	public SessionPeer(TransactionManager tm, String token, ServerPeer localPeer) {
		this.localPeer = localPeer;
		this.transactionManager = tm;
		this.token = token;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#check(java.lang.String)
	 */
	@Override
	public MetaData check(String name) {
		checkLogin();
		return localPeer.check(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#create(java.lang.String, byte[])
	 */
	@Override
	public void create(final String name, final byte[] data) {
		checkLogin();
		CreateAction createAction = new CreateAction(transactionManager, username, name,
				data);
		createAction.action();
		transactionManager.addAction(token, createAction);
	}

	private static class CreateAction implements Action {

		private String name;
		String owner;
		private byte[] data;
		private Peer peer;
		private TransactionManager tm;
		private MetaData updatedMetaData;

		public CreateAction(TransactionManager tm, String owner, String name, byte[] data) {
			this.peer = tm.getPeer();
			this.name = name;
			this.owner = owner;
			this.data = data;
			this.tm = tm;
		}

		@Override
		public void action() {
			tm.lock(name);
			try {
				peer.create(name, data);
				updatedMetaData = peer.check(name);
				updatedMetaData.setOwner(owner);
				updatedMetaData.setLastModifier(owner);

			} finally {
				tm.unlock(name);
			}
		}

		@Override
		public void rollback() {
			if (updatedMetaData == null) {
				return;
			}
			if (!updatedMetaData.equals(peer.check(name))) {
				throw new EppErrorException(EppErrorCode.ROLLBACK_FAILED,
						"Resource modified meanwhile");
			}
			peer.delete(name);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#delete(java.lang.String)
	 */
	@Override
	public void delete(String name) {
		checkLogin();
		DeleteAction action = new DeleteAction(transactionManager, name);
		transactionManager.addAction(token, action);
		action.action();
	}

	private static class DeleteAction implements Action {

		private String name;
		private ServerPeer peer;
		private TransactionManager tm;
		private Resource resource;

		public DeleteAction(TransactionManager tm, String name) {
			super();
			this.name = name;
			this.peer = tm.getPeer();
			this.tm = tm;
		}

		@Override
		public void action() {
			try {
				tm.lock(name);
				resource = peer.getResource(name);
				peer.delete(name);
			} finally {
				tm.unlock(name);
			}
		}

		@Override
		public void rollback() {
			try {
				tm.lock(name);
				if (peer.getResource(name) != null) {
					throw new EppErrorException(EppErrorCode.ROLLBACK_FAILED,
							"Resource meanwhile modified");
				}
				peer.updateResource(name, resource);
			} finally {
				tm.unlock(name);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#login(java.lang.String, java.lang.String)
	 */
	@Override
	public void login(String username, String pw) {
		localPeer.login(username, pw);
		this.username = username; 
		loggedIn = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#logout()
	 */
	@Override
	public void logout() {
		this.localPeer.logout();
		loggedIn = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#read(java.lang.String)
	 */
	@Override
	public byte[] read(String name) {
		checkLogin();
		// TODO: Do we need lock? transactionManager.lock(name);
		return localPeer.read(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#transferCancel(java.lang.String)
	 */
	@Override
	public void transferCancel(String token) {
		checkLogin();
		localPeer.transferCancel(token);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#transferExecute(java.lang.String, evs2009.MetaData,
	 * byte[])
	 */
	@Override
	public void transferExecute(String token, MetaData info, byte[] data) {
		checkLogin();
		
		localPeer.transferExecute(token, info, data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#transferRequest(java.lang.String, java.lang.String)
	 */
	@Override
	public void transferRequest(String name, String token) {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#update(java.lang.String, byte[])
	 */
	@Override
	public void update(String name, byte[] data) {
		UpdateAction action = new UpdateAction(transactionManager, username, name, data);
		transactionManager.addAction(token, action);
		action.action();
	}

	private static class UpdateAction implements Action {
		private TransactionManager tm;
		private String name;
		private String modifier;

		private Resource resource;
		private byte[] newData;
		private ServerPeer peer;

		public UpdateAction(TransactionManager tm, String modifier, String name, byte[] newData) {
			super();
			this.tm = tm;
			this.peer = tm.getPeer();
			this.name = name;
			this.modifier = modifier;
			this.newData = newData;
		}

		@Override
		public void action() {
			tm.lock(name);
			try {
				resource = peer.getResource(name);
				peer.update(name, newData);
				peer.check(name).setLastModifier(modifier);
				
			} finally {
				tm.unlock(name);
			}

		}

		@Override
		public void rollback() {
			tm.lock(name);
			try {
				if (!resource.getMetaData().equals(peer.check(name))) {
					throw new EppErrorException(EppErrorCode.ROLLBACK_FAILED,
							"Resource modified meanwhile");
				}
				peer.updateResource(name, resource);
			} finally {
				tm.unlock(name);
			}
		}
	}

	public void checkLogin() {
		if (!loggedIn)
			throw new EppErrorException(EppErrorCode.NOT_LOGGED_IN);
	}
}
