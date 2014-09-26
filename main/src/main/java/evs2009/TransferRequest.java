package evs2009;

public class TransferRequest {

	private String peer;
	private String resource;
	private String token;


	public TransferRequest(String peer, String resource, String token) {
		super();
		this.peer = peer;
		this.resource = resource;
		this.token = token;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPeer() {
		return peer;
	}
	public void setPeer(String peer) {
		this.peer = peer;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}

}
