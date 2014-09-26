package comm;

public class MockBridge {

	private Invoker callback;
	
	public ProtocolPluginClient getClientSide() {
		return new ProtocolPluginClient() {
			@Override
			public void configure(String location)
			throws ProtocolException {
				// ignore
			}
			@Override
			public byte[] sendData(AbsoluteObjectReference aor, byte[] data) throws ProtocolException {
				//ignore aor
				return callback.handleRequest(data);
			}
		};
	}
	public ProtocolPluginServer getServerSide() {
		return new ProtocolPluginServer() {
			@Override
			public void configure(Invoker callback) throws ProtocolException {
				MockBridge.this.callback = callback; 
			}
			@Override
			public void openServer() throws ProtocolException {
				//ignore
			}
		};
	}

}
