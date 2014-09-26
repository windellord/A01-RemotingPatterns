package evs2009;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import comm.Communication;

import evs2009.mapping.Epp;
import evs2009.mapping.MessageCreator;

public class ClientPeer implements Peer {

	

	private static final Logger log = LoggerFactory.getLogger(ClientPeer.class);
	
	private final Communication comm;
	private final JAXBContext context;

	private String token;
	private ITransferRequestManager trm;
	private String ownName;
	
	public ClientPeer(String ownName, Communication comm, ITransferRequestManager trm) {
		this.ownName = ownName;
		this.trm = trm;
		this.comm = comm;
		try {
			context = JAXBContext.newInstance(Epp.class);
		} catch (JAXBException e) {
			throw new EppErrorException(EppErrorCode.XML_ERROR, e.getMessage(),
					e);
		}
	}

	@Override
	public MetaData check(String name) {

		Epp epp = MessageCreator.info(name, token, true);

		Epp response = send(epp);

		byte[] data = response.getResponse().getResData().getInfData()
				.getData();
		try {
			MetaData md = MetaData.unserialize(data);
			return md;
		} catch (Exception e) {
			throw new EppErrorException(EppErrorCode.SERIALIZATION_ERROR,
					"Metadata unserialize failed", e);
		}
	}

	@Override
	public void create(String name, byte[] data) {
		Epp request = MessageCreator.create(name, data, token);
		Epp response = send(request);
		checkResponse(response, "1000", EppErrorCode.PERMISSION_DENIED,
				"Whatever");

	}

	private void checkResponse(Epp response, String code,
			EppErrorCode errorCode, String message) {
		if (!response.getResponse().getResult().getCode().equals(code)) {
			throw new EppErrorException(errorCode, message);
		}
	}

	@Override
	public void delete(String name) {
		Epp request = MessageCreator.delete(name, this.token);
		Epp response = send(request);
		checkResponse(response, "1000", EppErrorCode.PERMISSION_DENIED,
				"Delete Failed");
	}

	@Override
	public void login(String username, String pw) {
		Epp request = MessageCreator.login(username, pw);
		Epp response = send(request);

		checkResponse(response, "1000", EppErrorCode.LOGIN_FAILED,
				"Login Failed");
		token = response.getResponse().getTrID().getSvTRID();

	}

	@Override
	public void logout() {
		Epp request = MessageCreator.logout(token);
		Epp response = send(request);
		this.token = null;
	}

	@Override
	public byte[] read(String name) {
		Epp request = MessageCreator.info(name, this.token, false);
		Epp response = send(request);
		checkResponse(response, "1000", EppErrorCode.Internal_Server_Error,
				"Read Error");
		return response.getResponse().getResData().getInfData().getData();
	}

	@Override
	public void transferCancel(String transferToken) {
		Epp request = MessageCreator.transferCancel(transferToken, this.token);
		Epp response = send(request);
		checkResponse(response, "1000", EppErrorCode.PERMISSION_DENIED,
				"Message");
	}

	@Override
	public void transferExecute(String transferToken, MetaData info, byte[] data) {
		try {
			byte[] md = MetaData.serialize(info);
			Epp request = MessageCreator.transferExecute(transferToken , md, data, token);
			Epp response = send(request);
			checkResponse(response, "1000", EppErrorCode.PERMISSION_DENIED, "Message");
		} catch (IOException e) {
			log.warn("TransferExecute failed", e);
			throw new EppErrorException(EppErrorCode.SERIALIZATION_ERROR);
		}
	}

	@Override
	public void transferRequest(String name, String transferToken) {
		trm.putOutgoing(name, transferToken);
		Epp request = MessageCreator.transferRequest(name, ownName, transferToken, token);
		Epp response = send(request);
		checkResponse(response, "1000",  EppErrorCode.PERMISSION_DENIED, "Not possible");
	}

	@Override
	public void update(String name, byte[] data) {
		Epp request = MessageCreator.update(name, data, this.token);
		Epp response = send(request);
		checkResponse(response, "1000", EppErrorCode.PERMISSION_DENIED,
				"Not possible");
	}

	private Epp send(Epp epp) {
		byte[] request = MessageCreator.marshall(context, epp);
		byte[] reponse = comm.invoke(request);
		return MessageCreator.unmarshall(context, reponse);
	}
}
