package comm;

public class MockPlugin implements ProtocolPlugin {

	private ProtocolPluginClient client;
	private ProtocolPluginServer server;
	
	@Override
	public ProtocolPluginClient getClient() {
		return client;
	}
	@Override
	public ProtocolPluginServer getServer() {
		return server;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(ProtocolPluginClient client) {
		this.client = client;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(ProtocolPluginServer server) {
		this.server = server;
	}
}
