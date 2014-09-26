package comm;


import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import evs2009.PeerReader;
import static org.junit.Assert.*;

public class CommunicationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSocket() throws Exception {
		
		ArrayList<ProtocolPlugin> cplugins = new ArrayList<ProtocolPlugin>();
		cplugins.add(new comm.socket.SocketPlugin(12345));
		//cplugins.add(new comm.soap.SOAPPlugin(23456));
		RequestHandler crh = new RequestHandler(cplugins);
		
		ArrayList<ProtocolPlugin> splugins = new ArrayList<ProtocolPlugin>();
		splugins.add(new comm.socket.SocketPlugin(12300));
		//splugins.add(new comm.soap.SOAPPlugin(23400));
		RequestHandler srh = new RequestHandler(splugins);
		Thread.sleep(1000);
		
		MockCommunication con =  new MockCommunication();

		srh.register("peer", con);
		PeerReader peerReader = new PeerReader("peers.csv");
		Lookup lup = new Lookup(peerReader);
		AbsoluteObjectReference aor = lup.lookup("testsocket2");
		Communication remote = (Communication) crh.getObject(aor);

		String testMsg = "Hallo";
		byte[] response = remote.invoke(testMsg.getBytes());
		
		assertEquals(testMsg, new String(response));

	}
	
	@Test
	public void testSOAP() throws Exception {
		
		ArrayList<ProtocolPlugin> cplugins = new ArrayList<ProtocolPlugin>();
		//cplugins.add(new comm.socket.SocketPlugin(12345));
		cplugins.add(new comm.soap.SOAPPlugin(23456));
		RequestHandler crh = new RequestHandler(cplugins);
		
		ArrayList<ProtocolPlugin> splugins = new ArrayList<ProtocolPlugin>();
		//splugins.add(new comm.socket.SocketPlugin(12300));
		splugins.add(new comm.soap.SOAPPlugin(23400));
		RequestHandler srh = new RequestHandler(splugins);
		Thread.sleep(1000);
		
		MockCommunication con =  new MockCommunication();

		srh.register("peer", con);

		PeerReader peerReader = new PeerReader("peers.csv");
		Lookup lup = new Lookup(peerReader);
		
		AbsoluteObjectReference aor = lup.lookup("testsoap2");
		Communication remote = (Communication) crh.getObject(aor);

		String testMsg = "Hallo";
		byte[] response = remote.invoke(testMsg.getBytes());
		
		assertEquals(testMsg, new String(response));

	}
}
