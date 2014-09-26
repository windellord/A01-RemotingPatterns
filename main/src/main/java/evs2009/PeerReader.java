package evs2009;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csvreader.CsvReader;

public class PeerReader {

	private Map<String, List<PeerEndpoint> > peers = new HashMap<String, List<PeerEndpoint>>();
	
	public PeerReader(String filename) {
		CsvReader reader;
		try {
			reader = new CsvReader(filename);

			reader.readHeaders();

			while (reader.readRecord()) {
				String peerID = reader.get("PeerID");
				String peerAddress = reader.get("PeerAddress");

//				System.out.println("peerID: " + peerID);
//				System.out.println("peerAddress: " + peerAddress);

				String location = peerAddress.substring(peerAddress.indexOf("::") + 2);
//				System.out.println("location: " + location);
				String protocol = peerAddress.substring(0, peerAddress.indexOf("::"));
				int lastDp = location.lastIndexOf(":");
				
				int lastSlash = location.indexOf("/", lastDp);
				
//				System.out.println("lastDP:" + lastDp + "|lastSlash:" + lastSlash);
				int port = Integer.parseInt(location.substring(lastDp+1, lastSlash == -1 ? location.length() : lastSlash));
				
				if (peers.get(peerID) == null)
					peers.put(peerID, new ArrayList<PeerEndpoint>());
				peers.get(peerID).add(new PeerEndpoint(protocol, location, port));
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public List<PeerEndpoint> getEndpoints(String peer) {
		return peers.get(peer);
	}
	
	public static class PeerEndpoint {
		private String protocol;
		private String location;
		private int port;
		
		public PeerEndpoint(String protocol, String location, int port) {
			super();
			this.protocol = protocol;
			this.location = location;
			this.port = port;
		}

		public String getProtocol() {
			return protocol;
		}

		public String getLocation() {
			return location;
		}

		public int getPort() {
			return port;
		}
		
	}
}
