package evs2009;

public class MockServerPeer implements ServerPeer {

	@Override
	public Resource getResource(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransferRequest getTransferRequest(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateResource(String name, Resource resource) {
		// TODO Auto-generated method stub

	}

	@Override
	public MetaData check(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(String name, byte[] data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void login(String username, String pw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] read(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transferCancel(String token) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transferExecute(String token, MetaData info, byte[] data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transferRequest(String name, String token) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String name, byte[] data) {
		// TODO Auto-generated method stub

	}

}
