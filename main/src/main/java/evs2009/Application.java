package evs2009;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import comm.ProtocolPlugin;

import evs2009.PeerReader.PeerEndpoint;

public class Application {

	private static final Logger log = LoggerFactory
			.getLogger(Application.class);
	
	private EppCommunication eppc;
	private Lookup peerLookup;

	public Application(String peerId) {

		PeerReader peerReader = new PeerReader("peers.csv");
		
		ArrayList<ProtocolPlugin> plugins = new ArrayList<ProtocolPlugin>();
		List<PeerEndpoint> endpoints = peerReader.getEndpoints(peerId);
		
		for (PeerEndpoint peerEndpoint : endpoints) {
			if (peerEndpoint.getProtocol().equals("socket")) {
				plugins.add(new comm.socket.SocketPlugin(peerEndpoint.getPort()));	
			}
			else if (peerEndpoint.getProtocol().equals("soap")) {
				plugins.add(new comm.soap.SOAPPlugin(peerEndpoint.getPort()));
			}
				
		}
		try {
			ITransferRequestManager trm = new TransferRequestManager();
			trm.start();
			comm.Lookup commLookup = new comm.Lookup(peerReader);
			comm.RequestHandler rh = new comm.RequestHandler(plugins);
			this.eppc = new EppCommunication(peerId, trm); 
			
			rh.register("peer", this.eppc);
			this.peerLookup = new Lookup(peerId, trm, commLookup, rh);
			trm.setLookup(peerLookup);

		} catch (Exception e) {
			log.debug("",e);
		}
	}

	public EppCommunication getEppCommunication() {
		return eppc;
	}
	
	public Lookup getPeerLookup() {
		return peerLookup;
	}

	public void run() {
		peerLookup.lookup("testsocket2").login("testsocket1", "pw");
	}

	public static void main(String[] args) {
		log.info("Starting application");
		if (args.length < 1) {
			System.out.println("Usage Application <namelist> <peerId>");
		}
		new Application(args[0]).run();
		log.info("Application finished");
	}
}
