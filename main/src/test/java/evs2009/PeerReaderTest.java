package evs2009;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import evs2009.PeerReader.PeerEndpoint;


public class PeerReaderTest {

	@Test
	public void testParse() {
		PeerReader r = new PeerReader("peers.csv");
		List<PeerEndpoint> pe = r.getEndpoints("test3");
		assertEquals(2, pe.size());
		
		assertEquals("socket", pe.get(0).getProtocol());
		assertEquals("localhost:34567", pe.get(0).getLocation());
		assertEquals(34567, pe.get(0).getPort());
		
		assertEquals("soap", pe.get(1).getProtocol());
		assertEquals("http://localhost:34568/peer", pe.get(1).getLocation());
		assertEquals(34568, pe.get(1).getPort());
		
	}
	
}
