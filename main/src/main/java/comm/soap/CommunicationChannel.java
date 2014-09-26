package comm.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import comm.Invoker;

@WebService
@SOAPBinding(parameterStyle=ParameterStyle.BARE)
public class CommunicationChannel {

	private Invoker invoker;
	
	public CommunicationChannel() {
		// TODO Auto-generated constructor stub
	}
	
	public CommunicationChannel(Invoker invoker) {
		this.invoker = invoker;
	}

	@WebMethod
	public byte[] invoke(byte[] request) {
		return invoker.handleRequest(request);
	}
}
