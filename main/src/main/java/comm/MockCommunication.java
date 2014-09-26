package comm;

public class MockCommunication implements Communication {

	@Override
	public byte[] invoke(byte[] request) {
		return request;
	}
}
