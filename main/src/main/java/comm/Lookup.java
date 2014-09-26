package comm;

import evs2009.PeerReader;


public class Lookup {

	private PeerReader peerReader;
	
	public Lookup(PeerReader peerReader) {
		this.peerReader = peerReader;
	}
	
	

	public AbsoluteObjectReference lookup(String peerName, String clazzName, String objectId) {
		AbsoluteObjectReference aor = new AbsoluteObjectReference();
		aor.setEndpoints(peerReader.getEndpoints(peerName));
		aor.setObjectId(objectId);
		aor.setClazzName(clazzName);
		return aor;
	}
	
	public AbsoluteObjectReference lookup(String peerName) {
		return lookup(peerName, Communication.class.getName(), "peer");
	}
}
