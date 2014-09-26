package comm;

import java.util.List;

public class RequestHandler {

	List<ProtocolPlugin> plugins;
	Invoker invoker;
	Requestor requestor;

	public RequestHandler(List<ProtocolPlugin> plugins) throws ProtocolException {
		this.plugins = plugins;
		// invoker = new Invoker(plugin.getServer());
		int i = 0;
		ProtocolPluginServer[] pluginServer = new ProtocolPluginServer[plugins.size()];
		
		for (ProtocolPlugin protocolPlugin : plugins) {
			pluginServer[i] = protocolPlugin.getServer();
			i++;
		}
		invoker = new Invoker(pluginServer);
		// requestor = new Requestor(plugin.getClient());
	}

	public void register(String id, Object object) {
		invoker.register(id, object);
	}

	public Object getObject(AbsoluteObjectReference aor)
			throws ClassNotFoundException {
		requestor = new Requestor(aor.getPluginClient());
		return requestor.getObject(aor);
	}
}
